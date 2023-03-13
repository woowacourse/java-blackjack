package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.bettingMoney.BettingMoneyTable;
import domain.bettingMoney.Monies;
import domain.deck.DefaultDeckGenerator;
import domain.name.Names;
import domain.user.Player;
import domain.user.Users;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackTest {
    private Users users;
    private Blackjack blackJack;

    @BeforeEach
    void setUsers() {
        users = Users.from(Names.of(List.of("hongo")));
        BettingMoneyTable bettingMoneyTable = getDefaultBettingMoneyTable(users.getPlayers());
        blackJack = Blackjack.of(users, new DefaultDeckGenerator().generateDeck(), bettingMoneyTable);
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

    private BettingMoneyTable getDefaultBettingMoneyTable(List<Player> players) {
        return BettingMoneyTable.of(players, getDefaultMonies(players.size()));
    }

    private Monies getDefaultMonies(int size) {
        List<Integer> emptyMonies = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            emptyMonies.add(0);
        }
        return Monies.of(emptyMonies);
    }
}
