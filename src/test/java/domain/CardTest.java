package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드가 숫자일 때 정상 반환 검증")
    void 카드_숫자_정상_반환_테스트() {
        //given
        Card card = new Card("2", "하트");

        //when
        int number = card.getNumber();

        //then
        assertThat(number).isEqualTo(2);
    }

    @Test
    @DisplayName("카드가 K일 때 정상 반환 검증")
    void 카드_K_정상_반환_테스트() {
        //given
        Card card = new Card("K", "하트");

        //when
        int number = card.getNumber();

        //then
        assertThat(number).isEqualTo(10);
    }

    @Test
    @DisplayName("카드가 A일 때 정상 반환 검증")
    void 카드_A_정상_반환_테스트() {
        //given
        Card card = new Card("A", "하트");

        //when
        int number = card.getNumber();

        //then
        assertThat(number).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 value가 정상 입력되지 않았을 때 에러 반환")
    void 카드_value_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new Card("14", "하트");
        });
    }

    @Test
    @DisplayName("카드 pattern이 정상 입력되지 않았을 때 에러 반환")
    void 카드_pattern_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new Card("10", "별");
        });
    }
}