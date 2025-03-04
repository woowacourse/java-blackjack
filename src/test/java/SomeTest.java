import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Denomination;
import domain.Suit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SomeTest {

    @Test
    @DisplayName("카드는 모양과 끗수를 가진다")
    public void test1() {
        assertThat(new Card(Denomination.ACE, Suit.CLUB)).isInstanceOf(Card.class);
    }

}
