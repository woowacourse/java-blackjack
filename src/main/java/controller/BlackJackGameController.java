package controller;

import domain.card.Card;
import domain.card.Cards;
import domain.game.BlackJackGame;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.Player;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    public void run() {
        BlackJackGame blackJackGame = setUp();
    }

    private BlackJackGame setUp() {
        Cards cards = new Cards();
        Dealer dealer = new Dealer(cards.drawForFirstTurn());
        List<Player> players = setUpPlayers(cards);
        showSetUpResult(dealer, players);
        return new BlackJackGame(players, dealer, cards);
    }

    private List<Player> setUpPlayers(Cards cards) {
        return readUsersName().stream()
                .map(name -> new Player(new Name(name), cards.drawForFirstTurn()))
                .collect(Collectors.toList());
    }

    private void showSetUpResult(Dealer dealer, List<Player> players) {
        HashMap<String, List<Card>> setUpResult = new LinkedHashMap<>();
        setUpResult.put("딜러", dealer.getCards().subList(0,1));
        players.forEach(player -> setUpResult.put(player.getName(), player.getCards()));
        outputView.printSetUpResult(setUpResult);
    }

    private List<String> readUsersName() {
        outputView.printInputPlayerNameMessage();
        return inputView.readPlayersName();
    }
}
