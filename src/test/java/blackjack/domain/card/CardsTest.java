package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드 목록에 카드 추가 확인")
    void addCardTest() {
        Cards cards = new Cards(new ArrayList<>());

        Card card = Card.draw();
        cards.add(card);
        assertThat(cards.getCards().get(0) == card)
                .isTrue();
    }
}