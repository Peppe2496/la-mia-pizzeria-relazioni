package org.learning.springlamiapizzeriacrud.controller;


import jakarta.validation.Valid;
import org.hibernate.engine.jdbc.mutation.spi.BindingGroup;
import org.learning.springlamiapizzeriacrud.model.Ingredient;
import org.learning.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

@GetMapping
    public String index(Model model){
    List<Ingredient> ingredientsList;
    ingredientsList = ingredientRepository.findAll();
    model.addAttribute("ingredientsList" , ingredientsList);
    return "ingredient/index";
}

@GetMapping("/create")
    public String create(Model model){
    Ingredient ingredient = new Ingredient();
    model.addAttribute("ingredient", ingredient);
    return "ingredient/create";
}

@PostMapping("/create")
public String create(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model){

    if(bindingResult.hasErrors()){
        model.addAttribute("ingredient", ingredientRepository.findAll());
        return "ingredient/create";
    }
    Ingredient savedIngredient = ingredientRepository.save(formIngredient);
    return "redirect:/ingredient";

}

@GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
    Optional<Ingredient> result = ingredientRepository.findById(id);
    if (result.isPresent()){
        model.addAttribute("ingredient", result.get());
        return "ingredient/edit";
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient with id" + id + "not found");
    }

}

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        Optional<Ingredient> result = ingredientRepository.findById(id);
        if (result.isPresent()) {
            Ingredient ingredientToEdit = result.get();
            if (bindingResult.hasErrors()) {
                return "ingredient/edit";
            }
            Ingredient savedIngredient = ingredientRepository.save(formIngredient);
            return "redirect:/ingredient";

        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient with id " + id + " not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Ingredient> result = ingredientRepository.findById(id);
        if (result.isPresent()) {
            Ingredient ingredientToDelete = result.get();
            ingredientRepository.delete(ingredientToDelete);
            redirectAttributes.addFlashAttribute("redirectMessage", "ingredient" + ingredientToDelete.getName() + " deleted");
            return "redirect:/ingredient";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient with id " + id + " not found");
        }

    }


}
