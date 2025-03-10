package domain;

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
        Card card1 = new Card(Symbol.DIAMOND, Number.JACK);
        Card card2 = new Card(Symbol.CLOVER, Number.JACK);
        Card card3 = new Card(Symbol.DIAMOND, Number.ACE);
        List<Card> cardList = List.of(card1, card2, card3);
        Cards cards = new Cards(cardList);

        assertThat(cards.calculateTotalCardNumber()).isEqualTo(21);
    }

    @Test
    void 카드리스트에_에이스가_있고_합이_21이하이면_에이스를_11로_간주하여_합을_구한다() {
        Card card1 = new Card(Symbol.DIAMOND, Number.TWO);
        Card card2 = new Card(Symbol.CLOVER, Number.TWO);
        Card card3 = new Card(Symbol.DIAMOND, Number.ACE);
        List<Card> cardList = List.of(card1, card2, card3);
        Cards cards = new Cards(cardList);

        assertThat(cards.calculateTotalCardNumber()).isEqualTo(15);
    }

    @Test
    void 카드리스트에_에이스가_없는경우_단순합을_구한다() {
        Card card1 = new Card(Symbol.DIAMOND, Number.TWO);
        Card card2 = new Card(Symbol.CLOVER, Number.TWO);
        Card card3 = new Card(Symbol.DIAMOND, Number.THREE);
        List<Card> cardList = List.of(card1, card2, card3);
        Cards cards = new Cards(cardList);

        assertThat(cards.calculateTotalCardNumber()).isEqualTo(7);
    }

    @Test
    void 카드리스트에_카드를_추가한다() {
        Card card1 = new Card(Symbol.DIAMOND, Number.TWO);
        Card card2 = new Card(Symbol.CLOVER, Number.TWO);
        Card card3 = new Card(Symbol.DIAMOND, Number.THREE);
        List<Card> cardList = List.of(card1, card2, card3);
        Cards cards = new Cards(cardList);

        Card providedCard = new Card(Symbol.CLOVER, Number.EIGHT);
        Cards newCards = cards.addCards(List.of(providedCard));

        List<Card> newCardList = List.of(card1, card2, card3, providedCard);
        Cards expected = new Cards(newCardList);
        assertThat(newCards).isEqualTo(expected);
    }

    @Test
    void 카드리스트의_합계가_21초과이면_true_아니면_false를_반환한다() {
        Card card1 = new Card(Symbol.DIAMOND, Number.TWO);
        Card card2 = new Card(Symbol.CLOVER, Number.EIGHT);
        Card card3 = new Card(Symbol.DIAMOND, Number.THREE);
        Card card4 = new Card(Symbol.DIAMOND, Number.JACK);
        List<Card> cardList = List.of(card1, card2, card3);
        Cards notExceedCards = new Cards(cardList);

        List<Card> otherCardList = List.of(card1, card2, card3, card4);
        Cards exceedCards = new Cards(otherCardList);

        assertAll(
                () -> assertThat(notExceedCards.isBurst()).isFalse(),
                () -> assertThat(exceedCards.isBurst()).isTrue()
        );
    }
}
