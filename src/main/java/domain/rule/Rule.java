package domain.rule;

import domain.result.ResultType;
import domain.user.User;

public interface Rule {

    Boolean condition(User first, User second);

    ResultType getResultType();
}
