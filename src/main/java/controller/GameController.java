package controller;

import domain.Command;
import domain.DealerDto;
import domain.ExceptionHandler;
import domain.UserDto;
import domain.deck.TotalDeck;
import domain.deck.TotalDeckGenerator;
import domain.game.Game;
import domain.game.Index;
import domain.game.State;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

import static domain.game.State.BUST;
import static domain.game.State.HIT;

public class GameController {

    public void play() {
        Users users = ExceptionHandler.handle(InputView::inputNames);
        Game game = new Game(new TotalDeck(TotalDeckGenerator.generate()), users);
        showStartStatus(users);

        hitOrStay(game);
        showGameResult(users, game);
    }

    private void showStartStatus(Users users) {
        List<UserDto> userDtos = new ArrayList<>();
        List<Player> players = users.getPlayers();
        for (Player player : players) {
            userDtos.add(UserDto.from(player));
        }
        DealerDto dealerDto = DealerDto.from(users.getDealer());
        ResultView.showStartStatus(userDtos, dealerDto);
    }

    private void hitOrStay(Game game) {
        Index index = game.giveIndexOfGame();
        while (index.isEnd()) {
            index = hitOrStayOnce(game, index);
        }
        while (game.isDealerCardAddCondition()) {
            game.addDealerCard();
            ResultView.dealerHit();
        }
    }

    private Index hitOrStayOnce(Game game, final Index index) {
        User user = game.getUserByIndex(index);
        UserDto userDto = UserDto.from(user);
        Command command = ExceptionHandler.handle(
                () -> InputView.inputAddCommand(userDto.name)
        );
        State state = game.determineState(command, index);
        return getIndex(state, index, userDto);
    }

    private Index getIndex(State state, Index index, UserDto userDto) {
        if (state == HIT) {
            ResultView.printPlayerAndDeck(userDto.name, userDto.cards);
            return index;
        }
        if (state == BUST) {
            ResultView.printBust(userDto.name, userDto.cards);
            return index.next();
        }
        return index.next();
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
