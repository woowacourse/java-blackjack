package blackjack.controller;


import static blackjack.view.InputView.inputBetAmount;
import static blackjack.view.InputView.inputIsHit;
import static blackjack.view.InputView.inputPlayerNames;
import static blackjack.view.OutputView.printDealerHit;
import static blackjack.view.OutputView.printDealerProfit;
import static blackjack.view.OutputView.printHand;
import static blackjack.view.OutputView.printHandAndPoint;
import static blackjack.view.OutputView.printInitHands;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.players.Players;
import blackjack.domain.players.participant.Dealer;
import blackjack.domain.players.participant.Player;
import blackjack.view.OutputView;
import java.util.Map;
import java.util.stream.Collectors;

public final class BlackjackGame {
    private static final int INIT_NUMBER = 2;

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.popCards(INIT_NUMBER));
        Players players = initPlayers(cardDeck);
        printInitHands(players, dealer);

        startGame(players, dealer, cardDeck);
        showResult(players, dealer);
    }

    private Players initPlayers(final CardDeck cardDeck) {
        return new Players(inputPlayerNames().stream()
                .map(name -> new Player(name, inputBetAmount(name), cardDeck.popCards(INIT_NUMBER)))
                .collect(Collectors.toList())
        );
    }

    private void startGame(final Players players, final Dealer dealer, final CardDeck cardDeck) {
        players.start(cardDeck, (player, deck) -> {
            while (!player.getState().isFinished()) {
                player.draw(deck, inputIsHit(player.getName()));
                printHand(player);
            }
        });
        dealer.draw(cardDeck);
        printDealerHit(!dealer.isInitState());
    }

    private void showResult(final Players players, final Dealer dealer) {
        printHandAndPoint(players, dealer);

        Map<Player, Integer> payouts = players.getPayouts(dealer);
        printDealerProfit(dealer, dealer.getProfit(payouts));
        payouts.forEach(OutputView::printProfit);
    }
}
