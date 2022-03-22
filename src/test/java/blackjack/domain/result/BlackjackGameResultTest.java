package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackGameResultTest {

    private final Player player1 = new Player("kei");
    private final Player player2 = new Player("rookie");
    private final Player player3 = new Player("parang");
    private final Dealer dealer = new Dealer();

    @Test
    @DisplayName("딜러의 승패 결과 테스트")
    void calculateDealerResult() {
        game();
        BlackjackGameResult blackJackGameResult = new BlackjackGameResult(dealer, List.of(player1, player2, player3));

        final Map<String, Integer> dealerResult = blackJackGameResult.calculateDealerResult();

        assertThat(dealerResult).contains(
                Map.entry("승", 1),
                Map.entry("무", 0),
                Map.entry("패", 2)
        );
    }

    @Test
    @DisplayName("플레이어들의 승패 결과 테스트")
    void calculatePlayersResult() {
        game();
        BlackjackGameResult blackJackGameResult = new BlackjackGameResult(dealer, List.of(player1, player2, player3));


        final Map<Player, BlackjackMatch> playersResult = blackJackGameResult.calculatePlayersResult();

        assertThat(playersResult).contains(
                Map.entry(player1, BlackjackMatch.LOSE),
                Map.entry(player2, BlackjackMatch.WIN),
                Map.entry(player3, BlackjackMatch.WIN)
        );
    }

    private void game() {
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.TWO));
        dealer.requestStay();

        player1.receiveCard(Card.from(Suit.SPADE, Denomination.EIGHT));
        player1.receiveCard(Card.from(Suit.HEART, Denomination.TWO));
        player1.requestStay();

        player2.receiveCard(Card.from(Suit.DIAMOND, Denomination.ACE));
        player2.receiveCard(Card.from(Suit.DIAMOND, Denomination.KING));

        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.TWO));
        player3.requestStay();
    }
}
