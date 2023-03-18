package domain.player.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {
    private Hand hand;
    
    @BeforeEach
    void setUp() {
        hand = new Hand();
    }
    
    @Test
    @DisplayName("스코어가 21을 넘지 않은 경우 카드를 뽑을 수 있다.")
    void hit_state() {
        final State state = new Ready(
                new Card(Shape.HEART, Number.QUEEN),
                new Card(Shape.HEART, Number.JACK)
        ).play();
        
        assertThat(state).isExactlyInstanceOf(Hit.class);
    }
    
    @Test
    @DisplayName("처음 두 장의 카드 합이 21이 되면 블랙잭이다.")
    void blackjack_state() {
        final State state = new Ready(
                new Card(Shape.HEART, Number.QUEEN),
                new Card(Shape.HEART, Number.ACE)
        ).play();
        
        assertThat(state).isExactlyInstanceOf(BlackJack.class);
    }
}
