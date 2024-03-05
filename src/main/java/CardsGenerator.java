import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardsGenerator {

    public List<Card> generateRandomCards() {
        List<Card> cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(cardNumber, cardShape));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }
}
