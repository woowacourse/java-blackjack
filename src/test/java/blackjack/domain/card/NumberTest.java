package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberTest {

    @DisplayName("카드 이름 테스트")
    @Test
    void getNameTest() {
        assertThat(Number.ACE.getName()).isEqualTo("A");
        assertThat(Number.TWO.getName()).isEqualTo("2");
        assertThat(Number.THREE.getName()).isEqualTo("3");
        assertThat(Number.FOUR.getName()).isEqualTo("4");
        assertThat(Number.FIVE.getName()).isEqualTo("5");
        assertThat(Number.SIX.getName()).isEqualTo("6");
        assertThat(Number.SEVEN.getName()).isEqualTo("7");
        assertThat(Number.EIGHT.getName()).isEqualTo("8");
        assertThat(Number.NINE.getName()).isEqualTo("9");
        assertThat(Number.TEN.getName()).isEqualTo("10");
        assertThat(Number.JACK.getName()).isEqualTo("J");
        assertThat(Number.QUEEN.getName()).isEqualTo("Q");
        assertThat(Number.KING.getName()).isEqualTo("K");
    }

    @DisplayName("카드 점수 테스트")
    @Test
    void getScoreTest() {
        assertThat(Number.ACE.getScore()).isEqualTo(11);
        assertThat(Number.TWO.getScore()).isEqualTo(2);
        assertThat(Number.THREE.getScore()).isEqualTo(3);
        assertThat(Number.FOUR.getScore()).isEqualTo(4);
        assertThat(Number.FIVE.getScore()).isEqualTo(5);
        assertThat(Number.SIX.getScore()).isEqualTo(6);
        assertThat(Number.SEVEN.getScore()).isEqualTo(7);
        assertThat(Number.EIGHT.getScore()).isEqualTo(8);
        assertThat(Number.NINE.getScore()).isEqualTo(9);
        assertThat(Number.TEN.getScore()).isEqualTo(10);
        assertThat(Number.JACK.getScore()).isEqualTo(10);
        assertThat(Number.QUEEN.getScore()).isEqualTo(10);
        assertThat(Number.KING.getScore()).isEqualTo(10);
    }
}