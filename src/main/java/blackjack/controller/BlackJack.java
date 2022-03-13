package blackjack.controller;

import blackjack.domain.Result;
import blackjack.domain.card.Deck;
import blackjack.domain.strategy.ShuffledDeckGenerateStrategy;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import blackjack.dto.UserDto;
import blackjack.dto.UsersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BlackJack {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJack(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Users users = getUsers();

        Deck deck = new Deck(new ShuffledDeckGenerateStrategy());
        users.setInitCardsPerPlayer(deck);

        printInitCardInfo(users);

        drawAdditionalCard(users, deck);

        users.calculateAllUser();

        printFinalResult(users);
    }

    private Users getUsers() {
        try {
            List<String> playerNames = inputView.inputPlayerNames();

            Dealer dealer = new Dealer();

            return Users.of(playerNames, dealer);
        } catch(IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            return getUsers();
        }
    }

    private void printInitCardInfo(Users users) {
        UsersDto usersDto = new UsersDto(users);
        outputView.printInitCards(usersDto);
    }

    private void drawAdditionalCard(Users users, Deck deck) {
        Consumer<User> consumerPlayer = user -> drawCardPerPlayer(user, deck);

        Consumer<User> consumerDealer = user -> drawDealerCard(user, deck);

        users.drawAdditionalCard(consumerPlayer, consumerDealer);
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
        while (player.isDrawable() && inputView.inputWhetherToDrawCard(UserDto.from(player))) {
            player.drawCard(deck);
            outputView.printCards(UserDto.from(player));
        }
    }

    private void drawDealerCard(User dealer, Deck deck) {
        if (dealer.isDrawable()) {
            dealer.drawCard(deck);
            outputView.printDealer();
        }
    }

    private void printFinalResult(Users users) {
        User dealer = users.getDealer();
        List<User> players = users.getPlayers();

        outputView.printWithScore(UserDto.from(dealer), dealer.getScore());

        for (User player : players) {
            outputView.printWithScore(UserDto.from(player), player.getScore());
        }

        Map<String, Result> map = Result.getMap(players, dealer);
        outputView.printYield(map);
    }
}
