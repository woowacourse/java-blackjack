package card;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Suit;
import constant.Rank;
import java.util.ArrayList;
import java.util.List;

import game.Card;
import game.Cards;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, EIGHT, ACE, true",
            "EIGHT, SEVEN, SIX, true",
            "KING, KING, TWO, false",
            "QUEEN, JACK, KING, false"
    })
    void 카드를_한장_받았을_때_21이_넘는지_확인한다(Rank number1, Rank number2, Rank number3, boolean expected) {
        // given
        Cards cards = makeCards(number1, number2);
        Card card = new Card(number3, Suit.SPADE);

        // when
        boolean isOverBustStandard = cards.addOneCard(card);

        // then
        assertThat(isOverBustStandard).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, ACE, KING, 12",
            "ACE, THREE, FOUR, 18",
            "ACE, THREE, KING, 14",
    })
    void 카드들의_합을_구한다(Rank number1, Rank number2, Rank number3, int expected) {
        // given
        Cards cards = makeCards(number1, number2);
        cards.addOneCard(new Card(number3, Suit.HEART));

        // when
        int sumCards = cards.sumCardNumbers();

        // then
        assertThat(sumCards).isEqualTo(expected);
    }

    private Cards makeCards(Rank number1, Rank number2) {
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(new Card(number1, Suit.DIAMOND));
        initialCards.add(new Card(number2, Suit.HEART));
        return new Cards(initialCards);
    }
}
