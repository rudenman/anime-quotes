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
import ru.ecom.animequotes.repository.AnimeRepository
import ru.ecom.animequotes.service.AnimeService

@ExtendWith(MockitoExtension::class)
class AnimeServiceTest {

    @Mock
    lateinit var animeRepository: AnimeRepository

    private lateinit var animeService: AnimeService

    @BeforeEach
    fun setUp() {
        animeService = AnimeService(animeRepository)
    }

    @Test
    fun `should delete anime by name`() {
        val animeName = "MyAnime"
        given(animeRepository.removeAnimeByName(animeName)).willReturn(1)

        val result = animeService.deleteAnime(animeName)

        assertEquals(1, result)
        verify(animeRepository).removeAnimeByName(animeName)
    }

    @Test
    fun `should return true when updating anime name successfully`() {
        val animeName = "OldAnime"
        val newName = "NewAnime"
        given(animeRepository.updateAnimeNameByName(animeName, newName)).willReturn(1)

        val result = animeService.updateAnimeName(animeName, newName)

        assertTrue(result)
        verify(animeRepository).updateAnimeNameByName(animeName, newName)
    }

    @Test
    fun `should return false when updating anime name fails`() {
        val animeName = "NonExistentAnime"
        val newName = "NewAnime"
        given(animeRepository.updateAnimeNameByName(animeName, newName)).willReturn(0)

        val result = animeService.updateAnimeName(animeName, newName)

        assertFalse(result)
        verify(animeRepository).updateAnimeNameByName(animeName, newName) // Проверяем, что метод был вызван
    }
}
