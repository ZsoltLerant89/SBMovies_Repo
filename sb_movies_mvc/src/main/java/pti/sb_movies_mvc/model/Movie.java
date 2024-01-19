 package pti.sb_movies_mvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "publishyear")
	private int publishYear;
	
	@Column(name = "genre")
	private String genre;
	
	
	public Movie() {
		
	}
	
	
	public Movie(String title, int publishYear) {
		super();
		this.id = 0;
		this.title = title;
		this.publishYear = publishYear;
		
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
	
	public int getPublishYear() {
		return publishYear;
	}
	
	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}
	
	

	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", publishYear=" + publishYear + ", genre=" + genre + "]";
	}


	
	
	
	
}
