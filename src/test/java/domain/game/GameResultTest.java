package domain.game;

import static domain.card.Denomination.*;
import static domain.card.Suits.*;
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
        betting(players);
        giveCardToUsers(players);
        stayUsers(players);
    }

    void createUsers() {
        users = Users.from(List.of("hongo", "kiara", "ash", "woowa"));
    }

    void betting(List<Player> players) {
        for (Player player : players) {
            player.betting(1000);
        }
    }

    void stayUsers(List<Player> players) {
        for (int i = 0; i < 3; i++) {
            players.get(i).stay();
        }
        users.stayDealer();
    }

    void giveCardToUsers(List<Player> players) {
        giveCard(players.get(0), SIX);
        giveCard(players.get(0), TEN);
        giveCard(players.get(1), SEVEN);
        giveCard(players.get(1), TEN);
        giveCard(players.get(2), EIGHT);
        giveCard(players.get(2), TEN);
        giveCard(players.get(3), ACE);
        giveCard(players.get(3), JACK);
        users.hitCardToDealer(Card.of(SEVEN, HEART));
        users.hitCardToDealer(Card.of(TEN, HEART));
    }

    void giveCard(User user, Denomination denomination) {
        user.hit(Card.of(denomination, DIAMOND));
    }

    @DisplayName("플레이어의 결과를 계산한다")
    @Test
    void calculateWinnings() {
        GameResult gameResult = GameResult.from(users);

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
        GameResult gameResult = GameResult.from(users);

        int dealerProfit = gameResult.getDealerProfit();

        assertThat(dealerProfit).isEqualTo(-1500);
    }
}
