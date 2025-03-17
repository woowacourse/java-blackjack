package controller;

import model.card.CardDeck;
import model.card.ShuffledDeckGenerator;
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
        CardDeck deck = new CardDeck(new ShuffledDeckGenerator().generateDeck());
        Dealer dealer = new Dealer();
        dealer.divideInitialCardToParticipant(players, deck);
        OutputView.printDivisionStart(dealer, players.getPlayers());

        for (Player player : players.getPlayers()) {
            receiveAdditionalCard(player, dealer, deck);
        }
        receiveAdditionalCard(dealer, deck);

        OutputView.printAllParticipantScore(dealer, players);

        dealer.calculateVictory(players);
        OutputView.printResult(dealer, dealer.calculateVictory(players), players);
    }

    private void receiveAdditionalCard(Player player, Dealer dealer, CardDeck deck) {
        while (player.isHit() && agreeIntent(player)) {
            dealer.divideCardByParticipant(player, deck, 1);
            player.applyAceRule();
            OutputView.printCurrentHands(player);
        }
    }

    private void receiveAdditionalCard(Dealer dealer, CardDeck deck) {
        while (dealer.isHit()) {
            dealer.divideCardByParticipant(dealer, deck, 1);
            dealer.applyAceRule();
            OutputView.printStandingDealer(dealer);
        }
    }

    private boolean agreeIntent(Player player) {
        return Intent.from(InputView.readIntent(player.getNickname())).equals(Intent.Y);
    }
}
