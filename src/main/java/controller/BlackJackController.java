package controller;

import domain.*;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

import java.util.Map;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Players players = readUntilValidPlayers();

        BlackJackService blackJackService = new BlackJackService(deck, dealer, players);
        blackJackService.initHand();
        outputView.showInitialHands(dealer, players);

        playRound(deck, dealer, players);
        outputView.showHandsResult(dealer, players);

        Map<String, MatchResult> playerResults = blackJackService.calculateResults();
        outputView.showDealerResult(blackJackService.calculateDealerResult(playerResults));
        outputView.showPlayerGameResult(playerResults);
    }

    private void playRound(Deck deck, Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            playPlayerTurn(deck, player);
        }

        playDealerTurn(deck, dealer);
    }

    private void playPlayerTurn(Deck deck, Player player) {
        while (!player.getHand().isBust()) {
            if (!inputView.readPlayerToHitUntilValid(player.getName())) {
                break;
            }

            player.hit(deck.drawCard());
            outputView.showHand(player);
        }
    }

    private void playDealerTurn(Deck deck, Dealer dealer) {
        while (dealer.shouldHit()) {
            outputView.showDealerPlayMessage(dealer.shouldHit());
            dealer.hit(deck.drawCard());
        }
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
