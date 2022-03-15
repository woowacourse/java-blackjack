package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.cardElement.Denomination;
import blackjack.domain.card.cardElement.Suit;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PlayerTest {
    
    @Test
    public void 참여자생성() {
        Player player = Player.of("test");
        assertThat(player.getName()).isEqualTo("test");
    }
    
    @Test
    public void 참여자에_카드_추가() {
        Player player = Player.of("test");
        
        Card card5 = Card.of(Denomination.of("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.of("6"), Suit.SPADE);
        player.addCard(card5);
        player.addCard(card6);
        
        assertThat(player.getPoint())
                .isEqualTo(11);
    }
    
    @Test
    public void 참여자_투카드() {
        Player player = Player.of("test");
        
        Card card5 = Card.of(Denomination.of("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.of("6"), Suit.SPADE);
        player.addCard(card5);
        player.addCard(card6);
        
        assertThat(player.isTwoCard())
                .isTrue();
    }
    
    @Test
    public void 참여자_히트여부() {
        Player player = Player.of("test");
        
        Card card5 = Card.of(Denomination.of("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.of("6"), Suit.SPADE);
        player.addCard(card5);
        player.addCard(card6);
        
        assertThat(player.isAbleToHit())
                .isTrue();
    }
    
    @Test
    public void 참여자_히트여부_2() {
        Player player = Player.of("test");
        
        Card card5 = Card.of(Denomination.of("10"), Suit.SPADE);
        Card card6 = Card.of(Denomination.of("10"), Suit.SPADE);
        player.addCard(card5);
        player.addCard(card6);
        player.addCard(card6);
        
        assertThat(player.isAbleToHit())
                .isFalse();
    }
}
