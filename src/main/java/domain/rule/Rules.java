package domain.rule;

import java.util.List;

import domain.result.ResultType;
import domain.user.User;

public class Rules {

    private List<Rule> rules;

    public Rules(List<Rule> rules) {
        this.rules = rules;
    }

    public ResultType decideResultType(User first, User second) {
        return rules.stream()
                .filter(rule -> rule.condition(first, second))
                .findFirst()
                .get()
                .getResultType();
    }
}
