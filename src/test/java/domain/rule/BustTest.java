package domain.rule;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardScore;
import domain.card.CardSuit;
import domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {

    @Test
    @DisplayName("버스트이면 수익률은 -1.0이다.")
    void bustEarningRateTest() {
        Cards cards = new Cards(List.of(
                new Card(CardScore.JACK, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART)
        ));
        Bust bust = new Bust(cards);
        State dealerState = new Stay(new Cards(List.of(
                new Card(CardScore.TEN, CardSuit.CLUB),
                new Card(CardScore.SEVEN, CardSuit.HEART)
        )));
        assertThat(bust.earningRate(dealerState)).isEqualTo(-1.0);
    }
}
