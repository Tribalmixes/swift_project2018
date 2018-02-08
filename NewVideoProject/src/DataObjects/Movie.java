package DataObjects;

public class Movie {

    private String movieId;
    private String title;
    private String year;
    private String movieLength;

    public Movie(){

    }

    public Movie(String title){
        this.title = title;
    }

    public Movie(String title, String year, String movieLength){
        this.title = title;
        this.year = year;
        this.movieLength = movieLength;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(String movieLength) {
        this.movieLength = movieLength;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
