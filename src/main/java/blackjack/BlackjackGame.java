package blackjack;

import static blackjack.util.ExceptionHandler.retryUntilSuccess;

import blackjack.model.BetAmount;
import blackjack.model.card.CardProvider;
import blackjack.model.card.HitCommand;
import blackjack.model.gameresult.PlayersGameResult;
import blackjack.model.user.Dealer;
import blackjack.model.user.Player;
import blackjack.model.user.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
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

    public void provideInitCards(Users users) {
        cardProvider.provideInitCards(users);
    }

    public void hit(Users users, Function<Player, String> readHitCommand) {
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();

        for (Player player : players) {
            while (retryUntilSuccess(() -> checkY(player, readHitCommand)) && checkAddCard(player)) {
                cardProvider.provideOneCard(player);
                OutputView.printPlayerCards(player);
            }
        }

        while (dealer.isHitAvailable()) {
            cardProvider.provideOneCard(dealer);
            OutputView.printDealerHit();
        }
    }

    public PlayersGameResult determineWinner(Users users) {
        return users.determineWinner();
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
