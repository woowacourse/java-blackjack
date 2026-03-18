package blackjack;

import static blackjack.util.ExceptionHandler.retryUntilSuccess;

import blackjack.model.bet.BetAmount;
import blackjack.model.bet.BetAmounts;
import blackjack.model.card.CardProvider;
import blackjack.model.command.HitCommand;
import blackjack.model.gameresult.ProfitResult;
import blackjack.model.user.User;
import blackjack.model.user.Users;
import java.util.HashMap;
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

    public BetAmounts createBetAmount(Function<User, String> readBetAmount, Users users) {
        Map<User, BetAmount> betAmounts = new HashMap<>();
        for (User user : users.getPlayers()) {
            String input = readBetAmount.apply(user);
            BetAmount betAmount = new BetAmount(input);
            betAmounts.put(user, betAmount);
        }
        return new BetAmounts(betAmounts);
    }

    public void drawInitCards(Users users) {
        cardProvider.drawInitCards(users);
    }

    public void hitPlayers(Users users, Function<User, String> readHitCommand,
                           Consumer<User> printPlayerCards, Runnable printCantHit) {
        for (User player : users.getPlayers()) {
            hitPlayer(player, readHitCommand, printPlayerCards, printCantHit);
        }
    }

    public void hitDealer(Users users, Runnable printDealerHit) {
        User dealer = users.getDealer();
        while (dealer.isHitAvailable()) {
            cardProvider.drawOneCard(dealer);
            printDealerHit.run();
        }
        dealer.stay();
    }

    public ProfitResult judgeWinner(Users users, BetAmounts betAmounts) {
        return users.judgeWinner(betAmounts);
    }

    public void end(Runnable closeScanner) {
        closeScanner.run();
    }

    private void hitPlayer(User player, Function<User, String> readHitCommand, Consumer<User> printPlayerCards,
                           Runnable printCantHit) {
        while (retryUntilSuccess(() -> checkY(player, readHitCommand)) && isHitAvailable(player, printCantHit)) {
            cardProvider.drawOneCard(player);
            printPlayerCards.accept(player);
        }
        player.stay();
    }

    private boolean checkY(User player, Function<User, String> readHitCommand) {
        String input = readHitCommand.apply(player);
        HitCommand hitCommand = new HitCommand(input);
        return hitCommand.isY();
    }

    private boolean isHitAvailable(User player, Runnable printCantHit) {
        if (player.isHitAvailable()) {
            return true;
        }
        printCantHit.run();
        return false;
    }
}
