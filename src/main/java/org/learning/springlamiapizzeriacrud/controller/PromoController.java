package org.learning.springlamiapizzeriacrud.controller;

import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.model.Promo;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.learning.springlamiapizzeriacrud.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/promos")
public class PromoController {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/create")
    public String create(@RequestParam(name = "pizzaId", required = true) Integer pizzaId, Model model){
        Optional<Pizza> result = pizzaRepository.findById(pizzaId);
        if (result.isPresent()){
            Pizza pizzaToPromo = result.get();
            model.addAttribute("pizza", pizzaToPromo);
            Promo newPromo = new Promo();
            newPromo.setPizza(pizzaToPromo);
            model.addAttribute("promos", newPromo);

            return "promos/create";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id" + pizzaId + "not found");
        }
    }

    @PostMapping("/create")

    public String store(Promo formPromo){

        Promo storedPromo = promoRepository.save(formPromo);
        return "redirect:/pizza/show/" + storedPromo.getPizza().getId();
    }

 }
