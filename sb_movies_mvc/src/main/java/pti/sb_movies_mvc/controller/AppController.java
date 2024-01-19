package pti.sb_movies_mvc.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_movies_mvc.dto.MovieDTO;
import pti.sb_movies_mvc.dto.OrderDTO;
import pti.sb_movies_mvc.dto.RatingDTO;
import pti.sb_movies_mvc.service.AppService;

@Controller
public class AppController {
	
	private AppService service;
	
	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}

	
	
//	#1
	//localhost:8080/movie?mid=x
	@GetMapping("/movie")
	public String showMovie(
				Model model,
				@RequestParam("mid") int movieId
			)
	{
		
	   	MovieDTO dto = service.getMovieById(movieId);
	   	
	   	model.addAttribute("movie", dto);
		return "movie.html";
	}
	
//	#2
	@GetMapping("/movie/rating")
	public String showRating(
				Model model,
				@RequestParam("rid") int ratingId
			)
	{	
		RatingDTO dto = service.getRatingById(ratingId);
		
		model.addAttribute("rating", dto);
		
		return "rating.html";
	}
	
//	#3
	@PostMapping("/movie/save")
	public String saveMovie(
				Model model,
				@RequestParam("title") String mTitle,
				@RequestParam("pyear") int mPYear
			)
	{
		MovieDTO dto = service.saveMovie(mTitle,mPYear);
		
		model.addAttribute("movie",dto);
		
		
		return "movie.html";
	}
	
//	#4
	@PostMapping("/movie/update")
	public String updateMovie(
				Model model,
				@RequestParam("mid") int movieId,
				@RequestParam("title") String mTitle
				)
	{	
		MovieDTO dto = service.updateMovie(movieId, mTitle);
		
		model.addAttribute("movie", dto);
		
		
		return "movie.html";
	}
	
//	#5	
	@PostMapping("/movie/ratingupdate")
	public String updateMovieRating(
				Model model,
				@RequestParam("ratingid") int ratingId,
				@RequestParam("ratingnumber") int ratingNumber,
				@RequestParam("ratingtext") String ratingText			
				) 
	{	
		RatingDTO dto = service.updateRating(ratingId,ratingNumber,ratingText);
		
		model.addAttribute("rating", dto);
		
		return "rating.html";
	}

//	#6
	@PostMapping("/rating/remove")
	public String removeRating(
				Model model,
				@RequestParam("ratingid") int ratingId
			)
	{
		RatingDTO dto = service.removeRating(ratingId);
		
		model.addAttribute("rating", dto);
		
		
		return "rating.html";
	}

//	#7
	//localhost:8080/movie/updategenre?ratingid=x&title=y&genre=z
	@PostMapping("/movie/updategenre")
	public String updateGenre(
				Model model,
				@RequestParam("ratingid") int ratingId,
				@RequestParam("title") String title,
				@RequestParam("genre") String genre
			)
	{
		MovieDTO dto = service.updateGenre(ratingId,title,genre);
		
		model.addAttribute("movie", dto);
		
		return "result.html";
	}

//	#8
	//localhost:8080/movies
	@GetMapping("/movies")
	public String showMovies(
				Model model
			)
	{
		ArrayList<MovieDTO> dtos = service.showMovies(0);
		OrderDTO orderDto = service.getOrderDto(0);
		
		model.addAttribute("movies" , dtos);
		model.addAttribute("order", orderDto);
		
		return "allmovies.html";
	}
	
	//localhost:8080/movies/movie/{movieid}
	@GetMapping("/movies/movie/{movieid}")
	public String getRatingByMovieId(Model model, 
									@PathVariable("movieid")  int mId
									)
	{	
		ArrayList<MovieDTO> dtos = service.showMovies(0);
		OrderDTO orderDto = service.getOrderDto(0);
		
		model.addAttribute("movies", dtos);
		model.addAttribute("order", orderDto);
		
		ArrayList<RatingDTO> ratingDtos = service.getRatingByMovieId(mId);
		
		model.addAttribute("ratings", ratingDtos);
		
		
		return "allmovies.html";
	}
	
	//localhost:8080/movies/abc
	@GetMapping("/movies/abc")
	public String getMoviesAbc(Model model,
						@RequestParam("order") int order
					)
	{		
		ArrayList<MovieDTO> dtos =service.showMovies(order);
		OrderDTO orderDto = service.getOrderDto(order);
		
		model.addAttribute("movies", dtos);
		model.addAttribute("order", orderDto);
		
		return "allmovies.html";
	}

	//localhost:8080/movies/addnewrating
	@PostMapping("/movies/addnewrating")
	public String addNewRating(Model model,
						@RequestParam("movieId") int movieId,
						@RequestParam("ratingNumber") int ratingNumber,
						@RequestParam("ratingText") String ratingText
					)
	{
		ArrayList<MovieDTO> dtos = service.showMovies(0);
		OrderDTO orderDto = service.getOrderDto(0);	
		service.addRatingByMovieId(movieId, ratingNumber, ratingText);
		ArrayList<RatingDTO> ratingDtos = service.getRatingByMovieId(movieId);
		
		model.addAttribute("movies", dtos);
		model.addAttribute("order", orderDto);
		model.addAttribute("ratings", ratingDtos);
			
		return "allmovies.html";
	}
	
	//localhost:8080/movies/deleteallfrommovies
	@PostMapping("/movies/deleteallfrommovies")
	public String deleteDatabase(Model model)
	{		
		service.deleteAllFromRatingTable();
		service.deleteAllFromMovieTable();
		
		ArrayList<MovieDTO> dtos = service.showMovies(0);
		
		model.addAttribute("movies" , dtos);
	
		return "result.html";
	}
	
}
