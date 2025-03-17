package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.DealerResults;
import domain.result.PlayerResults;
import domain.result.PlayerWinningStatus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackResultCalculatorTest {

    @Test
    @DisplayName("딜러와 플레이어간의 승패를 계산한다")
    void should_return_result_of_dealer_between_player() {
        // given
        Participant dealer = new Dealer();
        Card cardOfHeartFive = new Card(Shape.HEART, Rank.FIVE);
        dealer.addCard(cardOfHeartFive);

        Participant player1 = new Player("a");
        Card cardOfHeartAce = new Card(Shape.HEART, Rank.ACE);
        player1.addCard(cardOfHeartAce);

        Participant player2 = new Player("b");
        Card cardOfHeartTwo = new Card(Shape.HEART, Rank.TWO);
        player2.addCard(cardOfHeartTwo);

        Participant player3 = new Player("c");
        Card cardOfSpadeFive = new Card(Shape.SPADE, Rank.FIVE);
        player3.addCard(cardOfSpadeFive);

        List<Participant> players = List.of(dealer, player1, player2, player3);
        Participants participants = new Participants(players);

        // when
        ParticipantsResult participantsResult = BlackJackResultCalculator.calculate(participants);
        DealerResults dealerResults = participantsResult.dealerResults();
        PlayerResults playerResults = participantsResult.playerResults();
        // then
        assertAll(
                () -> assertThat(dealerResults.getStatusCount(BlackJackWinningStatus.WIN)).isEqualTo(1),
                () -> assertThat(dealerResults.getStatusCount(BlackJackWinningStatus.LOSE)).isEqualTo(1),
                () -> assertThat(dealerResults.getStatusCount(BlackJackWinningStatus.DRAW)).isEqualTo(1),
                () -> {
                    PlayerWinningStatus playerWinningStatus = playerResults.findByPlayerName(player1.getName());
                    assertThat(playerWinningStatus.status()).isEqualTo(BlackJackWinningStatus.WIN);
                },
                () -> {
                    PlayerWinningStatus playerWinningStatus = playerResults.findByPlayerName(player2.getName());
                    assertThat(playerWinningStatus.status()).isEqualTo(BlackJackWinningStatus.LOSE);
                },
                () -> {
                    PlayerWinningStatus playerWinningStatus = playerResults.findByPlayerName(player3.getName());
                    assertThat(playerWinningStatus.status()).isEqualTo(BlackJackWinningStatus.DRAW);
                }
        );
    }

    @Test
    @DisplayName("딜러가 승리하는 경우의 딜러와 플레이어간의 승패를 계산한다")
    void should_return_result_of_dealer_between_player_dealer_win() {
        // given
        Participant dealer = new Dealer();
        Card heartFive = new Card(Shape.HEART, Rank.FIVE);
        dealer.addCard(heartFive);

        Participant player1 = new Player("player");
        Card heartTwo = new Card(Shape.HEART, Rank.TWO);
        player1.addCard(heartTwo);

        List<Participant> players = List.of(dealer, player1);
        Participants participants = new Participants(players);

        // when
        ParticipantsResult participantsResult = BlackJackResultCalculator.calculate(participants);

        // then
        assertAll(
                () -> {
                    DealerResults dealerResults = participantsResult.dealerResults();
                    assertThat(dealerResults.getStatusCount(BlackJackWinningStatus.WIN)).isEqualTo(1);
                },
                () -> {
                    PlayerResults playerResults = participantsResult.playerResults();
                    PlayerWinningStatus playerWinningStatus = playerResults.findByPlayerName(player1.getName());
                    BlackJackWinningStatus playerBlackJackWinningStatus = playerWinningStatus.status();
                    assertThat(playerBlackJackWinningStatus).isEqualTo(BlackJackWinningStatus.LOSE);
                }
        );
    }

    @Test
    @DisplayName("딜러가 비기는 경우의 딜러와 플레이어간의 승패를 계산한다")
    void should_return_result_of_dealer_between_player_dealer_draw() {
        // given
        Participant dealer = new Dealer();
        Card heartFive = new Card(Shape.HEART, Rank.FIVE);
        dealer.addCard(heartFive);

        Participant player1 = new Player("player");
        Card spaceFive = new Card(Shape.SPADE, Rank.FIVE);
        player1.addCard(spaceFive);

        List<Participant> players = List.of(dealer, player1);
        Participants participants = new Participants(players);

        // when
        ParticipantsResult participantsResult = BlackJackResultCalculator.calculate(participants);

        // then
        assertAll(
                () -> {
                    DealerResults dealerResults = participantsResult.dealerResults();
                    assertThat(dealerResults.getStatusCount(BlackJackWinningStatus.DRAW)).isEqualTo(1);
                },
                () -> {
                    PlayerResults playerResults = participantsResult.playerResults();
                    PlayerWinningStatus playerWinningStatus = playerResults.findByPlayerName(player1.getName());
                    BlackJackWinningStatus playerBlackJackWinningStatus = playerWinningStatus.status();
                    assertThat(playerBlackJackWinningStatus).isEqualTo(BlackJackWinningStatus.DRAW);
                }
        );
    }

    @Test
    @DisplayName("딜러가 패배하는 경우의 딜러와 플레이어간의 승패를 계산한다")
    void should_return_result_of_dealer_between_player_dealer_lose() {
        // given
        Participant dealer = new Dealer();
        Card heartFive = new Card(Shape.HEART, Rank.FIVE);
        dealer.addCard(heartFive);

        Participant player1 = new Player("player");
        Card heartSix = new Card(Shape.HEART, Rank.SIX);
        player1.addCard(heartSix);

        List<Participant> players = List.of(dealer, player1);
        Participants participants = new Participants(players);

        // when
        ParticipantsResult participantsResult = BlackJackResultCalculator.calculate(participants);

        // then
        assertAll(
                () -> {
                    DealerResults dealerResults = participantsResult.dealerResults();
                    assertThat(dealerResults.getStatusCount(BlackJackWinningStatus.LOSE)).isEqualTo(1);
                },
                () -> {
                    PlayerResults playerResults = participantsResult.playerResults();
                    PlayerWinningStatus playerWinningStatus = playerResults.findByPlayerName(player1.getName());
                    BlackJackWinningStatus playerBlackJackWinningStatus = playerWinningStatus.status();
                    assertThat(playerBlackJackWinningStatus).isEqualTo(BlackJackWinningStatus.WIN);
                }
        );
    }
}
