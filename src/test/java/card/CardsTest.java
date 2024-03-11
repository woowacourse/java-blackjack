package card;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.card.Cards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("Result로 낼 수 있는 가장 큰 결과 값을 return 하는지 확인한다.")
    @Test
    void getMaxCardScoreResult() {
        Cards cards = new Cards();
        cards.addCard(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN));
        cards.addCard(new Card(CardNumber.ACE, CardPattern.DIA_PATTERN));
        cards.addCard(new Card(CardNumber.EIGHT, CardPattern.SPADE_PATTERN));

        Assertions.assertThat(cards.countMaxScore()).isEqualTo(20);
    }
}
