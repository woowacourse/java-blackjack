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

class BlackJackTest {

    @Test
    @DisplayName("블랙잭일 경우 수익금은 배팅금액의 1.5배가 된다.")
    void profitTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.ACE, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        State state = new Init();
        state = state.drawCard(deck);
        state = state.drawCard(deck);

        BetMoney betMoney = new BetMoney(10000);

        assertThat(state.profit(betMoney, 18)).isEqualTo(15000);
    }
}
