package domain.result;

import static domain.Fixtures.ACE_CLOVER;
import static domain.Fixtures.ACE_HEART;
import static domain.Fixtures.JACK_CLOVER;
import static domain.Fixtures.JACK_SPADE;
import static domain.Fixtures.KING_CLOVER;
import static domain.Fixtures.KING_DIAMOND;
import static domain.Fixtures.KING_HEART;
import static domain.Fixtures.KING_SPADE;
import static domain.Fixtures.QUEEN_CLOVER;
import static domain.Fixtures.QUEEN_DIAMOND;
import static domain.Fixtures.QUEEN_HEART;
import static domain.Fixtures.QUEEN_SPADE;
import static domain.Fixtures.SEVEN_HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
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
        dealer.receiveInitialCards(new Cards(List.of(JACK_SPADE, JACK_CLOVER)));

        gitJjang = participants.getPlayers().get(0);
        gitJjang.receiveInitialCards(new Cards(List.of(KING_SPADE, KING_HEART)));

        irene = participants.getPlayers().get(1);
        irene.receiveInitialCards(new Cards(List.of(KING_CLOVER, KING_DIAMOND)));

        poo = participants.getPlayers().get(2);
        poo.receiveInitialCards(new Cards(List.of(QUEEN_SPADE, QUEEN_HEART)));

        kyle = participants.getPlayers().get(3);
        kyle.receiveInitialCards(new Cards(List.of(QUEEN_CLOVER, QUEEN_DIAMOND)));

        gitJjang.receiveCard(ACE_CLOVER); // K, K, A => 21
        poo.receiveCard(ACE_HEART); // Q, Q, A => 21
        kyle.receiveCard(SEVEN_HEART); // Q, Q, 7 => 27

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
