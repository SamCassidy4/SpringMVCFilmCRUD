package com.skilldistillery.film.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String description;
	private Integer releaseYear;
	private Integer languageId;
	private Integer rentalDuration;
	private Double rentalRate;
	private Integer length;
	private Double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;
	private String language;
	
	public Film() {
		
	}

	public Film(int id, String title, String description, Integer releaseYear, Integer languageId, Integer rentalDuration, Double rentalRate,
			Integer length, Double replacementCost, String rating, String features, List<Actor> actors, String language) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = features;
		this.actors = actors;
		this.language = language;
	}
		
		

	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public Double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(Double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(Double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actors, description, specialFeatures, id, languageId, language, length, rentalRate, rating, releaseYear,
				rentalDuration, replacementCost, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(description, other.description)
				&& Objects.equals(specialFeatures, other.specialFeatures) && id == other.id && languageId == other.languageId
				&& Objects.equals(language, other.language) && length == other.length
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear && rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Film id:" + id + " Title: " + title + " \n Description: " + description + " \n Release Year:" + releaseYear
				+ " Language Id: " + languageId + " Rent Duration: " + rentalDuration + " \n Rate: " + rentalRate + " \n Length: " + length + "\n Repair Cost: "
				+ replacementCost + "\n Rating: " + rating + " \n Features: " + specialFeatures + "\n Actors: " + actors + " \n Language: "
				+ language;
	}
	
}
