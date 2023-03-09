package domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Value;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    private static WinningResult winningResult;
    private static Player gitJjang;
    private static Player irene;
    private static Player poo;
    private static Player kyle;

    @BeforeAll
    static void setUp() {
        List<String> names = List.of("깃짱", "이리내", "푸우", "카일");
        Participants participants = new Participants(names);

        Dealer dealer = participants.getDealer();
        dealer.receiveInitialCards(List.of(new Card(Value.JACK, Shape.SPADE), new Card(Value.JACK, Shape.CLOVER)));

        gitJjang = participants.getPlayers().get(0);
        gitJjang.receiveInitialCards(List.of(new Card(Value.KING, Shape.SPADE), new Card(Value.KING, Shape.HEART)));

        irene = participants.getPlayers().get(1);
        irene.receiveInitialCards(List.of(new Card(Value.KING, Shape.CLOVER), new Card(Value.KING, Shape.DIAMOND)));

        poo = participants.getPlayers().get(2);
        poo.receiveInitialCards(List.of(new Card(Value.QUEEN, Shape.SPADE), new Card(Value.QUEEN, Shape.HEART)));

        kyle = participants.getPlayers().get(3);
        kyle.receiveInitialCards(List.of(new Card(Value.QUEEN, Shape.CLOVER), new Card(Value.QUEEN, Shape.DIAMOND)));

        gitJjang.receiveCard(new Card(Value.ACE, Shape.SPADE)); // K, K, A => 21
        poo.receiveCard(new Card(Value.ACE, Shape.HEART)); // Q, Q, A => 21
        kyle.receiveCard(new Card(Value.SEVEN, Shape.HEART)); // Q, Q, 7 => 27

        winningResult = new WinningResult(participants);
    }

    @DisplayName("딜러의 승패를 확인할 수 있다.")
    @Test
    void dealerResultTest() {
        assertAll(
                () -> assertThat(winningResult.getDealerResult().get(WinningStatus.WIN)).isEqualTo(1),
                () -> assertThat(winningResult.getDealerResult().get(WinningStatus.DRAW)).isEqualTo(1),
                () -> assertThat(winningResult.getDealerResult().get(WinningStatus.LOSE)).isEqualTo(2)
        );
    }

    @DisplayName("각 플레이어의 승패를 확인할 수 있다.")
    @Test
    void playersResultTest() {
        assertAll(
                () -> assertThat(winningResult.getPlayersResult().get(gitJjang)).isEqualTo(WinningStatus.WIN),
                () -> assertThat(winningResult.getPlayersResult().get(irene)).isEqualTo(WinningStatus.DRAW),
                () -> assertThat(winningResult.getPlayersResult().get(poo)).isEqualTo(WinningStatus.WIN),
                () -> assertThat(winningResult.getPlayersResult().get(kyle)).isEqualTo(WinningStatus.LOSE)
        );
    }
}
