package blackjack.domain.card;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class CardsTest {
    private Cards cards;
    
    @BeforeEach
    void setup() {
        cards = Cards.of();
        
        Card card5 = Card.of(Denomination.of("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.of("6"), Suit.SPADE);
        
        cards.add(card5);
        cards.add(card6);
    }
    
    @Test
    public void 카드모음_생성_테스트() {
        assertThat(cards.size()).isEqualTo(2);
    }
    
    @Test
    public void 카드모음_포인트_테스트() {
        assertThat(cards.getPoint()).isEqualTo(11);
    }
}
