package controller;

import model.card.CardDeck;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> values = InputView.readPlayerNames();
        Players players = Players.from(values);
        CardDeck deck = new CardDeck();
        deck.shuffle();
        Dealer dealer = Dealer.from(deck);
        dealer.divideInitialCardToParticipant(players);
        OutputView.printDivisionStart(dealer, players.getPlayers());

        for (Player player : players.getPlayers()) {
            receiveAdditionalCard(player, dealer);
        }
        receiveAdditionalCard(dealer);

        OutputView.printAllParticipantScore(dealer, players);

        dealer.calculateVictory(players);
        OutputView.printResult(dealer, players);
    }

    private void receiveAdditionalCard(Player player, Dealer dealer) {
        while (player.isHit() && agreeIntent(player)) {
            dealer.divideCardByParticipant(player);
            player.applyAceRule();
            OutputView.printCurrentHands(player);
        }
    }

    private void receiveAdditionalCard(Dealer dealer) {
        while (dealer.isHit()) {
            dealer.divideCardByParticipant(dealer);
            dealer.applyAceRule();
            OutputView.printStandingDealer(dealer);
        }
    }

    private boolean agreeIntent(Player player) {
        return Intent.from(InputView.readIntent(player.getNickname())).equals(Intent.Y);
    }
}
