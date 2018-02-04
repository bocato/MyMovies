package br.com.zup.mymovies.model;

import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.zup.mymovies.BR;

/**
 * Created by rafaelneiva on 04/02/18.
 */

public class OmdbVideoFull extends OmdbVideoBasic {
    
    @SerializedName("Rated")
    private String rated;
    
    @SerializedName("Released")
    private String released;
    
    @SerializedName("Runtime")
    private String runtime;
    
    @SerializedName("Genre")
    private String genre;
    
    @SerializedName("Director")
    private String director;
    
    @SerializedName("Writer")
    private String writer;
    
    @SerializedName("Actors")
    private String actors;
    
    @SerializedName("Plot")
    private String plot;
    
    @SerializedName("imdbRating")
    private String imdbRating;
    
    @SerializedName("imdbVotes")
    private String imdbVotes;

    // Rotten Tomatoes fields
    @SerializedName("tomatoMeter")
    private String tomatoMeter;
    
    @SerializedName("tomatoImage")
    private String tomatoImage;
    
    @SerializedName("tomatoRating")
    private String tomatoRating;
    
    @SerializedName("tomatoReviews")
    private String tomatoReviews;
    
    @SerializedName("tomatoFresh")
    private String tomatoFresh;
    
    @SerializedName("tomatoRotten")
    private String tomatoRotten;
    
    @SerializedName("tomatoConsensus")
    private String tomatoConsensus;
    
    @SerializedName("tomatoUserMeter")
    private String tomatoUserMeter;
    
    @SerializedName("tomatoUserRating")
    private String tomatoUserRating;
    
    @SerializedName("tomatoUserReviews")
    private String tomatoUserReviews;
    
    @SerializedName("tomatoURL")
    private String tomatoURL;
    
    @SerializedName("DVD")
    private String tomatoDvd;
    
    @SerializedName("BoxOffice")
    private String tomatoBoxOffice;
    
    @SerializedName("Production")
    private String tomatoProduction;
    
    @SerializedName("Website")
    private String tomatoWebsite;

    @SerializedName("languages")
    private List<String> languages;

    @SerializedName("countries")
    private List<String> countries;
    
    @SerializedName("Awards")
    private String awards;
    
    @SerializedName("Metascore")
    private int metascore;
    
    @SerializedName("Season")
    private Integer season;
    
    @SerializedName("Episode")
    private Integer episode;

