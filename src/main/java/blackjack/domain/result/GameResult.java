package blackjack.domain.result;

import blackjack.domain.participant.User;

public record GameResult(String name, int profit) {
    public static GameResult from(User user, int profit) {
        return new GameResult(
                user.getName(),
                profit
        );
    }
}
