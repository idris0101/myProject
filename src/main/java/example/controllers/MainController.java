package example.controllers;

import example.entities.Message;
import example.entities.User;
import example.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@org.springframework.stereotype.Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String,Object> model){
        return "greeting";
    }

    @GetMapping("/main")
    public String getForPost(Model model){
        Message message = new Message();
        model.addAttribute(message);

        return "main";
    }
//    @GetMapping("/main")
//    public String main(@RequestParam(required = false, defaultValue = "")String filter,
//                       Model model){
//        Iterable<Message> messages = messageRepo.findAll();
//        if (filter != null && !filter.isEmpty()) {
//            messages = messageRepo.findByTag(filter);
//        }else {
//            messages = messageRepo.findAll();
//        }
//        model.addAttribute("messages" , messages);
//        model.addAttribute("filter", filter);
//
//        return "main";
//    }



    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @ModelAttribute("messagePost") Message message,
                      Model model){
        model.addAttribute("message",  message);
        message.setAuthor(user);
        messageRepo.save(message);
       // Iterable<Message> messages = messageRepo.findAll();
        return "main";
    }
}
