package blackjack;

import static blackjack.view.OutputView.*;
import static blackjack.view.InputView.*;

import blackjack.domain.CardDeck;
import blackjack.domain.CardShuffler;
import blackjack.domain.Round;
import blackjack.domain.WinningDiscriminator;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import blackjack.domain.gambler.Name;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private static final Name DEALER = new Name("딜러");

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
                .collect(Collectors.toList());
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
        printGamblerCards("딜러", round.getInitialCardsByDealer());
        for (Name playerName : playerNames) {
            printGamblerCards(playerName.getName(), round.getCards(playerName));
        }
    }

    private static void processPlayersTurn(final List<Name> playerNames, final Round round) {
        for (Name playerName : playerNames) {
            processPlayerTurn(round, playerName);
        }
    }

    /**
     * 딜러를 상수화 TODO
     */
    private static void processDealerTurn(final Round round) {
        if (round.dealerMustDraw()) {
            round.distributeCards(DEALER, 1);
            printDealerDraw();
        }
    }

    private static void processPlayerTurn(final Round round, final Name playerName) {
        boolean flag = false;
        while (isHit(playerName)) {
            round.distributeCards(playerName, 1);
            printGamblerCards(playerName.getName(), round.getCards(playerName));
            boolean isBusted = round.isPlayerBusted(playerName);
            if (isBusted) {
                printBusted(playerName);
                break;
            }
            flag = true;
        }
        if (!flag) {
            printGamblerCards(playerName.getName(), round.getCards(playerName));
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
        printGamblerResult(DEALER.getName(), round.getCards(DEALER), round.getScore(DEALER));
        for (Name playerName : playerNames) {
            List<Card> cards = round.getCards(playerName);
            int score = round.getScore(playerName);
            printGamblerResult(playerName.getName(), cards, score);
        }
        WinningDiscriminator discriminator = round.getWinningDiscriminator();
        printWinning(discriminator);
    }
}
