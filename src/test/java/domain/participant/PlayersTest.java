package domain.participant;

import static domain.GameResult.LOSE;
import static domain.GameResult.TIE;
import static domain.GameResult.WIN;
import static domain.participant.HandsTestFixture.blackJack;
import static domain.participant.HandsTestFixture.bustHands;
import static domain.participant.HandsTestFixture.noBustHands;
import static domain.participant.HandsTestFixture.sum10Size2;
import static domain.participant.HandsTestFixture.sum17Size3One;
import static domain.participant.HandsTestFixture.sum18Size2;
import static domain.participant.HandsTestFixture.sum19Size3Ace1;
import static domain.participant.HandsTestFixture.sum20Size2;
import static domain.participant.HandsTestFixture.sum20Size3;
import static domain.participant.HandsTestFixture.sum21Size3;
import static domain.amount.BetAmount.defaultBetAmount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.GameResult;
import domain.amount.BetAmount;
import domain.amount.EarnAmount;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("참가자 중 버스트 되지 않은 참가자가 있다면 isAllBust가 False를 반환한다.")
    void isAllBustFalse() {
        //given
        final Player bustPlayer = new Player(new Name("레디"), bustHands, defaultBetAmount);
        final Player noBustPlayer = new Player(new Name("제제"), noBustHands, defaultBetAmount);
        final Players players = new Players(List.of(bustPlayer, noBustPlayer));

        //when && then
        assertThat(players.isAllBust()).isFalse();
    }

    @Test
    @DisplayName("모든 참가자가 버스트되면 isAllBust가 True를 반환한다.")
    void isAllBustTrue() {
        //given
        final Player player1 = new Player(new Name("레디"), bustHands, defaultBetAmount);
        final Player player2 = new Player(new Name("제제"), bustHands, defaultBetAmount);
        final Player player3 = new Player(new Name("수달"), bustHands, defaultBetAmount);
        final Player player4 = new Player(new Name("피케이"), bustHands, defaultBetAmount);

        final Players players = new Players(List.of(player1, player2, player3, player4));

        //when && then
        assertThat(players.isAllBust()).isTrue();
    }

    @Test
    @DisplayName("참여자의 승패무를 판단한다.")
    void playerResult() {
        //given
        final Player loser = new Player(new Name("레디"), sum18Size2, defaultBetAmount);
        final Player winner = new Player(new Name("제제"), sum21Size3, defaultBetAmount);
        final Player tier = new Player(new Name("수달"), sum20Size3, defaultBetAmount);

        final Players players = new Players(List.of(loser, winner, tier));
        final Dealer dealer = new Dealer(sum20Size3);

        //when & then
        final Map<Player, GameResult> expected = Map.of(loser, LOSE, winner, WIN, tier, TIE);
        assertThat(players.getPlayersResult(dealer)).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 버스트일때 참여자가 버스트가 아니면 WIN")
    void all() {
        //given
        final Dealer bustDealer = new Dealer(bustHands);
        final Player winner1 = new Player(new Name("레디"), sum18Size2, defaultBetAmount);
        final Player winner2 = new Player(new Name("브라운"), sum20Size2, defaultBetAmount);
        final Player loser = new Player(new Name("제제"), bustHands, defaultBetAmount);

        final Players players = new Players(List.of(winner1, winner2, loser));

        //when
        final Map<Player, GameResult> expectedPlayerResult = Map.of(winner1, WIN, winner2, WIN, loser, LOSE);
        final Map<GameResult, Integer> expectedDealerResult = Map.of(WIN, 1, LOSE, 2);

        //then
        assertAll(
                () -> assertThat(players.getPlayersResult(bustDealer)).isEqualTo(expectedPlayerResult),
                () -> assertThat(bustDealer.getDealerResult(players)).isEqualTo(expectedDealerResult));
    }


    @Test
    @DisplayName("모든 플레이어가 이긴 경우, 최종 수익을 계산한다. (블랙잭인 경우는 없다)")
    void calculateTotalAmountWhenAllPlayersWin() {
        //given
        final Dealer dealer = new Dealer(sum17Size3One);
        final Player winner1 = new Player(new Name("레디"), sum20Size3, new BetAmount(1_000));
        final Player winner2 = new Player(new Name("제제"), sum21Size3, new BetAmount(2_000));
        final Players players = new Players(List.of(winner1, winner2));
        final Map<Player, EarnAmount> expected = Map.of(winner1, new EarnAmount(1_000), winner2, new EarnAmount(2_000));

        //when
        final Map<Player, EarnAmount> result = players.calculateResult(dealer);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("참여자가 블랙잭인 경우 1.5배의 수익을 얻는다.")
    void blackJackAmount() {
        final Dealer dealer = new Dealer(sum17Size3One);
        final Player blackJackPlayer = new Player(new Name("수달"), blackJack, new BetAmount(10_000));
        final Player loser = new Player(new Name("레디"), sum10Size2, new BetAmount(2_000));
        final Players players = new Players(List.of(blackJackPlayer, loser));

        final Map<Player, EarnAmount> expected = Map.of(blackJackPlayer, new EarnAmount(15_000), loser,
                new EarnAmount(-2_000));

        //when
        final Map<Player, EarnAmount> result = players.calculateResult(dealer);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("참가자와 딜러 모두 블랙잭인 경우 배팅 금액을 돌려받는다.")
    void playerAndDealerBlackJack() {
        final Dealer dealer = new Dealer(blackJack);
        final Player blackJackPlayer = new Player(new Name("수달"), blackJack, new BetAmount(10_000));
        final Player loser = new Player(new Name("레디"), sum18Size2, new BetAmount(2_000));
        final Players players = new Players(List.of(blackJackPlayer, loser));
        final Map<Player, EarnAmount> expected = Map.of(blackJackPlayer, new EarnAmount(0), loser,
                new EarnAmount(-2_000));

        //when
        final Map<Player, EarnAmount> result = players.calculateResult(dealer);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 최종 수익을 계산한다.")
    void dealerAmount() {
        //given
        final Dealer dealer = new Dealer(sum19Size3Ace1);
        final Player winner1 = new Player(new Name("레디"), sum20Size3, new BetAmount(10_000));
        final Player winner2 = new Player(new Name("제제"), sum21Size3, new BetAmount(30_000));
        final Player loser1 = new Player(new Name("브라운"), sum17Size3One, new BetAmount(500_000));
        final Players players = new Players(List.of(winner1, winner2, loser1));

        //when
        final Map<Player, EarnAmount> playerAmountMap = players.calculateResult(dealer);
        final EarnAmount earnAmount = dealer.calculateRevenue(playerAmountMap);
        final EarnAmount expected = new EarnAmount(460_000);

        //then
        assertThat(earnAmount).isEqualTo(expected);
    }
}