    @Bindable
    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
        notifyPropertyChanged(BR.rated);
    }

    @Bindable
    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
        notifyPropertyChanged(BR.released);
    }

    @Bindable
    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
        notifyPropertyChanged(BR.runtime);
    }

    @Bindable
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }

    @Bindable
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
        notifyPropertyChanged(BR.director);
    }

    @Bindable
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
        notifyPropertyChanged(BR.writer);
    }

    @Bindable
    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
        notifyPropertyChanged(BR.actors);
    }

    @Bindable
    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
        notifyPropertyChanged(BR.plot);
    }

    @Bindable
    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
        notifyPropertyChanged(BR.imdbRating);
    }

    @Bindable
    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
        notifyPropertyChanged(BR.imdbVotes);
    }

    @Bindable
    public String getTomatoMeter() {
        return tomatoMeter;
    }

    public void setTomatoMeter(String tomatoMeter) {
        this.tomatoMeter = tomatoMeter;
        notifyPropertyChanged(BR.tomatoMeter);
    }

    @Bindable
    public String getTomatoImage() {
        return tomatoImage;
    }

    public void setTomatoImage(String tomatoImage) {
        this.tomatoImage = tomatoImage;
        notifyPropertyChanged(BR.tomatoImage);
    }

    @Bindable
    public String getTomatoRating() {
        return tomatoRating;
    }

    public void setTomatoRating(String tomatoRating) {
        this.tomatoRating = tomatoRating;
        notifyPropertyChanged(BR.tomatoRating);
    }

    @Bindable
    public String getTomatoReviews() {
        return tomatoReviews;
    }

    public void setTomatoReviews(String tomatoReviews) {
        this.tomatoReviews = tomatoReviews;
        notifyPropertyChanged(BR.tomatoReviews);
    }

    @Bindable
    public String getTomatoFresh() {
        return tomatoFresh;
    }

    public void setTomatoFresh(String tomatoFresh) {
        this.tomatoFresh = tomatoFresh;
        notifyPropertyChanged(BR.tomatoFresh);
    }

    @Bindable
    public String getTomatoRotten() {
        return tomatoRotten;
    }

    public void setTomatoRotten(String tomatoRotten) {
        this.tomatoRotten = tomatoRotten;
        notifyPropertyChanged(BR.tomatoRotten);
    }

    @Bindable
    public String getTomatoConsensus() {
        return tomatoConsensus;
    }

    public void setTomatoConsensus(String tomatoConsensus) {
        this.tomatoConsensus = tomatoConsensus;
        notifyPropertyChanged(BR.tomatoConsensus);
    }

    @Bindable
    public String getTomatoUserMeter() {
        return tomatoUserMeter;
    }

    public void setTomatoUserMeter(String tomatoUserMeter) {
        this.tomatoUserMeter = tomatoUserMeter;
        notifyPropertyChanged(BR.tomatoUserMeter);
    }

    @Bindable
    public String getTomatoUserRating() {
        return tomatoUserRating;
    }

    public void setTomatoUserRating(String tomatoUserRating) {
        this.tomatoUserRating = tomatoUserRating;
        notifyPropertyChanged(BR.tomatoUserRating);
    }

    @Bindable
    public String getTomatoUserReviews() {
        return tomatoUserReviews;
    }

    public void setTomatoUserReviews(String tomatoUserReviews) {
        this.tomatoUserReviews = tomatoUserReviews;
        notifyPropertyChanged(BR.tomatoUserReviews);
    }

    @Bindable
    public String getTomatoURL() {
        return tomatoURL;
    }

    public void setTomatoURL(String tomatoURL) {
        this.tomatoURL = tomatoURL;
        notifyPropertyChanged(BR.tomatoURL);
    }

    @Bindable
    public String getTomatoDvd() {
        return tomatoDvd;
    }

    public void setTomatoDvd(String tomatoDvd) {
        this.tomatoDvd = tomatoDvd;
        notifyPropertyChanged(BR.tomatoDvd);
    }

    @Bindable
    public String getTomatoBoxOffice() {
        return tomatoBoxOffice;
    }

    public void setTomatoBoxOffice(String tomatoBoxOffice) {
        this.tomatoBoxOffice = tomatoBoxOffice;
        notifyPropertyChanged(BR.tomatoBoxOffice);
    }

    @Bindable
    public String getTomatoProduction() {
        return tomatoProduction;
    }

    public void setTomatoProduction(String tomatoProduction) {
        this.tomatoProduction = tomatoProduction;
        notifyPropertyChanged(BR.tomatoProduction);
    }

    @Bindable
    public String getTomatoWebsite() {
        return tomatoWebsite;
    }

    public void setTomatoWebsite(String tomatoWebsite) {
        this.tomatoWebsite = tomatoWebsite;
        notifyPropertyChanged(BR.tomatoWebsite);
    }

    @Bindable
    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
        notifyPropertyChanged(BR.languages);
    }

    @Bindable
    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
        notifyPropertyChanged(BR.countries);
    }

    @Bindable
    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
        notifyPropertyChanged(BR.awards);
    }

    @Bindable
    public int getMetascore() {
        return metascore;
    }

    public void setMetascore(int metascore) {
        this.metascore = metascore;
        notifyPropertyChanged(BR.metascore);
    }

    @Bindable
    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
        notifyPropertyChanged(BR.season);
    }

    @Bindable
    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
        notifyPropertyChanged(BR.episode);
    }
}
