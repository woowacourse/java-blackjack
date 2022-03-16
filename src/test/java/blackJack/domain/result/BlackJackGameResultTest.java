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

        final Map<String, Integer> dealerResult = blackJackGameResult.calculateDealerResult();

        assertThat(dealerResult).contains(
                Map.entry("승", 1),
                Map.entry("무", 0),
                Map.entry("패", 2)
        );
    }

    @Test
    @DisplayName("플레이어 중 블랙잭이 있을 때 딜러의 승패 결과 테스트")
    void calculateDealerResultWhenBlackJackPlayer() {
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(dealer, List.of(player1, player2, player3));
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        player1.receiveCard(Card.from(Suit.DIAMOND, Denomination.ACE));
        player1.receiveCard(Card.from(Suit.DIAMOND, Denomination.JACK));
        player2.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        final Map<String, Integer> dealerResult = blackJackGameResult.calculateDealerResult();

        assertThat(dealerResult).contains(
                Map.entry("승", 0),
                Map.entry("무", 0),
                Map.entry("패", 3)
        );
    }

    @Test
    @DisplayName("딜러가 블랙잭일 때 딜러의 승패 결과 테스트")
    void calculateDealerResultWhenBlackJackDealer() {
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(dealer, List.of(player1, player2, player3));
        dealer.receiveCard(Card.from(Suit.DIAMOND, Denomination.ACE));
        dealer.receiveCard(Card.from(Suit.DIAMOND, Denomination.JACK));
        player1.receiveCard(Card.from(Suit.HEART, Denomination.ACE));
        player1.receiveCard(Card.from(Suit.HEART, Denomination.JACK));
        player2.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        player2.receiveCard(Card.from(Suit.CLOVER, Denomination.EIGHT));
        player2.receiveCard(Card.from(Suit.CLOVER, Denomination.THREE));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        final Map<String, Integer> dealerResult = blackJackGameResult.calculateDealerResult();

        assertThat(dealerResult).contains(
                Map.entry("승", 2),
                Map.entry("무", 1),
                Map.entry("패", 0)
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