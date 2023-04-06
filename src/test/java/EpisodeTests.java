import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class EpisodeTests {

    ObjectMapper mapper = new ObjectMapper();

    private HttpResponse<String> getHttpResponse(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void shouldReturnStatusCode200() {
        HttpResponse<String> response = getHttpResponse("https://rickandmortyapi.com/api/episode/10");
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void shouldReturnStatusCode404() {
        HttpResponse<String> response = getHttpResponse("https://rickandmortyapi.com/api/episode/7564");
        Assertions.assertEquals(404, response.statusCode());
    }

    @Test
     public void shouldReturnEpisodeInfo() throws JsonProcessingException {
        HttpResponse<String> response = getHttpResponse("https://rickandmortyapi.com/api/episode/1");

        Episode actual = mapper.readValue(response.body(), Episode.class);
        System.out.println(actual);

        Episode expected = Episode.builder()
                .id(1L)
                .name("Pilot")
                .air_date("December 2, 2013")
                .episode("S01E01")
                .characters(List.of("https://rickandmortyapi.com/api/character/1",
                        "https://rickandmortyapi.com/api/character/2",
                        "https://rickandmortyapi.com/api/character/35",
                        "https://rickandmortyapi.com/api/character/38",
                        "https://rickandmortyapi.com/api/character/62",
                        "https://rickandmortyapi.com/api/character/92",
                        "https://rickandmortyapi.com/api/character/127",
                        "https://rickandmortyapi.com/api/character/144",
                        "https://rickandmortyapi.com/api/character/158",
                        "https://rickandmortyapi.com/api/character/175",
                        "https://rickandmortyapi.com/api/character/179",
                        "https://rickandmortyapi.com/api/character/181",
                        "https://rickandmortyapi.com/api/character/239",
                        "https://rickandmortyapi.com/api/character/249",
                        "https://rickandmortyapi.com/api/character/271",
                        "https://rickandmortyapi.com/api/character/338",
                        "https://rickandmortyapi.com/api/character/394",
                        "https://rickandmortyapi.com/api/character/395",
                        "https://rickandmortyapi.com/api/character/435"
                ))
                .build();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCharacterAmountInEpisode() throws JsonProcessingException {
        HttpResponse<String> response =
                getHttpResponse("https://rickandmortyapi.com/api/episode/1");

        Episode actual = mapper.readValue(response.body(), Episode.class);
        System.out.println(actual);

        Assertions.assertEquals(19, actual.getCharacters().size());
    }

    @Test
    public void TestIfEpisodeContainsASpecificCharacterByUrl() throws JsonProcessingException {
        HttpResponse<String> response =
                getHttpResponse("https://rickandmortyapi.com/api/episode/1");

        Episode actual = mapper.readValue(response.body(), Episode.class);

        Assertions.assertTrue(actual.getCharacters()
                .contains("https://rickandmortyapi.com/api/character/1"));
    }

    @Test
    public void TestIfEpisodeContainsASpecificCharacterByObject() throws JsonProcessingException {
        HttpResponse<String> response =
                getHttpResponse("https://rickandmortyapi.com/api/episode/1");

        Episode actualEpisode = mapper.readValue(response.body(), Episode.class);


        Character actual = mapper.readValue(
                getHttpResponse((actualEpisode.getCharacters().get(0))).body(), Character.class);
        Character expected = Character.builder()
                .id(1)
                .name("Rick Sanchez")
                .gender("Male")
                .type("")
                .species("Human")
                .status("Alive")
                .build();
        Assertions.assertEquals(expected, actual);
    }
}
