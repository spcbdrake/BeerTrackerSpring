package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class BeerTrackerController {
    @Autowired
    BeerRepository beers;

    @RequestMapping("/")
    public String home(Model model, String type, Integer calories, String search, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null){
            return "login";
        }

        if (search != null){
            model.addAttribute("beers", beers.searchByName(search));
        }
        else if (type != null && calories != null){
            model.addAttribute("beers", beers.findByTypeAndCaloriesIsLessThanEqual(type, calories));
        }
        else if (type != null) {
            model.addAttribute("beers", beers.findByTypeOrderByNameAsc(type));
        }
        else{
            model.addAttribute("beers", beers.findAll());
        }
        return "home";
    }

    @RequestMapping("/add-beer")
    public String addBeer(String beername, String beertype, Integer beercalories) {
        Beer beer = new Beer();
        beer.name = beername;
        beer.type = beertype;
        beer.calories = beercalories;
        beers.save(beer);
        return "redirect:/";
    }

    @RequestMapping("/edit-beer")
    public String editBeer(Integer id, String name, String type) {
        Beer beer = beers.findOne(id);
        beer.name = name;
        beer.type = type;
        beers.save(beer);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login(String username, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        return "redirect:/";
    }
}
