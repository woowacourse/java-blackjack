package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackGameResultTest {

    @Test
    @DisplayName("게임의 수익률을 계산하는 결과 테스트")
    void calculateEarningResult() {
        Player player1 = new Player("kei");
        Player player2 = new Player("rookie");
        Dealer dealer = new Dealer();

        player1.betting(1000);
        player1.receiveCard(Card.valueOf(Suit.DIAMOND, Denomination.EIGHT));
        player1.receiveCard(Card.valueOf(Suit.DIAMOND, Denomination.ACE));

        player2.betting(2000);
        player2.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.JACK));
        player2.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.ACE));

        dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));
        dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.TEN));

        BlackJackGameResult blackJackGameResult = new BlackJackGameResult();

        blackJackGameResult.add(player1, OutCome.LOSE);
        blackJackGameResult.add(player2, OutCome.WIN);

        HashMap<String, Integer> gameResult = new LinkedHashMap<>();
        gameResult.put("kei", -1000);
        gameResult.put("rookie", 2000);

        assertThat(blackJackGameResult.calculateEarning().getDealerEarning()).isEqualTo(-1000);
        assertThat(blackJackGameResult.calculateEarning().getPlayerEarnings()).isEqualTo(gameResult);
    }
}
