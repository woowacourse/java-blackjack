package domain.game;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.player.Dealer;
import domain.player.ParticipantGameResult;
import domain.player.Participant;
import domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static domain.player.ParticipantGameResult.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.assertAll;

class RefereeTest {
    private Referee referee;
    private Player dealer;
    private Participant abel;
    
    @BeforeEach
    void setUp() {
        referee = new Referee();
        dealer = new Dealer();
        abel = new Participant("abel");
    }
    
    @Test
    @DisplayName("딜러와 참가자들간의 1:다수가 배틀 시, 딜러와 참가자들 모두 올바른 게임 결과를 반환한다.")
    void battleResults() {
        // given
        BlackJackGame blackJackGame = new BlackJackGame("아벨,여우,포비", cards -> cards);
        blackJackGame.giveTwoCardToPlayers();
        
        // when
        referee.decidePlayersBattleResults(blackJackGame);
        Map<ParticipantGameResult, Integer> dealerGameResults = referee.dealerGameResults();
        Map<Player, ParticipantGameResult> participantResults = referee.participantsGameResults();
        List<Player> participants = blackJackGame.getParticipants();
    
        // then
        assertAll(
                () -> assertThat(dealerGameResults)
                        .contains(entry(DRAW, 1), entry(WIN, 2)),
                () -> assertThat(participantResults)
                        .contains(
                                entry(participants.get(0), DRAW),
                                entry(participants.get(1), LOSE),
                                entry(participants.get(2), LOSE)
                        )
        );
    }
    
    @Test
    @DisplayName("참가자 블랙잭, 딜러 블랙잭이면 0 반환")
    void participantBlackjack_dealerBlackjack() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.ACE));
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        abel.draw(new Card(Shape.DIAMOND, Number.ACE));
        abel.draw(new Card(Shape.DIAMOND, Number.QUEEN));
        int betAmount = 1000;
    
        // when
        double betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isZero();
    }
    
    @Test
    @DisplayName("참가자 블랙잭, 딜러 점수가 더 낮으면 배팅 금액의 1.5배 반환")
    void participantBlackjack_dealerNotBlackjack() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.KING));
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        abel.draw(new Card(Shape.DIAMOND, Number.ACE));
        abel.draw(new Card(Shape.DIAMOND, Number.QUEEN));
        int betAmount = 1000;
        
        // when
        double betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isEqualTo(1500);
    }
    
    @Test
    @DisplayName("참가자 블랙잭, 딜러 버스트면 배팅 금액의 1.5배 반환")
    void participantBlackjack_dealerBust() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.KING));
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        abel.draw(new Card(Shape.DIAMOND, Number.ACE));
        abel.draw(new Card(Shape.DIAMOND, Number.QUEEN));
        int betAmount = 1000;
        
        // when
        double betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isEqualTo(1500);
    }
    
    @Test
    @DisplayName("참가자가 버스트, 딜러 블랙잭이면 -배팅 금액 반환")
    void participantBust_dealerBlackjack() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.ACE));
        dealer.draw(new Card(Shape.HEART, Number.KING));
        abel.draw(new Card(Shape.DIAMOND, Number.JACK));
        abel.draw(new Card(Shape.DIAMOND, Number.QUEEN));
        abel.draw(new Card(Shape.DIAMOND, Number.KING));
        int betAmount = 1000;
        
        // when
        double betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isEqualTo(-1000);
    }
    
    @Test
    @DisplayName("참가자가 버스트, 딜러 Stay이면 -배팅 금액 반환")
    void participantBust_dealerStay() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        dealer.draw(new Card(Shape.HEART, Number.KING));
        abel.draw(new Card(Shape.DIAMOND, Number.JACK));
        abel.draw(new Card(Shape.DIAMOND, Number.QUEEN));
        abel.draw(new Card(Shape.DIAMOND, Number.KING));
        int betAmount = 1000;
        
        // when
        double betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isEqualTo(-1000);
    }
    
    @Test
    @DisplayName("참가자가 버스트, 딜러 버스트면 -배팅 금액 반환")
    void participantBust_dealerBust() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        dealer.draw(new Card(Shape.HEART, Number.KING));
        abel.draw(new Card(Shape.DIAMOND, Number.JACK));
        abel.draw(new Card(Shape.DIAMOND, Number.QUEEN));
        abel.draw(new Card(Shape.DIAMOND, Number.KING));
        int betAmount = 1000;
        
        // when
        double betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isEqualTo(-1000);
    }
    
    @Test
    @DisplayName("참가자가 19, 딜러 버스트면 배팅 금액 그대로 반환")
    void participant19_dealerBust() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        dealer.draw(new Card(Shape.HEART, Number.KING));
        dealer.draw(new Card(Shape.HEART, Number.JACK));
        abel.draw(new Card(Shape.DIAMOND, Number.JACK));
        abel.draw(new Card(Shape.DIAMOND, Number.NINE));
        abel.drawStop();
        int betAmount = 1000;
        
        // when
        double betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isEqualTo(1000);
    }
    
    @Test
    @DisplayName("참가자가 19, 딜러 18이면 배팅 금액 그대로 반환")
    void participant19_dealer18() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        dealer.draw(new Card(Shape.HEART, Number.EIGHT));
        dealer.drawStop();
        abel.draw(new Card(Shape.DIAMOND, Number.JACK));
        abel.draw(new Card(Shape.DIAMOND, Number.NINE));
        abel.drawStop();
        int betAmount = 1000;
        
        // when
        double betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isEqualTo(1000);
    }
    
    @Test
    @DisplayName("참가자가 19, 딜러 19이면 0원 반환")
    void participant19_dealer19() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        dealer.draw(new Card(Shape.HEART, Number.NINE));
        dealer.drawStop();
        abel.draw(new Card(Shape.DIAMOND, Number.JACK));
        abel.draw(new Card(Shape.DIAMOND, Number.NINE));
        abel.drawStop();
        int betAmount = 1000;
        
        // when
        double betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isZero();
    }
    
    @Test
    @DisplayName("참가자가 19, 딜러 20이면 -배팅 금액 반환")
    void participant19_dealer20() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        dealer.draw(new Card(Shape.HEART, Number.JACK));
        dealer.drawStop();
        abel.draw(new Card(Shape.DIAMOND, Number.JACK));
        abel.draw(new Card(Shape.DIAMOND, Number.NINE));
        abel.drawStop();
        int betAmount = 1000;
        
        // when
        double betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isEqualTo(-1000);
    }
    
    @Test
    @DisplayName("참가자가 19, 딜러 블랙잭이면 -배팅 금액 반환")
    void participant19_dealerBlackjack() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        dealer.draw(new Card(Shape.HEART, Number.ACE));
        abel.draw(new Card(Shape.DIAMOND, Number.JACK));
        abel.draw(new Card(Shape.DIAMOND, Number.NINE));
        abel.drawStop();
        int betAmount = 1000;
        
        // when
        double betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isEqualTo(-1000);
    }
}