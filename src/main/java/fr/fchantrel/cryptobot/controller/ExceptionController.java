package fr.fchantrel.cryptobot.controller;

import fr.fchantrel.cryptobot.exceptions.CryptobotException;
import fr.fchantrel.cryptobot.exceptions.CryptobotRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller de gestion des Exceptions retournées par l'API. Mapping de ces Exceptions avec des codes retour HTTP.
 * @author fchantrel
 *
 */
public class ExceptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class.getName());
	
    @ExceptionHandler (CryptobotException.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public String handleBadRequestExceptions(CryptobotException ex) {
    	logger.error(ex.getMessage(), ex);
        return ex.getMessage();
    }
    
    @ExceptionHandler (CryptobotRuntimeException.class)
    @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleRuntimeExceptions(CryptobotRuntimeException ex) {
    	logger.error(ex.getMessage(), ex);
        return ex.getMessage();
    }
    

    
}