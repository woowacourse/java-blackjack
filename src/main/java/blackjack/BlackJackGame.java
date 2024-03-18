package blackjack;

import blackjack.dto.NameCardsScore;
import blackjack.dto.NameProfit;
import blackjack.model.betting.BettingRule;
import blackjack.model.betting.Money;
import blackjack.model.betting.MoneyStaff;
import blackjack.model.deck.Deck;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Gamer;
import blackjack.model.participant.Name;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import blackjack.model.result.Referee;
import blackjack.model.result.ResultCommand;
import blackjack.model.result.ResultRule;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.reader.ConsoleReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private static final ConsoleReader consoleReader = new ConsoleReader();

    public void run() {
        final Deck deck = Deck.createByRandomOrder();
        final Dealer dealer = new Dealer(deck.distributeInitialCard());
        final Players players = initPlayers(InputView.readPlayerNames(consoleReader), deck);
        final Referee referee = new Referee(new ResultRule(dealer), players);
        final MoneyStaff moneyStaff = initMoneyStaff(BettingRule.getInstance(), players);

        announceInitialCards(dealer, players);
        play(dealer, players, deck);
        Map<Player, ResultCommand> nameResults = announceResult(dealer, players, referee);
        announceProfitMoney(dealer, moneyStaff, nameResults);
    }

    private Players initPlayers(final List<String> names, final Deck deck) {
        return Players.of(names, deck);
    }

    private MoneyStaff initMoneyStaff(final BettingRule bettingRule, final Players players) {
        Map<Player, Money> playerBettingMoneys = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            Money bettingAmount = new Money(InputView.readBettingAmount(player.getName(), consoleReader));
            playerBettingMoneys.put(player, bettingAmount);
        }

        return new MoneyStaff(bettingRule, playerBettingMoneys);
    }


    private void announceInitialCards(final Dealer dealer, final Players players) {
        OutputView.printDistributionSubject(players.getNames());
        OutputView.printNameAndCards(dealer.getName(), dealer.openCard());
        players.collectCardsOfEachPlayer()
                .forEach(OutputView::printNameAndCards);
        OutputView.println();
    }

    private void play(final Dealer dealer, final Players players, final Deck deck) {
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

    private void distributeNewCard(final Gamer gamer, final Deck deck) {
        gamer.receiveCard(deck.distribute());
    }

    private Map<Player, ResultCommand> announceResult(final Dealer dealer, final Players players,
                                                      final Referee referee) {
        printFinalCardsAndScores(dealer, players);
        return referee.judgePlayerResult();
    }

    private void printFinalCardsAndScores(final Dealer dealer, final Players players) {
        OutputView.println();
        NameCardsScore dealerNameCardsScore = new NameCardsScore(dealer.getName(), dealer.openCards(),
                dealer.getScore());
        OutputView.printFinalCardsAndScore(dealerNameCardsScore);
        OutputView.printFinalCardsAndScore(collectFinalResults(players));
    }

    private List<NameCardsScore> collectFinalResults(final Players players) {
        return players.getPlayers().stream()
                .map(player -> new NameCardsScore(player.getName(), player.openCards(), player.getScore()))
                .toList();
    }

    private void announceProfitMoney(final Dealer dealer, final MoneyStaff moneyStaff,
                                     Map<Player, ResultCommand> playerResults) {
        final Map<Player, Money> playerAndBettingMoney = moneyStaff.calculateProfitMoneys(playerResults);
        Money dealerProfit = moneyStaff.calculateDealerProfitAmount(
                playerAndBettingMoney.values().stream().toList());
        OutputView.printProfits(collectNameProfit(dealer.getName(), dealerProfit, playerAndBettingMoney));
    }

    private List<NameProfit> collectNameProfit(final Name dealerName, final Money dealerProfit,
                                               final Map<Player, Money> playerAndBettingMoney) {
        List<NameProfit> nameProfits = new ArrayList<>();
        nameProfits.add(new NameProfit(dealerName, dealerProfit));
        playerAndBettingMoney.keySet().stream()
                .map(player -> new NameProfit(player.getName(), playerAndBettingMoney.get(player)))
                .forEach(nameProfits::add);

        return nameProfits;
    }
}
