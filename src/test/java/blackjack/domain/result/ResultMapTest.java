package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Shape;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultMapTest {

    private ResultMap resultMap;
    private Players players;

    @BeforeEach
    void setPlayers() {
        players = Players.from(List.of("oing", "ditoo"));

        Card heartKing = new Card(Shape.HEART, Symbol.KING);
        Card heartSeven = new Card(Shape.HEART, Symbol.SEVEN);
        Card heartThree = new Card(Shape.HEART, Symbol.THREE);

        players.getDealer().pick(heartSeven);
        players.getChallengers().get(0).pick(heartThree);
        players.getChallengers().get(1).pick(heartKing);
        resultMap = ResultMap.from(players);
    }

    @Test
    @DisplayName("도전자들의 승패가 올바르게 계산되는지 확인한다")
    void check_challengers_result() {
        Player oing = players.getChallengers().get(0);
        Player ditoo = players.getChallengers().get(1);

        assertThat(resultMap.getChallengerResult(oing)).isEqualTo(ResultType.LOSE);
        assertThat(resultMap.getChallengerResult(ditoo)).isEqualTo(ResultType.WIN);
    }

    @Test
    @DisplayName("딜러의 승패가 올바르게 계산되는지 확인한다")
    void check_dealer_result() {
        Map<ResultType, Integer> dealerResult = resultMap.getDealerResult();
        assertThat(dealerResult)
                .containsOnly(entry(ResultType.WIN, 1), entry(ResultType.LOSE, 1));
    }
}
