package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("카드가 개수에 맞게 생성됐는지 테스트")
    @Test
    void size() {
        Cards cards = new Cards();
        assertThat(cards.getCardDeck().size()).isEqualTo(48);
    }
}
