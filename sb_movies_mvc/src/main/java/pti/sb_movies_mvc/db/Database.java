package pti.sb_movies_mvc.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import pti.sb_movies_mvc.model.Movie;
import pti.sb_movies_mvc.model.Rating;

@Repository
public class Database {

	private SessionFactory sessionFactory;
	
	public Database()
	{
		Configuration cfg = new Configuration();
		cfg.configure();
		
		sessionFactory = cfg.buildSessionFactory();
	}
	
	public void closeDb()
	{
		sessionFactory.close();
	}
	
	public Movie getMovieById(int movieId)
	{
		Movie movie = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		movie = session.get(Movie.class, movieId);
		
		tx.commit(); 
		session.close();
		
		return movie;
	}
	
	public Rating getRatingById(int ratingId)
	{
		Rating rating = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		rating = session.get(Rating.class, ratingId);
		
		tx.commit();
		session.close();
		
		return rating;	
	}
	
	public void persistMovieById(Movie movie)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(movie);
		
		tx.commit();
		session.close();
		
	}

	public void mergeMovieById(Movie movie) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.merge(movie);
		
		tx.commit();
		session.close();
		
	}

	public void mergeRatingById(Rating rating) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.merge(rating);
		
		tx.commit();
		session.close();
	
	}
	
	public void removeRatingById(Rating rating) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.remove(rating);
		
		tx.commit();
		session.close();
	}

	public List<Movie> getMovies() {
		
		List<Movie> movieList = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Movie> query = session.createSelectionQuery("SELECT m FROM Movie m", Movie.class);
		movieList = query.getResultList();
		
		
		tx.commit();
		session.close();
		
		
		return movieList;
	}

	public List<Rating> getRatingByMovieId(int mId) {
		
		List<Rating> ratingList = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Rating> query = session.createSelectionQuery("SELECT r FROM Rating r WHERE r.movieId=?1", Rating.class);
		query.setParameter(1, mId);
		ratingList = query.getResultList();
		
		tx.commit();
		session.close();
			
		return ratingList;
	}

	public void persistRating(Rating rating)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(rating);
		
		tx.commit();
		session.close();
	}

	public void deleteAllFromRatingTable()
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		MutationQuery query = session.createMutationQuery("DELETE FROM Rating ");
		query.executeUpdate();
		
		tx.commit();
		session.close();
	}
	
	public void deleteAllFromMovieTable()
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		MutationQuery query = session.createMutationQuery("DELETE FROM Movie");
		query.executeUpdate();
		
		tx.commit();
		session.close();
	}
}
