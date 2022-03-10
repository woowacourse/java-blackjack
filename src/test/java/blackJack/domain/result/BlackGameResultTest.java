package blackJack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

class BlackGameResultTest {

    @Test
    @DisplayName("딜러의 승패 결과 테스트")
    void calculateDealerResult() {
        Player player1 = new Player("kei");
        Player player2 = new Player("rookie");
        Player player3 = new Player("parang");
        Dealer dealer = new Dealer();

        player1.receiveCard(new Card(Symbol.SPADE, Denomination.EIGHT));
        player2.receiveCard(new Card(Symbol.SPADE, Denomination.J));
        player3.receiveCard(new Card(Symbol.SPADE, Denomination.A));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.NINE));

        BlackJackGameResult blackJackGameResult =
            BlackJackGameResult.ofGameResult(dealer, List.of(player1, player2, player3));

        assertThat(blackJackGameResult.calculateDealerResult()).contains(
                Map.entry(WinOrLose.WIN, 1),
                Map.entry(WinOrLose.DRAW, 0),
                Map.entry(WinOrLose.LOSE, 2)
        );
    }
}