package classes;

import java.io.Serializable;

public class MovieInfo implements Serializable {
    public String id;
    private String name;
    private int releaseYear;
    private float rating;

    // constructor
    public MovieInfo() {

    }
    public MovieInfo(String id, String name, int releaseYear, float rating) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public MovieInfo(int releaseYear, String name, float rating) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }


    // getter
    public String getName() {
        return this.name;
    }
    public int getReleaseYear() {
        return this.releaseYear;
    }
    public float getRating() {
        return this.rating;
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setId(String id) {
        this.id = id;
    }
}
