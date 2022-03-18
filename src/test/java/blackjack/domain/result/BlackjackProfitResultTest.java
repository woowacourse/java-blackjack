package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackProfitResultTest {

    private final Player player1 = new Player("kei");
    private final Player player2 = new Player("rookie");
    private final Player player3 = new Player("parang");
    private final Dealer dealer = new Dealer();

    @Test
    @DisplayName("수익 테스트")
    void calculateDealerProfit() {
        BlackjackProfitResult blackjackProfitResult = new BlackjackProfitResult(dealer, initializePlayers());
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        player1.receiveCard(Card.from(Suit.SPADE, Denomination.EIGHT));
        player2.receiveCard(Card.from(Suit.DIAMOND, Denomination.ACE));
        player2.receiveCard(Card.from(Suit.DIAMOND, Denomination.KING));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        Map<Participant, BlackjackProfit> result = blackjackProfitResult.calculateParticipantsProfit();

        assertThat(result).containsExactly(
                Map.entry(dealer, BlackjackProfit.from(-17500.0)), Map.entry(player1, BlackjackProfit.from(-10000.0)),
                Map.entry(player2, BlackjackProfit.from(7500.0)), Map.entry(player3, BlackjackProfit.from(20000.0))
        );
    }

    private Map<Player, BettingMoney> initializePlayers() {
        final Map<Player, BettingMoney> players = new LinkedHashMap<>();
        players.put(player1, new BettingMoney(10000));
        players.put(player2, new BettingMoney(5000));
        players.put(player3, new BettingMoney(20000));
        return players;
    }
}