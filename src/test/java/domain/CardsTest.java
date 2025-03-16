package domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.constant.TrumpSuit;
import blackjack.constant.TrumpRank;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardsTest {

    @Test
    void 카드를_한장_더한다() {
        // given
        Cards cards = makeCards(TrumpRank.ACE, TrumpRank.EIGHT);
        Card card = new Card(TrumpRank.SIX, TrumpSuit.SPADE);

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
    void 카드들의_합을_구한다(TrumpRank rank1, TrumpRank rank2, TrumpRank rank3, int expected) {
        // given
        Cards cards = makeCards(rank1, rank2);
        cards.addOneCard(new Card(rank3, TrumpSuit.HEART));

        // when
        int sumCards = cards.sumCardScores();

        // then
        assertThat(sumCards).isEqualTo(expected);
    }

    private Cards makeCards(TrumpRank rank1, TrumpRank rank2) {
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(new Card(rank1, TrumpSuit.DIAMOND));
        initialCards.add(new Card(rank2, TrumpSuit.HEART));
        return new Cards(initialCards);
    }
}
