import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // create HTTP connection and search the top 250 movies
        // String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        String url = "http://localhost:8080/linguagens";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        // poderia delegar um extrator LinguagemAPI para esse do IMDB
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        // show data info for each movie
        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            // windows não aceita título com :
            String nomeArquivo = conteudo.getTitulo().replace(":", "-") + ".png";

            try {
                geradora.cria(inputStream, nomeArquivo);
            } catch (javax.imageio.IIOException err) {
                System.out.println("Formato inválido de imagem");
            }

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

    }
}
