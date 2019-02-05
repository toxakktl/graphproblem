package kz.bts.GraphProblem.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.bts.GraphProblem.exceptions.GraphException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * Класс для работы с графом
 * @param <T> Параметр любого типа
 */
@Component
public class Graph<T> {

    final static Logger logger = LogManager.getLogger(Graph.class);


    private final Map<T, ArrayList<T>> adjacencyList;

    @JsonIgnore
    private Class classType;


    public Graph(){
        adjacencyList = new HashMap<>();
    }


    public Map<T, ArrayList<T>> getAdjacencyList() {
        return adjacencyList;
    }

    public Class getClassType() {
        return classType;
    }

    /**
     * Добабляет новую вершину в граф
     * @param v
     */
    public void addVertex(T v) {
        if (containsVertex(v)){
            throw new GraphException("Вершина " + v + " уже есть в графе");
        }
        if (this.adjacencyList.size() == 0){
            classType = v.getClass();
        }
        this.adjacencyList.put(v, new ArrayList<>());
        logger.info("Вершина " + v + " добавлена.");
    }


    /**
     * Удаляет вершину с графа
     * @param v
     */
    public void removeVertex(T v){
        if (!containsVertex(v)){
            throw new GraphException("Вершины " + v + " нет в графе");
        }
        this.adjacencyList.remove(v);
        for (T u: getAllVertices()){
            this.adjacencyList.get(u).remove(v);
        }
        logger.info("Вершина " + v + " удалена.");
    }

    /**
     * Добавляет ребро между двумя вершинами
     * @param v
     * @param u
     */
    public void addEdge(T v, T u) {
        if (!containsVertex(v) || !containsVertex(u)){
            throw new GraphException("Заданная вершина не существует");
        }
        if (v.equals(u)){
            throw new GraphException("Вершины не могут быть одинаковыми.");
        }
        if (containsEdge(v, u)){
            throw new GraphException("Заданное ребро существует");
        }
        this.adjacencyList.get(v).add(u);
        this.adjacencyList.get(u).add(v);
        logger.info("Ребро " + v + "-" + u + " добавлено.");
    }

    /**
     * Удаляет ребро между двумя вершинами
     * @param v
     * @param u
     */
    public void removeEdge(T v, T u){
        if (!containsVertex(v) || !containsVertex(u)){
            throw new GraphException("Заданная вершина не существует");
        }
        if (!containsEdge(v, u)){
            throw new GraphException("Заданное ребро не существует");
        }
        this.adjacencyList.get(v).remove(u);
        this.adjacencyList.get(u).remove(v);
        logger.info("Ребро " + v + "-" + u + " удалено.");

    }

    /**
     * Возвращает соседние вершины
     * @param v
     * @return
     */
    public ArrayList<T> getNeighbours(T v){
        return this.adjacencyList.get(v);
    }

    /**
     * Проверяет наличие заданной вершины в графе
     * @param v
     * @return
     */
    public boolean containsVertex(T v){
        if (this.adjacencyList.containsKey(v))
            return true;
        return false;
    }

    /**
     * Проверяет наличие ребра в графе
     * @param v
     * @param u
     * @return
     */
    private boolean containsEdge(T v, T u){
        if (this.adjacencyList.get(v).contains(u) && this.adjacencyList.get(u).contains(v)){
            return true;
        }
        return false;
    }

    /**
     * Возвращает все вершины
     * @return
     */
    @JsonIgnore
    public Set<T> getAllVertices(){
        return this.adjacencyList.keySet();
    }

}
