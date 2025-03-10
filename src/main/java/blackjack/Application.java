package blackjack;

import static blackjack.domain.gambler.Dealer.DEALER_NAME;
import static blackjack.view.InputView.inputPlayerHit;
import static blackjack.view.InputView.inputPlayerName;
import static blackjack.view.OutputView.printBusted;
import static blackjack.view.OutputView.printDealerDraw;
import static blackjack.view.OutputView.printGamblerCards;
import static blackjack.view.OutputView.printGamblerResult;
import static blackjack.view.OutputView.printInitialDistributionPrompt;
import static blackjack.view.OutputView.printWinning;
import static java.util.stream.Collectors.toList;

import blackjack.domain.Round;
import blackjack.domain.WinningDiscriminator;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardShuffler;
import blackjack.domain.card.CardType;
import blackjack.domain.gambler.Name;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        CardDeck cardDeck = createCardDeck();
        List<Name> playerNames = getPlayerNames();
        Round round = new Round(cardDeck, playerNames);
        round.distributeInitialCards();
        printInitialDistributionPrompt(playerNames);
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

    private static List<Name> getPlayerNames() {
        try {
            return inputPlayerName();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPlayerNames();
        }
    }

    private static void printInitialCards(final Round round, final List<Name> playerNames) {
        printGamblerCards(DEALER_NAME, round.getInitialCards(DEALER_NAME));
        for (Name playerName : playerNames) {
            printGamblerCards(playerName, round.getInitialCards(playerName));
        }
    }

    private static void processPlayersTurn(final List<Name> playerNames, final Round round) {
        for (Name playerName : playerNames) {
            processPlayerTurn(round, playerName);
        }
    }

    private static void processDealerTurn(final Round round) {
        if (round.dealerMustDraw()) {
            round.distributeCards(DEALER_NAME, 1);
            printDealerDraw();
        }
    }

    private static void processPlayerTurn(final Round round, final Name playerName) {
        boolean flag = false;
        while (isHit(playerName)) {
            round.distributeCards(playerName, 1);
            printGamblerCards(playerName, round.getCards(playerName));
            boolean isBusted = round.isPlayerBusted(playerName);
            if (isBusted) {
                printBusted(playerName);
                break;
            }
            flag = true;
        }
        if (!flag) {
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

    private static void printGameResult(final Round round, final List<Name> playerNames) {
        printGamblerResult(DEALER_NAME, round.getCards(DEALER_NAME), round.getScore(DEALER_NAME));
        for (Name playerName : playerNames) {
            List<Card> cards = round.getCards(playerName);
            int score = round.getScore(playerName);
            printGamblerResult(playerName, cards, score);
        }
        WinningDiscriminator discriminator = round.getWinningDiscriminator();
        printWinning(discriminator);
    }
}
