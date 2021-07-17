package com.example.spring.apiController;

import com.example.spring.entity.User;
import com.example.spring.exception.ResourceNotFoundException;
import com.example.spring.repository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    RepoUser repoUser;

    @GetMapping("/account/GetList")
    public ResponseEntity<?> getList(){
        List<User> list = repoUser.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/account/create")
    public ResponseEntity<?> createAccount(@RequestBody User user){
        repoUser.save(user);
        User u = repoUser.findByUserName(user.getUserName());
        return ResponseEntity.ok(u);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable int id,@RequestBody User user) {
        User u = repoUser.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Employee not exist with id :" + id)));
        u.setFullName(user.getFullName());
        u.setUserName(user.getUserName());
        u.setUserPassword(user.getUserPassword());
        u.setEmail(user.getEmail());
        u.setPhone(user.getPhone());

        User updateUser = repoUser.save(u);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("/account/{userName}")
    public ResponseEntity<?> getDetail(@PathVariable String userName){
        User user = repoUser.findByUserName(userName);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        User user = repoUser.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        repoUser.delete(user);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/account/login")
    public ResponseEntity<?> checkLogin(@RequestBody User user){
        User u = repoUser.findByUserNameAndUserPassword(user.getUserName(),user.getUserPassword());
        return ResponseEntity.ok(u);
    }


}
