package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드덱이 정상적으로 카드를 분배하는지 확인")
    void distribute() {
        assertThat(CardDeck.size()).isEqualTo(52);
        assertThat(CardDeck.distribute()).isInstanceOf(Card.class);
        assertThat(CardDeck.size()).isEqualTo(51);
    }

}
