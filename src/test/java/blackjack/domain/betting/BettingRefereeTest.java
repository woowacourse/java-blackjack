package blackjack.domain.betting;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfFifteen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTwenty;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingRefereeTest {

    private static final int BETTING_AMOUNT = 1000;

    private static final Dealer dealerBlackjack = Dealer.of(getCardBundleOfBlackjack());

    private static final Player player10 = Player.of("ten", getCardBundleOfTen());
    private static final Player player15 = Player.of("fifteen", getCardBundleOfFifteen());
    private static final Player player20 = Player.of("twenty", getCardBundleOfTwenty());

    @DisplayName("ResultReferee 인스턴스는 생성될 때 딜러와 플레이어별 정보를 지니게 된다.")
    @Test
    void init() {
        BettingReferee referee = new BettingReferee(
                dealerBlackjack, generateBettingsOf(List.of(player10, player15)));

        assertThat(referee.getResults()).hasSize(3);
    }

    @DisplayName("딜러가 획득한 돈은 모든 플레이어들이 잃은 돈의 합과 같다.")
    @Test
    void dealerBetting_sumIsEqualToOppositeOfPlayerSum() {
        BettingReferee referee = new BettingReferee(
                dealerBlackjack, generateBettingsOf(List.of(player10, player15, player20)));

        int actual = extractDealerBettingResultFrom(referee).getMoneyOutcome();
        int expected = extractPlayerBettingSum(referee) * -1;

        assertThat(actual).isEqualTo(expected);
    }

    private List<PlayerBetting> generateBettingsOf(List<Player> players) {
        return players.stream()
                .map(player -> new PlayerBetting(player, BETTING_AMOUNT))
                .collect(Collectors.toUnmodifiableList());
    }

    private int extractPlayerBettingSum(BettingReferee referee) {
        return extractPlayerBettingResultFrom(referee).stream()
                .mapToInt(BettingResult::getMoneyOutcome)
                .sum();
    }

    private BettingResult extractDealerBettingResultFrom(BettingReferee referee) {
        return referee.getResults().get(0);
    }

    private List<BettingResult> extractPlayerBettingResultFrom(BettingReferee referee) {
        int size = referee.getResults().size();
        return referee.getResults().subList(1, size);
    }
}
