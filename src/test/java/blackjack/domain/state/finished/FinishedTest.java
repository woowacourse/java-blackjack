package blackjack.domain.state.finished;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestCardGenerator;
import blackjack.domain.state.State;
import blackjack.domain.state.running.Init;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinishedTest {

    private State state;
    private Deck deck;

    @BeforeEach
    void beforeEach() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.SIX, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER),
                        new Card(CardNumber.KING, CardType.HEART),
                        new Card(CardNumber.TWO, CardType.HEART)));
        deck = new Deck(cardGenerator);

        state = new Init();
        state = state.drawCard(deck);
        state = state.drawCard(deck); // hit 상태로 변경
        state = state.drawCard(deck); // bust 상태로 변경
    }

    @Test
    @DisplayName("Finish 상태에서 stand 메서드를 실행시키면 예외가 발생된다.")
    void standTest() {
        assertThatThrownBy(() -> state.stand())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Finish 상태에서 drawCard 메서드를 실행시키면 예외가 발생된다.")
    void drawCardTest() {
        assertThatThrownBy(() -> state.drawCard(deck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 이미 종료된 상태입니다.");
    }
}
