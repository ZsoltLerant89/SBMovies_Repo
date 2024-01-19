package pti.sb_movies_mvc.dto;

public class RatingDTO {

	private int ratingNumber;
	private String ratingText;
	private String title;
	private int publishYear;
	private String genre;
	
		
	public RatingDTO(int ratingNumber, String ratingText) 
	{
		this.ratingNumber = ratingNumber;
		this.ratingText = ratingText;
	}
	
	public RatingDTO(int ratingNumber, String ratingText, String title, int publishYear, String genre) {
		super();
		this.ratingNumber = ratingNumber;
		this.ratingText = ratingText;
		this.title = title;
		this.publishYear = publishYear;
		this.genre = genre;
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
		return "RatingDTO [ratingNumber=" + ratingNumber + ", ratingText=" + ratingText + ", title=" + title
				+ ", publishYear=" + publishYear + ", genre=" + genre + "]";
	}

	
	
	
	
	
	
	
	
	
}
