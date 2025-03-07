package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackManagerTest {

    @Test
    @DisplayName("게임이 시작되면 딜러를 포함한 참여자 모두에게 2장의 카드를 나눠준다")
    void should_given_all_players_that_of_2_card() {
        // given
        List<Participant> players = List.of(new Dealer(), new Player("a"), new Player("b"));
        CardBundle cardBundle = new CardBundle();
        CardDeck cardDeck = new CardDeck(cardBundle.getAllCards());
        BlackJackManager blackJackManager = new BlackJackManager(players, cardDeck);

        // when
        blackJackManager.start();

        // then
        for (Participant player : players) {
            assertThat(player.getCards()).hasSize(2);
        }
    }

    @Test
    @DisplayName("딜러와 플레이어간의 승패를 계산한다")
    void should_return_result_of_dealer_between_player() {
        // given
        Participant dealer = new Dealer();
        dealer.addCard(new Card(Shape.HEART, Rank.FIVE));
        Participant player1 = new Player("a");
        player1.addCard(new Card(Shape.HEART, Rank.A));
        Participant player2 = new Player("b");
        player2.addCard(new Card(Shape.HEART, Rank.ONE));
        Participant player3 = new Player("c");
        player3.addCard(new Card(Shape.SPADE, Rank.FIVE));
        List<Participant> players = List.of(dealer, player1, player2, player3);
        CardDeck cardDeck = new CardDeck(Collections.emptyList());
        BlackJackManager blackJackManager = new BlackJackManager(players, cardDeck);

        // when
        ParticipantsResult participantsResult = blackJackManager.calculateResult();

        // then
        assertAll(
            () -> {
                Map<GameResult, Integer> dealerExpected = new HashMap<>();
                dealerExpected.put(GameResult.WIN, 1);
                dealerExpected.put(GameResult.DRAW, 1);
                dealerExpected.put(GameResult.LOSE, 1);
                assertThat(participantsResult.dealerResult().getDealerResult()).isEqualTo(
                    dealerExpected);
            },
            () -> {
                List<PlayerResult> playerResults = participantsResult.playerResults();
                assertThat(playerResults.get(0)).isEqualTo(
                    new PlayerResult(player1, GameResult.WIN));
            },
            () -> {
                List<PlayerResult> playerResults = participantsResult.playerResults();
                assertThat(playerResults.get(1)).isEqualTo(
                    new PlayerResult(player2, GameResult.LOSE));
            },
            () -> {
                List<PlayerResult> playerResults = participantsResult.playerResults();
                assertThat(playerResults.get(2)).isEqualTo(
                    new PlayerResult(player3, GameResult.DRAW));
            }
        );
    }
}
