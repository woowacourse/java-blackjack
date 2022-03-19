package blackjack.controller;

import blackjack.domain.Game;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        Game game = new Game(InputView.inputUsersName());

        initBettingMoney(game);
        initDistribute(game);
        playAllUser(game);
        gameResult(game);
    }

    private void initBettingMoney(Game game) {
        for (String userName : game.getUserNames()) {
            int money = InputView.inputBettingMoney(userName);
            game.initBettingMoney(userName, money);
        }
    }

    private void initDistribute(Game game) {
        OutputView.printInitDistribute(game.initDistributed());
    }

    private void playAllUser(Game game) {
        for (String name : game.getUserNames()) {
            playEachUser(game, name);
        }
        playDealer(game);
    }

    private void playEachUser(Game game, String name) {
        while (game.checkUserNotBust(name) && InputView.inputMoreCard(name)) {
            OutputView.printParticipantCards(game.playEachUser(name));
        }
        if (game.checkUserNotBust(name)) {
            game.changeUserStateToStand(name);
        }
    }

    private void playDealer(Game game) {
        while (game.playDealer()) {
            OutputView.printDealerDraw();
        }
    }

    private void gameResult(Game game) {
        OutputView.printFinalCard(game.getDealerAndPlayerCard());
        OutputView.printFinalResult(game.getParticipantProfits());
    }
}
