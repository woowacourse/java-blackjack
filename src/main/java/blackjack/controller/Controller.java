package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Controller {

    public void run() {
        CardDeck cardDeck = CardDeck.initShuffled();
        Dealer dealer = new Dealer();
        Players players = Players.of(InputView.requestPlayerNames());

        dealer.receive(cardDeck.distribute(2));
        players.receive(cardDeck);

        OutputView.printCardHandStatus(dealer, players);

        playBlackJack(cardDeck, players);
    }

    private void playBlackJack(CardDeck cardDeck, Players players) {
        for (Player player : players.getPlayers()) {
            while (isPlayable(player)) {
                player.receive(cardDeck.distribute(1));
            }
        }
    }

    private boolean isPlayable(Player player) {
        return player.isReceivable() && InputView.requestDecision(player);
    }
}
