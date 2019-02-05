package kz.bts.GraphProblem.util;

import kz.bts.GraphProblem.data.Graph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс для поиска пути между двумя вершинами. Используется BFS для обхода.
 *
 * @param <T>
 */
@Component
public class GraphSearch<T> {

    final Logger logger = LogManager.getLogger(GraphSearch.class);
    /**
     *
     */
    private ArrayList<T> fullPath = new ArrayList<>();


    public ArrayList<T> findPath(T source, T destination, Graph graph) {
        fullPath.clear();
        if (source.equals(destination)){
            fullPath.add(source);
            return fullPath;
        }
        logger.info("Ищем путь между вершинами " + source + " и " + destination);
        //Список путей
        ArrayList<T> path = new ArrayList<>();

        LinkedList<T> queue = new LinkedList<>();

        //Вершины, которые посетили
        ArrayDeque<T> visited = new ArrayDeque<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            T src = queue.poll();
            visited.offer(src);
            ArrayList<T> neighboursList = graph.getNeighbours(src);
            int index = 0;
            int neighboursSize = neighboursList.size();
            while (index != neighboursSize) {
                T neighbour = neighboursList.get(index);

                path.add(neighbour);
                path.add(src);

                if (neighbour.equals(destination)) {
                    return processPath(source, destination, path);
                } else {
                    if (!visited.contains(neighbour)) {
                        queue.offer(neighbour);
                    }
                }
                index++;
            }
        }
        return null;
    }

    private ArrayList<T> processPath(T src, T destination,
                                     ArrayList<T> path) {

        int index = path.indexOf(destination);
        T source = path.get(index + 1);

        fullPath.add(0, destination);

        if (source.equals(src)) {
            fullPath.add(0, src);
            return fullPath;
        } else {
            return processPath(src, source, path);
        }
    }

}
