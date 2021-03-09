package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    void createCard() {
        Card card = new Card(Symbol.SPADE, CardNumber.ACE);

        assertThat(card).isEqualTo(new Card(Symbol.SPADE, CardNumber.ACE));
        assertThat(card).isNotEqualTo(new Card(Symbol.HEART, CardNumber.ACE));
        assertThat(card).isNotEqualTo(new Card(Symbol.SPADE, CardNumber.TWO));
    }

    @Test
    @DisplayName("카드로 부터 점수를 가져올 수 있다.")
    void getSocre_getScoreFromCard() {
        Card card = new Card(Symbol.SPADE, CardNumber.TWO);

        assertThat(card.getAccumulateScore()).isEqualTo(2);
    }
}
