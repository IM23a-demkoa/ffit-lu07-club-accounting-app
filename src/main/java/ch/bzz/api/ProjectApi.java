package ch.bzz.api;

import ch.bzz.generated.model.LoginProject200Response;
import ch.bzz.generated.model.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
@Validated
@Tag(name = "Project", description = "the Project API")
public interface ProjectApi {

    /**
     * POST /projects : Create a new accounting project
     *
     * @param loginRequest  (required)
     * @return Project successfully created (status code 201)
     *         or Invalid input (status code 400)
     */
    @Operation(
        operationId = "createProject",
        summary = "Create a new accounting project",
        tags = { "Project" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Project successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/projects",
        consumes = { "application/json" }
    )
    
    ResponseEntity<Void> createProject(
        @Parameter(name = "LoginRequest", description = "", required = true) @Valid @RequestBody LoginRequest loginRequest
    );


    /**
     * POST /login : Log in to an existing accounting project
     *
     * @param loginRequest  (required)
     * @return Logged in successfully (status code 200)
     *         or Invalid credentials (status code 401)
     */
    @Operation(
        operationId = "loginProject",
        summary = "Log in to an existing accounting project",
        tags = { "Project" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Logged in successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = LoginProject200Response.class))
            }),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/login",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<LoginProject200Response> loginProject(
        @Parameter(name = "LoginRequest", description = "", required = true) @Valid @RequestBody LoginRequest loginRequest
    );

}
