package blackjack.domain.game;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfFifteen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTwenty;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
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
                dealerBlackjack, List.of(player10, player15));

        assertThat(referee.getResults()).hasSize(3);
    }

    @DisplayName("딜러가 획득한 돈은 모든 플레이어들이 잃은 돈의 합과 같다.")
    @Test
    void dealerBettingSum() {
        ResultRefereeGreen referee = new ResultRefereeGreen(
                dealerBlackjack, List.of(player10, player15, player20));

        int dealerBetting = referee.getResults().get(0).getValue();
        int playerBettings = (player10.getBettingAmount() * -1)
                + (player15.getBettingAmount() * -1)
                + (player20.getBettingAmount() * -1);

        assertThat(dealerBetting).isEqualTo(playerBettings * -1);
    }
}
