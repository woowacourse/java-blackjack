package blackjack.participant.state.terminated;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import blackjack.game.MatchResult;
import blackjack.participant.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StandStateTest {

    private StandState createStandState() {
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.SPADE, Number.TEN));
        return new StandState(new Hand(cards));
    }

    @Test
    @DisplayName("상대가 버스트 상태라면 승리한다.")
    void winOnDealerBustTest() {
        // given
        List<Card> bustCards = List.of(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.DIAMOND, Number.TEN),
                new Card(Shape.HEART, Number.TEN));
        StandState state = createStandState();
        BustedState dealerState = new BustedState(new Hand(bustCards));
        // when
        MatchResult matchResult = state.createMatchResult(dealerState);
        // then
        assertThat(matchResult).isEqualTo(MatchResult.NORMAL_WIN);
    }

    @Test
    @DisplayName("상대와 점수가 같다면 비긴다.")
    void drawByComparingScoreTest() {
        // given
        StandState state = createStandState();
        StandState dealerState = createStandState();
        // when
        MatchResult matchResult = state.createMatchResult(dealerState);
        // then
        assertThat(matchResult).isEqualTo(MatchResult.TIE);
    }

    @Test
    @DisplayName("상대보다 점수가 높다면 승리한다.")
    void winByComparingScoreTest() {
        // given
        StandState state = createStandState();
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.SPADE, Number.NINE));
        StandState dealerState = new StandState(new Hand(cards));
        // when
        MatchResult matchResult = state.createMatchResult(dealerState);
        // then
        assertThat(matchResult).isEqualTo(MatchResult.NORMAL_WIN);
    }

    @Test
    @DisplayName("상대보다 점수가 낮다면 패배한다.")
    void loseByComparingScoreTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.SPADE, Number.ACE));
        StandState state = createStandState();
        StandState dealerState = new StandState(new Hand(cards));
        // when
        MatchResult matchResult = state.createMatchResult(dealerState);
        // then
        assertThat(matchResult).isEqualTo(MatchResult.LOSE);
    }
}
