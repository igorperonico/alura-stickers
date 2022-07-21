public class GerarURL {
    private static String url = "https://api.mocki.io/v2/549a5d8b/";
    private static String topMovies = "Top250Movies/";
    private static String topSeries = "Top250TVs/";
    private static String apiNasa = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
    

    /**
     * @param opcaoDesejada
     * @return
     */

    public static String setUrl(Object opcaoDesejada){
        if(opcaoDesejada == "Top 250 Movies"){
            url += topMovies;
            return url;
        }
        if(opcaoDesejada == "Top 250 Séries"){
            url += topSeries;
            return url;
        }
        if(opcaoDesejada == "Imagens da Nasa") {
            url = apiNasa;
            return url;
        }
        return null;
    }
}
