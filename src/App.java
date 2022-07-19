import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // create HTTP connection and search the top 250 movies
        String urlIMDB = "https://imdb-api.com/en/API/Top250Movies/k_44gfwthm";

        var http = new ClienteHttp();
        String json = http.buscaDados(urlIMDB);

        // exibir e manipular os dados
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        // show data info for each movie
        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < conteudos.size() - 1; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            // windows não aceita título com :
            // String nomeArquivo = conteudo.getTitulo().replace(":", "-") + ".png";

            String nomeArquivo = conteudo.getTitulo().replace(":", "-") + ".png";

            try {
                geradora.cria(inputStream, nomeArquivo);
            } catch (javax.imageio.IIOException err) {
                System.out.println("Formato inválido de imagem");
            }

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

        /*
         * 1º desafio: Consumir o endpoint de filmes mais populares da API do IMDB.
         * Procure também, na documentação da API do IMDB, o endpoint que retorna as
         * melhores séries e o que retorna as séries mais populares.
         */

        // url = "https://imdb-api.com/en/API/MostPopularMovies/k_zmuid1iv";

        // address = URI.create(url);

        // client = HttpClient.newHttpClient();

        // request = HttpRequest.newBuilder(address).GET().build();

        // response = client.send(request, BodyHandlers.ofString());
        // body = response.body();

        // parser = new JsonParser();
        // List<Map<String, String>> listaDosFilmesMaisPopulares = parser.parse(body);

        // for (Map<String, String> conteudo : listaDosFilmesMaisPopulares) {
        // System.out.println(conteudo.get("title"));
        // System.out.println(conteudo.get("image"));
        // System.out.println(conteudo.get("imDbRating"));
        // System.out.println();
        // }

        /*
         * 2º desafio: Usar sua criatividade para deixar a saída dos dados mais
         * bonitinha: usar
         * emojis com código UTF-8, mostrar a nota do conteudo como estrelinhas, decorar
         * o
         * terminal com cores, negrito e itálico usando códigos ANSI, e mais!
         */
        // for (int i = 0; i < 10; i++) {
        // Map<String, String> conteudo = listaDeFilmes.get(i);
        // String titulo = conteudo.get("title");
        // String urlImagem = conteudo.get("image");
        // String classificacao = conteudo.get("imDbRating");

        // System.out.println("Título: \033[0;1m" + titulo + "\033[0;0m");
        // System.out.println("Poster: \033[0;1m" + urlImagem + "\033[0;0m");
        // System.out.println("\u001B[37m\u001B[45mClassificação:" + classificacao +
        // "\u001B[0m");

        // int numeroEstrelinhas = Math.round(Float.parseFloat(classificacao));

        // String estrelinhas = "";
        // for (int j = 1; j <= numeroEstrelinhas; j++) {
        // estrelinhas += "";
        // }
        // System.out.println(estrelinhas);
        // }

        /*
         * 3º desafio: Colocar a chave da API do IMDB em algum lugar fora do código como
         * um arquivo de configuração (p. ex, um arquivo .properties) ou uma variável de
         * ambiente
         */

        /*
         * 4º desafio:Mudar o JsonParser para usar uma biblioteca de parsing de JSON
         * como Jackson ou GSON
         */

        /*
         * Desafio supremo: criar alguma maneira para você dar uma avaliação ao
         * conteudo,
         * puxando de algum arquivo de configuração OU pedindo a avaliação para o
         * usuário digitar no terminal.
         */

    }
}
