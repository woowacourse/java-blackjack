package domain.player;

import domain.card.Card;
import domain.card.Number;
import domain.card.Score;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    private Player dealer;
    
    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }
    
    @Test
    @DisplayName("딜러인 경우 true반환")
    void isDealer_true() {
        assertThat(dealer.isDealer()).isTrue();
    }
    
    @DisplayName("버스트인지 확인")
    @ParameterizedTest(name = "number : {0}, isBust : {1}")
    @CsvSource(value = {"FIVE,false", "SIX,true"})
    void isBust(Number number, boolean isBust) {
        dealer.initCards(new Card(Shape.HEART, Number.QUEEN), new Card(Shape.HEART, Number.SIX));
        dealer.draw(new Card(Shape.DIAMOND, number));
        
        assertThat(dealer.isBust()).isEqualTo(isBust);
    }
    
    @DisplayName("총 점수 구하기")
    @ParameterizedTest(name = "number : {0}, totalScore : {1}")
    @CsvSource(value = {"FIVE,21", "SIX,12"})
    void getTotalScore(Number number, int totalScore) {
        dealer.initCards(new Card(Shape.HEART, Number.ACE), new Card(Shape.HEART, Number.FIVE));
        dealer.draw(new Card(Shape.DIAMOND, number));
        
        assertThat(dealer.getTotalScore()).isEqualTo(new Score(totalScore));
    }
    
    @Test
    @DisplayName("스코어가 17 이상인 경우 true 반환")
    void isFinished() {
        dealer.initCards(new Card(Shape.HEART, Number.EIGHT), new Card(Shape.HEART, Number.NINE));
        
        assertThat(dealer.isFinished()).isTrue();
    }
    
    @Test
    @DisplayName("스코어가 16 이하인 경우 false 반환")
    void isNotFinished() {
        dealer.initCards(new Card(Shape.HEART, Number.EIGHT), new Card(Shape.HEART, Number.SEVEN));
        
        assertThat(dealer.isFinished()).isFalse();
    }
}