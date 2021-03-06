package ch.heigvd.amt.gamification.api;

import ch.heigvd.amt.gamification.dao.*;
import ch.heigvd.amt.gamification.dto.EventCreationDTO;
import ch.heigvd.amt.gamification.dto.EventPresentationDTO;
import ch.heigvd.amt.gamification.dto.EventtypeCreationDTO;
import ch.heigvd.amt.gamification.dto.EventtypePresentationDTO;
import ch.heigvd.amt.gamification.errors.ErrorMessageGenerator;
import ch.heigvd.amt.gamification.errors.HttpStatusException;
import ch.heigvd.amt.gamification.model.*;

import ch.heigvd.amt.gamification.security.Authentication;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.Date;


@javax.annotation.Generated(value = "class ch.heigvd.amt.gamification.codegen.languages.SpringCodegen", date = "2016-12-18T13:30:19.867Z")

@Controller
public class EventsApiController implements EventsApi {


    @Autowired
    private EventtypeDao eventtypeDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private ApplicationDao applicationDao;

    public ResponseEntity<EventPresentationDTO> eventsPost(@ApiParam(value = "Application token" ,required=true ) @RequestHeader(value="Authorization", required=true) String authorization,
                                                           @ApiParam(value = "New event" ,required=true ) @RequestBody EventCreationDTO eventDTO) {
        long appId = Authentication.getApplicationId(authorization);

        dataValidation(eventDTO, appId);

        Event event = new Event(new Date(),
                eventDTO.getUser_id(),
                eventtypeDao.findByApplicationIdAndId(appId, eventDTO.getEventtype_id()),
                applicationDao.findById(Authentication.getApplicationId(authorization)));

        eventDao.save(event);

        return new ResponseEntity<EventPresentationDTO>(new EventPresentationDTO(event), HttpStatus.CREATED);
    }

    private void dataValidation(EventCreationDTO eventDTO, long appId){
        if (eventDTO.getUser_id() == null) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, ErrorMessageGenerator.notFoundById("user", String.valueOf(eventDTO.getUser_id())));
        }
        if (eventDTO.getEventtype_id() == null || eventtypeDao.findByApplicationIdAndId(appId, eventDTO.getEventtype_id()) == null) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, ErrorMessageGenerator.notFoundById("eventtype", String.valueOf(eventDTO.getEventtype_id())));
        }
    }
}
