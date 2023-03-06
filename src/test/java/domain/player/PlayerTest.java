package domain.player;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @ParameterizedTest(name = "participantCardNumber : {0}, dealerResult : {1}, participantResult : {2}")
    @CsvSource(value = {"EIGHT,WIN,LOSE", "JACK,LOSE,WIN"})
    @DisplayName("딜러와 참가자가 배틀할 때, 딜러가 이긴 경우 딜러 1승, 참가자 패가 반환된다.")
    void battleResult_dealerWin_participantLose(Number participantCardNumber, GameResult dealerResult, GameResult participantResult) {
        Player dealer = new Dealer();
        dealer.addCard(new Card(Shape.HEART, Number.KING));
        dealer.addCard(new Card(Shape.HEART, Number.NINE));
        
        Player abel = new Participant("abel");
        abel.addCard(new Card(Shape.HEART, Number.QUEEN));
        abel.addCard(new Card(Shape.HEART, participantCardNumber));
    
        GameResult dealerGameResult = dealer.battleResult(abel);
        GameResult abelGameResult = abel.battleResult(dealer);
        
        assertAll(
                () -> assertThat(dealerGameResult).isEqualTo(dealerResult),
                () -> assertThat(abelGameResult).isEqualTo(participantResult)
        );
    }
}