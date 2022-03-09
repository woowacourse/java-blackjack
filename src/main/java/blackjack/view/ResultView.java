package blackjack.view;

import blackjack.domain.Gamer;
import blackjack.domain.Name;
import blackjack.domain.Gamers;
import blackjack.domain.Card;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    private static final String PRINT_INIT_CARD_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String PRINT_DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PRINT_INIT_DEALER_CARDS_FORMAT = "딜러: %s";
    private static final String PRINT_GAMER_CARDS_FORMAT = "%s카드: %s";
    private static final String PRINT_GAMER_RESULT_FORMAT = PRINT_GAMER_CARDS_FORMAT + " - 결과: %d";
    private static final String NAME_DELIMITER = ": ";
    private static final String CARD_DELIMITER = ", ";

    public static void printInitCard(Gamer dealer, Gamers gamers) {
        System.out.println(String.format(PRINT_INIT_CARD_FORMAT, printPlayersName(gamers)));
        printInitDealerCard(dealer);
        printGamersCard(gamers);
    }

    private static String printPlayersName(Gamers gamers) {
        return gamers.get().stream()
                .map(Gamer::getName)
                .map(Name::get)
                .collect(Collectors.joining(CARD_DELIMITER));
    }

    private static void printInitDealerCard(Gamer dealer) {
        List<Card> dealerCards = dealer.getCards().get();
        System.out.printf((PRINT_INIT_DEALER_CARDS_FORMAT) + System.lineSeparator(),
                getNumberAndType(dealerCards.get(0)));
    }

    private static void printGamersCard(Gamers gamers) {
        for (Gamer gamer : gamers.get()) {
            printGamerCard(gamer);
        }
        System.out.println();
    }

    public static void printGamerCard(Gamer gamer) {
        String cards = gamer.getCards().get().stream()
                .map(ResultView::getNumberAndType)
                .collect(Collectors.joining(CARD_DELIMITER));
        System.out.printf(PRINT_GAMER_CARDS_FORMAT + System.lineSeparator(), gamer.getName().get(), cards);
    }

    private static String getNumberAndType(Card card) {
        return card.getCardNumber().getNumber() + card.getType().getName();
    }

    public static void printDealerHitMessage() {
        System.out.println(PRINT_DEALER_HIT_MESSAGE);
    }

    public static void printResults(Gamer dealer, Gamers players) {
        System.out.println();
        printResult(dealer);
        players.get()
                .forEach(ResultView::printResult);
    }

    private static void printResult(Gamer dealer) {
        String cards = dealer.getCards().get().stream()
                .map(ResultView::getNumberAndType)
                .collect(Collectors.joining(CARD_DELIMITER));
        System.out.printf(PRINT_GAMER_RESULT_FORMAT + System.lineSeparator(), dealer.getName().get(), cards,
                dealer.getScore());
    }
}
