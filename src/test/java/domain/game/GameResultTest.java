package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    private Users users;

    @BeforeEach
    void setUpUsers() {
        createUsers();
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();
        betting(players);
        giveCardToUsers(players, dealer);
        stayUsers(players, dealer);
    }

    void createUsers() {
        users = Users.from(List.of("hongo", "kiara", "ash", "woowa"));
    }

    void betting(List<Player> players) {
        for (Player player : players) {
            player.betting(1000);
        }
    }

    void stayUsers(List<Player> players, Dealer dealer) {
        for (int i = 0; i < 3; i++) {
            players.get(i).stayIfRunning();
        }
        dealer.stayIfRunning();
    }

    void giveCardToUsers(List<Player> players, Dealer dealer) {
        giveCard(players.get(0), Denomination.SIX);
        giveCard(players.get(0), Denomination.TEN);
        giveCard(players.get(1), Denomination.SEVEN);
        giveCard(players.get(1), Denomination.TEN);
        giveCard(players.get(2), Denomination.EIGHT);
        giveCard(players.get(2), Denomination.TEN);
        giveCard(players.get(3), Denomination.ACE);
        giveCard(players.get(3), Denomination.JACK);
        giveCard(dealer, Denomination.SEVEN);
        giveCard(dealer, Denomination.TEN);
    }

    void giveCard(User user, Denomination denomination) {
        user.hit(Card.of(denomination, Suits.DIAMOND));
    }

    @DisplayName("플레이어의 결과를 계산한다")
    @Test
    void calculateWinnings() {
        GameResult gameResult = GameResult.of(users);

        Map<String, Integer> profits = gameResult.getPlayerProfits();

        assertThat(profits)
            .containsExactlyInAnyOrderEntriesOf(
                Map.of(
                    "hongo", -1000,
                    "kiara", 0,
                    "ash", 1000,
                    "woowa", 1500)
            );
    }

    @DisplayName("딜러의 결과를 계산한다")
    @Test
    void calculateDealerResult() {
        GameResult gameResult = GameResult.of(users);

        int dealerProfit = gameResult.getDealerProfit();

        assertThat(dealerProfit).isEqualTo(-1500);
    }
}
