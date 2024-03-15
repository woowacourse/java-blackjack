package domain.card;

import static domain.card.Rank.ACE;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.FOUR;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;
import static domain.card.Rank.TEN;
import static domain.card.Rank.TWO;
import static domain.card.Suit.SPADE;

import java.util.List;

public class CardFixture {

    public static Card cardOf(Rank rank) {
        return new Card(SPADE, rank);
    }

    public static Cards cardsOfBlackjack() {
        return Cards.from(List.of(cardOf(ACE), cardOf(KING)));
    }

    public static Cards cardsOfSoft22() {
        return Cards.from(List.of(cardOf(ACE), cardOf(ACE), cardOf(TEN)));
    }

    public static Cards cardsOfSoft20() {
        return Cards.from(List.of(cardOf(ACE), cardOf(NINE)));
    }

    public static Cards cardsOfSoft15() {
        return Cards.from(List.of(cardOf(ACE), cardOf(FOUR)));
    }

    public static Cards cardsOfSoft12() {
        return Cards.from(List.of(cardOf(ACE), cardOf(ACE)));
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
