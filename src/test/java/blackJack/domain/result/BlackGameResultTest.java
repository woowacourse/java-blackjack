package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackGameResultTest {

    @Test
    @DisplayName("딜러의 승패 결과 테스트")
    void calculateDealerResult() {
        Player player1 = new Player("kei");
        Player player2 = new Player("rookie");
        Player player3 = new Player("parang");
        Dealer dealer = new Dealer();

        player1.receiveCard(Card.valueOf(Suit.SPADE, Denomination.EIGHT));
        player2.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));
        player3.receiveCard(Card.valueOf(Suit.SPADE, Denomination.ACE));
        dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.NINE));

        BlackJackGameResult blackJackGameResult = new BlackJackGameResult();

        blackJackGameResult.add(player1, OutCome.LOSE);
        blackJackGameResult.add(player2, OutCome.WIN);
        blackJackGameResult.add(player3, OutCome.LOSE);

        assertThat(blackJackGameResult.calculateDealerResult()).contains(
                Map.entry(OutCome.WIN, 2),
                Map.entry(OutCome.DRAW, 0),
                Map.entry(OutCome.LOSE, 1)
        );
    }
}