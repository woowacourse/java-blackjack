package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackGameResultTest {

    @Test
    @DisplayName("블랙잭 게임은 게임종료 후 각 참가자들의 수익률을 계산할 수 있다.")
    void calculateEarningResult() {
        Map<Player, OutCome> outComes = createOutComes();

        BlackJackGameResult blackJackGameResult = BlackJackGameResult.from(outComes);

        assertThat(blackJackGameResult.getDealerEarning()).isEqualTo(-1000);
        assertThat(blackJackGameResult.getPlayerEarnings()).isEqualTo(
                new LinkedHashMap<>(Map.of("kei", -1000, "rookie", 2000))
        );
    }

    public Map<Player, OutCome> createOutComes() {
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

        Map<Player, OutCome> outComes = new LinkedHashMap<>();

        outComes.put(player1, OutCome.LOSE);
        outComes.put(player2, OutCome.WIN);

        return outComes;
    }
}
