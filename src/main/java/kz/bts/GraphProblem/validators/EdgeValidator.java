package kz.bts.GraphProblem.validators;

import kz.bts.GraphProblem.data.Graph;
import kz.bts.GraphProblem.data.request.EdgeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Валидатор для типа - ребро
 */
@Component
public class EdgeValidator implements Validator {

    @Autowired
    private Graph graph;

    @Override
    public boolean supports(Class<?> clazz) {
        return EdgeRequest.class.equals(clazz);
    }

    /**
     * Проверка на null, и проверка на тип полей, с которыми работает граф.
     * При первом добавлении элемента в граф, задается его тип. И граф работает только с этим типом
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        EdgeRequest request = (EdgeRequest) target;
        if (request.getSrc() == null && request.getDst() == null) {
            errors.rejectValue("src", "Вершины SRC и DST не могут быть пустыми значением");
            return;
        }
        if (request.getSrc() == null) {
            errors.rejectValue("src", "Вершина SRC не может быть пустым значением");
            return;
        }
        if (request.getDst() == null) {
            errors.rejectValue("dst", "Вершина DST не может быть пустым значением");
            return;
        }
        if (graph.getAdjacencyList().size() > 0) {
            if (!request.getSrc().getClass().equals(graph.getClassType())) {
                errors.rejectValue("src", "Ошибка типов. Ожидается тип входящего поля SRC: " + graph.getClassType().getName());
            }
            if (!request.getDst().getClass().equals(graph.getClassType())) {
                errors.rejectValue("dst", "Ошибка типов. Ожидается тип входящего поля DST: " + graph.getClassType().getName());
            }
        }
    }
}
