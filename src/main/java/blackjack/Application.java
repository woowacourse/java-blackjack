package blackjack;

import static blackjack.object.view.InputView.*;
import static blackjack.object.view.OutputView.printBusted;
import static blackjack.object.view.OutputView.printDealerReceiveCard;
import static blackjack.object.view.OutputView.printGamblerCards;
import static blackjack.object.view.OutputView.printGamblerResult;
import static blackjack.object.view.OutputView.printInitialDistributionPrompt;
import static blackjack.object.view.OutputView.printWinning;
import static java.util.stream.Collectors.toList;

import blackjack.object.Round;
import blackjack.object.ProfitCalculator;
import blackjack.object.card.Card;
import blackjack.object.card.CardDeck;
import blackjack.object.card.CardShape;
import blackjack.object.card.CardShuffler;
import blackjack.object.card.CardType;
import blackjack.object.gambler.Name;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        CardDeck cardDeck = createCardDeck();
        List<Name> playerNames = getPlayerNames();
        Map<Name, Integer> bettingRecords = getBettingRecords(playerNames);

        Round round = new Round(cardDeck, playerNames);

        round.distributeInitialCards();
        printInitialDistributionPrompt(playerNames);
        printInitialCards(round, playerNames);
        processPlayersTurn(playerNames, round);
        processDealerTurn(round);
        printGameResult(round, bettingRecords);
    }

    private static CardDeck createCardDeck() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(createCard())
                .collect(toList());
        return new CardDeck(cards, new CardShuffler());
    }

    private static Function<CardShape, Stream<? extends Card>> createCard() {
        return shape -> Arrays.stream(CardType.values())
                .map(type -> new Card(shape, type));
    }

    private static List<Name> getPlayerNames() {
        try {
            return inputPlayerName();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPlayerNames();
        }
    }

    private static Map<Name, Integer> getBettingRecords(List<Name> playerNames) {
        Map<Name, Integer> bettingRecords = new HashMap<>();
        for (Name playerName : playerNames) {
            int money = getBettingAmountByName(playerName);
            bettingRecords.put(playerName, money);
        }
        return bettingRecords;
    }

    private static int getBettingAmountByName(Name playerName) {
        try {
            return inputBettingAmount(playerName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBettingAmountByName(playerName);
        }
    }

    private static void printInitialCards(final Round round, final List<Name> playerNames) {
        printGamblerCards(Name.getDealerName(), round.getInitialCards(Name.getDealerName()));
        for (Name playerName : playerNames) {
            printGamblerCards(playerName, round.getInitialCards(playerName));
        }
    }

    private static void processPlayersTurn(final List<Name> playerNames, final Round round) {
        for (Name playerName : playerNames) {
            processPlayerTurn(round, playerName);
        }
    }

    private static void processPlayerTurn(final Round round, final Name playerName) {
        while (round.isGamblerCanReceiveCard(playerName, ProfitCalculator.BLACK_JACK) && isHit(playerName)) {
            round.distributeCards(playerName, 1);
            printGamblerCards(playerName, round.getCards(playerName));
        }
        if (!round.isGamblerCanReceiveCard(playerName, ProfitCalculator.BLACK_JACK)) {
            printBusted(playerName);
        }
        if (!round.isPlayerOwnsCardExceptInitialCards(playerName)) {
            printGamblerCards(playerName, round.getCards(playerName));
        }
    }

    private static boolean isHit(final Name playerName) {
        try {
            return inputPlayerHit(playerName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isHit(playerName);
        }
    }

    private static void processDealerTurn(final Round round) {
        if (round.isGamblerCanReceiveCard(Name.getDealerName(), Round.DEALER_RECEIVE_CRITERIA)) {
            round.distributeCards(Name.getDealerName(), 1);
            printDealerReceiveCard();
        }
    }

    private static void printGameResult(final Round round, final Map<Name, Integer> bettingRecords) {
        printGamblerResult(Name.getDealerName(), round.getCards(Name.getDealerName()), round.getScore(Name.getDealerName()));
        for (Name playerName : bettingRecords.keySet()) {
            List<Card> cards = round.getCards(playerName);
            int score = round.getScore(playerName);
            printGamblerResult(playerName, cards, score);
        }
        ProfitCalculator discriminator = round.getWinningDiscriminator(bettingRecords);
        printWinning(discriminator);
    }
}
