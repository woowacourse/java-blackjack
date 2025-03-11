package controller;

import model.card.CardDeck;
import model.participant.Dealer;
import model.GameManager;
import model.participant.Player;
import model.participant.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> values = InputView.readPlayerNames();
        Players players = Players.from(values);
        Dealer dealer = Dealer.of();
        CardDeck deck = new CardDeck();
        deck.shuffle();
        GameManager gameManager = new GameManager(dealer, players, deck);
        gameManager.divideInitialCardToParticipant();
        OutputView.printDivisionStart(dealer, players.getPlayers());

        for (Player player : players.getPlayers()) {
            receiveAdditionalCard(player, gameManager);
        }
        receiveAdditionalCard(dealer, gameManager);

        OutputView.printAllParticipantScore(dealer, players);

        gameManager.calculateVictory();
        OutputView.printResult(dealer, players);
    }

    private void receiveAdditionalCard(Player player, GameManager gameManager) {
        while (player.isHit() && agreeIntent(player)) {
            gameManager.divideCardByParticipant(player);
            player.applyAceRule();
            OutputView.printCurrentHands(player);
        }
    }

    private void receiveAdditionalCard(Dealer dealer, GameManager gameManager) {
        while (dealer.isHit()) {
            gameManager.divideCardByParticipant(dealer);
            dealer.applyAceRule();
            OutputView.printStandingDealer(dealer);
        }
    }

    private boolean agreeIntent(Player player) {
        return Intent.from(InputView.readIntent(player.getNickname())).equals(Intent.Y);
    }
}
