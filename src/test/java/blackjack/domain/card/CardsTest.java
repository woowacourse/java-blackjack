package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private static final int ALL_CARDS_SIZE = 52;

    @DisplayName("52장의 전체 카드 생성 테스트")
    @Test
    void createCards() {
        Cards cards = new Cards();
        Set<Card> notDuplicateCards = new HashSet<>();

        for (int i = 0; i < ALL_CARDS_SIZE; i++) {
            notDuplicateCards.add(cards.drawOneCard());
        }

        assertThat(notDuplicateCards).hasSize(ALL_CARDS_SIZE);

        for (CardShape cardShape : CardShape.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                assertThat(notDuplicateCards).contains(new Card(cardShape, cardNumber));
            }
        }

        assertThatThrownBy(cards::drawOneCard)
            .isInstanceOf(IndexOutOfBoundsException.class);
    }
}
