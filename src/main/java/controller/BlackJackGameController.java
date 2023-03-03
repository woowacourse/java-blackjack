package controller;

import domain.card.Cards;
import domain.game.BlackJackGame;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.Player;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private BlackJackGame setUp() {
        Cards cards = new Cards();

        Dealer dealer = new Dealer(cards.drawForFirstTurn());

        List<String> usersName = readUsersName();
        List<Player> players =  usersName.stream()
                .map(name -> new Player(new Name(name), cards.drawForFirstTurn()))
                .collect(Collectors.toList());

        return new BlackJackGame(players, dealer, cards);
    }

    private List<String> readUsersName() {
        outputView.printInputPlayerNameMessage();
        return inputView.readPlayersName();
    }
}
