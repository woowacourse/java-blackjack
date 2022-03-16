package blackjack.controller;

import static blackjack.dto.UserDto.from;

import blackjack.domain.BlackJack;
import blackjack.domain.card.Deck;
import blackjack.domain.strategy.ShuffledDeckGenerateStrategy;
import blackjack.domain.user.BettingMoney;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import blackjack.dto.UsersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJack blackJack = BlackJack.from(getPlayerInfo(), new ShuffledDeckGenerateStrategy());

        blackJack.setInitCardsPerPlayer();

        printInitCardInfo(blackJack.getUsers());

        drawAdditionalCard(blackJack);

        printFinalResult(blackJack.getUsers());
    }

    private Map<String, BettingMoney> getPlayerInfo() {
        List<String> playerNames = getPlayerNames();

        return getBettingMoney(playerNames);
    }

    private List<String> getPlayerNames() {
        try {
            return inputView.inputPlayerNames();
        } catch(IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            return getPlayerNames();
        }
    }

    private Map<String, BettingMoney> getBettingMoney(List<String> playerNames) {
        Map<String, BettingMoney> playerInfo = new HashMap<>();

        for (String playerName : playerNames) {

            BettingMoney bettingMoney = createBettingMoney(playerName);

            playerInfo.put(playerName, bettingMoney);
        }

        return playerInfo;
    }

    private BettingMoney createBettingMoney(String playerName) {
        try {
            int money = inputView.inputBettingMoney(playerName);

            return new BettingMoney(money);
        } catch(IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            return createBettingMoney(playerName);
        }
    }

    private void printInitCardInfo(Users users) {
        UsersDto usersDto = new UsersDto(users);
        outputView.printInitCards(usersDto);
    }

    private void drawAdditionalCard(BlackJack blackJack) {
        Deck deck = blackJack.getDeck();

        Consumer<User> consumerPlayer = user -> drawCardPerPlayer(user, deck);

        Consumer<User> consumerDealer = user -> drawDealerCard(user, deck);

        blackJack.drawAdditionalCard(consumerPlayer, consumerDealer);
    }

    private void drawCardPerPlayer(User player, Deck deck) {
        try {
            drawPlayerCardByYes(player, deck);
        } catch(IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            drawCardPerPlayer(player, deck);
        }
    }

    private void drawPlayerCardByYes(User player, Deck deck) {
        while (player.isDrawable() && inputView.inputWhetherToDrawCard(from(player))) {
            player.drawCard(deck);
            outputView.printCards(from(player));
        }
    }

    private void drawDealerCard(User dealer, Deck deck) {
        if (dealer.isDrawable()) {
            dealer.drawCard(deck);
            outputView.printDealer();
        }
    }

    private void printFinalResult(Users users) {
        Consumer<User> consumer = user -> outputView.printWithScore(from(user), user.calculateScore());

        users.printResult(consumer);

        Map<String, Integer> revenue = users.calculateRevenue();

        outputView.printRevenue(revenue);
    }
}
