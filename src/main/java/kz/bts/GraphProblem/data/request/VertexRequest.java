package kz.bts.GraphProblem.data.request;

/**
 * Дженерик объект для работы с РЕСТ операциями, которые работают в вершинами графа
 * @param <T>
 */
public class VertexRequest<T> {
    T vertex;

    public T getVertex() {
        return vertex;
    }
}
