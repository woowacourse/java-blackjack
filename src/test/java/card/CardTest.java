package card;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Suit;
import constant.Rank;
import java.util.ArrayList;
import java.util.List;

import game.Card;
import game.Cards;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 문양과_숫자로_카드를_생성한다() {
        // given
        Card card = createCard(Suit.DIAMOND, Rank.ACE);

        // then
        assertThat(card.getNumber()).isEqualTo(Rank.ACE);
        assertThat(card.getEmblem()).isEqualTo(Suit.DIAMOND);
    }

    @Test
    void 카드의_총합을_계산한다() {
        // given
        List<Card> emptyCards = new ArrayList<>();
        addCards(emptyCards);
        Cards cards = new Cards(emptyCards);

        // when
        int sum = cards.sumCardNumbers();

        // then
        assertThat(sum).isEqualTo(20);
    }

    private Card createCard(Suit emblem, Rank number) {
        return new Card(number, emblem);
    }

    private void addCards(List<Card> emptyCards) {
        emptyCards.add(createCard(Suit.DIAMOND, Rank.ACE));
        emptyCards.add(createCard(Suit.SPADE, Rank.TWO));
        emptyCards.add(createCard(Suit.CLOVER, Rank.THREE));
        emptyCards.add(createCard(Suit.HEART, Rank.FOUR));
    }

}
