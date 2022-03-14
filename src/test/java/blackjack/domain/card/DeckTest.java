package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DeckTest {

    @Test
    @DisplayName("덱에서 카드를 한 장 반환한다.")
    void drawCard() {
        Deck deck = new Deck(new CardGenerator(){
            @Override
            public Stack<Card> generate() {
                List<Card> cards = new ArrayList<>();
                cards.add(new Card(Type.HEART, Score.ACE));
                Stack<Card> deck = new Stack<>();
                deck.addAll(cards);
                return deck;
            }
        });
        Card card = deck.draw();

        assertThat(card).isEqualTo(new Card(Type.HEART, Score.ACE));
    }

    @Test
    @DisplayName("덱에서 draw된 카드는 서로 다르다.")
    void drawDifferentCard() {
        Deck deck = new Deck(new RandomGenerator());
        Card card1 = deck.draw();
        Card card2 = deck.draw();

        assertThat(card1).isNotEqualTo(card2);
    }
}
