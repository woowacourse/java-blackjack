package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러 생성")
    void createDealer() {
        assertThat(dealer).isEqualTo(new Dealer());
    }

    @Test
    @DisplayName("딜러 카드 추가 성공")
    void receiveCard() {
        dealer.firstDraw(new Card(Shape.CLOVER, Denomination.TWO), new Card(Shape.CLOVER, Denomination.SEVEN));
        dealer.draw(new Card(Shape.CLOVER, Denomination.EIGHT));
        Cards cards = dealer.getCurrentCards();
        assertThat(cards.getCards().get(2)).isEqualTo(new Card(Shape.CLOVER, Denomination.EIGHT));
    }

    @Test
    @DisplayName("딜러 카드 반환 성공")
    void getDealerCards() {
        dealer.firstDraw(new Card(Shape.SPADE, Denomination.FOUR), new Card(Shape.CLOVER, Denomination.THREE));
        dealer.draw(new Card(Shape.HEART, Denomination.ACE));

        Cards cards = dealer.getCurrentCards();
        assertTrue(cards.getCards().containsAll(Arrays.asList(new Card(Shape.SPADE, Denomination.FOUR),
                new Card(Shape.CLOVER, Denomination.THREE),
                new Card(Shape.HEART, Denomination.ACE))));
    }

    @Test
    @DisplayName("카드를 더 뽑을 수 있는지 확인")
    void canDraw() {
        dealer.firstDraw(new Card(Shape.DIAMOND, Denomination.ACE), new Card(Shape.DIAMOND, Denomination.THREE));
        dealer.draw(new Card(Shape.HEART, Denomination.ACE));
        assertTrue(dealer.canDraw());
    }

    @Test
    @DisplayName("카드를 뽑을 수 없는 경우 확인")
    void cannotDraw() {
        dealer.firstDraw(
                new Card(Shape.DIAMOND, Denomination.KING),
                new Card(Shape.DIAMOND, Denomination.THREE)
        );
        dealer.draw(new Card(Shape.DIAMOND, Denomination.FIVE));
        assertFalse(dealer.canDraw());
    }
}
