package blackjack.domain.betting;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfFifteen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfSeventeen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfSixteen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTwenty;
import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER8;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingResultsTest {

    private static final int BETTING_AMOUNT = 1000;
    private static final HitOrStayChoiceStrategy HIT_CHOICE = () -> true;
    private static final HitOrStayChoiceStrategy STAY_CHOICE = () -> false;
    private static final CardsViewStrategy VIEW_STRATEGY = (p) -> {
    };

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
        BettingResults referee = new BettingResults(
                dealerBlackjack, generateBettingsOf(List.of(player10, player15)));

        assertThat(referee.getValue()).hasSize(3);
    }

    @DisplayName("딜러가 획득한 돈은 모든 플레이어들이 잃은 돈의 합과 같다.")
    @Test
    void dealerBetting_isOppositeOfPlayerSum() {
        BettingResults referee = new BettingResults(
                dealerBlackjack, generateBettingsOf(List.of(player10, player15, player20)));

        int actual = extractDealerBettingResultFrom(referee).getMoneyOutcome();
        int expected = extractPlayerBettingSum(referee) * -1;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어가 블랙잭으로 승리한 경우, 베팅금액의 1.5배를 획득한다.")
    @Test
    void playerBetting_blackjackWin() {
        BettingResults referee = new BettingResults(
                dealer17, generateBettingsOf(List.of(playerBlackjack)));

        int actual = extractOnePlayerBettingResultFrom(referee).getMoneyOutcome();
        int expected = (int) (BETTING_AMOUNT * 1.5);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어가 승리한 경우, 베팅금액만큼 얻는다.")
    @Test
    void playerBetting_win() {
        BettingResults referee = new BettingResults(
                dealer17, generateBettingsOf(List.of(player20)));

        int actual = extractOnePlayerBettingResultFrom(referee).getMoneyOutcome();

        assertThat(actual).isEqualTo(BETTING_AMOUNT);
    }

    @DisplayName("무승부인 경우 플레이어는 돈을 얻지도, 잃지도 않는다.")
    @Test
    void playerBetting_draw() {
        BettingResults referee = new BettingResults(
                dealer20, generateBettingsOf(List.of(player20)));

        int actual = extractOnePlayerBettingResultFrom(referee).getMoneyOutcome();
        int expected = 0;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어가 패배한 경우, 베팅금액만큼 잃는다.")
    @Test
    void playerBetting_lose() {
        BettingResults referee = new BettingResults(
                dealerBlackjack, generateBettingsOf(List.of(player10)));

        int actual = extractOnePlayerBettingResultFrom(referee).getMoneyOutcome();
        int expected = BETTING_AMOUNT * -1;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("딜러와 플레이어 모두 Bust인 경우, 딜러가 승리하므로 플레이어는 베팅금액만큼 잃는다.")
    @Test
    void playerBetting_loseOnBothBust() {
        BettingResults referee = new BettingResults(
                getDealerBust(), generateBettingsOf(List.of(getPlayerBust())));

        int actual = extractOnePlayerBettingResultFrom(referee).getMoneyOutcome();
        int expected = BETTING_AMOUNT * -1;

        assertThat(actual).isEqualTo(expected);
    }

    private PlayerBettings generateBettingsOf(List<Participant> players) {
        for (Participant player : players) {
            player.drawAllCards(STAY_CHOICE, VIEW_STRATEGY, () -> CLOVER2);
        }
        return PlayerBettings.of(players, (name) -> BETTING_AMOUNT);
    }

    private int extractPlayerBettingSum(BettingResults referee) {
        final int size = referee.getValue().size();
        return referee.getValue()
                .subList(1, size)
                .stream()
                .mapToInt(BettingResult::getMoneyOutcome)
                .sum();
    }

    private BettingResult extractDealerBettingResultFrom(BettingResults referee) {
        return referee.getValue().get(0);
    }

    private BettingResult extractOnePlayerBettingResultFrom(BettingResults referee) {
        return referee.getValue().get(1);
    }

    private Dealer getDealerBust() {
        Dealer dealerBust = Dealer.of(getCardBundleOfSixteen());
        dealerBust.drawAllCards(HIT_CHOICE, VIEW_STRATEGY, () -> CLOVER_KING);
        return dealerBust;
    }

    private Player getPlayerBust() {
        Player playerBust = Player.of("twenty", getCardBundleOfTwenty());
        playerBust.drawAllCards(HIT_CHOICE, VIEW_STRATEGY, () -> CLOVER8);
        return playerBust;
    }
}
