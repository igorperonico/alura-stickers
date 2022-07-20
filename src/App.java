import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        // Usuário escolhe se quer ver a lista Top 250 filmes ou Top 250 séries

        Object[] menu = { "Top 250 Movies", "Top 250 Séries" };
        Object selectedMenu = JOptionPane.showInputDialog(null, "Escolha um item", "Menu",
				JOptionPane.INFORMATION_MESSAGE, null, menu, menu[0]);

        Object escolhaDoUsuario = selectedMenu;

        // fazer uma conexão HTTP (protocolo de comunicação na web) e buscar os top 250 filmes

        // String url = "https://api.mocki.io/v2/549a5d8b";

        String url = GerarURL.setUrl(escolhaDoUsuario);
        
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);

        // pegar somente os dados que nos interessam (titulo, poster, classificação)

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listDeFilmes = parser.parse(body);
        // System.out.println(listDeFilmes.size());
        // System.out.println(listDeFilmes.get(0));

        // exibir e manipular os dados
        // e agora vai criar uma figurinha para cada poster de filme na API

        var geradora = new GeradoraDeFigurinhas();

        for (Map<String,String> filme : listDeFilmes) {
        System.out.println("\u001b[1mTítulo:\u001b[m " + filme.get("title"));

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo.replace(":", " -") + ".png";
            String imDbRating = filme.get("imDbRating");

            geradora.cria(inputStream, nomeArquivo, imDbRating);

            System.out.println("\u001b[1mPoster:\u001b[m " + filme.get("image"));
            System.out.println("\u001b[45;1m \u001b[37;1mClassificação: " + filme.get("imDbRating") + " \u001b[m");
            // System.out.println("⭐⭐⭐⭐\u2B50\n");

            String starsRating = "";
            int iStars;
            for(iStars = 0; iStars < Double.valueOf(filme.get("imDbRating")).intValue(); iStars++) {
                starsRating += "\u2B50";
            }

            if(Integer.valueOf(filme.get("imDbRating").substring(filme.get("imDbRating").indexOf(".") + 1, filme.get("imDbRating").length())) > 0) {
                starsRating += "\u272E";
                iStars++;
            }

            for (; iStars < 10; iStars++) {
                starsRating += " \u2730";
            }

            System.out.println(starsRating + "\n");

        }

    }
}
