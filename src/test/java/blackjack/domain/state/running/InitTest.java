package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestCardGenerator;
import blackjack.domain.money.BetMoney;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.BlackJack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("stand 메서드를 실행시키면 예외를 발생시킨다.")
    void standTest() {
        State state = new Init();

        assertThatThrownBy(state::stand)
                .isInstanceOf(IllegalStateException.class);
    }


    @Test
    @DisplayName("profit 메서드를 실행시키면 예외가 발생한다.")
    void profitTest() {
        State state = new Init();
        BetMoney betMoney = new BetMoney(10000);

        assertThatThrownBy(() -> state.profit(betMoney, 20, false))
                .isInstanceOf(IllegalStateException.class);
    }
}
