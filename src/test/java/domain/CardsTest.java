package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.constant.TrumpEmblem;
import domain.constant.TrumpNumber;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardsTest {

    @Test
    void 카드를_한장_더한다() {
        // given
        Cards cards = makeCards(TrumpNumber.ACE, TrumpNumber.EIGHT);
        Card card = new Card(TrumpNumber.SIX, TrumpEmblem.SPADE);

        // when
        cards.addOneCard(card);

        // then
        assertThat(cards.getCards()).hasSize(3);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, ACE, KING, 12",
            "ACE, THREE, FOUR, 18",
            "ACE, THREE, KING, 14",
    })
    void 카드들의_합을_구한다(TrumpNumber number1, TrumpNumber number2, TrumpNumber number3, int expected) {
        // given
        Cards cards = makeCards(number1, number2);
        cards.addOneCard(new Card(number3, TrumpEmblem.HEART));

        // when
        int sumCards = cards.sumCardScores();

        // then
        assertThat(sumCards).isEqualTo(expected);
    }

    private Cards makeCards(TrumpNumber number1, TrumpNumber number2) {
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(new Card(number1, TrumpEmblem.DIAMOND));
        initialCards.add(new Card(number2, TrumpEmblem.HEART));
        return new Cards(initialCards);
    }
}
