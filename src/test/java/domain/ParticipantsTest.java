package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.ShuffledDeck;
import domain.card.Suit;
import domain.user.Dealer;
import domain.user.Participants;
import domain.user.Player;
import domain.user.Players;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("모든 참가자에게 초기 카드를 지급한다.")
    @Test
    void 초기_카드_지급() {
        // given
        Map<String, Integer> playerBettingAmountTable = new HashMap<>() {{
            put("Player1", 1_000);
            put("Player2", 2_000);
        }};
        Participants participants = Participants.from(playerBettingAmountTable);
        participants.drawInitialCardsEachParticipant(ShuffledDeck.createByCount(6));
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
        Map<String, Integer> playerBettingAmountTable = new HashMap<>() {{
            put("Player1", 1_000);
            put("Player2", 2_000);
        }};
        Participants participants = Participants.from(playerBettingAmountTable);
        participants.drawInitialCardsEachParticipant(ShuffledDeck.createByCount(6));

        Map<Player, Result> results = participants.calculateAllResults();
        List<Player> players = participants.getPlayers();

        players.forEach(player -> assertThat(results.get(player)).isInstanceOf(Result.class));
    }

    @DisplayName("플레이어의 점수가 딜러보다 높으면 플레이어가 승리하는 결과를 반환한다.")
    @Test
    void 플레이어_승리() {
        // given
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        participants.drawInitialCardsEachParticipant(DeterminedDeck.from(List.of(
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
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        participants.drawInitialCardsEachParticipant(DeterminedDeck.from(List.of(
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
    void 플레이어_패배_점수() {
        // given
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        participants.drawInitialCardsEachParticipant(DeterminedDeck.from(List.of(
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

    @DisplayName("플레이어의 점수가 딜러보다 낮으면 플레이어가 패배하는 결과를 반환한다.")
    @Test
    void 플레이어_패배_버스트() {
        // given
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        participants.drawInitialCardsEachParticipant(DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.KING), // Player1에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.KING), // Player1에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.FIVE), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE) // 딜러에게 주어지는 카드 1
        )));
        player.addCard(new Card(Suit.CLOVER, Rank.TWO));
        // when
        Map<Player, Result> results = participants.calculateAllResults();
        // then
        assertThat(results.get(player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러의 점수가 16 이하이면 hit 한다.")
    @Test
    void 딜러_히트() {
        // given
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        Deck determinedDeck = DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.ACE), // 딜러가 hit할 카드
                new Card(Suit.CLOVER, Rank.FOUR), // Player에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE), // Player에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.TEN), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.SIX) // 딜러에게 주어지는 카드 1
        ));
        participants.drawInitialCardsEachParticipant(determinedDeck);
        // when
        participants.hitOrStayByDealer(determinedDeck);
        Dealer dealer = participants.getDealer();
        // then
        assertThat(dealer.calculateScore()).isEqualTo(17);
    }

    @DisplayName("딜러의 점수가 17 이상이면 stay 한다.")
    @Test
    void 딜러_스테이() {
        // given
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        Deck determinedDeck = DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.ACE), // 딜러가 hit할 카드
                new Card(Suit.CLOVER, Rank.FOUR), // Player에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE), // Player에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.TEN), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.SEVEN) // 딜러에게 주어지는 카드 1
        ));
        participants.drawInitialCardsEachParticipant(determinedDeck);
        // when
        participants.hitOrStayByDealer(determinedDeck);
        Dealer dealer = participants.getDealer();
        // then
        assertThat(dealer.calculateScore()).isEqualTo(17);
    }
}