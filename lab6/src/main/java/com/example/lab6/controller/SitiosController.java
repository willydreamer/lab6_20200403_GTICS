package com.example.lab6.controller;

import com.example.lab6.entity.Site;
import com.example.lab6.repository.SitiosRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/sitios")
public class SitiosController {
    final SitiosRepository sitiosRepository;

    public SitiosController(SitiosRepository sitiosRepository) {
        this.sitiosRepository = sitiosRepository;
    }

    @GetMapping(value = {"","lista"})
    public String listarSitios (Model model){
        model.addAttribute("listaSitio", sitiosRepository.findAll());
        return "listaSitios";
    };

    @PostMapping("/save")
    public String guardarSitio(Site sitio, RedirectAttributes attr) {
        sitiosRepository.save(sitio);
        return "redirect:/lista";
    }

    @GetMapping("/editar")
    public String editarSitio(Model model, @RequestParam("id") int id) {

        Optional<Site> sitios = sitiosRepository.findById(id);

        if (sitios.isPresent()) {
            Site sitio = sitios.get();
            model.addAttribute("sitio", sitio);
            return "editarSitio";
        } else {
            return "redirect:/lista";
        }
    }

    @GetMapping(value = {"","nuevo"})
    public String nuevoSitio (Model model){
        model.addAttribute("listaSitios", sitiosRepository.findAll());
        return "nuevoSitio";
    };
    @GetMapping("/delete")
    public String borrarSitio(@RequestParam("id") int id, RedirectAttributes attributes) {

        Optional<Site> sitio = sitiosRepository.findById(id);

        if (sitio.isPresent()) {
            Site site = sitio.get();
            String nombre = site.getSiteName();
            sitiosRepository.deleteById(id);
            attributes.addFlashAttribute("msg", "Sitio " + nombre + " borrado exitosamente");
        }
        return "redirect:/sitios/lista";

    }



}
