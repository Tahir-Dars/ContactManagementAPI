package contactmgt.getting.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class CustomerController {

    @GetMapping
    public String getContacts() {
        return "Returning All contacts";
    }

    @PostMapping
    public String addContacts() {
        return "New Contact added";
    }

    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable int id) {
        return "Contact " + id + " deleted";
    }

    @GetMapping("/public/info")
    public String publicInfo() {
        return "This is public endpoint";
    }
}
