package pti.sb_movies_mvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ratings")
public class Rating {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "movieid")
	private int movieId;
	
	@Column(name = "ratingnumber")
	private int ratingNumber;
	
	@Column(name = "ratingtext")
	private String ratingText;

	
	public Rating() {
		super();
	}

	
	public Rating(int movieId, int ratingNumber2, String ratingText2) {
		this.id = 0;
		this.movieId = movieId;
		this.ratingNumber = ratingNumber2;
		this.ratingText = ratingText2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getRatingNumber() {
		return ratingNumber;
	}

	public void setRatingNumber(int ratingNumber) {
		this.ratingNumber = ratingNumber;
	}

	public String getRatingText() {
		return ratingText;
	}

	public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", movieId=" + movieId + ", ratingNumber=" + ratingNumber + ", ratingText="
				+ ratingText + "]";
	}
	
	
	

}
