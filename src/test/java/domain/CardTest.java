package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 숫자 정상 반환 검증")
    void 카드_숫자_정상_반환_테스트() {
        //given
        Card card = new Card("2", "하트");

        //when
        int number = card.getNumber();

        //then
        assertThat(number).isEqualTo(2);
    }
}