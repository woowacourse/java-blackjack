package game;

import java.util.EnumMap;

import domain.BlackjackResult;
import domain.BlackjackResultStatus;
import domain.card.CardMachine;
import domain.participant.dealer.Dealer;
import domain.participant.dealer.DealerResult;
import domain.participant.player.Player;
import domain.participant.player.PlayerResults;
import domain.participant.player.Players;
import view.GameCommand;
import view.InputView;
import view.ResultView;

public class BlackjackGame {

    public static final int BLACKJACK_SCORE = 21;

    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackGame(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void play() {
        Dealer dealer = Dealer.from(CardMachine.cardDecks());
        Players players = inputView.askPlayers();
        initialize(dealer, players);
        askAndDealMoreCards(dealer, players);
        dealAndPrintIfHit(dealer);
        printTotalAndResultOf(dealer, players);
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
        GameCommand command = inputView.askMoreCard(player);
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
        resultView.printResult(resultOf(dealer, players));
    }

    private BlackjackResult resultOf(final Dealer dealer, final Players players) {
        DealerResult dealerResult = new DealerResult(dealer, new EnumMap<>(BlackjackResultStatus.class));
        PlayerResults playerResults = new PlayerResults();
        players.forEach(player -> {
            dealerResult.put(dealer.resultStatusAgainst(player));
            playerResults.put(player, player.resultStatusAgainst(dealer));
        });
        return new BlackjackResult(dealerResult, playerResults);
    }
}
