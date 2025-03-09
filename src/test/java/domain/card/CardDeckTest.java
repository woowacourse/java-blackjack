package domain.card;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {

    @Test
    void 카드를_1장_드로우한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();

        //when
        Card actual = cardDeck.drawCard();

        //then
        Card expected = new Card(Pattern.SPADE, CardNumber.KING);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 게임_시작을_위해_카드를_2장_드로우한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();

        //when
        List<Card> actual = cardDeck.drawCardWhenStart();

        //then
        List<Card> expected = List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.QUEEN));
        assertThat(actual).containsExactlyElementsOf(expected);
    }
}
