package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static blackjack.domain.card.Cards.TOP_CARD;
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
        assertThat(dealer).isNotNull();
    }

    @Test
    @DisplayName("딜러 카드 추가 성공")
    void receiveCard() {
        dealer.receiveCard(new Card(Shape.CLOVER, Denomination.EIGHT));
        Cards cards = dealer.getCurrentCards();
        assertThat(cards.getCards().get(TOP_CARD)).isEqualTo(new Card(Shape.CLOVER, Denomination.EIGHT));
    }

    @Test
    @DisplayName("딜러 카드 반환 성공")
    void getDealerCards() {
        dealer.receiveCard(new Card(Shape.SPADE, Denomination.FOUR));
        dealer.receiveCard(new Card(Shape.CLOVER, Denomination.THREE));
        dealer.receiveCard(new Card(Shape.HEART, Denomination.ACE));

        Cards cards = dealer.getCurrentCards();
        assertTrue(cards.getCards().containsAll(Arrays.asList(new Card(Shape.SPADE, Denomination.FOUR),
                new Card(Shape.CLOVER, Denomination.THREE),
                new Card(Shape.HEART, Denomination.ACE))));
    }
}
