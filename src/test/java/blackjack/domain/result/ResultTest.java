package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    private Result result;
    private Players players;

    @BeforeEach
    void setPlayers() {
        players = Players.from(List.of("oing", "ditoo"));

        Card heartKing = new Card(Shape.HEART, Number.KING);
        Card heartSeven = new Card(Shape.HEART, Number.SEVEN);
        Card heartThree = new Card(Shape.HEART, Number.THREE);

        players.getDealer().pick(heartSeven);
        players.getChallengers().get(0).pick(heartThree);
        players.getChallengers().get(1).pick(heartKing);
        result = Result.from(players);
    }

    @Test
    @DisplayName("도전자들의 승패가 올바르게 계산되는지 확인한다")
    void check_challengers_result() {
        Player oing = players.getChallengers().get(0);
        Player ditoo = players.getChallengers().get(1);

        assertThat(result.getChallengerResult(oing)).isEqualTo(Rank.LOSE);
        assertThat(result.getChallengerResult(ditoo)).isEqualTo(Rank.WIN);
    }

    @Test
    @DisplayName("딜러의 승패가 올바르게 계산되는지 확인한다")
    void check_dealer_result() {
        assertThat(result.getDealerWinCount()).isEqualTo(1);
        assertThat(result.getDealerDrawCount()).isZero();
        assertThat(result.getDealerLoseCount()).isEqualTo(1);
    }
}
