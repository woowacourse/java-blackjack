package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.TrumpNumber;
import blackjack.model.card.TrumpSymbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerStateTest {

    @DisplayName("카드 점수의 합이 21을 넘으면 bust를 반환한다.")
    @Test()
    void state_bust() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        state.addCard(new Card(TrumpNumber.JACK, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.KING, TrumpSymbol.SPADE));

        assertThat(state.state()).isEqualTo("Bust");
    }

    @DisplayName("카드 점수의 합이 20이하 이면 Hit를 반환한다.")
    @Test()
    void state_Hit() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.JACK, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.KING, TrumpSymbol.SPADE));

        assertThat(state.state()).isEqualTo("Hit");
    }

    @DisplayName("카드가 두장이고 점수 합이 21이면 Blackjack을 반환한다.")
    @Test()
    void state_Blackjack() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.ACE, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.KING, TrumpSymbol.SPADE));

        assertThat(state.state()).isEqualTo("Blackjack");
    }

    @DisplayName("카드가 세장 이상이고 점수 합이 21이면 Finish를 반환한다.")
    @Test()
    void state_Finish() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.NINE, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.EIGHT, TrumpSymbol.SPADE));
        state.addCard(new Card(TrumpNumber.FOUR, TrumpSymbol.SPADE));

        assertThat(state.state()).isEqualTo("Finish");
    }
}
