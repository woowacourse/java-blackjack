package domain.result;

import domain.deck.Card;
import domain.deck.Symbol;
import domain.deck.Type;
import domain.user.Dealer;
import domain.user.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class TotalResultTest {
    private Dealer dealer;
    private Players players;

    private static Stream<Arguments> createResult() {
        return Stream.of(
                Arguments.of(WinningResult.WIN, 2),
                Arguments.of(WinningResult.LOSE, 1),
                Arguments.of(WinningResult.DRAW, 0)
        );
    }

    @BeforeEach
    void setUp() {
        dealer = Dealer.appoint();

        players = Players.of("pobi,jason,woo");
    }

    @ParameterizedTest
    @DisplayName("각 플레이어 승패 확인")
    @EnumSource(WinningResult.class)
    void playerResult(WinningResult winningResult) {
        dealer.draw(new Card(Symbol.SPADE, Type.SIX));

        players.getPlayers()
                .get(0)
                .draw(new Card(Symbol.SPADE, Type.SEVEN)); // 승
        players.getPlayers()
                .get(1)
                .draw(new Card(Symbol.HEART, Type.FIVE)); // 패
        players.getPlayers()
                .get(2)
                .draw(new Card(Symbol.CLOVER, Type.SIX)); // 무승부

        TotalResult totalResult = TotalResult.of(dealer, players);

        assertThat(totalResult.getPlayersResult()).containsValues(winningResult);
    }

    @ParameterizedTest
    @DisplayName("딜러 승패 확인")
    @MethodSource("createResult")
    void dealerResult(WinningResult winningResult, int result) {
        dealer.draw(new Card(Symbol.SPADE, Type.SIX));

        players.getPlayers()
                .get(0)
                .draw(new Card(Symbol.SPADE, Type.SEVEN)); // 플레이어 승
        players.getPlayers()
                .get(1)
                .draw(new Card(Symbol.HEART, Type.FIVE)); // 플레이어 패
        players.getPlayers()
                .get(2)
                .draw(new Card(Symbol.CLOVER, Type.TWO)); // 플레이어 패

        TotalResult totalResult = TotalResult.of(dealer, players);

        assertThat(totalResult.getDealerResult()).contains(entry(winningResult, result));
    }
}