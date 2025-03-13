package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.fixture.DealerFixture;
import blackjack.fixture.PlayerFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerProfitsTest {

    @Test
    @DisplayName("플레이어가 초기카드로 블랙잭을 만들 경우 이익을 구할 수 있다.")
    void canCreateWhenPlayerBlackjackWithInitialCard() {
        Dealer dealer = DealerFixture.createBust();
        Player player = PlayerFixture.createBlackJackWithInitialHand("플레이어");

        PlayerProfits playerProfits = new PlayerProfits(dealer, List.of(player));

        PlayerProfit playerProfit = playerProfits.getPlayerProfits().getFirst();
        assertThat(playerProfit.getNickname()).isEqualTo("플레이어");
        assertThat(playerProfit.getProfit()).isEqualTo((int) (player.getBettingAmount() * 1.5));
    }

    @ParameterizedTest
    @DisplayName("딜러가 버스트일 경우 플레이어의 수익을 구할 수 있다.")
    @MethodSource()
    void canCreateWhenDealerBust(Player player, int expectedProfit) {
        Dealer dealer = DealerFixture.createBust();

        PlayerProfits playerProfits = new PlayerProfits(dealer, List.of(player));

        PlayerProfit playerProfit = playerProfits.getPlayerProfits().getFirst();
        assertThat(playerProfit.getNickname()).isEqualTo(player.getNickname());
        assertThat(playerProfit.getProfit()).isEqualTo(expectedProfit);
    }

    static Stream<Arguments> canCreateWhenDealerBust() {
        return Stream.of(
                Arguments.of(PlayerFixture.createBust("플레이어1"),
                        PlayerFixture.DEFAULT_BETTING_AMOUNT.getValue()),
                Arguments.of(PlayerFixture.createBlackJackWithInitialHand("플레이어2"),
                        (int) (PlayerFixture.DEFAULT_BETTING_AMOUNT.getValue() * 1.5)),
                Arguments.of(PlayerFixture.createBlackJackWithFinalHand("플레이어3"),
                        PlayerFixture.DEFAULT_BETTING_AMOUNT.getValue()),
                Arguments.of(PlayerFixture.createLessThanBlackjack("플레이어4"),
                        PlayerFixture.DEFAULT_BETTING_AMOUNT.getValue())
        );
    }

    @ParameterizedTest
    @DisplayName("승패에 따라 플레이어의 수익을 구할 수 있다.")
    @MethodSource()
    void canCreateByWinningType(Dealer dealer, Player player, int expectedProfit) {
        WinningType.parse(dealer.getPoint(), player.getPoint());

        PlayerProfits playerProfits = new PlayerProfits(dealer, List.of(player));

        PlayerProfit playerProfit = playerProfits.getPlayerProfits().getFirst();
        assertThat(playerProfit.getNickname()).isEqualTo(player.getNickname());
        assertThat(playerProfit.getProfit()).isEqualTo(expectedProfit);
    }

    static Stream<Arguments> canCreateByWinningType() {
        return Stream.of(
                Arguments.of(
                        DealerFixture.createBlackJackWithFinalHand(),
                        PlayerFixture.createBlackJackWithFinalHand("플레이어"),
                        PlayerFixture.DEFAULT_BETTING_AMOUNT.getValue() * 0),
                Arguments.of(
                        DealerFixture.createBlackJackWithFinalHand(),
                        PlayerFixture.createLessThanBlackjack("플레이어"),
                        PlayerFixture.DEFAULT_BETTING_AMOUNT.getValue() * -1),
                Arguments.of(
                        DealerFixture.createLessThanBlackjack(),
                        PlayerFixture.createBlackJackWithFinalHand("플레이어"),
                        PlayerFixture.DEFAULT_BETTING_AMOUNT.getValue() * 1)
        );
    }

    @Test
    @DisplayName("딜러의 수익을 계산할 수 있다.")
    void canCalculateDealerProfit() {
        Dealer dealer = DealerFixture.createLessThanBlackjack();
        List<Player> players = List.of(
                PlayerFixture.createBlackJackWithFinalHand("플레이어"),
                PlayerFixture.createBlackJackWithFinalHand("플레이어"),
                PlayerFixture.createBust("플레이어"));

        PlayerProfits playerProfits = new PlayerProfits(dealer, players);

        assertThat(playerProfits.calculateDealerProfit())
                .isEqualTo(PlayerFixture.DEFAULT_BETTING_AMOUNT.getValue() * -1);
    }
}