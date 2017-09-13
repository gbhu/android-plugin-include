package com.yummy.modal;

import java.util.List;

/**
 * Created by Administrator on 2016-6-25.
 */
public class MovieModal {

    private String id;
    private String title;
    private String year;
    private String mpaa_rating;
    private String runtime;
    private ReleaseDates release_dates;
    private Ratings ratings;
    private String synopsis;
    private Posters posters;
    private List<Abridgedcast> abridged_cast;
    private AlternateIds alternate_ids;
    private Links links ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMpaa_rating() {
        return mpaa_rating;
    }

    public void setMpaa_rating(String mpaa_rating) {
        this.mpaa_rating = mpaa_rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public ReleaseDates getRelease_dates() {
        return release_dates;
    }

    public void setRelease_dates(ReleaseDates release_dates) {
        this.release_dates = release_dates;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Posters getPosters() {
        return posters;
    }

    public void setPosters(Posters posters) {
        this.posters = posters;
    }

    public List<Abridgedcast> getAbridged_cast() {
        return abridged_cast;
    }

    public void setAbridged_cast(List<Abridgedcast> abridged_cast) {
        this.abridged_cast = abridged_cast;
    }

    public AlternateIds getAlternate_ids() {
        return alternate_ids;
    }

    public void setAlternate_ids(AlternateIds alternate_ids) {
        this.alternate_ids = alternate_ids;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
