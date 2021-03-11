package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateFactoryTest {
    @DisplayName("hit 상태를 반환하는지 테스트")
    @Test
    void hit() {
        Card firstCard = Card.valueOf(CardShape.CLUB, CardNumber.TWO);
        Card secondCard = Card.valueOf(CardShape.DIAMOND, CardNumber.TEN);
        final State state = StateFactory.draw(firstCard, secondCard);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("blackjack 상태를 반환하는지 테스트")
    @Test
    void blackjack() {
        Card firstCard = Card.valueOf(CardShape.CLUB, CardNumber.ACE);
        Card secondCard = Card.valueOf(CardShape.DIAMOND, CardNumber.TEN);
        final State state = StateFactory.draw(firstCard, secondCard);
        assertThat(state).isInstanceOf(Blackjack.class);
    }
}