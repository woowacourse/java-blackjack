package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Hand;
import domain.card.Suits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
    @DisplayName("카드를 받아 자신의 카드 더미에 추가할 수 있다")
    @Test
    void hit() {
        User user = new Dealer();
        Card card1 = new Card(Denomination.TWO, Suits.HEART);
        Card card2 = new Card(Denomination.THREE, Suits.DIAMOND);
        user.hit(card1);
        user.hit(card2);
        Hand hand = user.getHand();

        assertThat(hand.getCards()).containsExactly(card1, card2);
    }

    @Test
    void test() {
        User dealer = new Dealer();
        long start = 0;
        long end = 0;

        start = System.nanoTime();
        assertTrue(dealer instanceof Dealer);
        end = System.nanoTime();
        System.out.println("instanceof: " + (end - start));

        double endSec = (double) end / 1_000_000_000;
        System.out.println(endSec + " seconds");
        start = System.nanoTime();
        assertTrue(dealer.isDealer());
        end = System.nanoTime();
        System.out.println("다형성: " + (end - start));
        endSec = (double) end / 1_000_000_000;
        System.out.println(endSec + " seconds");
    }
}
