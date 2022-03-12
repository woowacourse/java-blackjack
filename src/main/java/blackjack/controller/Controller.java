package blackjack.controller;

import static blackjack.domain.participant.Participant.*;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Controller {

    public void run() {
        CardDeck cardDeck = CardDeck.initShuffled();
        Dealer dealer = Dealer.createDefaultNameDealer();
        Players players = Players.of(InputView.requestPlayerNames());

        dealer.receive(cardDeck, INITIAL_CARD_HAND);
        players.receive(cardDeck, INITIAL_CARD_HAND);

        OutputView.printInitCardHandStatus(dealer, players);

        playBlackJack(cardDeck, dealer, players);
    }

    private void playBlackJack(CardDeck cardDeck, Dealer dealer, Players players) {
        playPlayersTurn(cardDeck, players);
        playDealerTurn(cardDeck, dealer);
        OutputView.printFinalStatus(dealer, players);
        OutputView.printFinalResult(dealer, players);
    }

    private void playPlayersTurn(CardDeck cardDeck, Players players) {
        for (Player player : players.getPlayers()) {
            while (isPlayable(player)) {
                player.receive(cardDeck, INITIAL_CARD_HAND);
                OutputView.printCardHandStatus(player);
            }
        }
    }

    private void playDealerTurn(CardDeck cardDeck, Dealer dealer) {
        while (dealer.isReceivable()) {
            OutputView.printDealerStatus();
            dealer.receive(cardDeck, INITIAL_CARD_HAND);
        }
    }

    private boolean isPlayable(Player player) {
        return player.isReceivable() && InputView.requestDecision(player);
    }
}
