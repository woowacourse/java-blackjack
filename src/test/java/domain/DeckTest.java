package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.constant.CardNumber;
import domain.constant.CardType;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("가장 마지막에 있는 카드를 반환한다.")
    void draw() {
        Card card = new Deck(List.of(Card.getCard(CardType.SPADE, CardNumber.ACE)))
                .draw();
        Assertions.assertAll(
                () -> assertThat(card.getCardType()).isEqualTo(CardType.SPADE),
                () -> assertThat(card.getCardNumber()).isEqualTo(CardNumber.ACE)
        );
    }

    @Test
    @DisplayName("뽑을 카드가 없으면 예외가 발생한다.")
    void drawException() {
        Deck deck = new Deck();
        assertThatThrownBy(() -> {
            for (int i = 0; i < 53; i++) {
                deck.draw();
            }
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("카드가 더이상 존재하지 않습니다.");
    }
}
