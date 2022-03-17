package blackjack.domain.betting;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfFifteen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfSeventeen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTwenty;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingRefereeTest {

    private static final int BETTING_AMOUNT = 1000;

    private static final Dealer dealer17 = Dealer.of(getCardBundleOfSeventeen());
    private static final Dealer dealer20 = Dealer.of(getCardBundleOfTwenty());
    private static final Dealer dealerBlackjack = Dealer.of(getCardBundleOfBlackjack());

    private static final Player player10 = Player.of("ten", getCardBundleOfTen());
    private static final Player player15 = Player.of("fifteen", getCardBundleOfFifteen());
    private static final Player player20 = Player.of("twenty", getCardBundleOfTwenty());
    private static final Player playerBlackjack = Player.of("blackjack", getCardBundleOfBlackjack());

    @DisplayName("ResultReferee 인스턴스는 생성될 때 딜러와 플레이어별 정보를 지니게 된다.")
    @Test
    void init() {
        BettingReferee referee = new BettingReferee(
                dealerBlackjack, generateBettingsOf(List.of(player10, player15)));

        assertThat(referee.getResults()).hasSize(3);
    }

    @DisplayName("딜러가 획득한 돈은 모든 플레이어들이 잃은 돈의 합과 같다.")
    @Test
    void dealerBetting_isOppositeOfPlayerSum() {
        BettingReferee referee = new BettingReferee(
                dealerBlackjack, generateBettingsOf(List.of(player10, player15, player20)));

        int actual = extractDealerBettingResultFrom(referee).getMoneyOutcome();
        int expected = extractPlayerBettingSum(referee) * -1;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어가 블랙잭으로 승리한 경우, 베팅금액의 1.5배를 획득한다.")
    @Test
    void playerBetting_blackjackWin() {
        BettingReferee referee = new BettingReferee(
                dealer17, generateBettingsOf(List.of(playerBlackjack)));

        int actual = extractOnePlayerBettingResultFrom(referee).getMoneyOutcome();
        int expected = (int) (BETTING_AMOUNT * 1.5);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어가 승리한 경우, 베팅금액만큼 얻는다.")
    @Test
    void playerBetting_win() {
        BettingReferee referee = new BettingReferee(
                dealer17, generateBettingsOf(List.of(player20)));

        int actual = extractOnePlayerBettingResultFrom(referee).getMoneyOutcome();

        assertThat(actual).isEqualTo(BETTING_AMOUNT);
    }

    @DisplayName("무승부인 경우 플레이어는 돈을 얻지도, 잃지도 않는다.")
    @Test
    void playerBetting_draw() {
        BettingReferee referee = new BettingReferee(
                dealer20, generateBettingsOf(List.of(player20)));

        int actual = extractOnePlayerBettingResultFrom(referee).getMoneyOutcome();
        int expected = 0;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어가 패배한 경우, 베팅금액만큼 잃는다.")
    @Test
    void playerBetting_lose() {
        BettingReferee referee = new BettingReferee(
                dealerBlackjack, generateBettingsOf(List.of(player10)));

        int actual = extractOnePlayerBettingResultFrom(referee).getMoneyOutcome();
        int expected = BETTING_AMOUNT * -1;

        assertThat(actual).isEqualTo(expected);
    }

    private PlayerBettings generateBettingsOf(List<Player> players) {
        return PlayerBettings.of(players, () -> BETTING_AMOUNT);
    }

    private int extractPlayerBettingSum(BettingReferee referee) {
        final int size = referee.getResults().size();
        return referee.getResults()
                .subList(1, size)
                .stream()
                .mapToInt(BettingResult::getMoneyOutcome)
                .sum();
    }

    private BettingResult extractDealerBettingResultFrom(BettingReferee referee) {
        return referee.getResults().get(0);
    }

    private BettingResult extractOnePlayerBettingResultFrom(BettingReferee referee) {
        return referee.getResults().get(1);
    }
}
