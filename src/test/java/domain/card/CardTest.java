package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드의 타입과 숫자를 입력했을 때 카드 객체가 정상적으로 생성된다.")
    void makeCard() {
        Suit suit = Suit.CLOVER;
        Denomination denomination = Denomination.A;

        assertDoesNotThrow(() -> Card.of(suit, denomination));
    }

    @Test
    @DisplayName("카드가 A인지 판단한다.")
    void isATest() {

        Card card = Card.of(Suit.CLOVER, Denomination.A);

        assertThat(card.isA()).isTrue();
    }
}

