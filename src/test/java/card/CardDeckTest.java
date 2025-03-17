package card;

import static card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    void 모든_카드를_생성한다() {
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);

        assertThat(cardDeck.getDeck()).hasSize(52);
    }

    @Test
    void 카드를_1장_드로우한다() {
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);

        List<Card> actual = cardDeck.drawCard(DRAW_COUNT_WHEN_HIT);

        Card expected = new Card(Pattern.SPADE, CardNumber.KING);
        assertThat(actual).contains(expected);
    }

    @Test
    void 카드를_1장_드로우하면_덱의_카드_수가_1개_줄어든다() {
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);

        cardDeck.drawCard(DRAW_COUNT_WHEN_HIT);

        assertThat(cardDeck.getDeck()).hasSize(51);
    }

    @Test
    void 게임_시작을_위해_카드를_2장_드로우한다() {
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);

        List<Card> actual = cardDeck.drawCard(CardDeck.DRAW_COUNT_WHEN_START);

        List<Card> expected = List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.QUEEN));
        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    void 카드_덱이_비어있으면_예외를_던진다() {
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);
        int deckSize = cardDeck.getDeck().size();
        for (int i = 0; i < deckSize; i++) {
            cardDeck.drawCard(DRAW_COUNT_WHEN_HIT);
        }

        assertThatThrownBy(() -> cardDeck.drawCard(DRAW_COUNT_WHEN_HIT))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
