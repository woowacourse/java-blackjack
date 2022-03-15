package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerDto;
import blackjack.view.dto.ReceiveDecision;

public class Controller {

    public void run() {
        CardDeck cardDeck = CardDeck.initShuffled();
        Dealer dealer = Dealer.createDefaultNameDealer();
        Players players = Players.of(InputView.requestPlayerNames());

        initDealerCards(cardDeck, dealer);
        initPlayerCards(cardDeck, players);
        OutputView.printInitCardHandStatus(DealerDto.of(dealer), players);

        playBlackJack(cardDeck, dealer, players);
        OutputView.printFinalStatus(dealer, players);
        OutputView.printFinalResult(dealer, players);
    }

    private void initDealerCards(CardDeck cardDeck, Dealer dealer) {
        dealer.receive(cardDeck.startCards());
    }

    private void initPlayerCards(CardDeck cardDeck, Players players) {
        for (Player player : players.getPlayers()) {
            player.receive(cardDeck.startCards());
        }
    }

    private void playBlackJack(CardDeck cardDeck, Dealer dealer, Players players) {
        playPlayersTurn(cardDeck, players);
        playDealerTurn(cardDeck, dealer);
    }

    private void playPlayersTurn(CardDeck cardDeck, Players players) {
        for (Player player : players.getPlayers()) {
            while (isPlayable(player)) {
                player.receive(cardDeck.draw());
                OutputView.printCardHandStatus(player);
            }
        }
    }

    private void playDealerTurn(CardDeck cardDeck, Dealer dealer) {
        while (dealer.isReceivable()) {
            OutputView.printDealerStatus();
            dealer.receive(cardDeck.draw());
        }
    }

    private boolean isPlayable(Player player) {
        return player.isReceivable() && ReceiveDecision.wantMore(InputView.requestDecision(player));
    }
}
