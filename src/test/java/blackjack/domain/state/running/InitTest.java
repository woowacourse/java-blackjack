package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestCardGenerator;
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

        Init state = new Init();

        assertThat(state.initDistributed(deck)).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("초기 카드를 받았을 때 합이 21이면 BlackJack 상태가 된다.")
    void initDistributeBlackJackTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.ACE, CardType.CLOVER),
                        new Card(CardNumber.JACK, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        Init state = new Init();

        assertThat(state.initDistributed(deck)).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("초기 상태에서 초기 분배 2장 이외의 카드를 뽑으려 할 경우 예외를 발생시킨다.")
    void drawCardTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.ACE, CardType.CLOVER),
                        new Card(CardNumber.JACK, CardType.CLOVER),
                        new Card(CardNumber.TWO, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        Init state = new Init();
        state.initDistributed(deck);

        assertThatThrownBy(() -> state.drawCard(deck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 초기 상태에서는 2장의 카드까지만 받을 수 있습니다.");
    }

}
