package blackjackgame.domain.game;

import static blackjackgame.domain.Fixtures.aceKing;
import static blackjackgame.domain.Fixtures.aceThreeFour;
import static blackjackgame.domain.Fixtures.eightNine;
import static blackjackgame.domain.Fixtures.jackKingAce;
import static blackjackgame.domain.Fixtures.jackKingNine;
import static blackjackgame.domain.Fixtures.sixFour;
import static org.assertj.core.api.Assertions.*;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.user.Bet;
import blackjackgame.domain.user.Dealer;
import blackjackgame.domain.user.DealerStatus;
import blackjackgame.domain.user.Names;
import blackjackgame.domain.user.Player;
import blackjackgame.domain.user.PlayerStatus;
import blackjackgame.domain.user.Players;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class RefereeTest {

    private Referee referee;
    private Player player;
    private Dealer dealer;
    private int betAmount;

    @BeforeEach
    void setUp() {
        betAmount = 10_000;
        Players players = new Players(new Names(List.of("민트")), List.of(new Bet(betAmount)));
        player = players.getPlayers().get(0);
        dealer = new Dealer();
        referee = new Referee(players, dealer);
    }

    @DisplayName("플레이어가 버스트일 경우, 딜러는 어떤 패를 가져도 승리한다.")
    @ParameterizedTest
    @MethodSource("generateEveryCaseOfHands")
    void judgeWinnerTest_playerBust(List<Card> cards) {
        dealer.receiveCards(cards);
        player.receiveCards(jackKingNine);

        referee.judgeWinner();

        assertThat(player.getStatus()).isSameAs(PlayerStatus.BUST);
        assertThat(player.getProfitAmount()).isEqualTo(-betAmount);
    }

    static Stream<List<Card>> generateEveryCaseOfHands() {
        return Stream.of(
                jackKingNine,
                sixFour,
                aceThreeFour,
                aceKing
        );
    }

    @DisplayName("플레이어는 버스트가 아니고 딜러만 버스트일 경우 플레이어가 승리한다.")
    @Test
    void judgeWinnerTest_playerNormalAndDealerBust() {
        dealer.receiveCards(jackKingNine);
        player.receiveCards(eightNine);

        referee.judgeWinner();

        assertThat(dealer.getStatus()).isSameAs(DealerStatus.BUST);
        assertThat(player.getStatus()).isNotSameAs(PlayerStatus.BUST);
        assertThat(player.getProfitAmount()).isEqualTo(betAmount);
    }

    @DisplayName("딜러와 플레이어 모두 버스트가 아니고, 딜러의 점수가 플레이어의 점수보다 높으면 딜러가 승리한다.")
    @Test
    void judgeWinnerTest_dealerWin() {
        dealer.receiveCards(eightNine);
        player.receiveCards(sixFour);

        referee.judgeWinner();

        assertThat(dealer.getStatus()).isNotSameAs(DealerStatus.BUST);
        assertThat(player.getStatus()).isNotSameAs(PlayerStatus.BUST);
        assertThat(player.getProfitAmount()).isEqualTo(-betAmount);
    }

    @DisplayName("딜러와 플레이어 모두 버스트가 아니고, 플레이어의 점수가 딜러의 점수보다 높으면 플레이어가 승리한다.")
    @Test
    void judgeWinnerTest_playerWin() {
        dealer.receiveCards(sixFour);
        player.receiveCards(eightNine);

        referee.judgeWinner();

        assertThat(dealer.getStatus()).isNotSameAs(DealerStatus.BUST);
        assertThat(player.getStatus()).isNotSameAs(PlayerStatus.BUST);
        assertThat(player.getProfitAmount()).isEqualTo(betAmount);
    }

    @DisplayName("딜러와 플레이어 모두 버스트가 아니며 점수가 같고, 블랙잭이 아니면 무승부다.")
    @Test
    void judgeWinnerTest_bothNormalSameScore() {
        player.receiveCards(eightNine);
        dealer.receiveCards(eightNine);

        referee.judgeWinner();

        assertThat(player.getScore()).isEqualTo(dealer.getScore());
        assertThat(player.getProfitAmount()).isEqualTo(0);
    }

    @DisplayName("딜러와 플레이어가 동점이지만, 플레이어만 블랙잭이면 플레이어가 승리하며 배팅금액 1.5배의 수익을 얻는다.")
    @Test
    void judgeWinnerTest_playerWinWithBlackJack() {
        player.receiveCards(aceKing);
        dealer.receiveCards(jackKingAce);

        referee.judgeWinner();

        assertThat(player.getScore()).isEqualTo(dealer.getScore());
        assertThat(player.getProfitAmount()).isEqualTo((int) (betAmount * 1.5));
    }

    @DisplayName("딜러와 플레이어가 동점이지만, 딜러만 블랙잭이면 플레이어는 패배한다.")
    @Test
    void judgeWinnerTest_dealerWinWithBlackJack() {
        player.receiveCards(jackKingAce);
        dealer.receiveCards(aceKing);

        referee.judgeWinner();

        assertThat(player.getScore()).isEqualTo(dealer.getScore());
        assertThat(player.getProfitAmount()).isEqualTo(-betAmount);
    }
}
