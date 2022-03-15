package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeckTest {

    private static Deck deck;
    private static Card card;

    @BeforeAll
    static void initDeck() {
        card = Card.of(CardSymbol.DIAMOND, CardNumber.ACE);
        deck = Deck.from(() -> List.of(card));
    }

    @Test
    @Order(1)
    @DisplayName("한 장 뽑는다.")
    void drawCard() {
        // when
        final Card actual = deck.drawCard();

        // then
        assertThat(actual).isEqualTo(card);
    }

    @Test
    @Order(2)
    @DisplayName("덱이 비어있으면 Null을 반환한다.")
    void emptyDeck() {
        // when
        final Card actual = deck.drawCard();

        // then
        assertThat(actual).isNull();
    }
}
