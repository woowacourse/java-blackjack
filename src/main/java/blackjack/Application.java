package blackjack;

import static blackjack.domain.gambler.Dealer.DEALER_NAME;
import static java.util.stream.Collectors.toList;

import blackjack.domain.gambler.Names;
import blackjack.domain.Round;
import blackjack.domain.WinningDiscriminator;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardShuffler;
import blackjack.domain.card.CardType;
import blackjack.domain.gambler.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        CardDeck cardDeck = createCardDeck();
        Names playerNames = getPlayerNames();
        Round round = new Round(cardDeck, playerNames);
        Map<Name, Integer> bettingAmounts = getBettingAmounts(playerNames);
        round.distributeInitialCards();
        outputView.printInitialDistributionPrompt(playerNames);
        printInitialCards(round, playerNames);
        processPlayersTurn(playerNames, round);
        processDealerTurn(round);
        printGameResult(round, playerNames);
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
            System.out.println(e.getMessage());
            return getPlayerNames();
        }
    }

    private static Map<Name, Integer> getBettingAmounts(final Names playerNames) {
        Map<Name, Integer> bettingAmounts = new HashMap<>();
        for (final Name playerName : playerNames.getNames()) {
            int bettingAmount = getBettingAmount(playerName);
            bettingAmounts.put(playerName, bettingAmount);
        }
        return bettingAmounts;
    }

    private static int getBettingAmount(final Name playerName) {
        try {
            return inputView.inputBettingAmount(playerName);
        } catch (final IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
            round.distributeCards(DEALER_NAME, 1);
            outputView.printDealerDraw();
        }
    }

    private static void processPlayerTurn(final Round round, final Name playerName) {
        boolean flag = false;
        while (isHit(playerName)) {
            round.distributeCards(playerName, 1);
            outputView.printGamblerCards(playerName, round.getCards(playerName));
            boolean isBusted = round.isPlayerBusted(playerName);
            if (isBusted) {
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
            System.out.println(e.getMessage());
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
        WinningDiscriminator discriminator = round.getWinningDiscriminator();
        outputView.printWinning(discriminator);
    }
}
