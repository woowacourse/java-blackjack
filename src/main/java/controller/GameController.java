package controller;

import domain.ExceptionHandler;
import domain.deck.TotalDeckGenerator;
import domain.game.Command;
import domain.game.Game;
import domain.game.State;
import domain.money.Money;
import domain.money.MoneyManager;
import domain.money.Profit;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import dto.UserDto;
import view.InputView;
import view.ResultView;

import java.util.*;

import static domain.game.State.BUST;
import static domain.game.State.STAY;

public class GameController {

    public void play() {
        Users users = ExceptionHandler.handle(() -> new Users(InputView.inputNames()));
        MoneyManager moneyManager = new MoneyManager(betting(users));
        Game game = new Game(TotalDeckGenerator.generate(), users);
        showStartStatus(users);
        hitOrStay(game, users);
        showResult(users, game, moneyManager);
    }

    private Map<Player, Money> betting(Users users) {
        Map<Player, Money> bettingManager = new LinkedHashMap<>();
        for (Player player : users.getPlayers()) {
            Money money = ExceptionHandler.handle(() -> new Money(InputView.inputBetting(player.getName())));
            bettingManager.put(player, money);
        }
        return Collections.unmodifiableMap(bettingManager);
    }

    private void showStartStatus(Users users) {
        List<UserDto> playerDtos = new ArrayList<>();
        for (Player player : users.getPlayers()) {
            playerDtos.add(UserDto.from(player));
        }
        UserDto dealerDto = UserDto.from(users.getDealer());
        ResultView.showStartStatus(playerDtos, dealerDto);
    }

    private void hitOrStay(Game game, Users users) {
        for (Player player : users.getPlayers()) {
            hitOrStay(game, player);
        }
        while (game.isDealerCardAddCondition()) {
            game.addDealerCard();
            ResultView.dealerHit();
        }
    }

    private void hitOrStay(Game game, Player player) {
        Command command = ExceptionHandler.handle(() -> Command.get(InputView.inputAddCommand(player.getName())));
        State state = game.determineState(command, player);
        UserDto userDto = UserDto.from(player);
        if (state == BUST || state == STAY) {
            notifyBust(state, userDto);
            return;
        }
        ResultView.printPlayerAndDeck(userDto);
        hitOrStay(game, player);
    }

    private void notifyBust(State state, UserDto userDto) {
        if (state == BUST) {
            ResultView.printBust(userDto);
        }
    }

    private void showResult(Users users, Game game, MoneyManager moneyManager) {
        showGameResultOfCards(users);
        Map<Player, Profit> profitOfPlayers = moneyManager.calculateProfit(game.generatePlayerResults());
        Profit profitOfDealer = moneyManager.makeDealerProfit(game.generatePlayerResults());
        showGameResultOfProfit(profitOfPlayers, profitOfDealer);
    }

    private void showGameResultOfCards(Users users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users.getUsers()) {
            userDtos.add(UserDto.from(user));
        }
        ResultView.showCardsAndSum(userDtos);
    }

    private void showGameResultOfProfit(Map<Player, Profit> profitManager, Profit profitOfDealer) {
        ResultView.showProfit(profitManager, profitOfDealer);
    }
}
