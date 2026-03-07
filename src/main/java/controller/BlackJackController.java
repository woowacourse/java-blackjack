package blackjack.controller;

import blackjack.service.GameManager;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void playGame() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Players players = readUntilValidPlayers();

        GameManager gameManager = new GameManager(dealer, players);
        gameManager.initHands(deck);
        outputView.showInitialHands(dealer, players);

        playBlackJack(deck, dealer, players);
        outputView.showHandResults(dealer, players);
        outputView.showGameResult(gameManager.calculateResults());
    }

    private void playBlackJack(Deck deck, Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            playPlayerTurn(deck, player);
        }

        playDealerTurn(deck, dealer);
    }

    private void playPlayerTurn(Deck deck, Player player) {
        while (!player.isBust()) {
            if (!inputView.readPlayerToHitUntilValid(player.getName())) {
                break;
            }

            player.receive(deck.drawCard());
            outputView.showPlayerHand(player);
        }
    }

    private void playDealerTurn(Deck deck, Dealer dealer) {
        while (dealer.shouldHit()) {
            outputView.showDealerHitMessage();
            dealer.receive(deck.drawCard());
            outputView.showDealerHand(dealer);
        }

        outputView.showDealerStandMessage();
    }

    private Players readUntilValidPlayers() {
        try {
            return new Players(inputView.readPlayers());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readUntilValidPlayers();
        }
    }
}
