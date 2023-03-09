package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.participant.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("정확히 2장의 카드를 초기에 받을 수 있다.")
    @Test
    void receiveInitialCardsTest() {
        Cards cards = new Cards();
        assertAll(
                () -> assertDoesNotThrow(() -> cards.receiveInitialCards(
                        List.of(new Card(Value.KING, Shape.SPADE), new Card(Value.QUEEN, Shape.HEART)))),
                () -> assertThatThrownBy(() -> cards.receiveInitialCards(
                        List.of(new Card(Value.KING, Shape.CLOVER))))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @DisplayName("전체의 합을 계산할 수 있다.")
    @Test
    void calculateScoreTest() {
        Cards cards = setCards();

        assertThat(cards.calculateScore()).isEqualTo(new Score(30));
    }

    @DisplayName("전체의 합이 21을 초과하면 Bust이다.")
    @Test
    void isBustTest() {
        Cards cards = setCards();

        assertTrue(cards.isBust());
    }

    private static Cards setCards() {
        Cards cards = new Cards();
        cards.receiveInitialCards(List.of(new Card(Value.KING, Shape.CLOVER), new Card(Value.KING, Shape.SPADE)));
        cards.receiveCard(new Card(Value.QUEEN, Shape.HEART));
        return cards;
    }
}
