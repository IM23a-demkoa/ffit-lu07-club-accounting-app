package ch.bzz.controller;

import ch.bzz.generated.api.ProjectApi;
import ch.bzz.generated.model.LoginProject200Response;
import ch.bzz.generated.model.LoginRequest;
import ch.bzz.model.Project;
import ch.bzz.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectApiController implements ProjectApi {

    private final ProjectRepository projectRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<Void> createProject(LoginRequest loginRequest) {
        // Prüfen, ob Projektname schon existiert
        if (projectRepository.existsById(loginRequest.getProjectName())) {
            return ResponseEntity.status(409).build(); // Conflict
        }

        // Passwort hashen
        String hashedPassword = encoder.encode(loginRequest.getPassword());

        // Projekt speichern
        Project project = new Project();
        project.setProjectName(loginRequest.getProjectName());
        project.setPasswordHash(hashedPassword);
        projectRepository.save(project);

        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<LoginProject200Response> loginProject(LoginRequest loginRequest) {
        // Projekt laden
        Project project = projectRepository.findById(loginRequest.getProjectName()).orElse(null);
        if (project == null) {
            return ResponseEntity.status(404).build();
        }

        // Passwort prüfen
        if (!encoder.matches(loginRequest.getPassword(), project.getPasswordHash())) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        // JWT generieren
        String token = jwtUtil.generateToken(project.getProjectName());

        // Response bauen
        LoginProject200Response response = new LoginProject200Response();
        response.setProjectName(project.getProjectName());
        response.setToken(token);

        return ResponseEntity.ok(response);
    }
}
