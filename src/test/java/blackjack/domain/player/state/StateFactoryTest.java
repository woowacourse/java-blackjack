package blackjack.domain.player.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateFactoryTest {

    @Test
    @DisplayName("21 스코어의 덱이 넣어질 경우 블랙잭 인스턴스가 생성되는 지")
    void createBlackjack() {
        State startState = StateFactory.start(Deck.of(
            Card.of(CardNumber.ACE, Symbol.CLOVER),
            Card.of(CardNumber.KING, Symbol.CLOVER)
        ));

        assertThat(startState).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("21 스코어 미만의 덱이 넣어질 경우 히트가 생성 되는지")
    void createHit() {
        State startState = StateFactory.start(Deck.of(
            Card.of(CardNumber.ACE, Symbol.CLOVER),
            Card.of(CardNumber.TWO, Symbol.CLOVER)
        ));

        assertThat(startState).isInstanceOf(Hit.class);
    }
}