package domain.game;

import domain.player.GameResult;
import domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class RefereeTest {
    @Test
    @DisplayName("딜러와 참가자들간의 1:다수가 배틀 시, 딜러와 참가자들 모두 올바른 게임 결과를 반환한다.")
    void battleResults() {
        // given
        Referee referee = new Referee();
        BlackJackGame blackJackGame = new BlackJackGame("아벨,여우,포비", cards -> cards);
        blackJackGame.giveTwoCardToPlayers();
        
        // when
        referee.decidePlayersBattleResults(blackJackGame);
        Map<GameResult, Integer> dealerGameResults = referee.dealerGameResults();
        Map<Player, GameResult> participantResults = referee.participantsGameResults();
        List<Player> participants = blackJackGame.getParticipants();
    
        // then
        assertAll(
                () -> assertThat(dealerGameResults)
                        .contains(entry(GameResult.DRAW, 1), entry(GameResult.WIN, 2)),
                () -> assertThat(participantResults)
                        .contains(
                                entry(participants.get(0), GameResult.DRAW),
                                entry(participants.get(1), GameResult.LOSE),
                                entry(participants.get(2), GameResult.LOSE)
                        )
        );
    }
}