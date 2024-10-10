import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import ru.ecom.animequotes.repository.HeroRepository
import ru.ecom.animequotes.service.HeroService

@ExtendWith(MockitoExtension::class)
class HeroServiceTest {

    @Mock
    lateinit var heroRepository: HeroRepository

    private lateinit var heroService: HeroService

    @BeforeEach
    fun setUp() {
        heroService = HeroService(heroRepository)
    }

    @Test
    fun `should return true when updating hero name successfully`() {
        val animeName = "MyAnime"
        val heroName = "OldHero"
        val newName = "NewHero"
        given(heroRepository.updateHeroNameByAnimeNameAndName(animeName, heroName, newName)).willReturn(1)

        val result = heroService.updateHeroName(animeName, heroName, newName)

        assertTrue(result)
        verify(heroRepository).updateHeroNameByAnimeNameAndName(animeName, heroName, newName)
    }

    @Test
    fun `should return false when updating hero name fails`() {
        val animeName = "MyAnime"
        val heroName = "NonExistentHero"
        val newName = "NewHero"
        given(heroRepository.updateHeroNameByAnimeNameAndName(animeName, heroName, newName)).willReturn(0)

        val result = heroService.updateHeroName(animeName, heroName, newName)

        assertFalse(result)
        verify(heroRepository).updateHeroNameByAnimeNameAndName(animeName, heroName, newName)
    }

    @Test
    fun `should delete hero by anime name and hero name`() {
        val animeName = "MyAnime"
        val heroName = "MyHero"
        given(heroRepository.removeHeroByAnimeNameAndName(animeName, heroName)).willReturn(1)

        val result = heroService.deleteHero(animeName, heroName)

        assertEquals(1, result)
        verify(heroRepository).removeHeroByAnimeNameAndName(animeName, heroName)
    }
}
