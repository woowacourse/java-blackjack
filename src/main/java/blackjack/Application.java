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
    public static void main(String[] args) {
        CardDeck cardDeck = createCardDeck();
        List<Name> playerNames = getPlayerNames();
        Round round = new Round(cardDeck, playerNames);
        round.distributeInitialCards();
        printInitialDistributionPrompt(playerNames);
        printGamblerCards("딜러", round.getInitialCardsByDealer());
        for (Name playerName : playerNames) {
            printGamblerCards(playerName.getName(), round.getCardsByPlayer(playerName));
        }
        processPlayersTurn(playerNames, round);
        processDealerTurn(round);
        printGameResult(round, playerNames);
    }

    private static void printGameResult(Round round, List<Name> playerNames) {
        printGamblerResult("딜러", round.getCardsByDealer(), round.getScoreByDealer());
        for (Name playerName : playerNames) {
            List<Card> cards = round.getCardsByPlayer(playerName);
            int score = round.getScoreByPlayer(playerName);
            printGamblerResult(playerName.getName(), cards, score);
        }
        WinningDiscriminator discriminator = round.getWinningDiscriminator();
        printWinning(discriminator);
    }

    private static void processDealerTurn(Round round) {
        if (round.dealerMustDraw()) {
            round.addDealerCard();
            printDealerDraw();
        }
    }

    private static void processPlayersTurn(List<Name> playerNames, Round round) {
        for (Name playerName : playerNames) {
            processPlayerTurn(round, playerName);
        }
    }

    private static void processPlayerTurn(Round round, Name playerName) {
        boolean flag = false;
        while (isHit(playerName)) {
            round.distributeCards(playerName, 1);
            printGamblerCards(playerName.getName(), round.getCardsByPlayer(playerName));
            boolean isBusted = round.isPlayerBusted(playerName);
            if (isBusted) {
                printBusted(playerName);
                break;
            }
            flag = true;
        }
        if (!flag) {
            printGamblerCards(playerName.getName(), round.getCardsByPlayer(playerName));
        }
    }

    private static boolean isHit(Name playerName) {
        try {
            return inputPlayerHit(playerName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isHit(playerName);
        }
    }

    private static List<Name> getPlayerNames() {
        try {
            return inputPlayerName();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPlayerNames();
        }
    }

    private static CardDeck createCardDeck() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardType.values())
                        .map(type -> new Card(shape, type)))
                .collect(Collectors.toList());
        return new CardDeck(cards, new CardShuffler());
    }
}
