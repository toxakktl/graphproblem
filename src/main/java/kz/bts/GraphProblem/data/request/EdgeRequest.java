package kz.bts.GraphProblem.data.request;

/**
 * Дженерик объект для работы с РЕСТ операциями, которые работают в ребрами графа
 * @param <T>
 */
public class EdgeRequest<T> {
    T src;

    T dst;

    public T getSrc() {
        return src;
    }

    public T getDst() {
        return dst;
    }
}
