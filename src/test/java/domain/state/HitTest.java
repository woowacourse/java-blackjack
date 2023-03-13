package domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {

    private State playerState;
    private Hand hand;

    @BeforeEach
    void setUp() {
        List<Card> initialCards = List.of(new Card(Value.JACK, Shape.HEART), new Card(Value.FIVE, Shape.SPADE));
        hand = new Hand(new ArrayList<>(initialCards));
        playerState = new Hit(hand);
    }

    @DisplayName("추가로 카드를 뽑은 후, 21 이하면 Hit 상태가 되는지 테스트")
    @Test
    void drawCardHitTest() {
        assertThat(playerState.drawCard(new Card(Value.THREE, Shape.SPADE))).isInstanceOf(Hit.class);
    }

    @DisplayName("추가로 카드를 뽑은 후, 21을 넘으면 Bust 상태가 되는지 테스트")
    @Test
    void drawCardBustTest() {
        assertThat(playerState.drawCard(new Card(Value.QUEEN, Shape.SPADE))).isInstanceOf(Bust.class);
    }

    @DisplayName("상태가 Hit일 때는 결과 값을 계산할 수 없다.")
    @Test
    void calculateResultTest() {
        State dealerState = new Hit(hand);

        assertThatThrownBy(() -> playerState.calculateResult(dealerState))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
