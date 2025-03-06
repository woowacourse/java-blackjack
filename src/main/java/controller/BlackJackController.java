package controller;

import domain.BlackJackGame;
import domain.Player;
import domain.TrumpCard;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackJackGame blackJackGame;

    public BlackJackController(InputView inputView, OutputView outputView, BlackJackGame blackJackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackGame = blackJackGame;
    }

    public void run() {
        List<Player> players = createPlayers();
        Map<String, List<TrumpCard>> playerCards = convertPlayerCards(players);
        TrumpCard dealerFirstCard = blackJackGame.getDealer().retrieveFirstCard();
        outputView.printInitialCards(playerCards, dealerFirstCard);

        players.forEach(this::executePlayerHit);
    }

    private void executePlayerHit(Player player) {
        while (blackJackGame.isPlayerHitAllowed(player.getHand().getCards()) &&
                inputView.readProcessHit(player.getName())) {

            blackJackGame.processPlayerHit(player);
            outputView.printPlayerCards(player.getName(), player.getHand().getCards());
        }
    }


    private List<Player> createPlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return blackJackGame.createPlayers(playerNames);
    }

    private Map<String, List<TrumpCard>> convertPlayerCards(List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(Player::getName, player -> player.getHand().getCards()));
    }
}
