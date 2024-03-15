package domain.card;

import static domain.card.CardRank.FIVE;
import static domain.card.CardRank.KING;
import static domain.card.CardRank.TWO;
import static domain.card.CardSuit.SPADE;

import java.util.List;

public class CardFixture {

    public static Card cardOf(CardRank rank) {
        return new Card(SPADE, rank);
    }

    public static Cards cardsOf22() {
        return Cards.from(List.of(cardOf(KING), cardOf(KING), cardOf(TWO)));
    }

    public static Cards cardsOf20() {
        return Cards.from(List.of(cardOf(KING), cardOf(KING)));
    }

    public static Cards cardsOf15() {
        return Cards.from(List.of(cardOf(KING), cardOf(FIVE)));
    }
}
