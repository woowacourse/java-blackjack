package blackjack.domain.card;

import java.util.List;

public record Card(CardSuit suit, CardScore score) {

    List<Integer> getScore() {
        return score.get();
    }
}
