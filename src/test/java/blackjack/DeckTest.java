package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DeckTest {
    @Test
    @DisplayName("카드 뭉치에서 카드 한 장을 꺼낸다.")
    void getCard() {
        Deck deck = Deck.create();

        assertThat(deck.draw()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드 뭉치의 카드가 52장인지 확인한다.")
    void draw52() {
        Deck deck = Deck.create();
        assertThatCode(() -> {
            for (int i = 0; i < 52; i++) {
                deck.draw();
            }
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드 뭉치가 비어있을때 드로우를 하면 예외를 발생한다.")
    void throwExceptionDrawEmptyCard() {
        Deck deck = Deck.create();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatIllegalArgumentException()
                .isThrownBy(deck::draw)
                .withMessageContaining("더 이상 꺼낼 카드가 존재하지 않습니다.");
    }
}
