package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    @Test
    void 딜러가_카드를_뽑으면_손패의_크기가_1_증가한다() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.draw(new Card(Suit.CLUBS, Rank.ACE));

        // then
        assertEquals(1, dealer.getHandSize());
    }

    @Test
    void 딜러의_카드_점수_합이_21을_초과하면_버스트_상태가_된다() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.draw(new Card(Suit.CLUBS, Rank.KING));
        dealer.draw(new Card(Suit.CLUBS, Rank.QUEEN));
        dealer.draw(new Card(Suit.CLUBS, Rank.JACK));

        // then
        assertTrue(dealer.isBurst());
    }

    @Test
    void 딜러가_stay하면_hit할수없다() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.CLUBS, Rank.NUM2));
        dealer.draw(new Card(Suit.DIAMONDS, Rank.NUM3));
        assertTrue(dealer.canHit());

        dealer.stay();

        assertFalse(dealer.canHit());
    }

    @Test
    void 딜러의_첫번째_카드이름을_반환한다() {
        Dealer dealer = new Dealer();
        Card first = new Card(Suit.CLUBS, Rank.ACE);
        dealer.draw(first);
        dealer.draw(new Card(Suit.DIAMONDS, Rank.NUM7));

        assertEquals(first.getCardName(), dealer.getFirstCardName());
    }

    @Test
    void 딜러의_카드합이_17이상이면_hit할수없다() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.CLUBS, Rank.NUM10));
        dealer.draw(new Card(Suit.DIAMONDS, Rank.NUM7));

        assertFalse(dealer.canHit());
    }

}