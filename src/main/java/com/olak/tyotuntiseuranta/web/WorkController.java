package com.olak.tyotuntiseuranta.web;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.olak.tyotuntiseuranta.web.domain.Work;
import com.olak.tyotuntiseuranta.web.domain.WorkRepository;

@Controller 
public class WorkController {
    private final WorkRepository workRepository;

    public WorkController(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/work")
    public String showWork(Model model){
        model.addAttribute("work", new Work());
        model.addAttribute("works", workRepository.findAll());
        return "work";
    }
    @PostMapping("/work/save")
    public String saveWork(Work work){
        workRepository.save(work);
        return "redirect:/work";
    }
    @PostMapping("work/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable("id") long id){
        workRepository.deleteById(id);
        return "redirect:/work";
    }
    @GetMapping ("work/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editBook(@PathVariable("id") long id, Model model){
        Work work= workRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Työtuntia ei löytynyt"));
        model.addAttribute(work);
        return "/editwork";
    }
    @ResponseBody 
    @GetMapping ("/api/work")
    public Iterable<Work> allWorks(){
        return workRepository.findAll();
    } 

    @ResponseBody 
    @GetMapping ("/api/work/{id}")
    public Optional<Work> oneWork(@PathVariable("id") long id){
        return workRepository.findById(id);
    } 
}
