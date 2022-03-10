package blackjack.controller;

import static blackjack.domain.participant.Participant.INITIAL_CARD_HAND;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Controller {

    public void run() {
        CardDeck cardDeck = CardDeck.initShuffled();
        Dealer dealer = new Dealer(new Name("딜러"));
        Players players = Players.of(InputView.requestPlayerNames());

        dealer.receive(cardDeck.distribute(INITIAL_CARD_HAND));
        players.receive(cardDeck);

        OutputView.printInitCardHandStatus(dealer, players);

        playBlackJack(cardDeck, dealer, players);
        OutputView.printFinalStatus(dealer, players);
    }

    private void playBlackJack(CardDeck cardDeck, Dealer dealer, Players players) {
        playPlayersTurn(cardDeck, players);
        playDealerTurn(cardDeck, dealer);
    }

    private void playPlayersTurn(CardDeck cardDeck, Players players) {
        for (Player player : players.getPlayers()) {
            while (isPlayable(player)) {
                player.receive(cardDeck.distribute(1));
                OutputView.printCardHandStatus(player);
            }
        }
    }

    private void playDealerTurn(CardDeck cardDeck, Dealer dealer) {
        while (dealer.isReceivable()) {
            OutputView.printDealerStatus();
            dealer.receive(cardDeck.distribute(1));
        }
    }

    private boolean isPlayable(Player player) {
        return player.isReceivable() && InputView.requestDecision(player);
    }
}
