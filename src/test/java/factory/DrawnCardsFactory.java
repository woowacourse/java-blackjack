package factory;

import domain.Card;
import domain.DrawnCards;
import domain.Type;
import domain.Value;
import java.util.List;

public class DrawnCardsFactory {

    public static DrawnCards createEmptyCards() {
        DrawnCards drawnCards = new DrawnCards(List.of());
        return drawnCards;
    }

    public static DrawnCards createStayCards() {
        Card card1 = new Card(Type.CLUB, Value.FIVE);
        Card card2 = new Card(Type.DIAMOND, Value.FIVE);

        DrawnCards drawnCards = new DrawnCards(List.of(card1, card2));
        return drawnCards;
    }

    public static DrawnCards createOverCards() {
        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.DIAMOND, Value.EIGHT);
        Card card3 = new Card(Type.SPADE, Value.EIGHT);

        DrawnCards drawnCards = new DrawnCards(List.of(card1, card2, card3));
        return drawnCards;
    }

    public static DrawnCards createBlackjackCards() {
        Card card1 = new Card(Type.CLUB, Value.ACE);
        Card card2 = new Card(Type.DIAMOND, Value.KING);

        DrawnCards drawnCards = new DrawnCards(List.of(card1, card2));
        return drawnCards;
    }
}
