package blackjack.domain.state;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StateFactoryTest {
    @DisplayName("처음 카드 두장을 뽑았을 때 21점을 넘지 않으면 hit이 된다.(카드를 더 뽑을 수 있는 상태)")
    @Test
    void state_factory_generate_hit() {
        StateFactory stateFactory = new StateFactory();
        State hit = stateFactory.createState(Card.of("하트", "10"),
                Card.of("스페이드", "2"));
        assertThat(hit).isInstanceOf(Hit.class);
    }

    @DisplayName("처음 카드 두장을 뽑았을 때 21점이면 블랙잭이 된다.")
    @Test
    void state_factory_generate_blackjack() {
        StateFactory stateFactory = new StateFactory();
        State blackjack = stateFactory.createState(Card.of("스페이드", "10"),
                Card.of("하트", "A"));
        assertThat(blackjack).isInstanceOf(Blackjack.class);

    }
}