package blackJack.domain.result;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackGameResultTest {

    private final Player player1 = new Player("kei");
    private final Player player2 = new Player("rookie");
    private final Player player3 = new Player("parang");
    private final Dealer dealer = new Dealer();


    @Test
    @DisplayName("딜러의 승패 결과 테스트")
    void calculateDealerResult() {
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(dealer, List.of(player1, player2, player3));
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        player1.receiveCard(Card.from(Suit.SPADE, Denomination.EIGHT));
        player2.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        final Map<WinDrawLose, Integer> dealerResult = blackJackGameResult.calculateDealerResult();

        assertThat(dealerResult).contains(
                Map.entry(WinDrawLose.WIN, 1),
                Map.entry(WinDrawLose.DRAW, 0),
                Map.entry(WinDrawLose.LOSE, 2)
        );
    }

    @Test
    @DisplayName("플레이어들의 승패 결과 테스트")
    void calculatePlayersResult() {
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(dealer, List.of(player1, player2, player3));
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        player1.receiveCard(Card.from(Suit.SPADE, Denomination.EIGHT));
        player2.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        final Map<Player, WinDrawLose> playersResult = blackJackGameResult.calculatePlayersResult();

        assertThat(playersResult).contains(
                Map.entry(player1, WinDrawLose.LOSE),
                Map.entry(player2, WinDrawLose.WIN),
                Map.entry(player3, WinDrawLose.WIN)
        );
    }
}