package controller;

import domain.CardDeck;
import domain.CardShuffler;
import domain.Dealer;
import domain.Players;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> playerNames = inputView.readPlayerNames();
        Players players = new Players(playerNames);
        Dealer dealer = new Dealer();

        CardShuffler cardShuffler = new CardShuffler();
        CardDeck cardDeck = CardDeck.createCards(cardShuffler);

        players.drawCardWhenStart(cardDeck);
        dealer.drawCardWhenStart(cardDeck);

        outputView.printInitialGame(dealer, players.getPlayers());
    }
}
