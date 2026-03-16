package domain.card;

import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    void 카드_더미를_생성하면_비어있어야_한다() {
        //given & when
        Cards cards = new Cards();

        //then
        Assertions.assertEquals(cards.size(), 0);
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
    void 에이스를_11로_계산해도_카드_합계가_21을_넘지_않으면_에이스를_11로_취급한다() {
        //given
        Card card1 = new Card(Suit.SPADE, Rank.ACE);
        Card card2 = new Card(Suit.SPADE, Rank.TEN);
        Cards cards = new Cards();

        //when
        cards.add(card1);
        cards.add(card2);

        //then
        Assertions.assertEquals(cards.getTotalSum(), 21);
    }

    @Test
    void 에이스를_11로_계산했을_때_카드_합계가_21을_넘으면_에이스를_1로_취급한다() {
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
}
