import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {


//create HTTP connection and search the top 250 movies
        String url = "https://imdb-api.com/en/API/Top250Movies/k_zmuid1iv";

        URI address=URI.create(url);

        var  client = HttpClient.newHttpClient(); 

        HttpRequest request = HttpRequest.newBuilder(address).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body=response.body();

        //parsing String to List Map
        var parser=new JsonParser();
        List<Map<String,String>> listaDeFilmes=parser.parse(body);


        //show data info for each movie
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imdbRating"));
            System.out.println();
        }
    }
}
