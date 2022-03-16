package blackjack;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.utils.ParticipantsCreationUtils.createDealerWithDenominations;
import static blackjack.utils.ParticipantsCreationUtils.createPlayerWithDenominations;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BettingMoney;
import blackjack.domain.ScoreBoard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RevenueResultTest {

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러는 아닐 때 플레이어의 수익률은 1.5배이다")
    void player_blackjack_when_player_blackjack_and_dealer_is_not() {
        // given
        List<BettingMoney> bettingMonies = new ArrayList<>();
        Player player = createPlayerWithDenominations("user a", ACE, JACK);
        Dealer dealer = createDealerWithDenominations(TEN, QUEEN);
        bettingMonies.add(new BettingMoney(player.getName(), "10000"));

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        RevenueResult revenueResult = RevenueResult.of(scoreBoard, bettingMonies);

        // then
        assertThat(revenueResult.findPlayerEarning(player.getName())).isEqualTo(15_000);
        assertThat(revenueResult.getDealerEarnings()).isEqualTo(-15_000);
    }
}
