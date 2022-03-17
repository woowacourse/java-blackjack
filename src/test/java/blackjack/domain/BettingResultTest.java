package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.GameResult;

class BettingResultTest {

    @Test
    @DisplayName("배팅 계산이 잘 되는지 확인")
    void bettingTest() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card(Denomination.THREE, Suit.CLUBS));

        Players players = new Players(List.of("짱구", "짱아"));
        players.getPlayers().forEach(player -> {
            player.betAmount(1000);
            player.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        });

        GameResult gameResult = dealer.judgeResult(players);
        BettingResult bettingResult = gameResult.calculateRevenue();

        assertThat(bettingResult.getDealerRevenue()).isEqualTo(2000);
    }
}