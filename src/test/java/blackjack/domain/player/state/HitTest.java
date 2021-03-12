package blackjack.domain.player.state;


import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {

    @Test
    @DisplayName("HIT 상태에서 BUST가 될 때 올바른 state 리턴 확인")
    void changeState() {
        Deck deck = Deck.of(
            Card.of(CardNumber.KING, Symbol.CLOVER),
            Card.of(CardNumber.JACK, Symbol.CLOVER)
        );

        State startState = StateFactory.start(deck);
        Assertions.assertThat(startState).isInstanceOf(Hit.class);

        deck.addCard(Card.of(CardNumber.TWO, Symbol.CLOVER));
        Assertions.assertThat(startState.currentState(deck)).isInstanceOf(Bust.class);
    }
}