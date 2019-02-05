package kz.bts.GraphProblem.exceptions;

import kz.bts.GraphProblem.data.response.GeneralResponse;
import kz.bts.GraphProblem.services.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Обработчик, который перехватывает exceptions и формирует ответ на запрос
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private GraphService graphService;

    @ExceptionHandler(GraphException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody GeneralResponse handleGraphException(GraphException ex){
        GeneralResponse response = new GeneralResponse();
        response.setHttpcode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(graphService.getCurrentTime());
        response.setDescription(ex.getMessage());
        ex.printException();
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody GeneralResponse handleException(Exception ex){
        GeneralResponse response = new GeneralResponse();
        response.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setTimestamp(graphService.getCurrentTime());
        response.setDescription(ex.getMessage());
        ex.printStackTrace();
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody GeneralResponse handleValidationException(MethodArgumentNotValidException ex){
        ex.getBindingResult();
        GeneralResponse response = new GeneralResponse();
        response.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setTimestamp(graphService.getCurrentTime());
        response.setDescription(ex.getMessage());
        ex.printStackTrace();
        return response;
    }
}
