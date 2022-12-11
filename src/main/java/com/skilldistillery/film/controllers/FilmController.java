package com.skilldistillery.film.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	private FilmDAO filmDAO;

	public void setFilmDAO(FilmDAO filmDAO) {
		this.filmDAO = filmDAO;
	}

	@RequestMapping("home.do")
	public String home() {
		return "WEB-INF/views/home.jsp";
	}

	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView createFilmFromHTML(Film film) throws SQLException {
		ModelAndView mv = new ModelAndView();
		Film f = filmDAO.createFilm(film);
		System.out.println(f.getId());
		mv.setViewName("WEB-INF/views/redirect.jsp");
		mv.addObject("film", film);
		return mv;
	}

	@RequestMapping(path = "searchForFilmId.do", method = RequestMethod.GET)
	public ModelAndView searchByID(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		Film film = null;
		film = filmDAO.findFilmById(id);
		if (film == null) {
			mv.setViewName("WEB-INF/views/error.jsp");
		}
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/views/redirect.jsp");
		return mv;
	}

	@RequestMapping(path = "delete.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView();

		boolean success = filmDAO.deleteFilm(filmDAO.findFilmById(id));
		// System.out.println(success);
		if (success) {
			mv.setViewName("WEB-INF/views/sucess.jsp");
		} else {
			mv.setViewName("WEB-INF/views/deleteError.jsp");
		}

		return mv;
	}

	@RequestMapping(path = "edit.do", method = RequestMethod.POST)
	public ModelAndView editFilm(@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("releaseYear") Integer releaseYear, @RequestParam("language") String language,
			@RequestParam("length") Integer length, @RequestParam("rating") String rating,
			@RequestParam("rentalDuration") Integer rentalDuration, @RequestParam("rentalRate") Double rentalRate,
			@RequestParam("replacementCost") Double replacementCost,
			@RequestParam("specialFeatures") String specialFeatures) {
		ModelAndView mv = new ModelAndView();
		Film filmEdit = new Film();
		filmEdit.setTitle(title);
		filmEdit.setDescription(description);
		filmEdit.setReleaseYear(releaseYear);
		filmEdit.setLanguage(language);
		filmEdit.setRating(rating);
		filmEdit.setRentalDuration(rentalDuration);
		filmEdit.setRentalRate(rentalRate);
		filmEdit.setReplacementCost(replacementCost);
		filmEdit.setSpecialFeatures(specialFeatures);

		mv.setViewName("WEB-INF/views/redirect.jsp");
		return mv;
	}

}
