import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.constants.Score;
import domain.constants.Shape;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @DisplayName("딜러가 가지고 있는 카드가 16 초과이면 참을 반환한다.")
    @Test
    void isUpToThreshold() {
        final Deck deck = createDeck();
        int threshold = 16;

        Dealer dealer = new Dealer("딜러");
        for (int i = 0; i < deck.getTotalSize(); i++) {
            dealer.pickOneCard(deck);
        }

        boolean isUp = dealer.isUpToThreshold(threshold);
        assertTrue(isUp);
    }

    private Deck createDeck() {
        return new Deck(new ArrayList<>() {{
            add(new Card(Score.TEN, Shape.CLOVER));
            add(new Card(Score.SEVEN, Shape.CLOVER));
            add(new Card(Score.NINE, Shape.CLOVER));
        }});
    }
}
