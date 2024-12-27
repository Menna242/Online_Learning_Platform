package com.example.demo.Controller;
import com.example.demo.Entity.User;
import com.example.demo.Service.ProfileManagmentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Profile")
@RequiredArgsConstructor
//update profile and view profile 
public class ProfileManagmentController {
    private final ProfileManagmentServices profileManagmentServices;
    @GetMapping("/viewProfile/{USER_ID}")
    public User viewProfile(@PathVariable("USER_ID") Long userId) {
        return profileManagmentServices.viewProfile(userId);
    }
    @PatchMapping("/updateProfile/{USER_ID}")
    public User updateProfile(@PathVariable Long USER_ID , @RequestBody UpdateRequest profile) {
        return profileManagmentServices.updateprofile(USER_ID , profile);
    }
    @PatchMapping("/updateAdmin/{USER_ID}")
    public User updateAdmin(@PathVariable Long USER_ID , @RequestBody updateAdmin profile) {
        return profileManagmentServices.updateAdmin(USER_ID , profile);
    }
}
