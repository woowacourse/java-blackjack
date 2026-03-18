package blackjack;

import static blackjack.util.ExceptionHandler.retryUntilSuccess;

import blackjack.model.bet.BetAmount;
import blackjack.model.bet.BetAmounts;
import blackjack.model.card.CardProvider;
import blackjack.model.card.HitCommand;
import blackjack.model.gameresult.ProfitResult;
import blackjack.model.user.User;
import blackjack.model.user.Users;
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

    public void hitPlayers(List<User> users, Function<User, String> readHitCommand,
                           Consumer<User> printPlayerCards, Runnable printCantHit) {
        for (User user : users) {
            hitPlayer(user, readHitCommand, printPlayerCards, printCantHit);
        }
    }

    public void hitDealer(User user, Runnable printDealerHit) {
        while (user.isHitAvailable()) {
            cardProvider.drawOneCard(user);
            printDealerHit.run();
        }
    }

    public ProfitResult judgeWinner(Users users, BetAmounts betAmounts) {
        return users.judgeWinner(betAmounts);
    }

    public void end(Runnable closeScanner) {
        closeScanner.run();
    }

    private void hitPlayer(User user, Function<User, String> readHitCommand, Consumer<User> printPlayerCards,
                           Runnable printCantHit) {
        while (retryUntilSuccess(() -> checkY(user, readHitCommand)) && isHitAvailable(user, printCantHit)) {
            cardProvider.drawOneCard(user);
            printPlayerCards.accept(user);
        }
    }

    private boolean checkY(User user, Function<User, String> readHitCommand) {
        String input = readHitCommand.apply(user);
        HitCommand hitCommand = new HitCommand(input);
        return hitCommand.isY();
    }

    private boolean isHitAvailable(User user, Runnable printCantHit) {
        if (user.isHitAvailable()) {
            return true;
        }
        printCantHit.run();
        return false;
    }
}
