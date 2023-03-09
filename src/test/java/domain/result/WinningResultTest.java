package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Shape;
import domain.card.Value;
import domain.shuffler.FixedCardsShuffler;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    private Player gitJjang;
    private Player irene;
    private Player poo;
    private Player kyle;

    @DisplayName("각 게임에 맞는 결과를 도출할 수 있다.")
    @Test
    void winningResultTest() {
        Participants participants = initializeParticipants();
        giveCardToParticipants();

        assertThat(gitJjang.getScore()).isEqualTo(21); // 승
        assertThat(irene.getScore()).isEqualTo(20); // 무
        assertThat(poo.getScore()).isEqualTo(21); // 승
        assertThat(kyle.getScore()).isEqualTo(27); // 패
        // 딜러: 20 -> 1승 1무 2패

        WinningResult winningResult = new WinningResult(participants);

        assertThat(winningResult.getDealerResult().get(WinningStatus.WIN)).isEqualTo(1);
        assertThat(winningResult.getDealerResult().get(WinningStatus.DRAW)).isEqualTo(1);
        assertThat(winningResult.getDealerResult().get(WinningStatus.LOSE)).isEqualTo(2);

        assertThat(winningResult.getPlayersResult().get(gitJjang)).isEqualTo(WinningStatus.WIN);
        assertThat(winningResult.getPlayersResult().get(irene)).isEqualTo(WinningStatus.DRAW);
        assertThat(winningResult.getPlayersResult().get(poo)).isEqualTo(WinningStatus.WIN);
        assertThat(winningResult.getPlayersResult().get(kyle)).isEqualTo(WinningStatus.LOSE);
    }

    private void giveCardToParticipants() {
        gitJjang.receiveCard(new Card(Value.ACE, Shape.SPADE)); // K, K, A => 21
        poo.receiveCard(new Card(Value.ACE, Shape.HEART)); // Q, Q, A => 21
        kyle.receiveCard(new Card(Value.SEVEN, Shape.HEART)); // Q, Q, 7 => 27
    }

    private Participants initializeParticipants() {
        List<String> names = Arrays.asList("깃짱", "이리내", "푸우", "카일");
        CardDeck cardDeck = new CardDeck(new FixedCardsShuffler());
        Participants participants = new Participants(names, cardDeck);

        gitJjang = participants.getPlayers().get(0); // K, K
        irene = participants.getPlayers().get(1); // K, K
        poo = participants.getPlayers().get(2); // Q, Q
        kyle = participants.getPlayers().get(3); // Q, Q
        return participants;
    }
}
