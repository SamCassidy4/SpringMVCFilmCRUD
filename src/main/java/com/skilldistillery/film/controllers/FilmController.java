package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

//	@RequestMapping("home.do")
//	public String home() {
//		return "WEB-INF/views/home.jsp";
//	}

	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView createFilmFromHTML(
			@RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("releaseYear") Integer releaseYear,
			@RequestParam("languageId") Integer languageId, @RequestParam("length") Integer length,
			@RequestParam("rating") String rating, @RequestParam("rentalDuration") Integer rentalDuration,
			@RequestParam("rentalRate") Double rentalRate, @RequestParam("replacementCost") Double replacementCost,
			@RequestParam("specialFeatures") String specialFeatures) {
		ModelAndView mv = new ModelAndView();
		Film f = new Film();
		
		f.setTitle(title);
		f.setDescription(description);
		f.setReleaseYear(releaseYear);
		f.setLanguageId(languageId);
		f.setLength(length);
		f.setRating(rating);
		f.setRentalDuration(rentalDuration);
		f.setRentalRate(rentalRate);
		f.setReplacementCost(replacementCost);
		f.setSpecialFeatures(specialFeatures);
		try {
			f = filmDAO.createFilm(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(f.getId());
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/views/redirect.jsp");
		return mv;
	}

	@RequestMapping(path = "searchForFilmId.do", method = RequestMethod.POST)
	public ModelAndView searchByID(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		Film film = null;
		film = filmDAO.findFilmById(id);
		System.out.println(film);
		if (film == null) {
			mv.setViewName("WEB-INF/views/error.jsp");
		} else {
			mv.addObject("film", film);
			mv.setViewName("WEB-INF/views/redirect.jsp");
		}

		return mv;
	}

	@RequestMapping(path = "delete.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView();

		boolean success = filmDAO.deleteFilm(filmDAO.findFilmById(id));
		// System.out.println(success);
		if (success) {
			mv.setViewName("WEB-INF/views/success.jsp");
		} else {
			mv.setViewName("WEB-INF/views/deleteError.jsp");
		}

		return mv;
	}

	@RequestMapping(path = "edit.do", method = RequestMethod.POST)
	public ModelAndView editFilmConfirm(@RequestParam("editFilm") String edit, @RequestParam("id") String id) {
		ModelAndView mv = new ModelAndView();
		if (edit.toUpperCase().equals("YES")) {
			Film toEdit = null;
			
			try {
				toEdit = filmDAO.findFilmById(Integer.valueOf(id));
			} catch (Exception e) {
				mv.setViewName("WEB-INF/views/error.jsp");
			}
			if (toEdit != null) {
				mv.addObject("film", toEdit);
				mv.setViewName("WEB-INF/views/edit.jsp");
			} else {
				mv.setViewName("WEB-INF/views/error.jsp");
			}
		} else {
			mv.setViewName("WEB-INF/index.html");
		}

		mv.setViewName("WEB-INF/views/edit.jsp");

		return mv;
	}

	@RequestMapping(path = "editYes.do", method = RequestMethod.POST)
	public ModelAndView editFilm(@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("releaseYear") Integer releaseYear, @RequestParam("languageId") Integer languageId,
			@RequestParam("length") Integer length, @RequestParam("rating") String rating,
			@RequestParam("rentalDuration") Integer rentalDuration, @RequestParam("rentalRate") Double rentalRate,
			@RequestParam("replacementCost") Double replacementCost,
			@RequestParam("specialFeatures") String specialFeatures, @RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView();
		Film f = new Film();
		f.setId(id);
		f.setTitle(title);
		f.setDescription(description);
		f.setReleaseYear(releaseYear);
		f.setLanguageId(languageId);
		f.setLength(length);
		f.setRating(rating);
		f.setRentalDuration(rentalDuration);
		f.setRentalRate(rentalRate);
		f.setReplacementCost(replacementCost);
		f.setSpecialFeatures(specialFeatures);

		mv.addObject("film", f);
		mv.setViewName("WEB-INF/views/redirect.jsp");
		return mv;
	}

	@RequestMapping(path = "findFilmByKeyword.do", method = RequestMethod.POST)
	public ModelAndView findFilmsByKeyword(@RequestParam("kw") String kw) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = new ArrayList<>();

		if (kw.equals("")) {
			mv.setViewName("WEB-INF/views/error.jsp");
			return mv;
		} else {
			films = filmDAO.findFilmByKeyword(kw);
		}
		if (films.size() == 0) {
			mv.setViewName("WEB-INF/views/error.jsp");
			return mv;
		}
		mv.addObject("films", films);
		mv.setViewName("WEB-INF/views/keyword.jsp");

		return mv;

	}

	@RequestMapping(path = "updateFilm.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(Film film) {
		ModelAndView mv = new ModelAndView();

		boolean success = filmDAO.saveFilm(film);
		if (success) {
			mv.addObject("film", film);
			mv.setViewName("WEB-INF/views/redirect.jsp");
			return mv;
		} else {
			mv.setViewName("WEB-INF/views/error.jsp");
			return mv;
		}
	}

}
