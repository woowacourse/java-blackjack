package blackjacktest.domaintest.cardtest;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @Test
    @DisplayName("카드 꾸러미 생성")
    void createCards() {
        Cards cards1 = new Cards(new Card(Shape.CLOVER, Denomination.FOUR));
        Cards cards2 = new Cards(Arrays.asList(
           new Card(Shape.SPADE, Denomination.ACE),
           new Card(Shape.HEART, Denomination.FIVE)
        ));
        assertThat(cards1).isNotNull();
        assertThat(cards2).isNotNull();
    }
}
