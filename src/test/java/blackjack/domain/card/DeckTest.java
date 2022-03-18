package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("카드 뭉치에서 카드 한 장을 꺼낸다.")
    void getCard() {
        Deck deck = new Deck();

        assertThat(deck.draw()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드 뭉치의 카드가 52장인지 확인한다.")
    void draw52() {
        Deck deck = new Deck();
        assertThatCode(() -> {
            for (int i = 0; i < 52; i++) {
                deck.draw();
            }
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드 뭉치가 비어있을때 드로우를 하면 예외를 발생한다.")
    void throwExceptionDrawEmptyCard() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatIllegalArgumentException()
                .isThrownBy(deck::draw)
                .withMessage("더 이상 꺼낼 카드가 존재하지 않습니다.");
    }
}
