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

class BlackjackGameResultTest {

    private final Player player1 = new Player("kei", "10000");
    private final Player player2 = new Player("rookie", "5000");
    private final Player player3 = new Player("parang", "20000");
    private final Dealer dealer = new Dealer();

    @Test
    @DisplayName("딜러의 승패 결과 테스트")
    void calculateDealerResult() {
        BlackjackGameResult blackjackGameResult = new BlackjackGameResult(dealer, List.of(player1, player2, player3));
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        player1.receiveCard(Card.from(Suit.SPADE, Denomination.EIGHT));
        player2.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        final Map<String, Integer> dealerResult = blackjackGameResult.calculateDealerResult();

        assertThat(dealerResult).contains(
                Map.entry("승", 1),
                Map.entry("무", 0),
                Map.entry("패", 2)
        );
    }

    @Test
    @DisplayName("플레이어 중 블랙잭이 있을 때 딜러의 승패 결과 테스트")
    void calculateDealerResultWhenBlackjackPlayer() {
        BlackjackGameResult blackjackGameResult = new BlackjackGameResult(dealer, List.of(player1, player2, player3));
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        player1.receiveCard(Card.from(Suit.DIAMOND, Denomination.ACE));
        player1.receiveCard(Card.from(Suit.DIAMOND, Denomination.JACK));
        player2.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        final Map<String, Integer> dealerResult = blackjackGameResult.calculateDealerResult();

        assertThat(dealerResult).contains(
                Map.entry("승", 0),
                Map.entry("무", 0),
                Map.entry("패", 3)
        );
    }

    @Test
    @DisplayName("딜러가 블랙잭일 때 딜러의 승패 결과 테스트")
    void calculateDealerResultWhenBlackjackDealer() {
        BlackjackGameResult blackjackGameResult = new BlackjackGameResult(dealer, List.of(player1, player2, player3));
        dealer.receiveCard(Card.from(Suit.DIAMOND, Denomination.ACE));
        dealer.receiveCard(Card.from(Suit.DIAMOND, Denomination.JACK));
        player1.receiveCard(Card.from(Suit.HEART, Denomination.ACE));
        player1.receiveCard(Card.from(Suit.HEART, Denomination.JACK));
        player2.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        player2.receiveCard(Card.from(Suit.CLOVER, Denomination.EIGHT));
        player2.receiveCard(Card.from(Suit.CLOVER, Denomination.THREE));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        final Map<String, Integer> dealerResult = blackjackGameResult.calculateDealerResult();

        assertThat(dealerResult).contains(
                Map.entry("승", 2),
                Map.entry("무", 1),
                Map.entry("패", 0)
        );
    }

    @Test
    @DisplayName("플레이어들의 승패 결과 테스트")
    void calculatePlayersResult() {
        BlackjackGameResult blackjackGameResult = new BlackjackGameResult(dealer, List.of(player1, player2, player3));
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        player1.receiveCard(Card.from(Suit.SPADE, Denomination.EIGHT));
        player2.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        final Map<Player, BlackjackMatch> playersResult = blackjackGameResult.calculatePlayersResult();

        assertThat(playersResult).contains(
                Map.entry(player1, BlackjackMatch.LOSE),
                Map.entry(player2, BlackjackMatch.WIN),
                Map.entry(player3, BlackjackMatch.WIN)
        );
    }

    @Test
    @DisplayName("딜러 수익 테스트")
    void calculateDealerProfit() {
        BlackjackGameResult blackjackGameResult = new BlackjackGameResult(dealer, List.of(player1, player2, player3));
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        player1.receiveCard(Card.from(Suit.SPADE, Denomination.EIGHT));
        player2.receiveCard(Card.from(Suit.DIAMOND, Denomination.ACE));
        player2.receiveCard(Card.from(Suit.DIAMOND, Denomination.KING));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        final double dealerProfit = blackjackGameResult.calculateDealerProfit();

        assertThat(dealerProfit).isEqualTo(-17500);
    }
}