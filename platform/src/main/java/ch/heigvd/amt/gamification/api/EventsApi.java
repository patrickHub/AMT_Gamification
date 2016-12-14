package ch.heigvd.amt.gamification.api;

import ch.heigvd.amt.gamification.model.Event;
import ch.heigvd.amt.gamification.model.Error;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@javax.annotation.Generated(value = "class ch.heigvd.amt.gamification.codegen.languages.SpringCodegen", date = "2016-12-13T18:36:02.067Z")

@Api(value = "events", description = "the events API")
public interface EventsApi {

    @ApiOperation(value = "Events generated by the client", notes = "The Events endpoint allows the client to submit new events occuring in his app", response = Event.class, tags={ "Events", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Newly created event", response = Event.class),
        @ApiResponse(code = 200, message = "Error payload", response = Event.class) })
    @RequestMapping(value = "/events",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Event> eventsPost(@ApiParam(value = "An event generated by the client application" ,required=true ) @RequestBody Event event,
        @ApiParam(value = "Application token" ,required=true ) @RequestHeader(value="Authorization", required=true) String authorization);

}