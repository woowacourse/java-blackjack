package controller;

import domain.Command;
import domain.ExceptionHandler;
import domain.UserDto;
import domain.deck.TotalDeckGenerator;
import domain.game.Game;
import domain.game.State;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

import static domain.game.State.BUST;
import static domain.game.State.STAY;

public class GameController {

    public void play() {
        Users users = ExceptionHandler.handle(InputView::inputNames);
        Game game = new Game(TotalDeckGenerator.generate(), users);
        showStartStatus(users);

        hitOrStay(game, users);
        showGameResult(users, game);
    }

    private void showStartStatus(Users users) {
        List<UserDto> playerDtos = new ArrayList<>();
        List<Player> players = users.getPlayers();
        for (Player player : players) {
            playerDtos.add(UserDto.from(player));
        }
        UserDto dealerDto = UserDto.from(users.getDealer());
        ResultView.showStartStatus(playerDtos, dealerDto);
    }

    private void hitOrStay(Game game, Users users) {
        List<Player> players = users.getPlayers();
        for (Player player : players) {
            hitOrStayOnce(game, player);
        }
        while (game.isDealerCardAddCondition()) {
            game.addDealerCard();
            ResultView.dealerHit();
        }
    }

    private void hitOrStayOnce(Game game, Player player) {
        Command command = ExceptionHandler.handle(() -> InputView.inputAddCommand(player.getName().value()));
        State state = game.determineState(command, player);
        UserDto userDto = UserDto.from(player);
        if (state == BUST || state == STAY) {
            showMidTermResult(state, userDto);
            return;
        }
        ResultView.printPlayerAndDeck(userDto);
        hitOrStayOnce(game, player);
    }

    private void showMidTermResult(State state, UserDto userDto) {
        if (state == BUST) {
            ResultView.printBust(userDto);
        }
    }

    private void showGameResult(Users users, Game game) {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> gameCompletedUsers = users.getUsers();
        for (User user : gameCompletedUsers) {
            userDtos.add(UserDto.from(user));
        }
        ResultView.showCardsAndSum(userDtos);
        ResultView.showResult(game.generatePlayerResults(), game.generateDealerResult());
    }
}
