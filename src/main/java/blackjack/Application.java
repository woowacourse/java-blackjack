package blackjack;

import static blackjack.domain.gambler.Dealer.DEALER_NAME;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.betting.ProfitCalculator;
import blackjack.domain.betting.BettingAmount;
import blackjack.domain.gambler.Names;
import blackjack.domain.game.Round;
import blackjack.domain.game.WinningDiscriminator;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardShuffler;
import blackjack.domain.card.CardType;
import blackjack.domain.gambler.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.domain.game.WinningType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final ProfitCalculator profitCalculator = new ProfitCalculator();

    public static void main(String[] args) {
        CardDeck cardDeck = createCardDeck();
        Names playerNames = getPlayerNames();
        Map<Name, BettingAmount> bettingAmounts = getBettingAmounts(playerNames);
        Round round = new Round(cardDeck, playerNames);
        round.distributeInitialCards();
        outputView.printInitialDistributionPrompt(playerNames);
        printInitialCards(round, playerNames);
        processPlayersTurn(playerNames, round);
        processDealerTurn(round);
        printGameResult(round, playerNames);
        printProfits(round.getWinningDiscriminator(), bettingAmounts);
    }

    private static CardDeck createCardDeck() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardType.values())
                        .map(type -> new Card(shape, type)))
                .collect(toList());
        return new CardDeck(cards, new CardShuffler());
    }

    private static Names getPlayerNames() {
        try {
            List<Name> names = inputView.inputPlayerName();
            return new Names(names);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getPlayerNames();
        }
    }

    private static Map<Name, BettingAmount> getBettingAmounts(final Names playerNames) {
        return playerNames.getNames()
                .stream()
                .collect(toMap(identity(), Application::getBettingAmount));
    }

    private static BettingAmount getBettingAmount(final Name playerName) {
        try {
            int bettingAmount = inputView.inputBettingAmount(playerName);
            return new BettingAmount(bettingAmount);
        } catch (final IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getBettingAmount(playerName);
        }
    }

    private static void printInitialCards(final Round round, final Names playerNames) {
        outputView.printGamblerCards(DEALER_NAME, round.getInitialCards(DEALER_NAME));
        for (final Name playerName : playerNames.getNames()) {
            outputView.printGamblerCards(playerName, round.getInitialCards(playerName));
        }
    }

    private static void processPlayersTurn(final Names playerNames, final Round round) {
        for (final Name playerName : playerNames.getNames()) {
            processPlayerTurn(round, playerName);
        }
    }

    private static void processDealerTurn(final Round round) {
        if (round.dealerMustDraw()) {
            round.hit(DEALER_NAME);
            outputView.printDealerDraw();
        }
    }

    private static void processPlayerTurn(final Round round, final Name playerName) {
        boolean flag = false;
        while (isHit(playerName)) {
            round.hit(playerName);
            outputView.printGamblerCards(playerName, round.getCards(playerName));
            boolean isBust = round.isBust(playerName);
            if (isBust) {
                outputView.printBusted(playerName);
                break;
            }
            flag = true;
        }
        if (!flag) {
            outputView.printGamblerCards(playerName, round.getCards(playerName));
        }
    }

    private static boolean isHit(final Name playerName) {
        try {
            return inputView.inputPlayerHit(playerName);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return isHit(playerName);
        }
    }

    private static void printGameResult(final Round round, final Names playerNames) {
        outputView.printGamblerResult(DEALER_NAME, round.getCards(DEALER_NAME), round.getScore(DEALER_NAME));
        for (final Name playerName : playerNames.getNames()) {
            List<Card> cards = round.getCards(playerName);
            int score = round.getScore(playerName);
            outputView.printGamblerResult(playerName, cards, score);
        }
    }

    private static void printProfits(final WinningDiscriminator winningDiscriminator,
                                     final Map<Name, BettingAmount> bettingAmounts) {
        Map<Name, WinningType> playersWinningResult = winningDiscriminator.judgePlayersResult();
        Map<Name, Integer> playersProfit = calculatePlayersProfit(bettingAmounts, playersWinningResult);
        int dealerProfit = profitCalculator.calculateDealerProfit(playersProfit);
        outputView.printProfit(dealerProfit, playersProfit);
    }

    private static Map<Name, Integer> calculatePlayersProfit(final Map<Name, BettingAmount> bettingAmounts,
                                                             final Map<Name, WinningType> playersWinningResult) {
        Map<Name, Integer> playersProfit = new HashMap<>();
        for (final Name name : playersWinningResult.keySet()) {
            WinningType winningType = playersWinningResult.get(name);
            BettingAmount bettingAmount = bettingAmounts.get(name);
            int profit = profitCalculator.calculatePlayerProfit(winningType, bettingAmount);
            playersProfit.put(name, profit);
        }
        return playersProfit;
    }
}
