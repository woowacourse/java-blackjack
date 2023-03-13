package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    @DisplayName("카드를 추가한 뒤, 플레이어 점수를 가져올 수 있다.")
    void givenCard_whenGetScore() {
        hand.addCard(new Card(Shape.SPADE, Number.THREE));

        assertThat(hand.getTotalScore()).isEqualTo(new Score(3));
    }

    @Test
    @DisplayName("나머지 점수가 10 이하인 경우, Ace의 점수를 11로 한다.")
    void givenScore_whenOrLessTen() {
        hand.addCard(new Card(Shape.SPADE, Number.THREE));
        hand.addCard(new Card(Shape.HEART, Number.ACE));
        hand.addCard(new Card(Shape.SPADE, Number.SEVEN));
    
        assertThat(hand.getTotalScore()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("나머지 점수가 11 이상인 경우, Ace의 점수를 1로 한다.")
    void givenScore_whenOrMoreEleven() {
        hand.addCard(new Card(Shape.SPADE, Number.THREE));
        hand.addCard(new Card(Shape.HEART, Number.ACE));
        hand.addCard(new Card(Shape.SPADE, Number.EIGHT));
    
        assertThat(hand.getTotalScore()).isEqualTo(new Score(12));
    }

    @Test
    @DisplayName("점수가 21을 초과했을 경우 Bust 된다.")
    void givenOverTwentyOneScore_thenBust() {
        hand.addCard(new Card(Shape.SPADE, Number.KING));
        hand.addCard(new Card(Shape.SPADE, Number.QUEEN));
        hand.addCard(new Card(Shape.SPADE, Number.TWO));

        assertThat(hand.isBust()).isTrue();
    }

    @Test
    @DisplayName("점수가 21 이하일 경우 Bust되지 않는다.")
    void givenOrLessTwentyOneScore_thenNotBust() {
        hand.addCard(new Card(Shape.SPADE, Number.KING));
        hand.addCard(new Card(Shape.SPADE, Number.ACE));
        hand.addCard(new Card(Shape.SPADE, Number.QUEEN));

        assertThat(hand.isBust()).isFalse();
    }
    
    @Test
    @DisplayName("에이스 2장인 경우 12")
    void aceAce() {
        hand.addCard(new Card(Shape.HEART, Number.ACE));
        hand.addCard(new Card(Shape.SPADE, Number.ACE));
    
        assertThat(hand.getTotalScore()).isEqualTo(new Score(12));
    }
    
    @Test
    @DisplayName("에이스 4장인 경우 12")
    void aceAceAceAce() {
        hand.addCard(new Card(Shape.HEART, Number.ACE));
        hand.addCard(new Card(Shape.SPADE, Number.ACE));
        hand.addCard(new Card(Shape.DIAMOND, Number.ACE));
        hand.addCard(new Card(Shape.CLOVER, Number.ACE));
    
        assertThat(hand.getTotalScore()).isEqualTo(new Score(14));
    }
    
    @Test
    @DisplayName("카드의 개수가 2장 미만인 경우 true 반환")
    void isNotEnoughInitCardsCount() {
        hand.addCard(new Card(Shape.HEART, Number.ACE));
        
        assertThat(hand.isNotEnoughInitCardsCount()).isTrue();
    }
    
    @Test
    @DisplayName("카드의 개수가 2장 이상인 경우 false 반환")
    void isEnoughInitCardsCount() {
        hand.addCard(new Card(Shape.HEART, Number.ACE));
        hand.addCard(new Card(Shape.HEART, Number.KING));
        
        assertThat(hand.isNotEnoughInitCardsCount()).isFalse();
    }
    
    @Test
    @DisplayName("스코어가 21이면 true 반환")
    void is21() {
        hand.addCard(new Card(Shape.HEART, Number.ACE));
        hand.addCard(new Card(Shape.HEART, Number.KING));
        
        assertThat(hand.isBlackJack()).isTrue();
    }
    
    @Test
    @DisplayName("스코어가 21이 아니면 false 반환")
    void isNot21() {
        hand.addCard(new Card(Shape.HEART, Number.QUEEN));
        hand.addCard(new Card(Shape.HEART, Number.KING));
        
        assertThat(hand.isBlackJack()).isFalse();
    }
}