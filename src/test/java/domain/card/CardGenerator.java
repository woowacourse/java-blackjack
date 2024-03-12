package domain.card;

import static domain.card.CardRank.FIVE;
import static domain.card.CardRank.KING;
import static domain.card.CardRank.TWO;
import static domain.card.CardSuit.SPADE;

import java.util.List;

public interface CardGenerator {

    static Card cardOf(CardRank rank) {
        return new Card(SPADE, rank);
    }

    static Cards cardsOf22() {
        return Cards.from(List.of(cardOf(KING), cardOf(KING), cardOf(TWO)));
    }

    static Cards cardsOf20() {
        return Cards.from(List.of(cardOf(KING), cardOf(KING)));
    }

    static Cards cardsOf15() {
        return Cards.from(List.of(cardOf(KING), cardOf(FIVE)));
    }
}
