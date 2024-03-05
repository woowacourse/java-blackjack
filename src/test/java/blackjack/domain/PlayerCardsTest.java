package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerCardsTest {
    @Test
    @DisplayName("한 장의 카드를 추가할 수 있다.")
    void addCardTest() {
        Card card = new Card(Shape.DIAMOND, Value.ACE);
        PlayerCards playerCards = new PlayerCards(new ArrayList<>());

        playerCards.append(card);
        List<Card> cards = playerCards.getCards();
        assertThat(cards).hasSize(1);
    }
}
