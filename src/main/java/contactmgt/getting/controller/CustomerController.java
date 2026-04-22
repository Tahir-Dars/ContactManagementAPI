package contactmgt.getting.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class CustomerController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String getContacts() {
        return "Returning All contacts";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String addContacts() {
        return "New Contact added";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteContact(@PathVariable int id) {
        return "Contact " + id + " deleted";
    }

    @GetMapping("/public/info")
    public String publicInfo() {
        return "This is public endpoint";
    }
}
