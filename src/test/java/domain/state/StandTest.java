package domain.state;

import static org.assertj.core.api.Assertions.assertThat;

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
    private Hand hand;

    @BeforeEach
    void setUp() {
        List<Card> initialCards = List.of(new Card(Value.JACK, Shape.HEART), new Card(Value.THREE, Shape.HEART));
        hand = new Hand(new ArrayList<>(initialCards));
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
}
