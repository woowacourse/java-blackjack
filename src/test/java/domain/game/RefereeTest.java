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
        Player dealer = blackJackGame.getDealer();
        List<Player> participants = blackJackGame.getParticipants();
        Player abel = participants.get(0);
        Player pobi = participants.get(1);
    
        dealer.initCards(new Card(Shape.HEART, Number.JACK), new Card(Shape.HEART, Number.QUEEN));
        dealer.drawStop();
    
        abel.initCards(new Card(Shape.HEART, Number.ACE), new Card(Shape.HEART, Number.QUEEN));
    
        pobi.initCards(new Card(Shape.HEART, Number.KING), new Card(Shape.HEART, Number.NINE));
        pobi.drawStop();
        blackJackGame.settingBetAmountToParticipantsBy(playerName -> 1000);
        
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