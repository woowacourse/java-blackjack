package blackjack.domain.state.finished;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestCardGenerator;
import blackjack.domain.money.BetMoney;
import blackjack.domain.state.State;
import blackjack.domain.state.running.Init;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StandTest {

    private BetMoney betMoney;
    private State state;

    @BeforeEach
    void beforeEach() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.NINE, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        state = new Init();
        state = state.drawCard(deck);
        state = state.drawCard(deck); // hit 상태로 변경
        state = state.stand(); //state 상태로 변경

        betMoney = new BetMoney(10000);
    }

    @Test
    @DisplayName("딜러의 점수보다 높으면 1배의 수익금을 얻는다.")
    void profitWinTest() {
        assertThat(state.profit(betMoney, 18)).isEqualTo(10000);
    }

    @Test
    @DisplayName("딜러의 점수보다 낮으면 -1배의 수익금을 얻는다.")
    void profitLoseTest() {
        assertThat(state.profit(betMoney, 20)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("딜러와 점수가 같으면 수익금이 없다.")
    void profitDrawTest() {
        assertThat(state.profit(betMoney, 19)).isEqualTo(0);
    }
}
