package kz.bts.GraphProblem.controllers;

import kz.bts.GraphProblem.data.Graph;
import kz.bts.GraphProblem.data.request.EdgeRequest;
import kz.bts.GraphProblem.data.request.VertexRequest;
import kz.bts.GraphProblem.data.response.DataResponse;
import kz.bts.GraphProblem.data.response.GeneralResponse;
import kz.bts.GraphProblem.exceptions.GraphException;
import kz.bts.GraphProblem.services.GraphService;
import kz.bts.GraphProblem.validators.EdgeValidator;
import kz.bts.GraphProblem.validators.VertexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Основной контроллер для работы с операциями с графом
 */
@RestController
@RequestMapping(value = "/graph", produces = MediaType.APPLICATION_JSON_VALUE)
public class GraphController<T> {

    @Autowired
    private GraphService<T> graphService;
    @Autowired
    private EdgeValidator edgeValidator;
    @Autowired
    private VertexValidator vertexValidator;

    @InitBinder("edgeRequest")
    protected void initEdgeValidator(WebDataBinder binder){
        binder.addValidators(edgeValidator);
    }
    @InitBinder("vertexRequest")
    protected void initVertexValidator(WebDataBinder binder){
        binder.addValidators(vertexValidator);
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public DataResponse<kz.bts.GraphProblem.data.Graph> getGraph(){
        DataResponse<Graph> response = new DataResponse<>();
        response.setData(graphService.getGraph());
        response.setDescription("Текущий граф");
        response.setHttpcode(HttpStatus.OK.value());
        response.setTimestamp(graphService.getCurrentTime());
        return response;
    }

    @RequestMapping(value = "/vertices/add", method = RequestMethod.POST)
    public GeneralResponse addVertex(@RequestBody @Validated VertexRequest<T> vertexRequest, BindingResult result) throws GraphException {
        if (result.hasErrors()){
            return graphService.handleErrors(result);
        }
        GeneralResponse response = new GeneralResponse();
        graphService.createVertex(vertexRequest.getVertex());
        response.setHttpcode(HttpStatus.OK.value());
        response.setDescription("Вершина " + vertexRequest.getVertex() + " добавлена.");
        response.setTimestamp(graphService.getCurrentTime());
        return response;

    }

    @RequestMapping(value = "/vertices/remove", method = RequestMethod.POST)
    public GeneralResponse removeVertex(@RequestBody @Validated VertexRequest<T> vertexRequest, BindingResult result){
        if (result.hasErrors()){
            return graphService.handleErrors(result);
        }
        GeneralResponse response = new GeneralResponse();
        graphService.removeVertex(vertexRequest.getVertex());
        response.setHttpcode(HttpStatus.OK.value());
        response.setDescription("Вершина " + vertexRequest.getVertex() + " удалена.");
        response.setTimestamp(graphService.getCurrentTime());
        return response;
    }

    @RequestMapping(value = "/edges/add", method = RequestMethod.POST)
    public GeneralResponse addEdge(@RequestBody @Validated EdgeRequest<T> edgeRequest, BindingResult result){
        if (result.hasErrors()){
            return graphService.handleErrors(result);
        }
        GeneralResponse response = new GeneralResponse();
        graphService.createEdge(edgeRequest.getSrc(), edgeRequest.getDst());
        response.setHttpcode(HttpStatus.OK.value());
        response.setDescription("Ребро " + edgeRequest.getSrc() + "-" + edgeRequest.getDst() + " добавлено.");
        response.setTimestamp(graphService.getCurrentTime());
        return response;
    }

    @RequestMapping(value = "/edges/remove", method = RequestMethod.POST)
    public GeneralResponse removeEdge(@RequestBody @Validated EdgeRequest<T> edgeRequest, BindingResult result){
        if (result.hasErrors()){
            return graphService.handleErrors(result);
        }
        GeneralResponse response = new GeneralResponse();
        graphService.removeEdge(edgeRequest.getSrc(), edgeRequest.getDst());
        response.setHttpcode(HttpStatus.OK.value());
        response.setDescription("Ребро " + edgeRequest.getSrc() + "-" + edgeRequest.getDst() + " удалено.");
        response.setTimestamp(graphService.getCurrentTime());
        return response;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public DataResponse findPath(@RequestBody @Validated EdgeRequest<T> edgeRequest, BindingResult result){
        if (result.hasErrors()){
            return graphService.handleErrors(result);
        }
        DataResponse<List<T>> response = new DataResponse<>();
        List<T> path = graphService.findPath(edgeRequest.getSrc(), edgeRequest.getDst());
        response.setHttpcode(HttpStatus.OK.value());
        response.setDescription("Путь между " + edgeRequest.getSrc() + " и " + edgeRequest.getDst() + ".");
        response.setTimestamp(graphService.getCurrentTime());
        response.setData(path);
        return response;
    }
}
