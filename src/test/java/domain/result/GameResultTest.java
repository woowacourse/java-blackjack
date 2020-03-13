package domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Players;

class GameResultTest {

    @Test
    @DisplayName("승패 결과")
    void getTotalWinningResult() {
        String expected = "딜러: 1승 1패 1무승부\npobi: 승\njason: 패\nwoo: 무승부";
        Dealer dealer = Dealer.appoint();
        dealer.draw(new Card(Symbol.SPADE, Type.SIX));

        Players players = Players.of("pobi,jason,woo");
        players.getPlayers()
                .get(0)
                .draw(new Card(Symbol.SPADE, Type.SEVEN));
        players.getPlayers()
                .get(1)
                .draw(new Card(Symbol.HEART, Type.FIVE));
        players.getPlayers()
                .get(2)
                .draw(new Card(Symbol.CLOVER, Type.SIX));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTotalWinningResults()).isEqualTo(expected);
    }
}