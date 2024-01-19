package pti.sb_movies_mvc.dto;

public class MovieDTO {
	
	private int id;
	private String title;
	private int publishYear;
	private String genre;
	
	
	public MovieDTO(int id, String title, int publishYear, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.publishYear = publishYear;
		this.genre = genre;
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
		return "MovieDTO [id=" + id + ", title=" + title + ", publishYear=" + publishYear + ", genre=" + genre + "]";
	}

	

	

	
	
	
	
	
}
