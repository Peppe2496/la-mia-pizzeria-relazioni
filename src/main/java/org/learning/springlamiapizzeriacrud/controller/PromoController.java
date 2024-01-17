package org.learning.springlamiapizzeriacrud.controller;

import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.model.Promo;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.learning.springlamiapizzeriacrud.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Promo> result = promoRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("promo", result.get());
            return "promos/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Promo with id" + id + "not found");
        }
    }


    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, Promo formPromo,BindingResult bindingResult) {

            if (bindingResult.hasErrors()){
                return "promos/edit";
            }
            Promo updatePromo = promoRepository.save(formPromo);

            return "redirect:/pizza/show/" + updatePromo.getPizza().getId();

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        Optional<Promo> result = promoRepository.findById(id);
        if (result.isPresent()){
            Promo promoDelete = result.get();
            promoRepository.delete(promoDelete);
            return "redirect:/pizza/show/" + promoDelete.getPizza().getId();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "promo with id" + id + "not found");
        }
    }

 }
