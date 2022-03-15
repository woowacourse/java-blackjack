package blackjack.domain.card;


import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.element.Denomination;
import blackjack.domain.card.element.Suit;
import blackjack.domain.card.group.Cards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private Cards cards;
    
    @BeforeEach
    void setup() {
        cards = new Cards();
        Card card5 = Card.of(Denomination.stringOf("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.stringOf("6"), Suit.SPADE);
        cards.add(card5);
        cards.add(card6);
    }
    
    @Test
    @DisplayName("카드모음 생성되는지 검사")
    public void equalSizeTest() {
        assertThat(cards.size()).isEqualTo(2);
    }
    
    @Test
    void getFirstCard() {
    }
    
    @Test
    void getRawPoint() {
    }
    
    @Test
    void getAceCount() {
    }
}
