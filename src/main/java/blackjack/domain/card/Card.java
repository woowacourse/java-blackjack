package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;

public record Card(CardSuit suit, CardScore score) {
    public static List<Card> getAll() {
        return Arrays.stream(CardSuit.values()).flatMap(cardSymbol ->
                        Arrays.stream(CardScore.values()).map(cardScore ->
                                new Card(cardSymbol, cardScore)))
                .toList();
    }

    List<Integer> getScore() {
        return score.get();
    }
}
