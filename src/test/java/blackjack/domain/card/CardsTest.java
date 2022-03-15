package blackjack.domain.card;


import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.cardelement.Denomination;
import blackjack.domain.card.cardelement.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private Cards cards;
    
    @BeforeEach
    void setup() {
        cards = new Cards();
        Card card5 = Card.of(Denomination.valueof("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.valueof("6"), Suit.SPADE);
        cards.add(card5);
        cards.add(card6);
    }
    
    @Test
    @DisplayName("카드모음 생성되는지 검사")
    public void equalSizeTest() {
        assertThat(cards.size()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("카드모음 포인트 올바른지 검사")
    public void equalPointTest() {
        assertThat(cards.getPoint()).isEqualTo(11);
    }
}
