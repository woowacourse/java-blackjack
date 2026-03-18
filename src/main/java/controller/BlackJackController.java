package controller;

import domain.card.Deck;
import domain.card.DefaultShuffleStrategy;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.money.BettingResult;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        Deck deck = new Deck(new DefaultShuffleStrategy());
        Dealer dealer = new Dealer();

        List<String> playerNames = readUntilValidPlayers();
        Map<String, Integer> playerBets = readUntilValidMoney(playerNames);
        Players players = new Players(playerBets);

        BlackJackService blackJackService = new BlackJackService(deck, dealer, players);
        blackJackService.initHand();
        outputView.showInitialHands(dealer, players);

        playRound(deck, dealer, players);
        outputView.showHandsResult(dealer, players);

        Map<String, BettingResult> bettingResults = blackJackService.calculateBettingResults();
        outputView.showDealerResult(blackJackService.calculateDealerResult(bettingResults));
        outputView.showPlayerGameResult(bettingResults);
    }

    private void playRound(Deck deck, Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            playPlayerTurn(deck, player);
        }

        playDealerTurn(deck, dealer);
    }

    private void playPlayerTurn(Deck deck, Player player) {
        while (!player.getHand().isBust() && !player.getHand().isBlackJack()) {
            if (!inputView.readPlayerToHitUntilValid(player.getName())) break;

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

    private List<String> readUntilValidPlayers() {
        return inputView.readPlayers();
    }

    private Map<String, Integer> readUntilValidMoney(List<String> playerNames) {
        Map<String, Integer> players = new HashMap<>();
        for (String name : playerNames) {
            int betAmount = inputView.readBettingAmount(name);
            players.put(name, betAmount);
        }
        return players;
    }
}
