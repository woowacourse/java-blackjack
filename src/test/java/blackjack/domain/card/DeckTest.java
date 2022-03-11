package blackjack.domain.card;

import static blackjack.domain.TestCardFixture.aceCard;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("덱이 정상적으로 생성되었는지 확인")
    void create() {
        Deck deck = Deck.createShuffledCards();
        List<Card> cards = deck.getCards();
        Card excepted = cards.get(0);
        Card draw = deck.draw();

        assertThat(draw).isEqualTo(excepted);
    }

    @Test
    @DisplayName("덱이 정상적으로 52장의 카드를 가지고 있는지 확인")
    void createFixedCards() {
        Deck deck = Deck.createShuffledCards();
        List<Card> cards = deck.getCards();

        assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드가 모두 소진되었을 때 예외를 발생한다")
    void cardEmptyException() {
        List<Card> cards = List.of(aceCard);
        Deck deck = new Deck(cards);
        deck.draw();

        assertThatThrownBy(deck::draw)
            .isInstanceOf(NoSuchElementException.class)
            .hasMessageContaining("[ERROR] 52장의 카드가 모두 소진되었습니다.");
    }
}
