package blackjack.domain.player.state;


import static blackjack.domain.card.CardSpec.JACK;
import static blackjack.domain.card.CardSpec.KING;
import static blackjack.domain.card.CardSpec.TWO;

import blackjack.domain.card.Deck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {

    @Test
    @DisplayName("HIT 상태에서 BUST가 될 때 올바른 state 리턴 확인")
    void changeState() {
        Deck deck = Deck.of(KING.card(), JACK.card());

        State startState = StateFactory.start(deck);
        Assertions.assertThat(startState).isInstanceOf(Hit.class);

        deck.addCard(TWO.card());
        Assertions.assertThat(startState.currentState(deck)).isInstanceOf(Bust.class);
    }
}