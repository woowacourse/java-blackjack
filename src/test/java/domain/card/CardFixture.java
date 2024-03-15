package domain.card;

import static domain.card.Rank.FIVE;
import static domain.card.Rank.KING;
import static domain.card.Rank.TWO;
import static domain.card.Suit.SPADE;

import java.util.List;

public class CardFixture {

    public static Card cardOf(Rank rank) {
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
