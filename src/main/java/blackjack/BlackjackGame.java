package blackjack;

import static blackjack.util.ExceptionHandler.retryUntilSuccess;

import blackjack.model.bet.BetAmount;
import blackjack.model.bet.BetAmounts;
import blackjack.model.card.CardProvider;
import blackjack.model.card.HitCommand;
import blackjack.model.gameresult.ProfitResult;
import blackjack.model.user.Dealer;
import blackjack.model.user.Player;
import blackjack.model.user.Users;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlackjackGame {

    private final CardProvider cardProvider;

    public BlackjackGame(CardProvider cardProvider) {
        this.cardProvider = cardProvider;
    }

    public Users createUsers(Supplier<String> readUsername) {
        String input = readUsername.get();
        return new Users(input);
    }

    public BetAmounts createBetAmount(Function<Player, String> readBetAmount, Users users) {
        List<Player> players = users.getPlayers();
        Map<Player, BetAmount> betAmounts = new HashMap<>();
        for (Player player : players) {
            String input = readBetAmount.apply(player);
            BetAmount betAmount = new BetAmount(input);
            betAmounts.put(player, betAmount);
        }
        return new BetAmounts(betAmounts);
    }

    public void provideInitCards(Users users) {
        cardProvider.provideInitCards(users);
    }

    public void hitPlayers(List<Player> players, Function<Player, String> readHitCommand,
                           Consumer<Player> printPlayerCards) {
        for (Player player : players) {
            while (retryUntilSuccess(() -> checkY(player, readHitCommand)) && checkAddCard(player)) {
                cardProvider.provideOneCard(player);
                printPlayerCards.accept(player);
            }
        }
    }

    public void hitDealer(Dealer dealer, Runnable printDealerHit) {
        while (dealer.isHitAvailable()) {
            cardProvider.provideOneCard(dealer);
            printDealerHit.run();
        }
    }

    public ProfitResult determineWinner(Users users, BetAmounts betAmounts) {
        return users.determineWinner(betAmounts);
    }

    public void end(Runnable closeScanner) {
        closeScanner.run();
    }

    private boolean checkY(Player player, Function<Player, String> readHitCommand) {
        String input = readHitCommand.apply(player);
        HitCommand hitCommand = new HitCommand(input);
        return hitCommand.isY();
    }

    private boolean checkAddCard(Player player) {
        if (player.isHitAvailable()) {
            return true;
        }
        OutputView.printCantHit();
        return false;
    }
}
