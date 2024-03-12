package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;

public record Card(CardSymbol symbol, CardScore score) {
    public static List<Card> getAll() {
        return Arrays.stream(CardSymbol.values()).flatMap(cardSymbol ->
                        Arrays.stream(CardScore.values()).map(cardScore ->
                                new Card(cardSymbol, cardScore)))
                .toList();
    }

    List<Integer> getScore() {
        return score.get();
    }
}
