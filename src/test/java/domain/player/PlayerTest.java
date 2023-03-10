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

class PlayerTest {
    private Player dealer;
    private Player abel;
    
    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        abel = new Participant("Abel");
    }
    
    @ParameterizedTest(name = "participantCardNumber : {0}, dealerResult : {1}, participantResult : {2}")
    @CsvSource(value = {"EIGHT,WIN,LOSE", "JACK,LOSE,WIN"})
    @DisplayName("딜러와 참가자가 배틀할 때, 딜러가 이긴 경우 딜러 1승, 참가자 패가 반환된다.")
    void battleResult_dealerWin_participantLose(Number participantCardNumber, GameResult dealerResult, GameResult participantResult) {
        dealer.addCard(new Card(Shape.HEART, Number.KING));
        dealer.addCard(new Card(Shape.HEART, Number.NINE));
        abel.addCard(new Card(Shape.HEART, Number.QUEEN));
        abel.addCard(new Card(Shape.HEART, participantCardNumber));
    
        GameResult dealerGameResult = dealer.battleResult(abel);
        GameResult abelGameResult = abel.battleResult(dealer);
        
        assertAll(
                () -> assertThat(dealerGameResult).isEqualTo(dealerResult),
                () -> assertThat(abelGameResult).isEqualTo(participantResult)
        );
    }
    
    @DisplayName("총 점수 구하기")
    @ParameterizedTest(name = "number : {0}, totalScore : {1}")
    @CsvSource(value = {"FIVE,21", "SIX,12"})
    void getTotalScore(Number number, int totalScore) {
        dealer.addCard(new Card(Shape.HEART, Number.ACE));
        dealer.addCard(new Card(Shape.HEART, Number.FIVE));
        dealer.addCard(new Card(Shape.DIAMOND, number));
        
        assertThat(dealer.getTotalScore()).isEqualTo(totalScore);
    }
    
    @DisplayName("버스트인지 확인")
    @ParameterizedTest(name = "number : {0}, isBust : {1}")
    @CsvSource(value = {"FIVE,false", "SIX,true"})
    void isBust(Number number, boolean isBust) {
        dealer.addCard(new Card(Shape.HEART, Number.QUEEN));
        dealer.addCard(new Card(Shape.HEART, Number.SIX));
        dealer.addCard(new Card(Shape.DIAMOND, number));
        
        assertThat(dealer.isBust()).isEqualTo(isBust);
    }
    
    @Test
    @DisplayName("딜러인 경우 true반환")
    void isDealer_true() {
        assertThat(dealer.isDealer()).isTrue();
    }
    
    @Test
    @DisplayName("딜러인 경우 false반환")
    void isDealer_false() {
        assertThat(abel.isDealer()).isFalse();
    }
}