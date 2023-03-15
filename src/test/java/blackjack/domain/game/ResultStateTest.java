package blackjack.domain.game;

import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.dto.ResultDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.CardConstant.*;

class ResultStateTest {

    @Test
    @DisplayName("블랙잭 상태를 제대로 가져오는지 확인")
    void getStateForBlackjack() {
        final ResultDto first = ResultDto.from(
                Player.of(Name.from("pobi"), List.of(DIAMOND_ACE, DIAMOND_TEN)));
        final ResultDto second = ResultDto.from(
                Player.of(Name.from("crong"), List.of(HEART_TEN, HEART_EIGHT)));

        Assertions.assertThat(ResultState.getState(first, second)).isEqualTo(ResultState.BLACKJACK);
    }

    @Test
    @DisplayName("승리 상태를 제대로 가져오는지 확인")
    void getStateForWin() {
        final ResultDto first = ResultDto.from(
                Player.of(Name.from("pobi"), List.of(DIAMOND_TEN, DIAMOND_NINE, DIAMOND_TWO)));
        final ResultDto second = ResultDto.from(
                Player.of(Name.from("crong"), List.of(HEART_TEN, HEART_EIGHT)));

        Assertions.assertThat(ResultState.getState(first, second)).isEqualTo(ResultState.WIN);
    }

    @Test
    @DisplayName("무승부 상태를 제대로 가져오는지 확인")
    void getStateForTie() {
        final ResultDto first = ResultDto.from(
                Player.of(Name.from("pobi"), List.of(DIAMOND_ACE, DIAMOND_TEN)));
        final ResultDto second = ResultDto.from(
                Player.of(Name.from("crong"), List.of(SPACE_ACE, HEART_TEN)));

        Assertions.assertThat(ResultState.getState(first, second)).isEqualTo(ResultState.TIE);
    }

    @Test
    @DisplayName("패배 상태를 제대로 가져오는지 확인")
    void getStateForLose() {
        final ResultDto first = ResultDto.from(
                Player.of(Name.from("pobi"), List.of(DIAMOND_NINE)));
        final ResultDto second = ResultDto.from(
                Player.of(Name.from("crong"), List.of(HEART_TEN)));

        Assertions.assertThat(ResultState.getState(first, second)).isEqualTo(ResultState.LOSE);
    }

    @Test
    @DisplayName("게임 결과가 블랙잭일 때, 베팅 금액의 1.5배를 반환하는지 확인")
    void calculateProfitTestByBlackjack() {
        // given
        final Betting betting = Betting.from(10000);
        final Betting expected = Betting.from(15000);

        // when
        final Betting actual = ResultState.BLACKJACK.calculateProfit(betting.getValue());

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("게임 결과가 승리일 때, 베팅 금액의 1배를 반환하는지 확인")
    void calculateProfitTestByWin() {
        // given
        final Betting betting = Betting.from(10000);
        final Betting expected = Betting.from(10000);

        // when
        final Betting actual = ResultState.WIN.calculateProfit(betting.getValue());

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }


    @Test
    @DisplayName("게임 결과가 무승부일 때, 베팅 금액의 0배를 반환하는지 확인")
    void calculateProfitTestByTie() {
        // given
        final Betting betting = Betting.from(10000);
        final Betting expected = Betting.from(0);

        // when
        final Betting actual = ResultState.TIE.calculateProfit(betting.getValue());

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }


    @Test
    @DisplayName("게임 결과가 패배일 때, 베팅 금액의 -1배를 반환하는지 확인")
    void calculateProfitTestByLose() {
        // given
        final Betting betting = Betting.from(10000);
        final Betting expected = Betting.from(-10000);

        // when
        final Betting actual = ResultState.LOSE.calculateProfit(betting.getValue());

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
