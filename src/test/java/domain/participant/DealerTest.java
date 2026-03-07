package domain.participant;

import static domain.Constant.DEALER_HIT_STAND_BOUNDARY;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Rank;
import domain.Suit;
import domain.card.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    void 정상적으로_생성되어야_한다(){
        assertDoesNotThrow(() -> new Dealer("딜러"));
    }

    @Test
    void 딜러_카드_중_첫번째_카드만_반환해야_한다(){
        Dealer dealer = new Dealer("딜러");

        dealer.add(new Card(Suit.CLUB, Rank.K));
        dealer.add(new Card(Suit.CLUB, Rank.ACE));
        dealer.add(new Card(Suit.CLUB, Rank.NINE));
        Card firstCard = dealer.getFirstCard();

        Assertions.assertEquals(firstCard, new Card(Suit.CLUB, Rank.K));
    }

    @Test
    void 딜러의_합계가_기준보다_작으면_참을_반환해야_한다(){
        Dealer dealer = new Dealer("딜러");

        dealer.add(new Card(Suit.CLUB, Rank.FOUR));
        dealer.add(new Card(Suit.CLUB, Rank.ACE));

        Assertions.assertEquals(dealer.decideHitStand(DEALER_HIT_STAND_BOUNDARY), true);
    }

    @Test
    void 딜러의_합계가_기준보다_크면_거짓을_반환해야_한다(){
        Dealer dealer = new Dealer("딜러");

        dealer.add(new Card(Suit.CLUB, Rank.SIX));
        dealer.add(new Card(Suit.CLUB, Rank.ACE));

        Assertions.assertEquals(dealer.decideHitStand(DEALER_HIT_STAND_BOUNDARY), false);
    }
}
