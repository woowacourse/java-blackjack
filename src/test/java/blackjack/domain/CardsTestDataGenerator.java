package blackjack.domain;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Suit.*;

import blackjack.domain.card.Card;
import java.util.List;

public class CardsTestDataGenerator {

    public static List<Card> generateCards() {
        return List.of(new Card(ACE, CLOVER), new Card(TWO, DIAMOND));
    }

    public static List<Card> generateBlackjack() {
        return List.of(new Card(JACK, CLOVER), new Card(ACE, CLOVER));
    }

    public static List<Card> generateTotalScoreGraterThan17Cards() {
        return List.of(new Card(JACK, HEART), new Card(SEVEN, SPADE));
    }

    public static List<Card> generateTotalScoreNotMoreThan16Cards() {
        return List.of(new Card(JACK, CLOVER), new Card(SIX, CLOVER));
    }
}
