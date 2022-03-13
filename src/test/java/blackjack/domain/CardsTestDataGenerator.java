package blackjack.domain;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Suit.*;

import blackjack.domain.card.Card;
import java.util.List;

public class CardsTestDataGenerator {

    public static List<Card> generateCards() {
        return List.of(Card.of(ACE, CLOVER), Card.of(KING, DIAMOND));
    }

    public static List<Card> generateTotalScoreGraterThan21Cards() {
        return List.of(Card.of(JACK, CLOVER), Card.of(QUEEN, CLOVER), Card.of(TWO, DIAMOND));
    }

    public static List<Card> generateTotalScoreNotMoreThan21Cards() {
        return List.of(Card.of(JACK, CLOVER), Card.of(ACE, CLOVER));
    }

    public static List<Card> generateTotalScoreGraterThan17Cards() {
        return List.of(Card.of(JACK, HEART), Card.of(SEVEN, SPADE));
    }

    public static List<Card> generateTotalScoreNotMoreThan16Cards() {
        return List.of(Card.of(JACK, CLOVER), Card.of(SIX, CLOVER));
    }
}
