package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

//	@RequestMapping(path ="createFilm.do", method=RequestMethod.POST)
//	public ModelAndView searchForFilm(
//			@RequestParam("title") String title,
//			@RequestParam("description") String description,
//			@RequestParam("releaseYear") Integer releaseYear,
//			@RequestParam("language") String language,
//			@RequestParam("rating") String rating
//			) {
//		ModelAndView mv = new ModelAndView();
//		Film film = new Film();
//		film.setTitle(title);
//		film.setDescription(description);
//		film.setReleaseYear(releaseYear);
//		film.setLanguage(language);
//		film.setRating(rating);filmDAO.createFilm(film);
//		return mv;
//	}

	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView createFilmFromHTML(Film film) {
		filmDAO.createFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/home.jsp");
		System.out.println(mv);
		return mv;
	}

	@RequestMapping(path ="searchForFilm.do", method=RequestMethod.GET)
	public ModelAndView searchByID(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(null);
		return mv;
	}
}
