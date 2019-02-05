package kz.bts.GraphProblem.services;

import kz.bts.GraphProblem.data.Graph;
import kz.bts.GraphProblem.data.response.DataResponse;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * Работа с графом осуществляется через данный класс
 * @param <T>
 */
public interface GraphService<T> {

    /**
     * Текущий граф
     * @return
     */
    Graph getGraph();

    /**
     * Создать вершину
     * @param u
     */
    void createVertex(T u);

    /**
     * Удалить вершину
     * @param v
     */
    void removeVertex(T v);

    /**
     * Создать ребро u-v
     * @param u
     * @param v
     */
    void createEdge(T u, T v);

    /**
     * Удалить ребро u-v
     * @param u
     * @param v
     */
    void removeEdge(T u, T v);

    /**
     * Найти путь между u-v
     * @param u
     * @param v
     * @return
     */
    List<T> findPath(T u, T v);

    /**
     * Форматированное время
     * @return
     */
    String getCurrentTime();

    /**
     * Обработчик ошибок со spring valdiator-ов
     * @param result
     * @return
     */
    DataResponse handleErrors(BindingResult result);
}
