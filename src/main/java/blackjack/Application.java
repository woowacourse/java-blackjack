package blackjack;

import static blackjack.blackjack.Blackjack.BLACKJACK_SCORE;

import blackjack.blackjack.Blackjack;
import blackjack.blackjack.UserAnswer;
import blackjack.cardMachine.CardMachine;
import blackjack.cardMachine.CardRandomMachine;
import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.gamer.Players;
import blackjack.view.BettingMoneyInputView;
import blackjack.view.HitOrStandInputView;
import blackjack.view.InputView;
import blackjack.view.NameInputView;
import blackjack.view.ResultView;
import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final Blackjack blackjack = new Blackjack();
        Dealer dealer = makeDealer(new CardRandomMachine());
        Players players = makePlayers();

        betPlayers(players);

        spreadInitCards(blackjack, dealer, players);

        printInitialCards(dealer, players);

        final boolean isPush = blackjack.isPush(dealer, players);

        if (!isPush) {
            spreadExtraCards(players, blackjack, dealer);
        }

        printCardsSum(dealer, players);

        printBettingResult(blackjack, dealer, players);
    }

    private static Dealer makeDealer(final CardMachine cardMachine) {
        return new Dealer(cardMachine);
    }

    private static Players makePlayers() {
        final InputView inputView = new NameInputView(scanner);
        final String joinedNames = inputView.read();
        try {
            return Players.from(joinedNames);
        } catch (IllegalArgumentException e) {
            inputView.printErrorMessage(e);
        }
        return makePlayers();
    }

    private static void betPlayers(final Players players) {
        for (Player player : players.getPlayers()) {
            betPlayer(player);
        }
    }

    private static void betPlayer(final Player player) {
        final InputView inputView = new BettingMoneyInputView(scanner, player.getNickName());
        try {
            final String amount = inputView.read();
            player.betMoney(amount);
        } catch (IllegalArgumentException e) {
            inputView.printErrorMessage(e);
            betPlayer(player);
        }
    }

    private static void spreadInitCards(final Blackjack blackjack, final Dealer dealer, final Players players) {
        blackjack.spreadInitCardsToDealer(dealer);
        blackjack.spreadInitCardsToPlayers(dealer, players);
    }

    private static void printInitialCards(final Dealer dealer, final Players players) {
        final ResultView resultView = new ResultView();
        resultView.printEmptyLine();
        resultView.printCards(dealer.getNickName(), dealer.showInitialCards());
        for (Player player : players.getPlayers()) {
            resultView.printCards(player.getNickName(), player.showInitialCards());
        }
    }

    private static void spreadExtraCards(
            final Players players,
            final Blackjack blackjack,
            final Dealer dealer
    ) {
        for (Player player : players.getPlayers()) {
            spreadExtraCardToPlayer(blackjack, dealer, player);
        }
        spreadExtraCardToDealer(blackjack, dealer);

    }

    private static void spreadExtraCardToPlayer(final Blackjack blackjack, final Dealer dealer, final Player player) {
        final ResultView resultView = new ResultView();
        while (!player.isBust(BLACKJACK_SCORE) && readIfHit(player)) {
            blackjack.spreadOneCardToPlayer(dealer, player);
            resultView.printCards(player.getNickName(), player.showAllCards());
        }
    }

    private static void spreadExtraCardToDealer(final Blackjack blackjack, final Dealer dealer) {
        final ResultView resultView = new ResultView();
        while (dealer.isHit()) {
            blackjack.spreadOneCardToDealer(dealer);
            resultView.printDealerHit();
        }
    }

    private static boolean readIfHit(final Player player) {
        final InputView inputView = new HitOrStandInputView(scanner, player.getNickName());
        try {
            final String answer = inputView.read();
            final UserAnswer userAnswer = UserAnswer.of(answer);
            return userAnswer.isYes();
        } catch (IllegalArgumentException e) {
            inputView.printErrorMessage(e);
        }
        return readIfHit(player);
    }

    private static void printCardsSum(final Dealer dealer, final Players players) {
        final ResultView resultView = new ResultView();
        resultView.printEmptyLine();
        resultView.printCardsSum(dealer.getNickName(), dealer.showAllCards(), dealer.sumCards());
        for (Player player : players.getPlayers()) {
            resultView.printCardsSum(player.getNickName(), player.showAllCards(), player.sumCards());
        }
    }

    private static void printBettingResult(
            final Blackjack blackjack,
            final Dealer dealer,
            final Players players
    ) {
        final ResultView resultView = new ResultView();
        blackjack.calculateState(players, dealer);
        resultView.printProfitHead();
        resultView.printProfit(dealer, dealer.getProfit());
        for (Player player : players.getPlayers()) {
            resultView.printProfit(player, player.getProfit());
        }
    }
}
