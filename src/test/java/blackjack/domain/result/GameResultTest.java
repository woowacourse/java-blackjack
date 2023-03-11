package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Number;
import blackjack.domain.card.Pattern;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private GameResult gameResult;
    private Participants participants;
    private Map<Player, Result> playerResultMap;

    @BeforeEach
    void setUp() {
        Players players = Players.from(List.of("a", "b", "c"));
        Dealer dealer = new Dealer();
        participants = new Participants(dealer, players);

        // player1 점수 : 20
        participants.getPlayers().get(0).receiveCard(new Card(Number.K, Pattern.CLUB));
        participants.getPlayers().get(0).receiveCard(new Card(Number.TEN, Pattern.CLUB));
        // player2 점수 : 23
        participants.getPlayers().get(1).receiveCard(new Card(Number.K, Pattern.HEART));
        participants.getPlayers().get(1).receiveCard(new Card(Number.TEN, Pattern.HEART));
        participants.getPlayers().get(1).receiveCard(new Card(Number.THREE, Pattern.HEART));
        // player3 점수 : 19
        participants.getPlayers().get(2).receiveCard(new Card(Number.TEN, Pattern.SPADE));
        participants.getPlayers().get(2).receiveCard(new Card(Number.NINE, Pattern.SPADE));
        // dealer 점수 : 19
        participants.getDealer().receiveCard(new Card(Number.TEN, Pattern.DIAMOND));
        participants.getDealer().receiveCard(new Card(Number.NINE, Pattern.DIAMOND));

        gameResult = new GameResult(participants);
        playerResultMap = gameResult.makePlayersResult();
    }

    @Test
    @DisplayName("플레이어 결과 확인")
    void decidePlayersResult() {
        // expect
        assertAll(
                () -> assertThat(playerResultMap.get(participants.getPlayers().get(0))).isEqualTo(Result.WIN),
                () -> assertThat(playerResultMap.get(participants.getPlayers().get(1))).isEqualTo(Result.LOSE),
                () -> assertThat(playerResultMap.get(participants.getPlayers().get(2))).isEqualTo(Result.DRAW)
        );
    }

    @Test
    @DisplayName("플레이어 결과 뒤집기")
    void reverseResult() {
        Map<Player, Result> reverseResult = gameResult.reverseResult(playerResultMap);
        // expect
        assertAll(
                () -> assertThat(reverseResult.get(participants.getPlayers().get(0))).isEqualTo(Result.LOSE),
                () -> assertThat(reverseResult.get(participants.getPlayers().get(1))).isEqualTo(Result.WIN),
                () -> assertThat(reverseResult.get(participants.getPlayers().get(2))).isEqualTo(Result.DRAW)
        );
    }

    @Test
    @DisplayName("딜러 결과 카운트 확인")
    void getDealerResultCount() {
         // given
         List<Integer> dealerResult = gameResult.getDealerResultCount(playerResultMap);

         // expect
         assertAll(
                 () -> assertThat(dealerResult.get(0)).isEqualTo(1),
                 () -> assertThat(dealerResult.get(1)).isEqualTo(1),
                 () -> assertThat(dealerResult.get(2)).isEqualTo(1)
         );
    }
}
