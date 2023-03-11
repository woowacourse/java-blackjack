package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import domain.deck.DefaultDeckGenerator;
import domain.name.Names;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Users;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackTest {
    private Users users;
    private Blackjack blackJack;

    @BeforeEach
    void setUsers() {
        users = Users.from(Names.of(List.of("hongo")));
        blackJack = Blackjack.of(users, new DefaultDeckGenerator().generateDeck());
    }

    @DisplayName("플레이어의 승부 결과를 반환한다")
    @Test
    void calculateGameResults() {
        users = Users.from(Names.of(List.of("hongo", "kiara")));
        //blackJack = Blackjack.of(users, new ZeroIndexGenerator());

        Blackjack blackJack = Blackjack.of(users, new DefaultDeckGenerator().generateDeck());

        // 카드 현황
        // player1 : ACE(11), 2 => 13
        // player2 : 3, 4       => 7
        // dealer  : 5, 6       => 11
        Map<String, Result> results = blackJack.calculateTotalPlayerResults();
        assertThat(results.get("hongo")).isEqualTo(Result.WIN);
        assertThat(results.get("kiara")).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어와 딜러의 점수가 같을 경우 무승부(PUSH)를 반환한다")
    @Test
    void calculateGameResults_PUSH() {
        Dealer dealer = users.getDealer();
        dealer.hit(new Card(Denomination.SIX, Suits.DIAMOND));

        // 카드 현황
        // player : ACE(11), 2 => 13
        // dealer : 3, 4, 6    => 13
        Map<String, Result> results = blackJack.calculateTotalPlayerResults();
        assertThat(results.get("hongo")).isEqualTo(Result.PUSH);
    }

    @DisplayName("딜러와 플레이어의 카드가 21 초과일 경우 패배를 반환한다")
    @Test
    void LOSE_whenBothCardsOver21() {
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
        Map<String, Result> results = blackJack.calculateTotalPlayerResults();
        assertThat(results.get("hongo")).isEqualTo(Result.LOSE);
    }

    @DisplayName("유저가 요청하면 카드를 하나 더 준다")
    @Test
    void giveCard_whenRequest() {
        List<Player> players = users.getPlayers();

        Player player = players.get(0);
        int oldScore = player.getScore().getValue();
        blackJack.giveCard("hongo");
        assertThat(player.getScore().getValue()).isGreaterThan(oldScore);
    }
}
