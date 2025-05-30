package com.example.demo.controller;
import com.example.demo.dto.UserDTO;
import com.example.demo.response.ApiResponse;
import java.nio.file.Files;
import java.io.File;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Exemple de GET
    @GetMapping("/{id}")
    public ApiResponse<UserDTO> getUserById(@PathVariable Long id) {
        // Traitement logique métier ici
        //UserDTO user = userService.getUserById(id);
        UserDTO user = new UserDTO(id, "John Doe");
        if (user == null) {
            return ApiResponse.failure("fail");
        }
        return ApiResponse.success("success", user);
    }

    // Exemple de POST
    @PostMapping("/create")
    public ApiResponse<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        // Traitement logique métier ici
        //UserDTO createdUser = userService.createUser(userDTO);
        UserDTO createdUser = new UserDTO(1L, userDTO.getName());
        if (createdUser == null) {
            return ApiResponse.failure("fail");
        }
        return ApiResponse.success("success", createdUser);
    }

    // Exemple de GET contenant une image
    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage() {
        File file = new File("path/to/image.png");
        try {
            byte[] imageBytes = Files.readAllBytes(file.toPath());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(imageBytes);
        } catch (java.io.IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    // TODO: Voir si on peut renvoyer ChartPanel
}