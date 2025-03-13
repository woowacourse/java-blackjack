package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import java.util.List;

public class CardFixture {

    public static Card make(CardValue cardValue) {
        return new Card(CardShape.HEART, cardValue);
    }

    public static List<Card> makeLessThanBlackjack() {
        return List.of(
                CardFixture.make(CardValue.SEVEN),
                CardFixture.make(CardValue.KING));
    }

    public static List<Card> makeBlackjackByTwoCard() {
        return List.of(
                CardFixture.make(CardValue.ACE),
                CardFixture.make(CardValue.KING));
    }

    public static List<Card> makeBlackjackOverTwoCard() {
        return List.of(
                CardFixture.make(CardValue.ACE),
                CardFixture.make(CardValue.QUEEN),
                CardFixture.make(CardValue.KING));
    }

    public static List<Card> makeBust() {
        return List.of(
                CardFixture.make(CardValue.JACK),
                CardFixture.make(CardValue.KING),
                CardFixture.make(CardValue.QUEEN));
    }
}
