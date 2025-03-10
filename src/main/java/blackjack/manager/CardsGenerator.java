package blackjack.manager;

import blackjack.domain.CardRank;
import blackjack.domain.CardSuit;
import blackjack.domain.Card;
import java.util.Arrays;
import java.util.List;

public class CardsGenerator {

    public List<Card> generate() {
        return Arrays.stream(CardSuit.values())
                .flatMap(suit -> Arrays.stream(CardRank.values())
                        .map(rank -> new Card(suit, rank)))
                .toList();
    }
}
