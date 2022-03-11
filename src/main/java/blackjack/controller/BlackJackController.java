package blackjack.controller;

import blackjack.domain.Result;
import blackjack.domain.Rule;
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

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Users users = getUsers();

        Deck deck = new Deck();
        users.setInitCardsPerPlayer(deck);

        printInitCardInfo(users);

        drawAdditionalCard(users, deck);

        calculateByRule(users);

        printFinalResult(users);
    }

    private Users getUsers() {
        List<String> playerNames = inputView.inputPlayerNames();

        Dealer dealer = new Dealer();

        return Users.from(playerNames, dealer);
    }

    private void printInitCardInfo(Users users) {
        UsersDto usersDto = new UsersDto(users);
        outputView.printInitCards(usersDto);
    }

    private void drawAdditionalCard(Users users, Deck deck) {
        List<Player> players = users.getPlayers();

        for (Player player : players) {
            drawPlayerCardByYes(deck, player);
        }

        drawDealerCard(deck, users.getDealer());
    }

    private void drawPlayerCardByYes(Deck deck, Player player) {
        while (player.isDrawable()) {
            if (inputView.inputWhetherToDrawCard(UserDto.from(player))) {
                player.drawCard(deck);
            }
            outputView.printCards(UserDto.from(player));
        }
    }

    private void drawDealerCard(Deck deck, Dealer dealer) {
        if (dealer.isDrawable()) {
            dealer.drawCard(deck);
            outputView.printDealer();
        }
    }

    private void calculateByRule(Users users) {
        Rule rule = new Rule();

        users.calculateAllUser(rule);
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
