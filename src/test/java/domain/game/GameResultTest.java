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
        giveCardToPlayer(players.get(0), Denomination.SIX);
        giveCardToPlayer(players.get(1), Denomination.FIVE);
        giveCardToPlayer(players.get(2), Denomination.FOUR);
        giveCardToPlayer(dealer, Denomination.FIVE);
    }

    void createUsers() {
        users = Users.from(List.of("hongo", "kiara", "ash"));
    }

    void giveCardToPlayer(User user, Denomination denomination) {
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
                    "hongo", Winning.WIN,
                    "kiara", Winning.PUSH,
                    "ash", Winning.LOSE)
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
