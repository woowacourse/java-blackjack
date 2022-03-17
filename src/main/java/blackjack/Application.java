package blackjack;

import static java.util.stream.Collectors.toList;

import blackjack.domain.BlackJack;
import blackjack.domain.strategy.ShuffledDeckGenerateStrategy;
import blackjack.domain.user.BettingMoney;
import blackjack.domain.user.User;
import blackjack.domain.vo.Name;
import blackjack.dto.UserDto;
import blackjack.dto.UsersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Application {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        BlackJack blackJack = BlackJack.from(getPlayerInfo(), new ShuffledDeckGenerateStrategy());

        blackJack.setInitCardsPerPlayer();

        printInitCardInfo(blackJack);

        drawAdditionalCard(blackJack);

        printFinalResult(blackJack);
    }

    private static Map<Name, BettingMoney> getPlayerInfo() {
        List<Name> playerNames = getPlayerNames();

        return createPlayerInfo(playerNames);
    }

    private static List<Name> getPlayerNames() {
        try {
            List<String> names = inputView.inputPlayerNames();

            return names.stream()
                    .map(name -> Name.of(name))
                    .collect(toList());
        } catch(IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            return getPlayerNames();
        }
    }

    private static Map<Name, BettingMoney> createPlayerInfo(List<Name> playerNames) {
        Map<Name, BettingMoney> playerInfo = new HashMap<>();

        for (Name playerName : playerNames) {
            BettingMoney bettingMoney = createBettingMoney(playerName);
            playerInfo.put(playerName, bettingMoney);
        }

        return playerInfo;
    }

    private static BettingMoney createBettingMoney(Name playerName) {
        try {
            int money = inputView.inputBettingMoney(playerName.getName());

            return new BettingMoney(money);
        } catch(IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            return createBettingMoney(playerName);
        }
    }

    private static void printInitCardInfo(BlackJack blackJack) {
        UsersDto usersDto = new UsersDto(blackJack.getUsers());
        outputView.printInitCards(usersDto);
    }

    private static void drawAdditionalCard(BlackJack blackJack) {
        Consumer<User> consumerPlayer = user -> drawCardPerPlayer(blackJack, user);

        Consumer<User> consumerDealer = user -> drawDealerCard(blackJack, user);

        blackJack.drawAdditionalCard(consumerPlayer, consumerDealer);
    }

    private static void drawCardPerPlayer(BlackJack blackJack, User player) {
        try {
            drawPlayerCardByYes(blackJack, player);
        } catch(IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            drawCardPerPlayer(blackJack, player);
        }
    }

    private static void drawPlayerCardByYes(BlackJack blackJack, User player) {
        while (player.isDrawable() && inputView.inputWhetherToDrawCard(UserDto.from(player))) {
            blackJack.drawCardFromUser(player);
            outputView.printCardsInfoWithName(UserDto.from(player));
        }
    }

    private static void drawDealerCard(BlackJack blackJack, User dealer) {
        if (dealer.isDrawable()) {
            blackJack.drawCardFromUser(dealer);
            outputView.printDealerMessage();
        }
    }

    private static void printFinalResult(BlackJack blackJack) {
        Consumer<User> consumer = user -> outputView.printWithScore(UserDto.from(user), user.getScore());

        blackJack.printResult(consumer);

        Map<String, Integer> revenue = blackJack.calculateRevenueAllUser();

        outputView.printRevenue(revenue);
    }
}
