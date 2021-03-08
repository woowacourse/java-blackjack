package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private static final int ALL_CARDS_SIZE = 52;

    @DisplayName("52장의 전체 카드로 채워진 Cards 생성 테스트")
    @Test
    void createAllShuffledCards() {
        Cards cards = Cards.createAllShuffledCards();
        Set<Card> notDuplicateCards = new HashSet<>();

        for (int i = 0; i < ALL_CARDS_SIZE; i++) {
            notDuplicateCards.add(cards.drawOneCard());
        }

        assertThat(notDuplicateCards).hasSize(ALL_CARDS_SIZE);

        for (CardShapeType cardShape : CardShapeType.values()) {
            for (CardNumberType cardNumber : CardNumberType.values()) {
                assertThat(notDuplicateCards).contains(new Card(cardShape, cardNumber));
            }
        }

        assertThatThrownBy(cards::drawOneCard)
            .isInstanceOf(IndexOutOfBoundsException.class);
    }
}
