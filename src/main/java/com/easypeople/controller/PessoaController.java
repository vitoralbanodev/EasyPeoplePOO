package com.easypeople.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easypeople.entity.PessoaEntity;
import com.easypeople.repository.PessoaRepository;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaRepository repository;

    public PessoaController(PessoaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pessoas", repository.findAll());
        model.addAttribute("pessoa", new PessoaEntity());
        return "list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("pessoa", new PessoaEntity());
        return "form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute PessoaEntity pessoa) {
        repository.save(pessoa);
        return "redirect:/pessoas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        PessoaEntity pessoa = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("pessoa", pessoa);
        return "form";
    }

    @GetMapping("/ver/{id}")
    public String visualizar(@PathVariable Long id, Model model) {
        PessoaEntity pessoa = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("pessoa", pessoa);
        return "view";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/pessoas";
    }
}
