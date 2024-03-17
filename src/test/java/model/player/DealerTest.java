package model.player;

import model.card.Card;
import model.card.Cards;
import model.card.Denomination;
import model.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DealerTest {

    @DisplayName("카드의 합이 16이하일 때는 참을 반환한다.")
    @Test
    void noticeTrue() {
        Dealer dealer = new Dealer(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.TWO))));

        assertTrue(dealer.isHit());
    }

    @DisplayName("카드의 합이 16초과일 때는 거짓을 반환한다.")
    @Test
    void noticeFalse() {
        Dealer dealer = new Dealer(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.TWO))));

        dealer.addCard(Card.of(Suit.CLOVER, Denomination.NINE));
        assertFalse(dealer.isHit());
    }
}
