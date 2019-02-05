package kz.bts.GraphProblem.validators;

import kz.bts.GraphProblem.data.Graph;
import kz.bts.GraphProblem.data.request.VertexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Валидатор для типа - вершина
 */
@Component
public class VertexValidator implements Validator {

    @Autowired
    private Graph graph;

    @Override
    public boolean supports(Class<?> clazz) {
        return VertexRequest.class.equals(clazz);
    }

    /**
     * Проверка на null, и проверка на тип полей, с которыми работает граф.
     * При первом добавлении элемента в граф, задается его тип. И граф работает только с этим типом
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        VertexRequest request = (VertexRequest) target;
        if (request.getVertex() == null) {
            errors.rejectValue("vertex", "Вершина не может быть пустым значением.");
            return;
        }
        if (graph.getAdjacencyList().size() > 0) {
            if (!request.getVertex().getClass().equals(graph.getClassType())) {
                errors.rejectValue("vertex", "Ошибка типов. Ожидается тип входящего поля: " + graph.getClassType().getName());
            }
        }
    }
}
