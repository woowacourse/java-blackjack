package blackjack.controller;


import static blackjack.view.InputView.inputBetAmount;
import static blackjack.view.InputView.inputIsDraw;
import static blackjack.view.InputView.inputPlayerNames;
import static blackjack.view.OutputView.printDealerHit;
import static blackjack.view.OutputView.printDealerProfit;
import static blackjack.view.OutputView.printHand;
import static blackjack.view.OutputView.printHandAndPoint;
import static blackjack.view.OutputView.printInitHands;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Player;
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
        printGameResult(players, dealer);
    }

    private Players initPlayers(final CardDeck cardDeck) {
        return new Players(inputPlayerNames().stream()
                .map(name -> new Player(name, inputBetAmount(name), cardDeck.popCards(INIT_NUMBER)))
                .collect(Collectors.toList())
        );
    }

    private void startGame(final Players players, final Dealer dealer, final CardDeck cardDeck) {
        players.get().forEach(player -> startPlayer(player, cardDeck));
        printDealerHit(dealer.draw(cardDeck));
    }

    private void startPlayer(final Player player, final CardDeck cardDeck) {
        while (!player.getState().isFinished()) {
            player.draw(cardDeck, inputIsDraw(player.getName()));
            printHand(player);
        }
    }

    private void printGameResult(final Players players, final Dealer dealer) {
        printHandAndPoint(players, dealer);

        Map<Player, Integer> payouts = players.getPayouts(dealer);
        printDealerProfit(dealer, dealer.getProfit(payouts));
        payouts.forEach(OutputView::printProfit);
    }
}
