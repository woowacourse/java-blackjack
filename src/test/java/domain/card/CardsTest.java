package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("카드들 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CardsTest {

    @Test
    void 카드리스트에_에이스가_있고_합이_21을_초과하면_에이스를_1로_간주하여_합을_구한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.NINE);
        Card card2 = new Card(Suit.CLOVER, Rank.TWO);
        Card card3 = new Card(Suit.DIAMOND, Rank.ACE);
        Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.calculateTotalRank()).isEqualTo(12);
    }

    @Test
    void 카드리스트에_에이스가_있고_합이_21이하이면_에이스를_11로_간주하여_합을_구한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.EIGHT);
        Card card2 = new Card(Suit.CLOVER, Rank.TWO);
        Card card3 = new Card(Suit.DIAMOND, Rank.ACE);
        Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.calculateTotalRank()).isEqualTo(21);
    }

    @Test
    void 카드리스트에_에이스가_두장이면_한장의_에이스를_1로_간주하여_합을_구한다() {
        Card card1 = new Card(Suit.HEART, Rank.ACE);
        Card card2 = new Card(Suit.CLOVER, Rank.ACE);
        Cards cards = new Cards(List.of(card1, card2));

        assertThat(cards.calculateTotalRank()).isEqualTo(12);
    }

    @Test
    void 카드리스트에_에이스가_세장이면_두장의_에이스를_1로_간주하여_합을_구한다() {
        Card card1 = new Card(Suit.HEART, Rank.ACE);
        Card card2 = new Card(Suit.CLOVER, Rank.ACE);
        Card card3 = new Card(Suit.DIAMOND, Rank.ACE);
        Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.calculateTotalRank()).isEqualTo(13);
    }

    @Test
    void 카드리스트에_에이스가_없는경우_단순합을_구한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.TWO);
        Card card2 = new Card(Suit.CLOVER, Rank.TWO);
        Card card3 = new Card(Suit.DIAMOND, Rank.THREE);
        Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.calculateTotalRank()).isEqualTo(7);
    }

    @Test
    void 카드리스트에_카드를_추가한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.TWO);
        Card card2 = new Card(Suit.CLOVER, Rank.TWO);
        Card card3 = new Card(Suit.DIAMOND, Rank.THREE);
        List<Card> cards = List.of(card1, card2, card3);
        Cards oldCards = new Cards(cards);

        Card providedCard = new Card(Suit.CLOVER, Rank.EIGHT);
        Cards newCards = oldCards.addCards(List.of(providedCard));

        List<Card> newCardList = List.of(card1, card2, card3, providedCard);
        Cards expected = new Cards(newCardList);
        assertThat(newCards).isEqualTo(expected);
    }

    @Test
    void 카드리스트의_합계가_21초과이면_true를_반환한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.JACK);
        Card card2 = new Card(Suit.CLOVER, Rank.QUEEN);
        Card card3 = new Card(Suit.DIAMOND, Rank.ACE);
        Card card4 = new Card(Suit.DIAMOND, Rank.ACE);
        Cards exceedCards = new Cards(List.of(card1, card2, card3, card4));

        assertThat(exceedCards.isBlackjackScoreExceeded()).isTrue();
    }

    @Test
    void 카드리스트의_합계가_21이하이면_false를_반환한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.JACK);
        Card card2 = new Card(Suit.CLOVER, Rank.QUEEN);
        Card card3 = new Card(Suit.DIAMOND, Rank.ACE);
        Cards notExceedCards = new Cards(List.of(card1, card2, card3));

        assertThat(notExceedCards.isBlackjackScoreExceeded()).isFalse();
    }

    @Test
    void 카드리스트의_합계가_16초과이면_true를_반환한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.QUEEN);
        Card card2 = new Card(Suit.CLOVER, Rank.FIVE);
        Card card3 = new Card(Suit.DIAMOND, Rank.ACE);
        Card card4 = new Card(Suit.DIAMOND, Rank.ACE);
        Cards exceedCards = new Cards(List.of(card1, card2, card3, card4));

        assertThat(exceedCards.isDealerDrawLimitExceeded()).isTrue();
    }

    @Test
    void 카드리스트의_합계가_16이하이면_false를_반환한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.QUEEN);
        Card card2 = new Card(Suit.CLOVER, Rank.FIVE);
        Card card3 = new Card(Suit.DIAMOND, Rank.ACE);
        Cards notExceedCards = new Cards(List.of(card1, card2, card3));

        assertThat(notExceedCards.isDealerDrawLimitExceeded()).isFalse();
    }

    @Test
    void 카드리스트의_합계가_21이면_true를_반환한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.QUEEN);
        Card card2 = new Card(Suit.CLOVER, Rank.ACE);
        Cards equalToBlackjackScoreCards = new Cards(List.of(card1, card2));

        assertThat(equalToBlackjackScoreCards.equalToBlackjackScore()).isTrue();
    }

    @Test
    void 카드리스트의_합계가_21초과이면_false를_반환한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.QUEEN);
        Card card2 = new Card(Suit.CLOVER, Rank.ACE);
        Card card3 = new Card(Suit.SPADE, Rank.ACE);
        Cards exceedCards = new Cards(List.of(card1, card2, card3));

        assertThat(exceedCards.equalToBlackjackScore()).isFalse();
    }

    @Test
    void 카드리스트의_합계가_21미만이면_false를_반환한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.QUEEN);
        Card card2 = new Card(Suit.HEART, Rank.JACK);
        Cards underCards = new Cards(List.of(card1, card2));

        assertThat(underCards.equalToBlackjackScore()).isFalse();
    }
}
