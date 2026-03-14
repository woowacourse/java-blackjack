package domain.state;

import domain.card.Card;
import domain.member.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerHitTest {

    @DisplayName("딜러가 1장의 카드를 가진 상태에서 추가 카드를 뽑으면 점수에 따라 상태가 반환된다")
    @ParameterizedTest
    @CsvSource({
            "10, 6, domain.state.DealerHit",
            "10, 7, domain.state.Stay",
            "A, 10, domain.state.Blackjack"
    })
    void dealerDraw_SecondCardDrawn_ReturnStatus(String initialNumber, String drawNumber, String expectedState) {
        Hand hand = new Hand().appendCard(Card.from(initialNumber, "하트"));
        State state = new DealerHit(hand);

        State nextState = state.draw(Card.from(drawNumber, "클로버"));

        assertThat(nextState.getClass().getName()).isEqualTo(expectedState);
    }

    @DisplayName("딜러가 2장의 카드를 가진 상태(16점 이하)에서 추가 카드를 뽑으면 결과와 상관없이 종료 상태가 반환된다")
    @ParameterizedTest
    @CsvSource({
            "10, 4, 2, domain.state.Stay",
            "10, 5, 2, domain.state.Stay",
            "10, 6, 10, domain.state.Bust"
    })
    void draw_ThirdCardDrawn_ReturnsFinishedState(String card1, String card2, String drawNumber, String expectedState) {
        Hand hand = new Hand().appendCard(Card.from(card1, "하트"))
                .appendCard(Card.from(card2, "다이아몬드"));
        State state = new DealerHit(hand);

        State nextState = state.draw(Card.from(drawNumber, "클로버"));

        assertThat(nextState.getClass().getName()).isEqualTo(expectedState);
    }
}
