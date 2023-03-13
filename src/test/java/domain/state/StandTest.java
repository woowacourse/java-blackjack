package domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Result;
import domain.card.Card;
import domain.card.Shape;
import domain.card.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StandTest {

    private State playerState;

    @BeforeEach
    void setUp() {
        List<Card> initialCards = List.of(new Card(Value.JACK, Shape.HEART), new Card(Value.THREE, Shape.HEART));
        Hand hand = new Hand(new ArrayList<>(initialCards));
        playerState = new Stand(hand);
    }

    @DisplayName("현재 상태가 Bust인지 확인하는 테스트")
    @Test
    void isBustTest() {
        assertThat(playerState.isBust()).isFalse();
    }

    @DisplayName("현재 상태가 BlackJack인지 확인하는 테스트")
    @Test
    void isBlackJackTest() {
        assertThat(playerState.isBlackJack()).isFalse();
    }

    @DisplayName("딜러보다 점수가 크면, 결과 값은 WIN으로 계산된다.")
    @Test
    void calculateResultTestWhenGreaterThanDealer() {
        Hand dealerHand = new Hand(new ArrayList<>(List.of(new Card(Value.THREE, Shape.HEART))));
        State dealerState = new Stand(dealerHand);

        assertThat(playerState.calculateResult(dealerState)).isEqualTo(Result.WIN);
    }

    @DisplayName("딜러보다 점수가 작으면, 결과 값은 LOSE로 계산된다.")
    @Test
    void calculateResultTestWhenLessThanDealer() {
        Hand dealerHand = new Hand(new ArrayList<>(List.of(new Card(Value.QUEEN, Shape.HEART), new Card(Value.KING, Shape.HEART))));
        State dealerState = new Stand(dealerHand);

        assertThat(playerState.calculateResult(dealerState)).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러와 점수가 같으면, 결과 값은 TIE로 계산된다.")
    @Test
    void calculateResultTestWhenEqualThanDealer() {
        Hand dealerHand = new Hand(new ArrayList<>(List.of(new Card(Value.JACK, Shape.HEART), new Card(Value.THREE, Shape.HEART))));
        State dealerState = new Stand(dealerHand);

        assertThat(playerState.calculateResult(dealerState)).isEqualTo(Result.TIE);
    }

    @DisplayName("딜러가 Blackjack이면, 결과 값은 LOSE로 계산된다.")
    @Test
    void calculateResultTestWhenDealerBlackjack() {
        Hand dealerHand = new Hand(new ArrayList<>(List.of(new Card(Value.JACK, Shape.HEART), new Card(Value.ACE, Shape.HEART))));
        State dealerState = new Blackjack(dealerHand);

        assertThat(playerState.calculateResult(dealerState)).isEqualTo(Result.LOSE);
    }
}
