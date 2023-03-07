package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackTest {

    private Users users;
    private BlackJack blackJack;

    @BeforeEach
    void setUsers() {
        users = Users.from(List.of("hongo"));
        blackJack = BlackJack.of(users, cards -> {
        });
    }

    @DisplayName("플레이어의 승부 결과를 반환한다")
    @Test
    void calculateGameResults() {
        users = Users.from(List.of("hongo", "kiara"));
        blackJack = BlackJack.of(users, cards -> {
        });

        // 카드 현황
        // player1 : ACE(11), 2 => 13
        // player2 : 3, 4       => 7
        // dealer  : 5, 6       => 11
        Map<String, GameResult> gameResults = blackJack.calculatePlayerResults();

        assertThat(gameResults)
            .containsEntry("hongo", GameResult.WIN)
            .containsEntry("kiara", GameResult.LOSE);
    }

    @DisplayName("플레이어와 딜러의 점수가 같을 경우 무승부(PUSH)를 반환한다")
    @Test
    void calculateGameResults_PUSH() {
        Dealer dealer = users.getDealer();
        dealer.hit(new Card(Denomination.SIX, Suits.DIAMOND));

        // 카드 현황
        // player : ACE(11), 2 => 13
        // dealer : 3, 4, 6    => 13
        Map<String, GameResult> gameResults = blackJack.calculatePlayerResults();

        assertThat(gameResults)
            .containsEntry("hongo", GameResult.PUSH);
    }

    @DisplayName("딜러와 플레이어의 카드가 21 초과일 경우 플레이어가 진다")
    @Test
    void PUSH_whenBothCardsOver21() {
        List<Player> players = users.getPlayers();
        Player player = players.get(0);
        Dealer dealer = users.getDealer();
        player.hit(new Card(Denomination.JACK, Suits.HEART));
        player.hit(new Card(Denomination.QUEEN, Suits.HEART));
        dealer.hit(new Card(Denomination.FIVE, Suits.DIAMOND));
        dealer.hit(new Card(Denomination.JACK, Suits.DIAMOND));

        // 카드 현황
        // player : ACE(1), 2, 10, 10  => 23
        // dealer : 3, 4, 5, 10        => 22
        Map<String, GameResult> gameResults = blackJack.calculatePlayerResults();

        assertThat(gameResults)
            .containsEntry("hongo", GameResult.LOSE);
    }

    @DisplayName("유저가 요청하면 카드를 하나 더 준다")
    @Test
    void giveCard_whenRequest() {
        List<Player> players = users.getPlayers();
        Player player = players.get(0);
        int oldScore = player.getScore();

        blackJack.giveCard("hongo");

        assertThat(player.getScore()).isGreaterThan(oldScore);
    }

    @DisplayName("딜러에게 카드를 한 장 더 준다")
    @Test
    void giveCard_toDealer() {
        Dealer dealer = users.getDealer();
        int oldCardSize = dealer.getCards().size();

        blackJack.giveCardToDealer();

        assertThat(dealer.getCards().size()).isEqualTo(oldCardSize + 1);
    }

    @DisplayName("딜러의 점수를 계산한다")
    @Test
    void calculateDealerResult() {
        users = Users.from(List.of("hongo", "kiara", "ash"));
        blackJack = BlackJack.of(users, cards -> {
        });
        List<Player> players = blackJack.getHittablePlayers();
        players.get(1)
            .hit(new Card(Denomination.EIGHT, Suits.DIAMOND));
        players.get(2)
            .hit(new Card(Denomination.FIVE, Suits.DIAMOND));

        // 카드 현황
        // player1 : ACE(11), 2 => 13
        // player2 : 3, 4, 8    => 15
        // player3 : 5, 6, 5    => 16
        // dealer  : 7, 8       => 15
        Map<GameResult, Integer> dealerResult = blackJack.calculateDealerResult();

        assertThat(dealerResult)
            .containsEntry(GameResult.WIN, 1)
            .containsEntry(GameResult.PUSH, 1)
            .containsEntry(GameResult.LOSE, 1);
    }

    @DisplayName("플레이어와 카드 맵을 반환한다")
    @Test
    void getPlayerToCard() {
        users = Users.from(List.of("hongo", "kiara", "ash"));
        blackJack = BlackJack.of(users, cards -> {
        });
        blackJack.giveCard("kiara");
        blackJack.giveCard("ash");
        blackJack.giveCard("ash");

        Map<String, List<Card>> playerToCard = blackJack.getPlayerToCard();

        assertThat(playerToCard.keySet())
            .containsExactly("hongo", "kiara", "ash");
        assertThat(playerToCard)
            .hasEntrySatisfying("hongo", cards -> assertSize(cards, 2))
            .hasEntrySatisfying("kiara", cards -> assertSize(cards, 3))
            .hasEntrySatisfying("ash", cards -> assertSize(cards, 4));
    }

    private void assertSize(List<Card> cards, int size) {
        assertThat(cards.size()).isEqualTo(size);
    }

    @DisplayName("플레이어와 점수 맵을 반환한다")
    @Test
    void getPlayerToScore() {
        users = Users.from(List.of("hongo", "kiara", "ash"));
        blackJack = BlackJack.of(users, cards -> {
        });

        // 카드 현황
        // player1 : ACE(11), 2 => 13
        // player2 : 3, 4       => 7
        // player3 : 5, 6       => 11
        Map<String, Integer> playerToScore = blackJack.getPlayerToScore();

        assertThat(playerToScore.keySet())
            .containsExactly("hongo", "kiara", "ash");
        assertThat(playerToScore)
            .hasEntrySatisfying("hongo", score -> assertThat(score).isEqualTo(13))
            .hasEntrySatisfying("kiara", score -> assertThat(score).isEqualTo(7))
            .hasEntrySatisfying("ash", score -> assertThat(score).isEqualTo(11));
    }
}
