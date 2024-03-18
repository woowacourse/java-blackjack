package controller;

import domain.ExceptionHandler;
import domain.deck.TotalDeckGenerator;
import domain.game.Command;
import domain.game.Game;
import domain.game.State;
import domain.money.Money;
import domain.money.MoneyManager;
import domain.money.Profit;
import domain.user.*;
import dto.UserDto;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.game.State.BUST;
import static domain.game.State.STAY;

public class GameController {

    public void play() {
        Users users = makeUsers();
        Game game = new Game(TotalDeckGenerator.generate(), users);
        showStartStatus(users);
        hitOrStay(game, users);
        MoneyManager moneyManager = new MoneyManager(game.generatePlayerResults());
        showResult(users, moneyManager);
    }

    private Users makeUsers() {
        Names names = ExceptionHandler.handle(() -> new Names(getNames()));
        List<Player> players = new ArrayList<>();
        updatePlayers(names, players);
        return new Users(Collections.unmodifiableList(players));
    }

    private List<Name> getNames() {
        return ExceptionHandler.handle(() -> {
            List<String> inputNames = InputView.inputNames();
            return inputNames.stream()
                    .map(Name::new)
                    .collect(Collectors.toList());
        });
    }

    private void updatePlayers(Names names, List<Player> players) {
        for (Name name : names.value()) {
            Money money = ExceptionHandler.handle(() -> new Money(InputView.inputBetting(name.value())));
            Player player = new Player(name, money);
            players.add(player);
        }
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
            showBust(state, userDto);
            return;
        }
        ResultView.printPlayerAndDeck(userDto);
        hitOrStay(game, player);
    }

    private void showBust(State state, UserDto userDto) {
        if (state == BUST) {
            ResultView.printBust(userDto);
        }
    }

    private void showResult(Users users, MoneyManager moneyManager) {
        showGameResultOfCards(users);
        Map<Player, Profit> profitOfPlayers = moneyManager.calculateProfit();
        Profit profitOfDealer = moneyManager.makeDealerProfit();
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
