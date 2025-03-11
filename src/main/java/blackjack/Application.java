package blackjack;

import static blackjack.view.InputView.inputPlayerHit;
import static blackjack.view.InputView.inputPlayerName;
import static blackjack.view.OutputView.printBusted;
import static blackjack.view.OutputView.printDealerReceiveCard;
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
import java.util.function.Function;
import java.util.stream.Stream;

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

    private static void processDealerTurn(final Round round) {
        if (round.isGamblerCanReceiveCard(Name.getDealerName(), Round.DEALER_RECEIVE_CRITERIA)) {
            round.distributeCards(Name.getDealerName(), 1);
            printDealerReceiveCard();
        }
    }

    private static void processPlayerTurn(final Round round, final Name playerName) {
        while (isHit(playerName)) {
            round.distributeCards(playerName, 1);
            printGamblerCards(playerName, round.getCards(playerName));
            boolean isBusted = !round.isGamblerCanReceiveCard(playerName, WinningDiscriminator.BLACK_JACK);
            if (isBusted) {
                printBusted(playerName);
                break;
            }
        }
        if (!round.isPlayerOwnsCardExceptInitialCards(playerName)) { // 첫 카드를 받은 이후로 카드를 받지 않은 경우
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
        printGamblerResult(Name.getDealerName(), round.getCards(Name.getDealerName()), round.getScore(Name.getDealerName()));
        for (Name playerName : playerNames) {
            List<Card> cards = round.getCards(playerName);
            int score = round.getScore(playerName);
            printGamblerResult(playerName, cards, score);
        }
        WinningDiscriminator discriminator = round.getWinningDiscriminator();
        printWinning(discriminator);
    }
}
