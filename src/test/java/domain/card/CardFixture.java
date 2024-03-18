package domain.card;

import static domain.card.Rank.ACE;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.FOUR;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;
import static domain.card.Rank.TWO;
import static domain.card.Suit.SPADE;

import java.util.Arrays;
import java.util.List;

public class CardFixture {

    public static Card cardOf(Rank rank) {
        return new Card(SPADE, rank);
    }

    public static List<Card> cardsOf(Rank... ranks) {
        return Arrays.stream(ranks)
                .map(CardFixture::cardOf)
                .toList();
    }

    public static List<Card> cardsOfBlackjack() {
        return List.of(cardOf(ACE), cardOf(KING));
    }

    public static List<Card> cardsOfSoft20() {
        return List.of(cardOf(ACE), cardOf(NINE));
    }

    public static List<Card> cardsOfSoft15() {
        return List.of(cardOf(ACE), cardOf(FOUR));
    }

    public static List<Card> cardsOfSoft12() {
        return List.of(cardOf(ACE), cardOf(ACE));
    }

    public static List<Card> cardsOf22() {
        return List.of(cardOf(KING), cardOf(KING), cardOf(TWO));
    }

    public static List<Card> cardsOf20() {
        return List.of(cardOf(KING), cardOf(KING));
    }

    public static List<Card> cardsOf15() {
        return List.of(cardOf(KING), cardOf(FIVE));
    }
}
