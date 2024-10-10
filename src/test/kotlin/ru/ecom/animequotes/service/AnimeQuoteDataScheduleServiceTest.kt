import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import ru.ecom.animequotes.dto.schedule.QuoteDataResponse
import ru.ecom.animequotes.service.AnimeQuoteDataScheduleService
import ru.ecom.animequotes.service.QuoteService

@ExtendWith(MockitoExtension::class)
class AnimeQuoteDataScheduleServiceTest {

    @Mock
    lateinit var quoteService: QuoteService

    @Mock
    lateinit var webClientBuilder: WebClient.Builder

    @Mock
    lateinit var webClient: WebClient

    @Mock
    lateinit var webClientRequest: WebClient.RequestHeadersUriSpec<*>

    @Mock
    lateinit var webClientResponse: WebClient.ResponseSpec

    @Test
    fun `should fetch and save quotes`() {
        given(webClientBuilder.baseUrl("https://animechan.io/api/v1")).willReturn(webClientBuilder)
        given(webClientBuilder.build()).willReturn(webClient)
        given(webClient.get()).willReturn(webClientRequest)
        given(webClientRequest.uri("/quotes/random")).willReturn(webClientRequest)
        given(webClientRequest.retrieve()).willReturn(webClientResponse)

        val quoteDataResponse = QuoteDataResponse(
            status = "success",
            data = QuoteDataResponse.QuoteData(
                content = "This is a quote",
                anime = QuoteDataResponse.QuoteData.Anime(id = 1, name = "MyAnime"),
                character = QuoteDataResponse.QuoteData.Character(id = 1, name = "Hero")
            )
        )

        given(webClientResponse.bodyToMono(QuoteDataResponse::class.java)).willReturn(Mono.just(quoteDataResponse))

        val service = AnimeQuoteDataScheduleService(quoteService, webClientBuilder)

        service.fetchQuotes()

        verify(quoteService, times(5)).saveQuoteIfNotExists("MyAnime", "Hero", "This is a quote")
    }
}
