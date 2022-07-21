import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // // Usuário escolhe se quer ver a lista Top 250 filmes ou Top 250 séries ou Imagens da Nasa

        // Object[] menu = { "Top 250 Movies", "Top 250 Séries", "Imagens da Nasa" };
        // Object selectedMenu = JOptionPane.showInputDialog(null, "Escolha um item", "Menu",
		// 		JOptionPane.INFORMATION_MESSAGE, null, menu, menu[0]);

        // Object escolhaDoUsuario = selectedMenu;

        // String url = GerarURL.setUrl(escolhaDoUsuario);

        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        // String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();

        // fazer uma conexão HTTP (protocolo de comunicação na web)
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados

        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo().replace(":", " -") + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

    }
}
