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
        giveCardToUsers(players, dealer);
        stayUsers(players, dealer);
    }

    void createUsers() {
        users = Users.from(List.of("hongo", "kiara", "ash"));
    }

    void stayUsers(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            player.stay();
        }
        dealer.stay();
    }

    void giveCardToUsers(List<Player> players, Dealer dealer) {
        giveCard(players.get(0), Denomination.SIX);
        giveCard(players.get(0), Denomination.TEN);
        giveCard(players.get(1), Denomination.SEVEN);
        giveCard(players.get(1), Denomination.TEN);
        giveCard(players.get(2), Denomination.EIGHT);
        giveCard(players.get(2), Denomination.TEN);
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

        Map<String, Winning> winnings = gameResult.getPlayerResults();

        assertThat(winnings)
            .containsExactlyInAnyOrderEntriesOf(
                Map.of(
                    "hongo", Winning.LOSE,
                    "kiara", Winning.PUSH,
                    "ash", Winning.WIN)
            );
    }

    @DisplayName("딜러의 결과를 계산한다")
    @Test
    void calculateDealerResult() {
        GameResult gameResult = GameResult.of(users);

        Map<Winning, Integer> dealerResult = gameResult.getDealerResult();

        assertThat(dealerResult)
            .containsEntry(Winning.WIN, 1)
            .containsEntry(Winning.PUSH, 1)
            .containsEntry(Winning.LOSE, 1);
    }

}
