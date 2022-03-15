package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.element.Denomination;
import blackjack.domain.card.element.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    
    @Test
    @DisplayName("참여자 객체 생성 기능 검사")
    public void createTest() {
        Player player = Player.from("test");
        assertThat(player.getName()).isEqualTo("test");
    }
    
    @Test
    @DisplayName("참여자 객체 카드 추가 기능 검사")
    public void addCardTest() {
        // given
        Player player = Player.from("test");
        Card card5 = Card.of(Denomination.valueof("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.valueof("6"), Suit.SPADE);
        
        // when
        player.addCard(card5);
        player.addCard(card6);
        
        // then
        assertThat(player.getPoint()).isEqualTo(11);
    }
    
    @Test
    @DisplayName("참여자 객체 카드 2개인지 확인 기능 검사")
    public void isTwoCardTest() {
        // given
        Player player = Player.from("test");
        Card card5 = Card.of(Denomination.valueof("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.valueof("6"), Suit.SPADE);
        
        // when
        player.addCard(card5);
        player.addCard(card6);
        
        // then
        assertThat(player.isTwoCard()).isTrue();
    }
    
    @Test
    @DisplayName("참여자 히트 여부 확인 기능 참 리턴 검사")
    public void isAbleToHitTest() {
        // given
        Player player = Player.from("test");
        Card card5 = Card.of(Denomination.valueof("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.valueof("6"), Suit.SPADE);
        
        // when
        player.addCard(card5);
        player.addCard(card6);
        
        // then
        assertThat(player.isAbleToHit()).isTrue();
    }
    
    @Test
    @DisplayName("참여자 히트 여부 확인 기능 거짓 리턴 검사")
    public void isAbleToHitTest2() {
        // given
        Player player = Player.from("test");
        Card card5 = Card.of(Denomination.valueof("10"), Suit.SPADE);
        Card card6 = Card.of(Denomination.valueof("10"), Suit.SPADE);
        
        // when
        player.addCard(card5);
        player.addCard(card6);
        player.addCard(card6);
        
        // then
        assertThat(player.isAbleToHit()).isFalse();
    }
    
    @Test
    @DisplayName("카드모음 포인트 올바른지 검사")
    public void equalPointTest() {
        // given
        Player player = Player.from("test");
        Card card5 = Card.of(Denomination.valueof("2"), Suit.SPADE);
        Card card6 = Card.of(Denomination.valueof("9"), Suit.SPADE);
    
        // when
        player.addCard(card5);
        player.addCard(card6);
    
        // then
        assertThat(player.getPoint()).isEqualTo(11);
    }
}
