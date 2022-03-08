package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    @DisplayName("카드 한 장을 저장한다.")
    void saveCard() {
        Cards cards = new Cards();
        cards.save(new Card(Suit.CLOVER,Denomination.FIVE));
        assertThat(cards.getCards().size()).isEqualTo(1);
    }
}
