package domain;

import static domain.participant.GameResult.DRAW;
import static domain.participant.GameResult.LOSE;
import static domain.participant.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Betting;
import domain.participant.Dealer;
import domain.participant.GameResult;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.ParticipantsResult;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        player1 = new Player("pobi", new Betting(10000));
        player2 = new Player("james", new Betting(20000));
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
            //given
            dealer.addCard(new Card(Shape.HEART, Rank.KING));
            dealer.addCard(new Card(Shape.HEART, Rank.JACK));
            player1.addCard(new Card(Shape.HEART, Rank.FIVE));
            player2.addCard(new Card(Shape.HEART, Rank.SEVEN));

            //when
            ParticipantsResult participantsResult = participants.calculateOfResult();
            Map<Participant, GameResult> playersResult = participantsResult.getPlayersResult();
            Map<GameResult, Integer> dealerResult = participantsResult.getDealerResult();

            //then
            assertThat(dealerResult.get(GameResult.WIN)).isEqualTo(2);
            assertThat(playersResult.get(player1)).isEqualTo(LOSE);
            assertThat(playersResult.get(player2)).isEqualTo(LOSE);
        }


        @DisplayName("딜러가 1승 1패인 경우, 이긴 플레이어는 승, 패배한 플레이어는 패 이어야 한다")
        @Test
        void when_dealer_one_win_one_lose() {
            //given
            dealer.addCard(new Card(Shape.HEART, Rank.KING));
            dealer.addCard(new Card(Shape.HEART, Rank.NINE));
            player1.addCard(new Card(Shape.HEART, Rank.QUEEN));
            player1.addCard(new Card(Shape.HEART, Rank.EIGHT));
            player1.addCard(new Card(Shape.HEART, Rank.THREE));
            player2.addCard(new Card(Shape.SPADE, Rank.FIVE));

            //when
            ParticipantsResult participantsResult = participants.calculateOfResult();
            Map<Participant, GameResult> playersResult = participantsResult.getPlayersResult();
            Map<GameResult, Integer> dealerResult = participantsResult.getDealerResult();

            //then
            assertThat(dealerResult.get(GameResult.WIN)).isEqualTo(1);
            assertThat(dealerResult.get(LOSE)).isEqualTo(1);
            assertThat(dealerResult.get(GameResult.DRAW)).isNull();
            assertThat(playersResult.get(player1)).isEqualTo(WIN);
            assertThat(playersResult.get(player2)).isEqualTo(LOSE);
        }

        @DisplayName("딜러가 패배하고, 플레이어 2명이 승리한 경우 딜러는 2패, 각각의 플레이어는 승리여야 한다.")
        @Test
        void when_dealer_lose_and_two_player_win() {
            //given
            dealer.addCard(new Card(Shape.HEART, Rank.FIVE));
            player1.addCard(new Card(Shape.HEART, Rank.KING));
            player2.addCard(new Card(Shape.HEART, Rank.SEVEN));

            //when
            ParticipantsResult participantsResult = participants.calculateOfResult();
            Map<Participant, GameResult> playersResult = participantsResult.getPlayersResult();
            Map<GameResult, Integer> dealerResult = participantsResult.getDealerResult();

            //then
            assertThat(dealerResult.get(WIN)).isNull();
            assertThat(dealerResult.get(LOSE)).isEqualTo(2);
            assertThat(dealerResult.get(DRAW)).isNull();
            assertThat(playersResult.get(player1)).isEqualTo(WIN);
            assertThat(playersResult.get(player2)).isEqualTo(WIN);
        }

        @DisplayName("딜러가 0승 1무인 경우, 이긴 플레이어는 승, 비긴 플레이어는 무 이어야 한다.")
        @Test
        void when_dealer_one_win_one_draw() {
            //given
            dealer.addCard(new Card(Shape.HEART, Rank.FIVE));
            player1.addCard(new Card(Shape.HEART, Rank.KING));
            player2.addCard(new Card(Shape.SPADE, Rank.FIVE));

            //when
            ParticipantsResult participantsResult = participants.calculateOfResult();
            Map<Participant, GameResult> playersResult = participantsResult.getPlayersResult();
            Map<GameResult, Integer> dealerResult = participantsResult.getDealerResult();

            //then
            assertThat(dealerResult.get(WIN)).isNull();
            assertThat(dealerResult.get(LOSE)).isEqualTo(1);
            assertThat(dealerResult.get(DRAW)).isEqualTo(1);
            assertThat(playersResult.get(player1)).isEqualTo(WIN);
            assertThat(playersResult.get(player2)).isEqualTo(DRAW);
        }
    }
}
