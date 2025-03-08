package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.DealerResult;
import domain.participant.GameResult;
import domain.participant.Participant;
import domain.participant.ParticipantResult;
import domain.participant.Participants;
import domain.participant.ParticipantsResult;
import domain.participant.Player;
import domain.participant.PlayerResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    Participants participants;
    Participant dealer;
    Participant player1;
    Participant player2;

    @BeforeEach
    void init() {
        List<Participant> tmpParticipants = new ArrayList<>();
        dealer = new Dealer();
        player1 = new Player("pobi");
        player2 = new Player("james");
        tmpParticipants.add(dealer);
        tmpParticipants.add(player1);
        tmpParticipants.add(player2);
        participants = new Participants(tmpParticipants);
    }


    @Nested
    @DisplayName("딜러와 플레이어간의 승패를 계산한다")
    class calculate_result_by_dealer_and_player {

        @DisplayName("딜러가 이기고, 플레이어 2명이 패배한 경우 딜러는 2승, 각각의 플레이어는 패배여야 한다.")
        @Test
        void when_dealer_win_and_two_player_lose() {
            dealer.addCard(new Card(Shape.HEART, Rank.KING));
            dealer.addCard(new Card(Shape.HEART, Rank.JACK));
            player1.addCard(new Card(Shape.HEART, Rank.FIVE));
            player2.addCard(new Card(Shape.HEART, Rank.SEVEN));
            ParticipantsResult participantsResult = participants.calculate();
            Set<ParticipantResult> participantResults = participantsResult.getParticipantsResult();
            DealerResult dealerResult = new DealerResult();
            dealerResult.add(GameResult.WIN);
            dealerResult.add(GameResult.WIN);
            PlayerResult expectedPlayer1Result = new PlayerResult("pobi", GameResult.LOSE);
            PlayerResult expectedPlayer2Result = new PlayerResult("james", GameResult.LOSE);
            assertThat(participantResults).contains(dealerResult);
            assertThat(participantResults).contains(expectedPlayer1Result);
            assertThat(participantResults).contains(expectedPlayer2Result);
        }

        @DisplayName("딜러가 1승 1패인 경우, 이긴 플레이어는 승, 패배한 플레이어는 패 이어야 한다")
        @Test
        void when_dealer_one_win_one_lose() {
            dealer.addCard(new Card(Shape.HEART, Rank.KING));
            dealer.addCard(new Card(Shape.HEART, Rank.JACK));
            player1.addCard(new Card(Shape.HEART, Rank.QUEEN));
            player1.addCard(new Card(Shape.HEART, Rank.A));
            player2.addCard(new Card(Shape.SPADE, Rank.FIVE));
            ParticipantsResult participantsResult = participants.calculate();
            Set<ParticipantResult> participantResults = participantsResult.getParticipantsResult();
            DealerResult dealerResult = new DealerResult();
            dealerResult.add(GameResult.WIN);
            dealerResult.add(GameResult.LOSE);
            PlayerResult expectedPlayer1Result = new PlayerResult("pobi", GameResult.WIN);
            PlayerResult expectedPlayer2Result = new PlayerResult("james", GameResult.LOSE);
            assertThat(participantResults).contains(dealerResult);
            assertThat(participantResults).contains(expectedPlayer1Result);
            assertThat(participantResults).contains(expectedPlayer2Result);
        }

        @DisplayName("딜러가 패배하고, 플레이어 2명이 승리한= 경우 딜러는 2패, 각각의 플레이어는 승리여야 한다.")
        @Test
        void when_dealer_lose_and_two_player_win() {
            dealer.addCard(new Card(Shape.HEART, Rank.FIVE));
            player1.addCard(new Card(Shape.HEART, Rank.KING));
            player2.addCard(new Card(Shape.HEART, Rank.SEVEN));
            ParticipantsResult participantsResult = participants.calculate();
            Set<ParticipantResult> participantResults = participantsResult.getParticipantsResult();
            DealerResult dealerResult = new DealerResult();
            dealerResult.add(GameResult.LOSE);
            dealerResult.add(GameResult.LOSE);
            PlayerResult expectedPlayer1Result = new PlayerResult("pobi", GameResult.WIN);
            PlayerResult expectedPlayer2Result = new PlayerResult("james", GameResult.WIN);
            assertThat(participantResults).contains(dealerResult);
            assertThat(participantResults).contains(expectedPlayer1Result);
            assertThat(participantResults).contains(expectedPlayer2Result);
        }

        @DisplayName("딜러가 1승 1무인 경우, 이긴 플레이어는 승, 비긴 플레이어는 무 이어야 한다.")
        @Test
        void when_dealer_one_win_one_draw() {
            dealer.addCard(new Card(Shape.HEART, Rank.FIVE));
            player1.addCard(new Card(Shape.HEART, Rank.KING));
            player2.addCard(new Card(Shape.SPADE, Rank.FIVE));
            ParticipantsResult participantsResult = participants.calculate();
            Set<ParticipantResult> participantResults = participantsResult.getParticipantsResult();
            DealerResult dealerResult = new DealerResult();
            dealerResult.add(GameResult.LOSE);
            dealerResult.add(GameResult.DRAW);
            PlayerResult expectedPlayer1Result = new PlayerResult("pobi", GameResult.WIN);
            PlayerResult expectedPlayer2Result = new PlayerResult("james", GameResult.DRAW);
            assertThat(participantResults).contains(dealerResult);
            assertThat(participantResults).contains(expectedPlayer1Result);
            assertThat(participantResults).contains(expectedPlayer2Result);
        }
    }
}
