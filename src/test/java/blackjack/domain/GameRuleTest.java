package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameRuleTest {

    @ParameterizedTest
    @DisplayName("버스트 여부를 확인할 수 있다.")
    @CsvSource({"21,false", "22,true", "20,false"})
    void canCheckIsBust(int point, boolean isBust) {
        // given
        // when
        // then
        assertThat(GameRule.isBurst(point)).isEqualTo(isBust);
    }

    @ParameterizedTest
    @DisplayName("딜러가 카드를 더 받을지 여부를 확인할 수 있다.")
    @CsvSource({"15,true", "16,true", "17,false"})
    void canCheckDealerHit(int point, boolean shouldHit) {
        // given
        // when
        // then
        assertThat(GameRule.shouldDrawCardToDealer(point)).isEqualTo(shouldHit);
    }


}