package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.fixture.MoneyFixture;
import domain.fixture.PlayerNameFixture;
import domain.participant.PlayerName;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerBetMoneyTest {

    private static Stream<Arguments> playerNameTestArguments() {
        return Stream.of(
                Arguments.of(
                        PlayerNameFixture.playerNameFirst,
                        PlayerNameFixture.playerNameFirst, true
                ),
                Arguments.of(
                        PlayerNameFixture.playerNameFirst,
                        PlayerNameFixture.playerNameSecond,
                        false
                )
        );
    }

    @ParameterizedTest
    @DisplayName("배팅 금액의 주인과 플레이어 이름과 같다면 true, 다르다면 false 반환한다.")
    @MethodSource("playerNameTestArguments")
    void should_return_true_when_same_name(PlayerName playerName, PlayerName comparePlayerName, boolean expected) {
        // given
        PlayerBetMoney playerBetMoney = new PlayerBetMoney(playerName, MoneyFixture.money10000);

        // when
        boolean isSameName = playerBetMoney.isSameName(comparePlayerName);

        // then
        assertThat(isSameName).isEqualTo(expected);
    }

    @Test
    @DisplayName("배팅 금액을 반환한다.")
    void should_return_bet_money_value() {
        // given
        PlayerBetMoney playerBetMoney = new PlayerBetMoney(PlayerNameFixture.playerNameFirst, MoneyFixture.money10000);

        // when
        int betMoneyValue = playerBetMoney.getMoneyValue();

        // then
        assertThat(betMoneyValue).isEqualTo(10000);
    }
}
