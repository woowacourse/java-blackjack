package blackjacktest.domaintest.gamertest;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        dealer.receiveCard(new Card(Shape.CLOVER, Denomination.EIGHT));
        List<Card> cards = dealer.getCards();
        assertThat(cards.get(0)).isEqualTo(new Card(Shape.CLOVER, Denomination.EIGHT));
    }

    @Test
    @DisplayName("딜러 카드 반환 성공")
    void getDealerCards() {
        dealer.receiveCard(new Card(Shape.SPADE, Denomination.FOUR));
        dealer.receiveCard(new Card(Shape.CLOVER, Denomination.THREE));
        dealer.receiveCard(new Card(Shape.HEART, Denomination.ACE));

        List<Card> cards = dealer.getCards();
        assertTrue(cards.containsAll(Arrays.asList(new Card(Shape.SPADE, Denomination.FOUR),
                new Card(Shape.CLOVER, Denomination.THREE),
                new Card(Shape.HEART, Denomination.ACE))));
    }
}
