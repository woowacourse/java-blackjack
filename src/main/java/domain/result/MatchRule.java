package domain.result;

import domain.user.User;

public interface MatchRule {
    Result match(User player, User dealer);
}
