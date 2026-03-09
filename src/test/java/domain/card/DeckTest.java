package domain.card;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("초기에 생성할 때, 완전한 카드 덱을 구성한다.")
    void createCardsTest() {
        Deck deck = new Deck();
        long cardSize = deck.getCards().stream().distinct().count();
        Assertions.assertThat(cardSize).isEqualTo(52);
    }

    @Test
    @DisplayName("초기 카드는 두 장씩 나누어준다.")
    void handOutCardsTest() {
        Deck deck = new Deck();
        List<Card> cards = deck.dealFirstHandCards();
        Assertions.assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 한 장 뽑는다.")
    void peekOneCardTest() {
        Deck deck = new Deck();
        Card card = deck.drawCard();
        Assertions.assertThat(deck.getCards().size()).isEqualTo(51);
        Assertions.assertThat(card).isInstanceOf(Card.class);
        Assertions.assertThat(card).isNotIn(deck.getCards());
    }
}
