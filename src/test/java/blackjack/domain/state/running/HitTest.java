package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestCardGenerator;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HitTest {

    @Test
    @DisplayName("카드를 뽑았을 때 21이 넘으면 Bust 상태가 된다.")
    void drawCardBustTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.SIX, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER),
                        new Card(CardNumber.SIX, CardType.HEART)));
        Deck deck = new Deck(cardGenerator);

        State state = new Init();
        state = state.drawCard(deck);
        state = state.drawCard(deck); // hit 상태로 변경

        state = state.drawCard(deck);

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("카드를 뽑았을 때 21을 넘지 않으면 Hit 상태가 된다.")
    void drawCardHitTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.SIX, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER),
                        new Card(CardNumber.FIVE, CardType.HEART)));
        Deck deck = new Deck(cardGenerator);

        State state = new Init();
        state = state.drawCard(deck);
        state = state.drawCard(deck); // hit 상태로 변경

        state = state.drawCard(deck);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("stand 메서드를 실행시키면 Stand 상태가 된다.")
    void standTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.SIX, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        State state = new Init();
        state = state.drawCard(deck);
        state = state.drawCard(deck); // hit 상태로 변경

        state = state.stand();

        assertThat(state).isInstanceOf(Stand.class);
    }
}
