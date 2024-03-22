package game;

import static exception.ExceptionHandler.retry;

import java.util.List;

import domain.card.Deck;
import domain.participant.attributes.Bet;
import domain.participant.attributes.Name;
import domain.participant.dealer.Dealer;
import domain.participant.player.Player;
import domain.participant.player.Players;
import domain.result.BlackjackResult;
import view.GameCommand;
import view.InputView;
import view.ResultView;

public class BlackjackGame {

    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackGame(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void play() {
        Players players = createPlayers();
        Dealer dealer = new Dealer(Deck.create());
        initialize(dealer, players);
        askAndDealMoreCards(dealer, players);
        dealAndPrintIfHit(dealer);
        printTotalAndResultOf(dealer, players);
    }

    private Players createPlayers() {
        List<Name> names = inputView.askPlayerNames();
        List<Bet> bets = names.stream()
                .map(inputView::askPlayerBet)
                .toList();
        return Players.of(names, bets);
    }

    private void initialize(final Dealer dealer, final Players players) {
        dealer.shuffleCards();
        dealer.dealInitialCards(players);
        resultView.printInitialCards(dealer, players);
    }

    private void askAndDealMoreCards(final Dealer dealer, final Players players) {
        players.forEach(player -> askAndDealMoreCard(dealer, player));
    }

    private void askAndDealMoreCard(final Dealer dealer, final Player player) {
        if (player.isBust() || player.isBlackjack()) {
            return;
        }
        GameCommand command = retry(() -> inputView.askMoreCard(player));
        if (command.yes()) {
            dealer.deal(player);
            resultView.printParticipantHand(player);
            askAndDealMoreCard(dealer, player);
        }
    }

    private void dealAndPrintIfHit(final Dealer dealer) {
        if (dealer.canHit()) {
            resultView.printDealerHit(dealer);
            dealer.deal(dealer);
        }
    }

    private void printTotalAndResultOf(final Dealer dealer, final Players players) {
        resultView.printCardsAndTotalOf(dealer, players);
        resultView.printResult(BlackjackResult.of(dealer, players));
    }
}
