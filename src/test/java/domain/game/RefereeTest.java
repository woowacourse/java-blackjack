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
    @DisplayName("참가자 블랙잭, 딜러 블랙잭이면 무승부")
    void participantBlackjack_dealerBlackjack() {
        // given
        dealer.draw(new Card(Shape.HEART, Number.ACE));
        dealer.draw(new Card(Shape.HEART, Number.QUEEN));
        abel.draw(new Card(Shape.DIAMOND, Number.ACE));
        abel.draw(new Card(Shape.DIAMOND, Number.QUEEN));
        int betAmount = 1000;
    
        // when
        int betResult = referee.decidePlayersBattleResults1(dealer, abel, betAmount);
        
        // then
        assertThat(betResult).isZero();
    }
}