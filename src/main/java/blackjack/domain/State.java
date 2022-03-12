package blackjack.domain;

import blackjack.domain.card.Cards;
import java.util.Arrays;
import java.util.function.Predicate;

public enum State {

    BUST(cards -> cards.calculateScore().getValue() > 21),
    BLACKJACK(cards -> cards.calculateScore().getValue() == 21 && cards.getCards().size() == 2),
    NOTHING(cards -> true),
    ;

    private final Predicate<Cards> isMatch;

    State(Predicate<Cards> isMatch) {
        this.isMatch = isMatch;
    }

    public static State from(Cards cards) {
        return Arrays.stream(values())
                .filter(state -> state.isMatch.test(cards))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 조건에 맞지 않습니다."));
    }
}
