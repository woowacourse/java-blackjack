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

class BlackjackTest {

    private State blackjack;
    private Hand hand;

    @BeforeEach
    void setUp() {
        List<Card> initialCards = List.of(new Card(Value.ACE, Shape.HEART), new Card(Value.QUEEN, Shape.HEART));
        hand = new Hand(new ArrayList<>(initialCards));
        blackjack = new Blackjack(hand);
    }

    @DisplayName("현재 상태가 Bust인지 확인하는 테스트")
    @Test
    void isBustTest() {
        assertThat(blackjack.isBust()).isFalse();
    }

    @DisplayName("현재 상태가 BlackJack인지 확인하는 테스트")
    @Test
    void isBlackJackTest() {
        assertThat(blackjack.isBlackJack()).isTrue();
    }

    @DisplayName("상태가 Blackjack일 때, 결과 값은 BLACKJACK으로 계산된다.")
    @Test
    void calculateResultTest() {
        Hand dealerHand = new Hand(new ArrayList<>(List.of(new Card(Value.KING, Shape.SPADE))));
        State dealerState = new Hit(dealerHand);
        assertThat(blackjack.calculateResult(dealerState)).isEqualTo(Result.BLACKJACK);
    }

    @DisplayName("딜러와 플레이어 둘 다 Blackjack일 때, 결과 값은 TIE로 계산된다.")
    @Test
    void calculateResultTestWhenBothBlackjack() {
        State dealerState = new Blackjack(hand);
        assertThat(blackjack.calculateResult(dealerState)).isEqualTo(Result.TIE);
    }
}
