package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("초기에 생성할 때, 완전한 카드 덱을 구성한다.")
    void createCardsTest() {
        Deck deck = new Deck();
        Set<Card> cardSet = new HashSet<>(deck.getCards());

        assertThat(cardSet.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드를 한 장 뽑는다.")
    void peekOneCardTest() {
        Deck deck = new Deck();
        Card card = deck.draw();

        assertThat(deck.getCards().size()).isEqualTo(51);
        assertThat(card).isInstanceOf(Card.class);
        assertThat(card).isNotIn(deck.getCards());
    }
}
