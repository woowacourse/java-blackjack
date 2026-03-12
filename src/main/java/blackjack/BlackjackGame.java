package blackjack;

import blackjack.model.BetAmount;
import blackjack.model.user.Player;
import blackjack.model.user.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlackjackGame {

    public Users createUsers(Supplier<String> readUsername) {
        String input = readUsername.get();
        return new Users(input);
    }

    public List<BetAmount> createBetAmount(Function<Player, String> readBetAmount, Users users) {
        List<Player> players = users.getPlayers();
        List<BetAmount> betAmounts = new ArrayList<>();
        for (Player player : players) {
            String input = readBetAmount.apply(player);
            BetAmount betAmount = new BetAmount(player, input);
            betAmounts.add(betAmount);
        }
        return betAmounts;
    }
}
