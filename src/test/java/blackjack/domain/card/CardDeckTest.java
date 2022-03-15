package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {
    
    @Test
    @DisplayName("카드덱 생성되는지 검사")
    public void createTest() {
        CardDeck cardDeck = CardDeck.newInstance();
        assertThat(cardDeck.size()).isEqualTo(52);
    }
    
    @Test
    @DisplayName("카드 주기 기능 검사")
    void drawTest() {
        // given
        CardDeck cardDeck = CardDeck.newInstance();
        
        // when
        cardDeck.draw();
        
        // then
        assertThat(cardDeck.size()).isEqualTo(51);
    }
    
    @Test
    @DisplayName("카드 주기 예외처리 검사")
    void drawErrorTest() {
        // given
        CardDeck cardDeck = CardDeck.newInstance();
        
        // when
        for (int i = 0; i < 52; i++) {
            cardDeck.draw();
        }
        
        // then
        assertThatThrownBy(cardDeck::draw)
                .isInstanceOf(Exception.class)
                .hasMessage("덱에 남은 카드가 없습니다");
    }
}
