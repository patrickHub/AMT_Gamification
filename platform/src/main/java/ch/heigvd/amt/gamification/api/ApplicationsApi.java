package ch.heigvd.amt.gamification.api;

import ch.heigvd.amt.gamification.dto.ApplicationCreationDTO;
import ch.heigvd.amt.gamification.dto.ApplicationPresentationDTO;
import ch.heigvd.amt.gamification.model.Application;
import ch.heigvd.amt.gamification.model.Token;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@javax.annotation.Generated(value = "class ch.heigvd.amt.gamification.codegen.languages.SpringCodegen", date = "2016-12-18T13:30:19.867Z")

@Api(value = "applications", description = "the applications API")
public interface ApplicationsApi {

    @ApiOperation(value = "Get the application's token", notes = "", response = Token.class, tags = {"Applications", "Authentication",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Application token", response = Token.class)})
    @RequestMapping(value = "/applications/auth",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Token> applicationsAuthPost(@ApiParam(value = "Requested application", required = true) @RequestBody ApplicationCreationDTO application);


    @ApiOperation(value = "Delete application with specified id", notes = "", response = Void.class, tags = {"Applications",})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted successfully", response = Void.class)})
    @RequestMapping(value = "/applications",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> applicationsDelete(@ApiParam(value = "Application token", required = true) @RequestHeader(value = "Authorization", required = true) String authorization);


    @ApiOperation(value = "Create a new application", notes = "", response = Application.class, tags = {"Applications",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Application created", response = Application.class)})
    @RequestMapping(value = "/applications",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<ApplicationPresentationDTO> applicationsPost(@ApiParam(value = "New application", required = true) @RequestBody ApplicationCreationDTO application);

}
