package domain.game;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.player.Participant;
import domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RefereeTest {
    private Referee referee;
    
    @BeforeEach
    void setUp() {
        referee = new Referee();
    }
    
    @Test
    @DisplayName("딜러와 참가자의 배틀 결과를 계산 후 반환한다.")
    void saveBattleResults() {
        // given
        BlackJackGame blackJackGame = new BlackJackGame("아벨,포비", cards -> cards);
        blackJackGame.settingBetAmountToParticipantsBy(playerName -> 1000);
        Player dealer = blackJackGame.getDealer();
        List<Player> participants = blackJackGame.getParticipants();
        Player abel = participants.get(0);
        Player pobi = participants.get(1);
        
        dealer.draw(new Card(Shape.HEART, Number.JACK));
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        dealer.drawStop();
        
        abel.draw(new Card(Shape.DIAMOND, Number.ACE));
        abel.draw(new Card(Shape.DIAMOND, Number.QUEEN));
        
        pobi.draw(new Card(Shape.SPADE, Number.KING));
        pobi.draw(new Card(Shape.SPADE, Number.NINE));
        pobi.drawStop();
        
        // when
        referee.saveBattleResults(blackJackGame);
        
        // then
        assertAll(
                () -> assertThat(referee.findProfitByPlayer(dealer)).isEqualTo(-500),
                () -> assertThat(referee.findProfitByPlayer(abel)).isEqualTo(1500),
                () -> assertThat(referee.findProfitByPlayer(pobi)).isEqualTo(-1000)
        );
    }
}