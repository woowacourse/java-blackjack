package blackjack.controller;

import static blackjack.domain.participant.Participant.ADDITIONAL_CARD_COUNT;
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

        initCardHand(cardDeck, dealer, players);
        playBlackJack(cardDeck, dealer, players);
        showResult(dealer, players);
    }

    private void initCardHand(CardDeck cardDeck, Dealer dealer, Players players) {
        dealer.receive(cardDeck.distribute(INITIAL_CARD_HAND));
        players.receive(cardDeck);

        OutputView.printInitCardHandStatus(dealer, players);
    }

    private void playBlackJack(CardDeck cardDeck, Dealer dealer, Players players) {
        playPlayersTurn(cardDeck, players);
        OutputView.printEmptyLine();
        playDealerTurn(cardDeck, dealer);
    }

    private void playPlayersTurn(CardDeck cardDeck, Players players) {
        for (Player player : players.getPlayers()) {
            playPlayerTurn(cardDeck, player);
        }
    }

    private void playPlayerTurn(CardDeck cardDeck, Player player) {
        while (isPlayable(player)) {
            player.receive(cardDeck.distribute(ADDITIONAL_CARD_COUNT));
            OutputView.printCardHandStatus(player);
        }
    }

    private boolean isPlayable(Player player) {
        return player.isReceivable() && InputView.requestDecision(player);
    }

    private void playDealerTurn(CardDeck cardDeck, Dealer dealer) {
        while (dealer.isReceivable()) {
            OutputView.printDealerStatus();
            dealer.receive(cardDeck.distribute(ADDITIONAL_CARD_COUNT));
        }
    }

    private void showResult(Dealer dealer, Players players) {
        OutputView.printFinalStatus(dealer, players);
        OutputView.printFinalResult(dealer, players);
    }
}
