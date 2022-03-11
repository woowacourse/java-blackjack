package blackjack.domain.result;

import static blackjack.domain.result.Result.LOSE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameScoreBoardTest {

    private static final Card aceCard = new Card(Denomination.ACE, Suit.SPADE);
    private static final Card tenCard = new Card(Denomination.TEN, Suit.DIAMOND);
    private static final Card sevenCard = new Card(Denomination.SEVEN, Suit.HEART);

    private Dealer dealer;
    private List<Player> players;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(createCardHand(aceCard, sevenCard));
        players = createPlayers();
    }

    @Test
    void equalsDealerGameResult() {
        GameScoreBoard result = GameScoreBoard.recordGameScore(dealer, players);
        Map<Result, Integer> dealerGameResult = result.getDealerGameResult();

        for (Entry<Result, Integer> dealerResult : dealerGameResult.entrySet()) {
            assertThat(dealerResult.getKey()).isEqualTo(LOSE);
        }
    }

    @Test
    void equalsPlayerGameResult() {
        GameScoreBoard result = GameScoreBoard.recordGameScore(dealer, players);
        Map<String, String> playerGameResultMap = result.getPlayerGameResultMap();

        for (Entry<String, String> playerResult : playerGameResultMap.entrySet()) {
            assertThat(playerResult.getValue()).isEqualTo("승");
        }
    }

    private List<Player> createPlayers() {
        return Collections.singletonList(
            new Player(new Name("승팡"), createCardHand(aceCard, tenCard)));
    }

    private Hand createCardHand(Card card1, Card card2) {
        Hand cardHand = new Hand();
        cardHand.add(card1, card2);
        return cardHand;
    }
}
