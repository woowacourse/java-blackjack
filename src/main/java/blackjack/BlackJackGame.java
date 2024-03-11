package blackjack;

import blackjack.dto.NameCardsScore;
import blackjack.model.participant.Dealer;
import blackjack.model.deck.Deck;
import blackjack.model.participant.Player;
import blackjack.model.participant.Participants;
import blackjack.model.result.Referee;
import blackjack.model.result.ResultCommand;
import blackjack.model.result.Rule;
import blackjack.util.ConsoleReader;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private static final ConsoleReader consoleReader = new ConsoleReader();

    public void run() {
        final Deck deck = Deck.createByRandomOrder();
        final Participants participants = initPlayers(InputView.readPlayerNames(consoleReader), deck);
        final Dealer dealer = new Dealer(deck.distributeInitialCard());
        final Referee referee = new Referee(new Rule(dealer), participants);

        announceInitialCards(dealer, participants);
        play(dealer, participants.getPlayers(), deck);
        announceResult(dealer, participants, referee);
    }

    private Participants initPlayers(final List<String> names, final Deck deck) {
        return Participants.of(names, deck.distributeInitialCard(names.size()));
    }

    private void announceInitialCards(final Dealer dealer, final Participants participants) {
        OutputView.printDistributionSubject(participants.getNames());
        OutputView.printNameAndCards(dealer.getName(), dealer.openCard());
        participants.collectCardsOfEachPlayer()
                .forEach(OutputView::printNameAndCards);
        OutputView.println();
    }

    private void play(final Dealer dealer, final List<Player> players, final Deck deck) {
        players.forEach(player -> playPlayerTurn(player, deck));
        playDealerTurn(dealer, deck);
    }

    private void playPlayerTurn(final Player player, final Deck deck) {
        if (!player.isBust() && player.canHit()) {
            final boolean isHit = InputView.readHitOrNot(player.getName(), consoleReader);
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

    private void announceResult(final Dealer dealer, final Participants participants, final Referee referee) {
        printFinalCardsAndScores(dealer, participants);
        printFinalResultCommand(referee);
    }

    private void printFinalCardsAndScores(final Dealer dealer, final Participants participants) {
        OutputView.println();
        NameCardsScore dealerNameCardsScore = new NameCardsScore(dealer.getName(), dealer.openCards(),
                dealer.getScore());
        List<NameCardsScore> playerNameCardsScore = participants.collectFinalResults();
        OutputView.printFinalCardsAndScore(dealerNameCardsScore);
        OutputView.printFinalCardsAndScore(playerNameCardsScore);
    }

    private void printFinalResultCommand(final Referee referee) {
        Map<ResultCommand, Integer> dealerResults = referee.judgeDealerResult();
        OutputView.printDealerFinalResult(dealerResults);
        Map<String, ResultCommand> playerResults = referee.judgePlayerResult();
        OutputView.printFinalResult(playerResults);
    }
}
