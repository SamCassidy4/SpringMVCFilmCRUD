package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDAOImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	String username = "student";
	String password = "student";

	// establishing database link
	public FilmDAOImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// using the maven technology
		} catch (ClassNotFoundException e) {
			System.err.println("Error loading database Driver");
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {

		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, username, password);
			String sql = "SELECT*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setLanguage(rs.getString("name"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setActors(findActorsByFilmId(filmId));
			}
			pstmt.close();
			conn.close();
			rs.close();
		} catch (SQLException e) {
			System.err.println("Database Error in film table");
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {

			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

			Connection conn = DriverManager.getConnection(URL, username, password);
			PreparedStatement stmt = conn.prepareStatement(sql);
			System.out.println(stmt);

			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				actor = new Actor();

				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
				stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println("Database error");
			System.err.println(e);
		}
		return actor;

	}

	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON film_actor.actor_id = actor.id JOIN film ON film_actor.film_id = film.id WHERE film.id = ?;";
			Connection conn = DriverManager.getConnection(URL, username, password);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);

			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Database error actors by film id");
			e.printStackTrace();
		}
		if (actors.size() == 0) {
			return null;
		}
		return actors;

	}
	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> movie = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, username, password);
			String sql = "SELECT*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.description LIKE ? OR film.title LIKE ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");

			// need two bind setString() methods for two bind variables
			// need a setString() for each bind variable used

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				// need to create film objects to set each film criteria
				// movie is the name of our list
				// it doesn't make sense to use movie here
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setLanguage(rs.getString("name"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setActors(findActorsByFilmId(film.getId()));

				if (film.getTitle().toLowerCase().contains(keyword.toLowerCase())
						|| (film.getDescription().toLowerCase().contains(keyword.toLowerCase()))) {
					movie.add(film);
					// instead we add all the films we made to the movie list here
				}

			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			System.err.println("Database error, find film by keyword");
		}

		return movie;

	}

	@Override
	public Actor createActor(Actor actor) {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, username, password);
			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "INSERT INTO actor (first_name, last_name) " + " VALUES (?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();

				if (keys.next()) {
					int newActorId = keys.getInt(1);
					actor.setId(newActorId);

					if (actor.getFilms() != null && actor.getFilms().size() > 0) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";

						stmt = conn.prepareStatement(sql);

						for (Film film : actor.getFilms()) {
							stmt.setInt(1, film.getId());
							stmt.setInt(2, newActorId);
							updateCount = stmt.executeUpdate();
						}
					}
				}
			} else {
				actor = null;
			}
			conn.commit(); // COMMIT TRANSACTION
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + actor);
		}
		return actor;
	}

	@Override
	public boolean saveActor(Actor actor) {
		String username = "student";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, username, password);
			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "UPDATE actor SET first_name=?, last_name=? " + " WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setInt(3, actor.getId());
			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				// Replace actor's film list
				sql = "DELETE FROM film_actor WHERE actor_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actor.getId());
				updateCount = stmt.executeUpdate();

				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
				stmt = conn.prepareStatement(sql);

				for (Film film : actor.getFilms()) {
					stmt.setInt(1, film.getId());
					stmt.setInt(2, actor.getId());
					updateCount = stmt.executeUpdate();
				}
				conn.commit(); // COMMIT TRANSACTION
				conn.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteActor(Actor actor) {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, username, password);
			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "DELETE FROM film_actor WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, actor.getId());
			int updateCount = stmt.executeUpdate();

			sql = "DELETE FROM actor WHERE id = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, actor.getId());
			updateCount = stmt.executeUpdate();

			conn.commit(); // COMMIT TRANSACTION
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
	@Override
	public Film createFilm(Film film) {

		try (Connection conn = DriverManager.getConnection(URL, username, password)) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO film (title, language_id, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating, special_features) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, film.getTitle());
			ps.setInt(2, film.getLanguageId());
			ps.setString(3, film.getDescription());
			ps.setInt(4, film.getReleaseYear());
			ps.setInt(5, film.getRentalDuration());
			ps.setDouble(6, film.getRentalRate());
			ps.setInt(7, film.getLength());
			ps.setDouble(8, film.getReplacementCost());
			ps.setString(9, film.getRating());
			ps.setString(10, film.getSpecialFeatures());

			int updateCount = ps.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = ps.getGeneratedKeys();
				if (keys.next()) {
					int newId = keys.getInt(1);
					film.setId(newId);
//					if (film.getActors() != null && film.getActors().size() > 0) {
//						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
//						ps = conn.prepareStatement(sql);
//						ps.setInt(1, film.getId());
//						updateCount = ps.executeUpdate();
//						System.out.println(updateCount);
////////						////
//						for (Actor actor : film.getActors()) {
//							ps.setInt(2, actor.getId());
//							updateCount = ps.executeUpdate();
//
//						}
//					}
				}
			} else {
				film = null;
			}
			conn.commit();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException("Error inserting film " + film);
		}
		return film;
	}
	@Override
	public boolean saveFilm(Film film) {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, username, password);
			conn.setAutoCommit(false);

			String sql = "UPDATE film SET title=? , rating=?, description=?, release_year=?, language_id=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?, special_features=? WEHRE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, film.getTitle() == null ? "" : film.getTitle());
			ps.setString(2, film.getRating() == null ? "" : film.getRating());
			ps.setString(3, film.getDescription() == null ? "" : film.getDescription());
			ps.setInt(4, film.getReleaseYear() == null ? 0 : film.getReleaseYear());
			ps.setInt(5, film.getLanguageId() == null ? 0 : film.getLanguageId());
			ps.setInt(6, film.getRentalDuration() == null ? 0 : film.getRentalDuration());
			ps.setDouble(7, film.getRentalRate() == null ? 0.0 : film.getRentalRate());
			ps.setInt(8, film.getLength() == null ? 0 : film.getLength());
			ps.setDouble(9, film.getReplacementCost() == null ? 0.0 : film.getReplacementCost());
			ps.setString(10, film.getSpecialFeatures() == null ? "" : film.getSpecialFeatures());
			ps.setInt(11, film.getId() == 0 ? 0 : film.getId());
			int updateCount = ps.executeUpdate();

			if (updateCount == 1) {
				sql = "DELETE FROM film_actor WHERE film_id =?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, film.getId());
				updateCount = ps.executeUpdate();
				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, film.getId());

				List<Actor> cast = film.getActors();

				if (cast != null && cast.size() != 0) {
					for (Actor actor : film.getActors()) {

						ps.setInt(2, actor.getId());
						updateCount = ps.executeUpdate();

					}
				}

				conn.commit();
				conn.close();

			}

		} catch (SQLException e) {
			System.err.println("Error saving film");
			e.printStackTrace();
			return false;

		}
		return true;
	}
	@Override
	public boolean deleteFilm(Film film) {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, username, password);
			conn.setAutoCommit(false);
			String sql = "DELETE FROM film_actor where film_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, film.getId());

			int updateCount = ps.executeUpdate();
			sql = "DELETE FROM film WHERE id = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, film.getId());
			updateCount = ps.executeUpdate();
			System.out.println(updateCount);
			conn.commit();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

}