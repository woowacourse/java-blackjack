package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestCardGenerator;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.BlackJack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InitTest {

    @Test
    @DisplayName("초기 카드를 받았을 때 21이하면 Hit 상태가 된다.")
    void initDistributeHitTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.SIX, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        State state = new Init();
        state = state.drawCard(deck);
        state = state.drawCard(deck);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("초기 카드를 받았을 때 합이 21이면 BlackJack 상태가 된다.")
    void initDistributeBlackJackTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.ACE, CardType.CLOVER),
                        new Card(CardNumber.JACK, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        State state = new Init();
        state = state.drawCard(deck);
        state = state.drawCard(deck);

        assertThat(state).isInstanceOf(BlackJack.class);
    }
}
