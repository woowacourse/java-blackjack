package domain;

import domain.ScoreResult.BattleResult;
import domain.ScoreResult.BattleResults;
import domain.ScoreResult.ScoreBoard;
import domain.card.GameCardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    @DisplayName("딜러 2패, 플레이어 1승, 플레이어 1승")
    @Test
    void 스코어보드_계산_확인1() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        participants.drawTwoCards(gameCardDeck);
        participants.drawTwoCards(gameCardDeck);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);

        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        assertAll(
                () -> Assertions.assertThat(
                                battleResultsMap.get(originParticipants.getFirst()).getResults().get(BattleResult.WIN))
                        .isEqualTo(1),
                () -> Assertions.assertThat(
                                battleResultsMap.get(originParticipants.get(1)).getResults().get(BattleResult.WIN))
                        .isEqualTo(1),
                () -> Assertions.assertThat(
                                battleResultsMap.get(originParticipants.getLast()).getResults().get(BattleResult.LOSE))
                        .isEqualTo(2)
        );
    }

    @DisplayName("딜러 1패 1승, 플레이어 1승, 플레이어 1패")
    @Test
    void 스코어보드_계산_확인2() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);

        Participants onlyPlayers = participants.findPlayers();
        Participant dealer = participants.findDealer();
        Participant player1 = onlyPlayers.getParticipants().getFirst();
        Participant player2 = onlyPlayers.getParticipants().getLast();

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        player1.drawCard(gameCardDeck, 4);
        player2.drawCard(gameCardDeck, 7);
        dealer.drawCard(gameCardDeck, 4);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);

        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(player1).getResults().get(BattleResult.LOSE))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(player2).getResults().get(BattleResult.WIN))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().get(BattleResult.LOSE))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().get(BattleResult.WIN))
                        .isEqualTo(1)
        );
    }

    @DisplayName("딜러 2승, 플레이어 1패, 플레이어 1패")
    @Test
    void 스코어보드_계산_확인3() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);

        Participant dealer = participants.findDealer();

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        participants.drawTwoCards(gameCardDeck);
        dealer.drawCard(gameCardDeck, 4);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);

        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().get(BattleResult.WIN))
                        .isEqualTo(2),
                () -> Assertions.assertThat(battleResultsMap.get(participants.getParticipants().getFirst()).getResults()
                        .get(BattleResult.LOSE)).isEqualTo(1),
                () -> Assertions.assertThat(
                                battleResultsMap.get(participants.getParticipants().get(1)).getResults().get(BattleResult.LOSE))
                        .isEqualTo(1)
        );
    }


    @DisplayName("딜러 2무, 플레이어 1무, 플레이어 1무")
    @Test
    void 스코어보드_계산_확인4() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);
        Participants onlyPlayer = participants.findPlayers();

        Participant dealer = participants.findDealer();
        Participant player1 = onlyPlayer.getParticipants().getFirst();
        Participant player2 = onlyPlayer.getParticipants().getLast();

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        player1.drawCard(gameCardDeck, 1);
        player2.drawCard(gameCardDeck, 1);
        dealer.drawCard(gameCardDeck, 1);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);

        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(player1).getResults().get(BattleResult.DRAW))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(player2).getResults().get(BattleResult.DRAW))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().get(BattleResult.DRAW))
                        .isEqualTo(2)
        );
    }

    @DisplayName("딜러 1무 1승, 플레이어 1패, 플레이어 1무")
    @Test
    void 스코어보드_계산_확인5() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);

        Participants onlyPlayers = participants.findPlayers();
        Participant dealer = participants.findDealer();
        Participant player1 = onlyPlayers.getParticipants().getFirst();
        Participant player2 = onlyPlayers.getParticipants().getLast();

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        player1.drawCard(gameCardDeck, 1);
        player2.drawCard(gameCardDeck, 1);
        dealer.drawCard(gameCardDeck, 1);
        player1.drawCard(gameCardDeck, 1);
        dealer.drawCard(gameCardDeck, 1);
        player2.drawCard(gameCardDeck, 1);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);

        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(player1).getResults().get(BattleResult.LOSE))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(player2).getResults().get(BattleResult.DRAW))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().get(BattleResult.DRAW))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().get(BattleResult.WIN))
                        .isEqualTo(1)
        );
    }

    @DisplayName("딜러 1무 1패, 플레이어 1승, 플레이어 1무")
    @Test
    void 스코어보드_계산_확인6() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);

        Participants onlyPlayers = participants.findPlayers();
        Participant dealer = participants.findDealer();
        Participant player1 = onlyPlayers.getParticipants().getFirst();
        Participant player2 = onlyPlayers.getParticipants().getLast();

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        player1.drawCard(gameCardDeck, 1);
        player2.drawCard(gameCardDeck, 1);
        dealer.drawCard(gameCardDeck, 1);
        player1.drawCard(gameCardDeck, 1);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);

        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(player1).getResults().get(BattleResult.WIN))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(player2).getResults().get(BattleResult.DRAW))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().get(BattleResult.DRAW))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().get(BattleResult.LOSE))
                        .isEqualTo(1)
        );
    }

    @DisplayName("전부 버스트")
    @Test
    void 스코어보드_계산_확인7() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);

        Participants onlyPlayers = participants.findPlayers();
        Participant dealer = participants.findDealer();
        Participant player1 = onlyPlayers.getParticipants().getFirst();
        Participant player2 = onlyPlayers.getParticipants().getLast();

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        player1.drawCard(gameCardDeck, 12);
        player2.drawCard(gameCardDeck, 12);
        dealer.drawCard(gameCardDeck, 12);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);

        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(player1).getResults().get(BattleResult.LOSE))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(player2).getResults().get(BattleResult.LOSE))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().get(BattleResult.WIN))
                        .isEqualTo(2)
        );
    }

    @DisplayName("딜러가 버스트로인해서 패배, 플레이어 승, 플레이어 승")
    @Test
    void 스코어보드_계산_확인8() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);

        Participants onlyPlayers = participants.findPlayers();
        Participant dealer = participants.findDealer();
        Participant player1 = onlyPlayers.getParticipants().getFirst();
        Participant player2 = onlyPlayers.getParticipants().getLast();

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        dealer.drawCard(gameCardDeck, 12);
        player1.drawCard(gameCardDeck, 1);
        player2.drawCard(gameCardDeck, 1);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);

        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(player1).getResults().get(BattleResult.WIN))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(player2).getResults().get(BattleResult.WIN))
                        .isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().get(BattleResult.LOSE))
                        .isEqualTo(2)
        );
    }

    @DisplayName("플레이어가 버스트라서 패배, 딜러는 버스트 아님, 딜러 승, 플레이어 패배, 플레이어 패배")
    @Test
    void 스코어보드_계산_확인9() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);

        Participants onlyPlayers = participants.findPlayers();
        Participant dealer = participants.findDealer();
        Participant player1 = onlyPlayers.getParticipants().getFirst();
        Participant player2 = onlyPlayers.getParticipants().getLast();

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        player1.drawCard(gameCardDeck, 12);
        player2.drawCard(gameCardDeck, 12);
        dealer.drawCard(gameCardDeck, 1);

        //when
        ScoreBoard scoreBoard = new ScoreBoard(participants);

        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        //then
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(battleResultsMap.get(player1).getResults().get(BattleResult.LOSE)).isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(player2).getResults().get(BattleResult.LOSE)).isEqualTo(1),
                () -> Assertions.assertThat(battleResultsMap.get(dealer).getResults().get(BattleResult.WIN)).isEqualTo(2)
        );
    }
}