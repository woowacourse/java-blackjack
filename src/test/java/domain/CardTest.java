package domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.constant.TrumpSuit;
import blackjack.constant.TrumpRank;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 문양과_숫자로_카드를_생성한다() {
        // given
        Card card = createCard(TrumpSuit.DIAMOND, TrumpRank.ACE);

        // then
        assertThat(card.getRank()).isEqualTo(TrumpRank.ACE);
        assertThat(card.getSuit()).isEqualTo(TrumpSuit.DIAMOND);
    }

    @Test
    void 카드의_총합을_계산한다() {
        // given
        List<Card> emptyCards = new ArrayList<>();
        addCards(emptyCards);
        Cards cards = new Cards(emptyCards);

        // when
        int sum = cards.sumCardScores();

        // then
        assertThat(sum).isEqualTo(20);
    }

    private Card createCard(TrumpSuit suit, TrumpRank rank) {
        return new Card(rank, suit);
    }

    private void addCards(List<Card> emptyCards) {
        emptyCards.add(createCard(TrumpSuit.DIAMOND, TrumpRank.ACE));
        emptyCards.add(createCard(TrumpSuit.SPADE, TrumpRank.TWO));
        emptyCards.add(createCard(TrumpSuit.CLOVER, TrumpRank.THREE));
        emptyCards.add(createCard(TrumpSuit.HEART, TrumpRank.FOUR));
    }

}
