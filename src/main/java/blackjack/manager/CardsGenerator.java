package blackjack.manager;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
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
