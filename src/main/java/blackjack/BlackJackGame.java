package blackjack;

import blackjack.dto.NameCardsScore;
import blackjack.model.betting.BettingRule;
import blackjack.model.betting.Money;
import blackjack.model.betting.MoneyStaff;
import blackjack.model.deck.Deck;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Participants;
import blackjack.model.participant.Playable;
import blackjack.model.participant.Player;
import blackjack.model.result.Referee;
import blackjack.model.result.ResultCommand;
import blackjack.model.result.ResultRule;
import blackjack.util.ConsoleReader;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private static final ConsoleReader consoleReader = new ConsoleReader();

    public void run() {
        final Deck deck = Deck.createByRandomOrder();
        final Dealer dealer = new Dealer(deck.distributeInitialCard());
        final Participants participants = initPlayers(InputView.readPlayerNames(consoleReader), deck);
        final Referee referee = new Referee(new ResultRule(dealer), participants);
        final MoneyStaff moneyStaff = initMoneyStaff(new BettingRule(dealer), participants);

        announceInitialCards(dealer, participants);
        play(dealer, participants, deck);
        Map<Player, ResultCommand> nameResults = announceResult(dealer, participants, referee);
        announceProfitMoney(moneyStaff, nameResults);
    }

    private Participants initPlayers(final List<String> names, final Deck deck) {
        return Participants.of(names, deck.distributeInitialCard(names.size()));
    }

    private MoneyStaff initMoneyStaff(final BettingRule bettingRule, final Participants participants) {
        Map<Player, Money> playerBettingMoneys = new LinkedHashMap<>();
        for (Player player : participants.getPlayers()) {
            Money bettingAmount = new Money(InputView.readBettingAmount(player.getName(), consoleReader));
            playerBettingMoneys.put(player, bettingAmount);
        }

        return new MoneyStaff(bettingRule, playerBettingMoneys);
    }


    private void announceInitialCards(final Dealer dealer, final Participants participants) {
        OutputView.printDistributionSubject(participants.getNames());
        OutputView.printNameAndCards(dealer.getName(), dealer.openCard());
        participants.collectCardsOfEachPlayer()
                .forEach(OutputView::printNameAndCards);
        OutputView.println();
    }

    private void play(final Dealer dealer, final Participants players, final Deck deck) {
        players.getPlayers().forEach(player -> playPlayerTurn(player, deck));
        playDealerTurn(dealer, deck);
    }

    private void playPlayerTurn(final Player player, final Deck deck) {
        if (player.canHit()) {
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

    private void distributeNewCard(final Playable playable, final Deck deck) {
        playable.receiveCard(deck.distribute());
    }

    private Map<Player, ResultCommand> announceResult(final Dealer dealer, final Participants participants,
                                                      final Referee referee) {
        printFinalCardsAndScores(dealer, participants);
        return referee.judgePlayerResult();
    }

    private void printFinalCardsAndScores(final Dealer dealer, final Participants participants) {
        OutputView.println();
        NameCardsScore dealerNameCardsScore = new NameCardsScore(dealer.getName(), dealer.openCards(),
                dealer.getScore());
        OutputView.printFinalCardsAndScore(dealerNameCardsScore);
        OutputView.printFinalCardsAndScore(collectFinalResults(participants));
    }

    private List<NameCardsScore> collectFinalResults(final Participants participants) {
        return participants.getPlayers().stream()
                .map(player -> new NameCardsScore(player.getName(), player.openCards(), player.getScore()))
                .toList();
    }

    private void announceProfitMoney(final MoneyStaff moneyStaff, Map<Player, ResultCommand> playerResults) {
        final Map<Player, Money> playerAndBettingMoney = moneyStaff.calculateProfitMoneys(playerResults);
        final Money dealerProfit = moneyStaff.calculateDealerProfitAmount(
                playerAndBettingMoney.values().stream().toList());
        OutputView.printDealerProfit(dealerProfit);
        OutputView.printPlayerProfit(playerAndBettingMoney);
    }
}
