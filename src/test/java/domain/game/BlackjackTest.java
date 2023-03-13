package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.money.BettingMoney;
import domain.money.BettingMoneyTable;
import domain.money.BettingMonies;
import domain.deck.DefaultDeckGenerator;
import domain.name.Names;
import domain.user.Player;
import domain.user.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
        BettingMonies bettingMonies = getZeroNumbers(players.size()).stream()
                .map(number -> BettingMoney.of(number))
                .collect(Collectors.collectingAndThen(Collectors.toList(), BettingMonies::of));
        return BettingMoneyTable.of(players, bettingMonies);
    }

    private List<Integer> getZeroNumbers(int size) {
        List<Integer> zeroNumbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            zeroNumbers.add(0);
        }
        return zeroNumbers;
    }
}
