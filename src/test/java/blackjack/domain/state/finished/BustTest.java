package blackjack.domain.state.finished;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestCardGenerator;
import blackjack.domain.money.BetMoney;
import blackjack.domain.state.State;
import blackjack.domain.state.running.Init;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BustTest {

    @Test
    @DisplayName("버스트일 경우 수익금은 배팅금액의 -1배가 된다.")
    void profitTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.NINE, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER),
                        new Card(CardNumber.KING, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        State state = new Init();
        state = state.drawCard(deck);
        state = state.drawCard(deck); // hit 상태로 변경
        state = state.drawCard(deck); // bust 상태로 변경

        BetMoney betMoney = new BetMoney(10000);

        assertThat(state.profit(betMoney, 18, false)).isEqualTo(-10000);
    }
}
