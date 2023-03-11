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
        dealer = Dealer.getInstance();
        abel = new Participant("Abel");
    }
    
    @ParameterizedTest(name = "participantCardNumber : {0}, dealerResult : {1}, participantResult : {2}")
    @CsvSource(value = {"EIGHT,WIN,LOSE", "JACK,LOSE,WIN"})
    @DisplayName("딜러와 참가자가 배틀할 때, 딜러가 이긴 경우 딜러 1승, 참가자 패가 반환된다.")
    void battleResult_dealerWin_participantLose(Number participantCardNumber, ParticipantGameResult dealerResult, ParticipantGameResult participantResult) {
        dealer.draw(new Card(Shape.HEART, Number.KING));
        dealer.draw(new Card(Shape.HEART, Number.NINE));
        abel.draw(new Card(Shape.HEART, Number.QUEEN));
        abel.draw(new Card(Shape.HEART, participantCardNumber));
    
        ParticipantGameResult dealerParticipantGameResult = dealer.battleResult(abel);
        ParticipantGameResult abelParticipantGameResult = abel.battleResult(dealer);
        
        assertAll(
                () -> assertThat(dealerParticipantGameResult).isEqualTo(dealerResult),
                () -> assertThat(abelParticipantGameResult).isEqualTo(participantResult)
        );
    }
    
    @Test
    @DisplayName("딜러와 참가자가 모두 버스트인 경우 딜러 1승, 참가자 패가 반환된다.")
    void battleResult_dealerWin_participantLose() {
        dealer.draw(new Card(Shape.HEART, Number.KING));
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        dealer.draw(new Card(Shape.HEART, Number.JACK));
        abel.draw(new Card(Shape.DIAMOND, Number.KING));
        abel.draw(new Card(Shape.DIAMOND, Number.QUEEN));
        abel.draw(new Card(Shape.DIAMOND, Number.JACK));
        
        ParticipantGameResult dealerParticipantGameResult = dealer.battleResult(abel);
        ParticipantGameResult abelParticipantGameResult = abel.battleResult(dealer);
        
        assertAll(
                () -> assertThat(dealerParticipantGameResult).isEqualTo(ParticipantGameResult.WIN),
                () -> assertThat(abelParticipantGameResult).isEqualTo(ParticipantGameResult.LOSE)
        );
    }
}