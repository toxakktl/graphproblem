package kz.bts.GraphProblem.data.response;

/**
 * Класс дополнительно к GeneralResponse добавляет объект
 * @param <T>
 */
public class DataResponse<T> extends GeneralResponse {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
