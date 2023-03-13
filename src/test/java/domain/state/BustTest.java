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

class BustTest {
    private State bust;

    @BeforeEach
    void setUp() {
        List<Card> initialCards = List.of(new Card(Value.JACK, Shape.HEART), new Card(Value.QUEEN, Shape.HEART), new Card(Value.KING, Shape.HEART));
        Hand hand = new Hand(new ArrayList<>(initialCards));
        bust = new Bust(hand);
    }

    @DisplayName("현재 상태가 Bust인지 확인하는 테스트")
    @Test
    void isBustTest() {
        assertThat(bust.isBust()).isTrue();
    }

    @DisplayName("현재 상태가 BlackJack인지 확인하는 테스트")
    @Test
    void isBlackJackTest() {
        assertThat(bust.isBlackJack()).isFalse();
    }
}
