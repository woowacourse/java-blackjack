package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CardTest {


    @Test
    void A클로버라는_카드가_생성된다(){

        Card card = new Card(CardPoint.ACE,CardPattern.CLUB);
        assertNotNull(card);

        assertThat(card.getCardPointName()).isEqualTo("A");
        assertThat(card.getCardPoint()).isEqualTo(11);
    }
}
