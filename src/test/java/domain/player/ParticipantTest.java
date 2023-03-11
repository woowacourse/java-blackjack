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

class ParticipantTest {
    private Player abel;
    
    @BeforeEach
    void setUp() {
        abel = new Participant("Abel");
    }
    
    @Test
    @DisplayName("딜러가 아닌 경우 false반환")
    void isDealer_false() {
        assertThat(abel.isDealer()).isFalse();
    }
    
    @DisplayName("버스트인지 확인")
    @ParameterizedTest(name = "number : {0}, isBust : {1}")
    @CsvSource(value = {"FIVE,false", "SIX,true"})
    void isBust(Number number, boolean isBust) {
        abel.draw(new Card(Shape.HEART, Number.QUEEN));
        abel.draw(new Card(Shape.HEART, Number.SIX));
        abel.draw(new Card(Shape.DIAMOND, number));
        
        assertThat(abel.isBust()).isEqualTo(isBust);
    }
    
    @DisplayName("총 점수 구하기")
    @ParameterizedTest(name = "number : {0}, totalScore : {1}")
    @CsvSource(value = {"FIVE,21", "SIX,12"})
    void getTotalScore(Number number, int totalScore) {
        abel.draw(new Card(Shape.HEART, Number.ACE));
        abel.draw(new Card(Shape.HEART, Number.FIVE));
        abel.draw(new Card(Shape.DIAMOND, number));
        
        assertThat(abel.getTotalScore().getScore()).isEqualTo(totalScore);
    }
}