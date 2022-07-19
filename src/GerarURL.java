public class GerarURL {
    private static String url = "https://api.mocki.io/v2/549a5d8b/";
    private static String topMovies = "Top250Movies/";
    private static String topSeries = "Top250TVs/";

    /**
     * @param opcaoDesejada
     * @return
     */

    public static String setUrl(Object opcaoDesejada){
        if(opcaoDesejada == "Top 250 Movies"){
            url += topMovies;
            return url;
        }else if(opcaoDesejada == "Top 250 Séries"){
            url += topSeries;
            return url;
        }
        return null;
    }
}
