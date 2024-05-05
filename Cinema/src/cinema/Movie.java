package cinema;

public class Movie {

    private String title;
    private String genre;
    private String duration;
    private String publishedDate;

    public Movie(String title, String genre, String duration, String publishedDate) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.publishedDate = publishedDate;
    }

    public void setTitle (String title) {
    	this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    public void setGenre (String genre) {
    	this.genre = genre;
    }
    
    public String getGenre() {
    	return genre;
    }
    
    public void setDuration (String duration) {
    	this.duration = duration;
    }
    
    public String getDuration() {
    	return duration;
    }
    
    public void setPublishedDate (String publishedDate) {
    	this.publishedDate = publishedDate;
    }
    
    public String getPublishedDate() {
    	return publishedDate;
    }
}
