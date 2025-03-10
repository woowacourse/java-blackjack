package domain;

import domain.ScoreResult.BattleResults;
import domain.ScoreResult.ScoreBoard;
import domain.card.ParticipantCardDeck;
import domain.ScoreResult.BattleResult;
import domain.game.GameBoard;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    @Test
    void 게임판_테스트() {
        //given
        Player player1 = new Player("우가");
        Player player2 = new Player("히스타");

        //when
        GameBoard gameBoard = new GameBoard(new Participants(List.of(player1, player2)));

        //then
        Assertions.assertThat(gameBoard).isInstanceOf(GameBoard.class);
    }

    @Test
    void 카드를_모두에게_2장씩_나눠준다() {
        //given
        List<Participant> originParticipants = List.of(
                new Player("우가"),
                new Player("히스타"),
                new Dealer()
        );
        Participants participantsGroup = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participantsGroup);

        //when
        gameBoard.drawTwoCards();

        List<Participant> participants = participantsGroup.getParticipants();

        for (Participant participant : participants) {
            ParticipantCardDeck participantCardDeck = participant.getCardDeck();
            Assertions.assertThat(participantCardDeck.getCards().size()).isEqualTo(2);
        }
    }

    @DisplayName("총 플레잉 카드 52장에서 참가자 3명이 각각 2장씩 가져가 총 46장이 남는다.")
    @Test
    void 카드덱_2장_빠지기() {
        //given
        List<Participant> originParticipants = List.of(
                new Player("우가"),
                new Player("히스타"),
                new Dealer()
        );
        Participants participantsGroup = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participantsGroup);

        //when
        gameBoard.drawTwoCards();

        Assertions.assertThat(gameBoard.getPlayingCard().getCards().size()).isEqualTo(46);
    }

    @Test
    void 카드를_참가자에게_1장_준다() {
        //given
        Participant targetParticipant = new Player("우가");
        List<Participant> originParticipants = List.of(
                targetParticipant,
                new Player("히스타"),
                new Dealer()
        );
        Participants participantsGroup = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participantsGroup);

        //when
        gameBoard.drawCardTo(targetParticipant);
        List<Participant> participants = participantsGroup.getParticipants();
        //then
        ParticipantCardDeck ownedParticipantCardDeck = participants.getFirst().getCardDeck();
        Assertions.assertThat(ownedParticipantCardDeck.getCards().size()).isEqualTo(1);
    }

    @Test
    void 한명의_카드덱을_가져온다() {
        //given
        Participant targetParticipant = new Player("우가");
        List<Participant> originParticipants = List.of(
                targetParticipant,
                new Player("히스타"),
                new Dealer()
        );
        Participants participantsGroup = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participantsGroup);

        gameBoard.drawCardTo(targetParticipant);

        //when & then
        Assertions.assertThat(targetParticipant.getCardDeck().getCards().size()).isEqualTo(1);
    }

    @Test
    void 한명의_총_점수를_계산한다() {
        //given
        Participant targetParticipant = new Player("우가");
        List<Participant> originParticipants = List.of(
                targetParticipant,
                new Player("히스타"),
                new Dealer()
        );
        Participants participantsGroup = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participantsGroup);

        gameBoard.drawCardTo(targetParticipant);

        //when
        int actual = targetParticipant.getScore();

        //then
        Assertions.assertThat(actual).isEqualTo(11);
    }

    @Test
    void 딜러_점수_스테이_확인() {
        //given
        Participant targetParticipant = new Dealer();
        List<Participant> originParticipants = List.of(
                targetParticipant,
                new Player("히스타"),
                new Player("우가")
        );
        Participants participantsGroup = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participantsGroup);

        for (int i = 0; i < 6; i ++) {
            gameBoard.drawCardTo(targetParticipant);
        }

        //when
        boolean actual = gameBoard.ableToDraw(targetParticipant);

        //then
        Assertions.assertThat(actual).isFalse();
    }

    @Test
    void 참가자_점수_스테이_확인() {
        //given
        Participant targetParticipant = new Player("우가");
        List<Participant> originParticipants = List.of(
                targetParticipant,
                new Player("히스타"),
                new Dealer()
        );
        Participants participantsGroup = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participantsGroup);

        for (int i = 0; i < 12; i ++) {
            gameBoard.drawCardTo(targetParticipant);
        }

        //when
        boolean actual = gameBoard.ableToDraw(targetParticipant);

        //then
        Assertions.assertThat(actual).isFalse();
    }

    @Test
    void 에이스_점수_계산_확인() {
        //given
        Participant targetParticipant = new Player("우가");
        List<Participant> originParticipants = List.of(
                targetParticipant,
                new Player("히스타"),
                new Dealer()
        );
        Participants participantsGroup = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participantsGroup);

        gameBoard.drawCardTo(targetParticipant);
        gameBoard.drawCardTo(targetParticipant);
        gameBoard.drawCardTo(targetParticipant);

        //when
        int actual = targetParticipant.getScore();

        //then
        Assertions.assertThat(actual).isEqualTo(13);
    }

    @Test
    void 승패_계산_확인() {
        //given
        Participant participant1 = new Player("우가");
        Participant participant2 = new Player("히스타");
        Participant dealer = new Dealer();

        List<Participant> originParticipants = List.of(
                participant1,
                participant2,
                dealer
        );
        Participants participants = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participants);

        gameBoard.drawTwoCards();

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);
        scoreBoard.calculateScoreBoard();
        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(participant1).getResults().containsKey(BattleResult.WIN)).isTrue(),
                () -> Assertions.assertThat(battleResultsMap.get(participant2).getResults().containsKey(BattleResult.WIN)).isTrue(),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().containsKey(BattleResult.LOSE)).isTrue()
                );
    }

    @Test
    void 무승부_계산_확인() {
        //given
        Participant participant1 = new Player("히스타");
        Participant dealer = new Dealer();

        List<Participant> originParticipants = List.of(
                dealer,
                participant1
        );
        Participants participants = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participants);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);
        scoreBoard.calculateScoreBoard();
        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(participant1).getResults().containsKey(BattleResult.DRAW)).isTrue(),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().containsKey(BattleResult.DRAW)).isTrue()
        );
    }

    @Test
    void 모두가_버스트_무승부_발생_확인() {
        //given
        Participant participant1 = new Player("우가");
        Participant participant2 = new Player("히스타");
        Participant dealer = new Dealer();

        List<Participant> originParticipants = List.of(
                participant1,
                participant2,
                dealer
        );

        Participants participants = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participants);

        for (int i = 0; i < 12; i ++) {
            gameBoard.drawCardTo(participant1);
        }
        for (int i = 0; i < 6; i ++) {
            gameBoard.drawCardTo(participant2);
        }
        for (int i = 0; i < 4; i ++) {
            gameBoard.drawCardTo(dealer);
        }

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);
        scoreBoard.calculateScoreBoard();
        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(participant1).getResults().containsKey(BattleResult.DRAW)).isTrue(),
                () -> Assertions.assertThat(battleResultsMap.get(participant2).getResults().containsKey(BattleResult.DRAW)).isTrue(),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().containsKey(BattleResult.DRAW)).isTrue()
        );
    }

    @Test
    void 플레이어_버스트_딜러_버스트아님() {
        //given
        Participant participant1 = new Player("히스타");
        Participant dealer = new Dealer();

        List<Participant> originParticipants = List.of(
                dealer,
                participant1
        );

        Participants participants = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participants);

        for (int i = 0; i < 12; i++) {
            gameBoard.drawCardTo(participant1);
        }

        gameBoard.drawCardTo(dealer);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);
        scoreBoard.calculateScoreBoard();
        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(participant1).getResults().containsKey(BattleResult.LOSE)).isTrue(),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().containsKey(BattleResult.WIN)).isTrue()
        );
    }

    @Test
    void 딜러가_점수_더_높은데_버스트인경우() {
        //given
        Participant participant1 = new Player("히스타");
        Participant dealer = new Dealer();

        List<Participant> originParticipants = List.of(
                dealer,
                participant1
        );

        Participants participants = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participants);

        for (int i = 0; i < 12; i++) {
            gameBoard.drawCardTo(dealer);
        }

        gameBoard.drawCardTo(participant1);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);
        scoreBoard.calculateScoreBoard();
        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(participant1).getResults().containsKey(BattleResult.WIN)).isTrue(),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().containsKey(BattleResult.LOSE)).isTrue()
        );
    }

    @Test
    void 딜러가_점수_더_높은데_버스트아닌경우() {
        //given
        Participant participant1 = new Player("히스타");
        Participant dealer = new Dealer();

        List<Participant> originParticipants = List.of(
                dealer,
                participant1
        );

        Participants participants = new Participants(originParticipants);
        GameBoard gameBoard = new GameBoard(participants);

        for (int i = 0; i < 11; i++) {
            gameBoard.drawCardTo(dealer);
        }

        gameBoard.drawCardTo(participant1);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);
        scoreBoard.calculateScoreBoard();
        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(participant1).getResults().containsKey(BattleResult.LOSE)).isTrue(),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().containsKey(BattleResult.WIN)).isTrue()
        );
    }
}
