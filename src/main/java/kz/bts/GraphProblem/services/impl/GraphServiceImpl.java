package kz.bts.GraphProblem.services.impl;

import kz.bts.GraphProblem.data.Graph;
import kz.bts.GraphProblem.data.response.DataResponse;
import kz.bts.GraphProblem.exceptions.GraphException;
import kz.bts.GraphProblem.services.GraphService;
import kz.bts.GraphProblem.util.GraphSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class GraphServiceImpl<T> implements GraphService<T> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private Graph graph;
    @Autowired
    private GraphSearch graphSearch;

    @Override
    public Graph getGraph() {
        return graph;
    }

    @Override
    public void createVertex(T vertex) throws GraphException {
        graph.addVertex(vertex);
    }

    @Override
    public void removeVertex(T vertex) {
        graph.removeVertex(vertex);
    }

    @Override
    public void createEdge(T u, T v) {
        graph.addEdge(u, v);
    }

    @Override
    public void removeEdge(T u, T v) {
        graph.removeEdge(u,v);
    }

    @Override
    public ArrayList<T> findPath(T source, T destination) {
        if (!graph.containsVertex(source) || !graph.containsVertex(destination))
            throw new GraphException("Вершина не существует");
        return graphSearch.findPath(source, destination, graph);
    }

    @Override
    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(formatter);
        return formatDateTime;
    }

    @Override
    public DataResponse handleErrors(BindingResult result) {
        List<String> errors = new ArrayList<>();
        DataResponse response = new DataResponse();
        response.setTimestamp(getCurrentTime());
        response.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setDescription("Произошла ошибка");
        for (Object object: result.getAllErrors()){
            ObjectError error = (ObjectError) object;
            errors.add(error.getCode());
        }
        response.setData(errors);
        return response;
    }

}
