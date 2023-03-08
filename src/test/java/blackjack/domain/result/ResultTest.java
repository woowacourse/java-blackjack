package blackjack.domain.result;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ResultTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Players players;

    @BeforeEach
    void setUp() {
        players = new Players(List.of("1", "2", "3"));
        player1 = players.getPlayers().get(0);
        player2 = players.getPlayers().get(1);
        player3 = players.getPlayers().get(2);
    }

    @Test
    @DisplayName("플레이어들의 최종 승패를 결정한다.")
    void decideResult() {
        int dealerScore = 20;

        player1.receiveCard(new Card(Number.ACE, Suit.HEART));
        player1.receiveCard(new Card(Number.TEN, Suit.SPADE));

        player2.receiveCard(new Card(Number.SEVEN, Suit.CLOVER));
        player2.receiveCard(new Card(Number.K, Suit.SPADE));

        player3.receiveCard(new Card(Number.THREE, Suit.HEART));
        player3.receiveCard(new Card(Number.NINE, Suit.SPADE));
        player3.receiveCard(new Card(Number.EIGHT, Suit.CLOVER));

        Map<Player, Result> result = Result.makeResult(players, dealerScore);
        assertAll(
                () -> assertThat(result.get(player1)).isEqualTo(Result.WIN),
                () -> assertThat(result.get(player2)).isEqualTo(Result.LOSE),
                () -> assertThat(result.get(player3)).isEqualTo(Result.DRAW)
        );
    }

    @Test
    @DisplayName("플레이어의 결과를 이용하여 딜러의 승패를 결정한다.")
    void makeSelfResultTest() {
        Map<Player, Result> resultsFromPlayer = new HashMap<>() {{
            put(player1, WIN);
            put(player2, DRAW);
            put(player3, LOSE);
        }};

        Map<Result, Integer> dealerResult = Result.resultOfDealer(resultsFromPlayer);

        assertAll(
                () -> assertThat(dealerResult.get(WIN)).isEqualTo(1),
                () -> assertThat(dealerResult.get(DRAW)).isEqualTo(1),
                () -> assertThat(dealerResult.get(LOSE)).isEqualTo(1)
        );
    }
}
