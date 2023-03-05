package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Suit;

class SuitTest {
    @Test
    @DisplayName("하트 모양의 이름을 가져올 수 있다.")
    void getHeartValue() {
        String name = Suit.HEART.getValue();
        assertThat(name).isEqualTo("하트");
    }

    @Test
    @DisplayName("클로버 모양의 이름을 가져올 수 있다.")
    void getCloverValue() {
        String name = Suit.CLOVER.getValue();
        assertThat(name).isEqualTo("클로버");
    }

}
