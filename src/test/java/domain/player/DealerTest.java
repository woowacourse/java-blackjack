package domain.player;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DealerTest {
    private Player dealer;
    
    @BeforeEach
    void setUp() {
        dealer = Dealer.getInstance();
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
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        dealer.draw(new Card(Shape.HEART, Number.SIX));
        dealer.draw(new Card(Shape.DIAMOND, number));
        
        assertThat(dealer.isBust()).isEqualTo(isBust);
    }
    
    @DisplayName("총 점수 구하기")
    @ParameterizedTest(name = "number : {0}, totalScore : {1}")
    @CsvSource(value = {"FIVE,21", "SIX,12"})
    void getTotalScore(Number number, int totalScore) {
        dealer.draw(new Card(Shape.HEART, Number.ACE));
        dealer.draw(new Card(Shape.HEART, Number.FIVE));
        dealer.draw(new Card(Shape.DIAMOND, number));
        
        assertThat(dealer.getTotalScore().getScore()).isEqualTo(totalScore);
    }
}