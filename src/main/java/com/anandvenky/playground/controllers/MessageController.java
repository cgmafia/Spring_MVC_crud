package com.anandvenky.playground.controllers;

import com.anandvenky.playground.entity.Message;
import com.anandvenky.playground.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/messages/")
public class MessageController {
    private final MessageRepository messageRepository;

    @Autowired
    MessageController(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @GetMapping("/sample")
    public String sampleMessage(){
        return "sample";
    }

    @GetMapping("/signup")
    String showSignupForm(Message message){
        return "add-message";
    }

    @GetMapping("/list")
    String showUpdate(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addMessage(@Valid Message message, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-message";
        }
        messageRepository.save(message);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid message Id:" + id));
        model.addAttribute("message", message);
        return "update-message";
    }

    @PostMapping("update/{id}")
    public String updatemessage(@PathVariable("id") long id, @Valid Message message, BindingResult result, Model model) {
        if (result.hasErrors()) {
            message.setId(id);
            return "update-message";
        }
        messageRepository.save(message);
        model.addAttribute("messages", messageRepository.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteMessage(@PathVariable("id") long id, Model model) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        messageRepository.delete(message);
        model.addAttribute("messages", messageRepository.findAll());
        return "index";
    }
}
