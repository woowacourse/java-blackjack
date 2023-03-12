package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PlayerGameResult는 ")
class PlayerGameResultTest {

    @Test
    void 블랙잭_승리시_150퍼센트_수익이다() {
        //given

        //when
        int benefit = PlayerGameResult.BLACKJACK.calculateBenefit(10_000);

        //then
        assertThat(benefit).isEqualTo(15_000);
    }

    @Test
    void 승리시_수익은_100퍼센트이다_() {
        //given

        //when
        int benefit = PlayerGameResult.WIN.calculateBenefit(10_000);

        //then
        assertThat(benefit).isEqualTo(10_000);
    }

    @Test
    void 패배시_배팅금액만큼_잃는다() {
        //given

        //when
        int benefit = PlayerGameResult.LOSE.calculateBenefit(10_000);

        //then
        assertThat(benefit).isEqualTo(-10_000);
    }

    @Test
    void 무승부시_수익은_없다() {
        //given

        //when
        int benefit = PlayerGameResult.DRAW.calculateBenefit(10_000);

        //then
        assertThat(benefit).isEqualTo(0);
    }
}