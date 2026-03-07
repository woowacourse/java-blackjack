package domain.card;

import static domain.Constant.BLACKJACK_MAX_NUMBER;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    void 카드_더미가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> new Cards());
    }

    @Test
    void 카드가_정상적으로_추가되어야_한다() {
        //given
        Card card = new Card(Suit.SPADE, Rank.ACE);
        Cards cards = new Cards();

        //when
        cards.add(card);

        //then
        Assertions.assertEquals(cards.size(), 1);
    }

    @Test
    void 카드_합계가_정상적으로_수행되어야_한다() {
        //given
        Card card1 = new Card(Suit.SPADE, Rank.ACE);
        Card card2 = new Card(Suit.SPADE, Rank.TEN);
        Cards cards = new Cards();

        //when
        cards.add(card1);
        cards.add(card2);

        //then
        Assertions.assertEquals(cards.getTotalSum(), BLACKJACK_MAX_NUMBER);
    }

    @Test
    void 에이스가_여러개인_경우_카드_합계가_정상적으로_수행되어야_한다() {
        //given
        Card card1 = new Card(Suit.SPADE, Rank.ACE);
        Card card2 = new Card(Suit.HEART, Rank.ACE);
        Card card3 = new Card(Suit.CLUB, Rank.ACE);
        Card card4 = new Card(Suit.SPADE, Rank.TEN);
        Cards cards = new Cards();

        //when
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        //then
        Assertions.assertEquals(cards.getTotalSum(), 13);
    }

    @Test
    void 뽑을_수_있는_카드가_존재하지_않으면_예외가_발생한다(){
        Cards cards = new Cards();
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> cards.pull()).isInstanceOf(IllegalArgumentException.class);
    }
}
