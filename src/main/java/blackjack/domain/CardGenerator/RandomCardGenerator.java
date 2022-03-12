package blackjack.domain.CardGenerator;

import blackjack.domain.Denomination;
import blackjack.domain.PlayingCard;
import blackjack.domain.Suit;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class RandomCardGenerator implements CardGenerator{
    public Stack<PlayingCard> generate() {
        return Arrays.stream(Suit.values())
            .filter(suit -> !suit.equals(Suit.BURST))
            .flatMap(suit -> Arrays.stream(Denomination.values())
                .filter(denomination -> !denomination.equals(Denomination.BURST))
                .map(denomination -> PlayingCard.of(suit, denomination)))
            .collect(Collectors.toCollection(Stack::new));
    }
}
