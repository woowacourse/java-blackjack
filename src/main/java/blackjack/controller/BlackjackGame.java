package blackjack.controller;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Player;
import blackjack.domain.participant.human.name.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class BlackjackGame {
    private static final int INIT_CARD_NUMBER = 2;

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.popCards(INIT_CARD_NUMBER));
        Players players = initPlayers(cardDeck);
        OutputView.printInitCards(players, dealer);

        startGame(players, dealer, cardDeck);
        printGameResult(players, dealer);
    }

    private Players initPlayers(final CardDeck cardDeck) {
        List<Name> names = InputView.inputPlayerNames();
        return new Players(names.stream()
                .map(name -> initPlayer(name, cardDeck))
                .collect(Collectors.toList())
        );
    }

    private Player initPlayer(final Name name, final CardDeck cardDeck) {
        return new Player(name, InputView.inputPlayerBetting(name.get()),
                cardDeck.popCards(INIT_CARD_NUMBER));
    }

    private void startGame(final Players players, final Dealer dealer, final CardDeck cardDeck) {
        players.get()
                .forEach(player -> startPlayer(player, cardDeck));
        startDealer(dealer, cardDeck);
    }

    private void startPlayer(final Player player, final CardDeck cardDeck) {
        while (!player.getState().isFinished()) {
            player.draw(cardDeck, InputView.inputOneMoreCard(player.getName()));
            printPlayerHand(player);
        }
    }

    private void printPlayerHand(final Player player) {
        if (player.hasCardSizeOf(2) || !player.getState().isFinished()) {
            OutputView.printHumanHand(player);
        }
    }

    private void startDealer(final Dealer dealer, final CardDeck cardDeck) {
        if (dealer.isAbleToHit()) {
            dealer.addCard(cardDeck.pop());
            OutputView.printDealerHit();
        }
        dealer.setStay();
    }

    private void printGameResult(final Players players, final Dealer dealer) {
        OutputView.printHandAndPoint(players, dealer);

        Map<Player, Integer> payouts = players.getPayouts(dealer);
        OutputView.printResult(dealer, dealer.getRemainMoney(payouts));
        payouts.forEach(OutputView::printHumanResult);
    }
}
