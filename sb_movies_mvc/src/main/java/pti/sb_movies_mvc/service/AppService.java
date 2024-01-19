package pti.sb_movies_mvc.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_movies_mvc.db.Database;
import pti.sb_movies_mvc.dto.MovieDTO;
import pti.sb_movies_mvc.dto.OrderDTO;
import pti.sb_movies_mvc.dto.RatingDTO;
import pti.sb_movies_mvc.model.Movie;
import pti.sb_movies_mvc.model.Rating;

@Service
public class AppService {
	
	private Database db;
	
	@Autowired
	public AppService(Database db) {
		super();
		this.db = db;
	}
	
	

	public MovieDTO getMovieById(int movieId)
	{
		MovieDTO dto = null;
		
		Movie movie = db.getMovieById(movieId);
		
		if(movie != null)
		{
			dto = new MovieDTO
					(
						movie.getId(),
						movie.getTitle(),
						movie.getPublishYear(),
						movie.getGenre()			
					);
			
		}
		
		return dto;
	}
	
	public RatingDTO getRatingById(int ratingId)
	{
		RatingDTO dto = null;
		
		Rating rating = db.getRatingById(ratingId);
		
		
		if(rating != null)
		{
			int movieId = rating.getMovieId();
			Movie movie = db.getMovieById(movieId);
			
			dto = new RatingDTO
					(
							
						rating.getRatingNumber(),
						rating.getRatingText(),
						movie.getTitle(),
						movie.getPublishYear(),
						movie.getGenre()
						
					);
		
		}
		
		return dto;
	}

	public MovieDTO saveMovie(String mTitle, int mPYear) {
		
		MovieDTO dto = null;
		
		Movie movie = new Movie(mTitle, mPYear);
		
		db.persistMovieById(movie);
		
		dto = new MovieDTO(
					movie.getId(),
					movie.getTitle(),
					movie.getPublishYear(),
					movie.getGenre()
					
				);
		
		
		return dto;
	}

	public MovieDTO updateMovie(int movieId, String mTitle) {
		
		MovieDTO dto = null;
		
		Movie movie = db.getMovieById(movieId);
		
		if (movie != null)
		{
			movie.setTitle(mTitle);
			db.mergeMovieById(movie);

			dto = new MovieDTO(
						movie.getId(),
						movie.getTitle(),
						movie.getPublishYear(),
						movie.getGenre()					
					);		
		}
		
		return dto;
	}

	public RatingDTO updateRating(int ratingId, int ratingNumber, String ratingText) {
		
		RatingDTO dto = null;
		
		Rating rating = db.getRatingById(ratingId);
		
		if (rating != null)
		{
			rating.setRatingNumber(ratingNumber);
			rating.setRatingText(ratingText);
			db.mergeRatingById(rating);
			
			dto = new RatingDTO(
					rating.getRatingNumber(),
					rating.getRatingText()
					);
			
		}
		
		return dto;
	}

	public RatingDTO removeRating(int ratingId) {
		
		RatingDTO dto = null;
		
		Rating rating = db.getRatingById(ratingId);
		
		if (rating != null)
		{
			db.removeRatingById(rating);
			
			dto = new RatingDTO(
					rating.getRatingNumber(),
					rating.getRatingText()		
					);
		}
		
		return dto;
	}

	public MovieDTO updateGenre(int ratingId, String title, String genre) {
		
		MovieDTO dto = null;
		
		Rating rating = db.getRatingById(ratingId);
		
		if (rating != null)
		{	
			int movieId = rating.getMovieId();
			
			Movie movie = db.getMovieById(movieId);
			
			if(movie.getTitle().equals(title))
			{
				movie.setGenre(genre);
				db.mergeMovieById(movie);
				
				dto = new MovieDTO(
						movie.getId(),
						movie.getTitle(),
						movie.getPublishYear(),
						movie.getGenre()						
						);			
			}
			
		}
		
		return dto;
	}

	public ArrayList<MovieDTO> showMovies(int resultOrder) 
	{
		
		ArrayList<MovieDTO> dtoList =new ArrayList<>();
	
		List<Movie> movieList = db.getMovies();
		
		for (int index = 0; index < movieList.size(); index++)
		{
			Movie currentMovie= movieList.get(index);
			boolean found = false;
			MovieDTO dto = new MovieDTO(currentMovie.getId(),currentMovie.getTitle(),currentMovie.getPublishYear(),currentMovie.getGenre());
			if (resultOrder == 0)
			{
				dtoList.add(dto);
			}
			else
			{
	
				if(dtoList.size() == 0)
				{
					dtoList.add(dto);
				}
				else
				{
					
					for (int currentIndex = 0; currentIndex < dtoList.size(); currentIndex++)
					{
						if(dtoList.get(currentIndex).getTitle().compareTo(dto.getTitle()) > 0)
						{
							MovieDTO lastDto = dtoList.get(dtoList.size()-1);
							dtoList.add(lastDto);
							
							for(int copyIndex = dtoList.size()-3; copyIndex >= currentIndex; copyIndex-- )
							{
								MovieDTO copyMovie = dtoList.get(copyIndex);
								dtoList.set(copyIndex+1, copyMovie);							
							}
							dtoList.set(currentIndex,dto);
							found = true;						
							break;
							
						}
						
					}
					
					if (found == false)
					{
						dtoList.add(dto);
					}
					
				}
			}
			
		}
		
		if (resultOrder == 2)
		{
			
			Collections.reverse(dtoList);
		}	
		
		return dtoList;
	}
	
	public ArrayList<RatingDTO> getRatingByMovieId(int mId) {
		
		ArrayList<RatingDTO> dtoList = new ArrayList<>();
		
		List<Rating> ratingList = db.getRatingByMovieId(mId);
		
		if (ratingList != null)
		{
			Movie movie = db.getMovieById(mId);
			for (int index = 0; index < ratingList.size(); index++)
			{
				Rating currentRating = ratingList.get(index);
				RatingDTO dto = new RatingDTO(currentRating.getRatingNumber(),currentRating.getRatingText(),movie.getTitle(),movie.getPublishYear(),movie.getGenre());
				dtoList.add(dto);
				
			}

		}

		
		if (dtoList.size() == 0)
		{
			dtoList = null;
		}
		
		return dtoList;
	}
	
	public OrderDTO getOrderDto(int order)
	{
		OrderDTO dto = new OrderDTO(order);
		
		if (order == 0)
		{
			dto.setOrder(1);
		}
		else if (order == 1)
		{
			dto.setOrder(2);
		}
		else if (order == 2)
		{
			dto.setOrder(1);
		}	
		
		return dto;
	}
	
	public void addRatingByMovieId(int movieId,int ratingNumber, String ratingText)
	{
		Rating rating = new Rating(movieId,ratingNumber,ratingText);
		db.persistRating(rating);		
	}
	
	public void deleteAllFromRatingTable()
	{
		db.deleteAllFromRatingTable();
	}
	
	public void deleteAllFromMovieTable()
	{
		db.deleteAllFromMovieTable();
	}
}
