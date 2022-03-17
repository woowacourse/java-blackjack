package blackjack.domain.game;

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

public class ResultRefereeGreenTest {

    private static final Dealer dealerBlackjack = Dealer.of(getCardBundleOfBlackjack());

    private static final Player player10 = Player.of("ten", getCardBundleOfTen());
    private static final Player player15 = Player.of("fifteen", getCardBundleOfFifteen());
    private static final Player player20 = Player.of("twenty", getCardBundleOfTwenty());

    @DisplayName("ResultReferee 인스턴스는 생성될 때 딜러와 플레이어별 정보를 지니게 된다.")
    @Test
    void init() {
        ResultRefereeGreen referee = new ResultRefereeGreen(
                dealerBlackjack, generateBettingsOf(List.of(player10, player15)));

        assertThat(referee.getResults()).hasSize(3);
    }

    @DisplayName("딜러가 획득한 돈은 모든 플레이어들이 잃은 돈의 합과 같다.")
    @Test
    void dealerBetting_sumIsEqualToOppositeOfPlayerSum() {
        ResultRefereeGreen referee = new ResultRefereeGreen(
                dealerBlackjack, generateBettingsOf(List.of(player10, player15, player20)));

        int playerBettings = 0;
        for (int i = 1; i < 4; i++) {
            playerBettings += referee.getResults().get(i).getMoneyOutcome();
        }

        int actual = referee.getResults().get(0).getMoneyOutcome();
        int expected = playerBettings * -1;

        assertThat(actual).isEqualTo(expected);
    }

    private List<PlayerBetting> generateBettingsOf(List<Player> players) {
        return players.stream()
                .map(player -> new PlayerBetting(player, 1000))
                .collect(Collectors.toUnmodifiableList());
    }
}
