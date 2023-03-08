package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("모든 참가자에게 초기 카드를 지급한다.")
    @Test
    void 초기_카드_지급() {
        // given
        Participants participants = Participants.of(List.of("name1", "name2"));
        participants.drawInitialCardsEachParticipant(new ShuffledDeck());
        // when
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        // then
        assertThat(dealer.getCards()).hasSize(2);
        players.forEach(player -> assertThat(player.getCards()).hasSize(2));
    }

    @DisplayName("모든 플레이어의 결과를 반환한다.")
    @Test
    void 결과_계산() {
        Participants participants = Participants.of(List.of("Player1", "Player2", "Player3"));
        participants.drawInitialCardsEachParticipant(new ShuffledDeck());

        Map<Player, Result> results = participants.calculateAllResults();
        List<Player> players = participants.getPlayers();

        players.forEach(player -> assertThat(results.get(player)).isInstanceOf(Result.class));
    }

    @DisplayName("플레이어의 점수가 딜러보다 높으면 플레이어가 승리하는 결과를 반환한다.")
    @Test
    void 플레이어_승리() {
        // given
        Player player = new Player("Player");
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        participants.drawInitialCardsEachParticipant(DeterminedDeck.of(List.of(
                new Card(Suit.CLOVER, Rank.FIVE), // Player1에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE), // Player1에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.FOUR), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE) // 딜러에게 주어지는 카드 1
        )));
        // when
        Map<Player, Result> results = participants.calculateAllResults();
        // then
        assertThat(results.get(player)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어의 점수가 딜러와 같으면 무승부 결과를 반환한다.")
    @Test
    void 플레이어_무승부() {
        // given
        Player player = new Player("Player");
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        participants.drawInitialCardsEachParticipant(DeterminedDeck.of(List.of(
                new Card(Suit.CLOVER, Rank.FIVE), // Player1에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE), // Player1에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.FIVE), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE) // 딜러에게 주어지는 카드 1
        )));
        // when
        Map<Player, Result> results = participants.calculateAllResults();
        // then
        assertThat(results.get(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어의 점수가 딜러보다 낮으면 플레이어가 패배하는 결과를 반환한다.")
    @Test
    void 플레이어_패배() {
        // given
        Player player = new Player("Player");
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        participants.drawInitialCardsEachParticipant(DeterminedDeck.of(List.of(
                new Card(Suit.CLOVER, Rank.FOUR), // Player1에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE), // Player1에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.FIVE), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE) // 딜러에게 주어지는 카드 1
        )));
        // when
        Map<Player, Result> results = participants.calculateAllResults();
        // then
        assertThat(results.get(player)).isEqualTo(Result.LOSE);
    }
}