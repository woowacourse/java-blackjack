package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Value;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드를 추가로 받고, 점수를 계산할 수 있다.")
    @Test
    void fillCardsTest() {
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(List.of(new Card(Value.KING, Shape.SPADE), new Card(Value.KING, Shape.HEART)));
        dealer.receiveCard(new Card(Value.TEN, Shape.SPADE));
        assertThat(dealer.getScore()).isEqualTo(23);
    }
}
