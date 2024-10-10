import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import ru.ecom.animequotes.entity.Anime
import ru.ecom.animequotes.entity.Hero
import ru.ecom.animequotes.entity.Quote
import ru.ecom.animequotes.repository.AnimeRepository
import ru.ecom.animequotes.repository.HeroRepository
import ru.ecom.animequotes.repository.QuoteRepository
import ru.ecom.animequotes.service.QuoteService

@ExtendWith(MockitoExtension::class)
class QuoteServiceTest {

    @Mock
    lateinit var animeRepository: AnimeRepository

    @Mock
    lateinit var heroRepository: HeroRepository

    @Mock
    lateinit var quoteRepository: QuoteRepository

    private lateinit var quoteService: QuoteService

    @BeforeEach
    fun setUp() {
        quoteService = QuoteService(animeRepository, heroRepository, quoteRepository)
    }

    @Test
    fun `should return existing quote if already exists`() {
        val animeName = "MyAnime"
        val heroName = "MyHero"
        val quoteContent = "This is a quote"
        val anime = Anime(name = animeName).apply { id = 1 }
        val hero = Hero(name = heroName, anime = anime).apply { id = 1 }
        val existingQuote = Quote(content = quoteContent, hero = hero)

        given(animeRepository.findAnimeByName(animeName)).willReturn(anime)
        given(heroRepository.findHeroByNameAndAnimeId(heroName, anime.id!!)).willReturn(hero)
        given(quoteRepository.findQuoteByContentAndHeroId(quoteContent, hero.id!!)).willReturn(existingQuote)

        val savedQuote = quoteService.saveQuoteIfNotExists(animeName, heroName, quoteContent)

        assertSame(existingQuote, savedQuote)
        verify(animeRepository, times(0)).save(anime)
        verify(heroRepository, times(0)).save(hero)
        verify(quoteRepository, times(0)).save(existingQuote)
    }


    @Test
    fun `should get quotes by anime name`() {
        val animeName = "MyAnime"
        val quotes = listOf(
            Quote(content = "Quote 1", hero = Hero(name = "Hero1")),
            Quote(content = "Quote 2", hero = Hero(name = "Hero2"))
        )
        given(quoteRepository.findQuotesByAnimeName(animeName)).willReturn(quotes)

        val result = quoteService.getQuotesByAnime(animeName)

        assertEquals(quotes, result)
        verify(quoteRepository).findQuotesByAnimeName(animeName)
    }

    @Test
    fun `should get quotes by anime name and hero name`() {
        val animeName = "MyAnime"
        val heroName = "MyHero"
        val quotes = listOf(Quote(content = "Quote 1", hero = Hero(name = heroName)))
        given(quoteRepository.findQuotesByAnimeNameAndHeroName(animeName, heroName)).willReturn(quotes)

        val result = quoteService.getQuotesByAnimeNameAndHeroName(animeName, heroName)

        assertEquals(quotes, result)
        verify(quoteRepository).findQuotesByAnimeNameAndHeroName(animeName, heroName)
    }

    @Test
    fun `should update quote content successfully`() {
        val animeName = "MyAnime"
        val heroName = "MyHero"
        val quoteContent = "Old Quote"
        val newContent = "Updated Quote"

        given(
            quoteRepository.updateQuoteContentByAnimeNameAndHeroNameAndContent(
                animeName,
                heroName,
                quoteContent,
                newContent
            )
        ).willReturn(1)

        val result = quoteService.updateQuoteContent(animeName, heroName, quoteContent, newContent)

        assertTrue(result)
        verify(quoteRepository).updateQuoteContentByAnimeNameAndHeroNameAndContent(
            animeName,
            heroName,
            quoteContent,
            newContent
        )
    }

    @Test
    fun `should return false when update quote content fails`() {
        val animeName = "MyAnime"
        val heroName = "MyHero"
        val quoteContent = "Old Quote"
        val newContent = "Updated Quote"

        given(
            quoteRepository.updateQuoteContentByAnimeNameAndHeroNameAndContent(
                animeName,
                heroName,
                quoteContent,
                newContent
            )
        ).willReturn(0)

        val result = quoteService.updateQuoteContent(animeName, heroName, quoteContent, newContent)

        assertFalse(result)
        verify(quoteRepository).updateQuoteContentByAnimeNameAndHeroNameAndContent(
            animeName,
            heroName,
            quoteContent,
            newContent
        )
    }

    @Test
    fun `should delete quote content successfully`() {
        val animeName = "MyAnime"
        val heroName = "MyHero"
        val quoteContent = "This is a quote"

        given(
            quoteRepository.removeQuoteByAnimeNameAndHeroNameAndContent(
                animeName,
                heroName,
                quoteContent
            )
        ).willReturn(1)

        val result = quoteService.deleteQuoteContent(animeName, heroName, quoteContent)

        assertEquals(1, result)
        verify(quoteRepository).removeQuoteByAnimeNameAndHeroNameAndContent(animeName, heroName, quoteContent)
    }
}
