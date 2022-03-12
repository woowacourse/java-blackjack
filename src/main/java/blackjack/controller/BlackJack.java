package blackjack.controller;

import blackjack.domain.Result;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;
import blackjack.dto.UserDto;
import blackjack.dto.UsersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackJack {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJack(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Users users = getUsers();

        Deck deck = new Deck();
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
        List<Player> players = users.getPlayers();

        for (Player player : players) {
            drawCardPerPlayer(deck, player);
        }

        drawDealerCard(deck, users.getDealer());
    }

    private void drawCardPerPlayer(Deck deck, Player player) {
        try {
            drawPlayerCardByYes(deck, player);
        } catch(IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            drawCardPerPlayer(deck, player);
        }
    }

    private void drawPlayerCardByYes(Deck deck, Player player) {
        while (player.isDrawable() && inputView.inputWhetherToDrawCard(UserDto.from(player))) {
            player.drawCard(deck);
            outputView.printCards(UserDto.from(player));
        }
    }

    private void drawDealerCard(Deck deck, Dealer dealer) {
        if (dealer.isDrawable()) {
            dealer.drawCard(deck);
            outputView.printDealer();
        }
    }

    private void printFinalResult(Users users) {
        Dealer dealer = users.getDealer();
        List<Player> players = users.getPlayers();

        outputView.printWithScore(UserDto.from(dealer), dealer.getScore());

        for (Player player : players) {
            outputView.printWithScore(UserDto.from(player), player.getScore());
        }

        Map<String, Result> map = Result.getMap(players, dealer);
        outputView.printYield(map);
    }
}
