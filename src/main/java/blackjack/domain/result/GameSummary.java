package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.participant.User;
import java.util.List;

public record GameSummary(String name, List<Card> cards, int score) {
    public static GameSummary from(User user) {
        return new GameSummary(
                user.getName(),
                user.cards(),
                user.getScore()
        );
    }
}
