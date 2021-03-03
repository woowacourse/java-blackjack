package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("점수가 16 초과일 경우 카드를 뽑지 않는 기능")
    void isContinue() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.QUEEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN)));

        dealer.drawCard(deck);
        dealer.drawCard(deck);
        assertThat(dealer.isContinue()).isFalse();
    }
}
