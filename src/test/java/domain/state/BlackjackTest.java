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

class BlackjackTest {

    private State blackjack;

    @BeforeEach
    void setUp() {
        List<Card> initialCards = List.of(new Card(Value.ACE, Shape.HEART), new Card(Value.QUEEN, Shape.HEART));
        Hand hand = new Hand(new ArrayList<>(initialCards));
        blackjack = new Blackjack(hand);
    }

    @DisplayName("현재 상태가 Bust인지 확인하는 테스트")
    @Test
    void isBustTest() {
        assertThat(blackjack.isBust()).isFalse();
    }
}
