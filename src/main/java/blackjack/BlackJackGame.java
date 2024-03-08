package blackjack;

import blackjack.dto.NameCardsScore;
import blackjack.model.participant.Dealer;
import blackjack.model.deck.Deck;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import blackjack.model.result.Referee;
import blackjack.model.result.ResultCommand;
import blackjack.model.result.Rule;
import blackjack.util.ConsoleReader;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private static final ConsoleReader CONSOLE_READER = new ConsoleReader();

    public void run() {
        final Deck deck = new Deck();

        final List<String> names = InputView.readPlayerNames(CONSOLE_READER);
        final Players players = Players.of(names, deck.distributeInitialCard(names.size()));

        final Dealer dealer = new Dealer(deck.distributeInitialCard());
        final Referee referee = new Referee(new Rule(dealer), players);

        OutputView.printDistributionSubject(players.getNames());
        printInitialCards(dealer, players);

        playPlayersTurn(players.getPlayers(), deck);
        playDealerTurn(dealer, deck);

        printFinalResult(dealer, players, referee);
    }

    private void printInitialCards(final Dealer dealer, final Players players) {
        OutputView.printNameAndCards(dealer.getName(), dealer.openCard());
        players.collectCardsOfEachPlayer()
                .forEach(OutputView::printNameAndCards);
        OutputView.println();
    }

    private void playPlayersTurn(final List<Player> players, final Deck deck) {
        for (Player player : players) {
            playPlayerTurn(player, deck);
        }
    }

    private void playPlayerTurn(final Player player, final Deck deck) {
        if (!player.isBust()) {
            final boolean isHit = InputView.readHitOrNot(player.getName(), CONSOLE_READER);
            distributeIfPlayerWant(isHit, player, deck);
        }
    }

    private void distributeIfPlayerWant(final boolean isHit, final Player player, final Deck deck) {
        if (isHit) {
            distributeNewCard(player, deck);
            OutputView.printNameAndCards(player.getName(), player.openCards());
            playPlayerTurn(player, deck);
        }
    }

    private void playDealerTurn(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            OutputView.printDealerHit();
            distributeNewCard(dealer, deck);
        }
    }

    private void distributeNewCard(final Player player, final Deck deck) {
        player.receiveCard(deck.distribute());
    }

    private void printFinalResult(final Dealer dealer, final Players players, final Referee referee) {
        OutputView.println();
        NameCardsScore dealerNameCardsScore = new NameCardsScore(dealer.getName(), dealer.openCards(),
                dealer.notifyScore());
        List<NameCardsScore> playerNameCardsScore = players.collectFinalResults();
        OutputView.printFinalCardsAndScore(dealerNameCardsScore);
        OutputView.printFinalCardsAndScore(playerNameCardsScore);

        Map<ResultCommand, Integer> dealerResults = referee.judgeDealerResult();
        OutputView.printDealerFinalResult(dealerResults);
        Map<String, ResultCommand> playerResults = referee.judgePlayerResult();
        OutputView.printFinalResult(playerResults);
    }
}
