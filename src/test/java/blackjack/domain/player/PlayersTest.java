package blackjack.domain.player;

import static blackjack.domain.CardsTestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

class PlayersTest {

    @DisplayName("플레이어가 1명 이상인지 확인한다.")
    @ParameterizedTest
    @ArgumentsSource(PlayerArgumentsProvider.class)
    void 플레이어_1명이상_확인_정상(List<Player> players) {
        assertDoesNotThrow(() -> new Players(players));
    }

    @DisplayName("1명 이하일 때 에러")
    @Test
    void 플레이어_Empty() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Players(List.of()));
    }


    @DisplayName("플레이어들의 최종 수익울 계산한다.")
    @ParameterizedTest
    @ArgumentsSource(PlayerArgumentsProvider.class)
    void 플레이어들_최종수익_계산_정상(List<Player> value) {
        // given
        Players players = new Players(value);
        Dealer dealer = new Dealer(generateTotalScoreGraterThan17Cards());
        // when
        int beforeTotalBettingMoney = players.getValue().stream()
                .mapToInt(player -> player.getBettingMoney())
                .sum();

        players.createPlayerResult(dealer);
        // then
        int afterTotalBettingMoney = players.getValue().stream()
                .mapToInt(player -> player.getBettingMoney())
                .sum();
        assertThat(afterTotalBettingMoney).isEqualTo((int) (beforeTotalBettingMoney * 1.5));
    }

    @DisplayName("딜러의 최종 수익울 계산한다.")
    @ParameterizedTest
    @ArgumentsSource(PlayerArgumentsProvider.class)
    void 딜러_최종수익_계산_정상(List<Player> value) {
        // given
        Players players = new Players(value);
        Dealer dealer = new Dealer(generateTotalScoreGraterThan17Cards());
        // when
        int beforeTotalBettingMoney = players.getValue().stream()
                .mapToInt(player -> player.getBettingMoney())
                .sum();

        players.createPlayerResult(dealer);
        players.createDealerResult(dealer);
        // then

        assertThat(dealer.getBettingMoney()).isEqualTo((int) (beforeTotalBettingMoney * -1.5));
    }
}