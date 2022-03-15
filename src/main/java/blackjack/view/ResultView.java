package blackjack.view;

import blackjack.domain.user.User;
import blackjack.domain.user.UserName;
import blackjack.domain.user.Players;
import blackjack.domain.card.Card;
import blackjack.domain.result.Outcome;
import blackjack.domain.result.OutcomeResults;
import blackjack.domain.result.Results;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    private static final String PRINT_INIT_CARD_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String PRINT_DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PRINT_OUTCOME_RESULTS_MESSAGE = "## 최종 승패";

    private static final String PRINT_INIT_DEALER_HAND_FORMAT = "딜러: %s";
    private static final String PRINT_GAMER_CARDS_FORMAT = "%s카드: %s";
    private static final String PRINT_GAMER_RESULT_FORMAT = PRINT_GAMER_CARDS_FORMAT + " - 결과: %d";

    private static final String DEALER_NAME = "딜러";
    private static final String CARD_DELIMITER = ", ";
    private static final String PRINT_RESULTS_DELIMITER = ": ";
    private static final String SPACE_DELIMITER = " ";

    public static void printInitHand(User dealer, Players players) {
        System.out.println(String.format(PRINT_INIT_CARD_FORMAT, printPlayerNames(players)));
        printInitDealerHand(dealer);
        printInitPlayersHand(players);
    }

    private static String printPlayerNames(Players players) {
        return players.get().stream()
                .map(User::getName)
                .map(UserName::get)
                .collect(Collectors.joining(CARD_DELIMITER));
    }

    private static void printInitDealerHand(User dealer) {
        List<Card> dealerCards = dealer.getCards().get();
        System.out.printf((PRINT_INIT_DEALER_HAND_FORMAT) + System.lineSeparator(),
                getCardNumberAndType(dealerCards.get(0)));
    }

    private static void printInitPlayersHand(Players players) {
        players.get()
                .stream()
                .forEach(player -> printHand(player));
        System.out.println();
    }

    public static void printHand(User user) {
        String hand = user.getCards().get().stream()
                .map(ResultView::getCardNumberAndType)
                .collect(Collectors.joining(CARD_DELIMITER));

        System.out.printf(PRINT_GAMER_CARDS_FORMAT + System.lineSeparator(), user.getName().get(), hand);
    }

    private static String getCardNumberAndType(Card card) {
        return card.getCardNumber().getNumber() + card.getType().getType();
    }

    public static void printDealerHitMessage() {
        System.out.println(PRINT_DEALER_HIT_MESSAGE);
    }

    public static void printCardsResults(User dealer, Players players) {
        System.out.println();
        printResult(dealer);
        players.get()
                .stream()
                .forEach(ResultView::printResult);
        System.out.println();
    }

    private static void printResult(User dealer) {
        String hand = dealer.getCards().get().stream()
                .map(ResultView::getCardNumberAndType)
                .collect(Collectors.joining(CARD_DELIMITER));

        System.out.printf(PRINT_GAMER_RESULT_FORMAT + System.lineSeparator(), dealer.getName().get(), hand,
                dealer.getScore());
    }

    public static void printOutcomeResults(Results results) {
        System.out.println(PRINT_OUTCOME_RESULTS_MESSAGE);

        OutcomeResults dealerResult = results.getDealerResult();
        printDealerOutcomeState(dealerResult);

        Map<User, OutcomeResults> outcomeResults = results.getPlayerResults();
        for (User user : outcomeResults.keySet()) {
            printPlayerOutcomeState(user, outcomeResults.get(user));
        }
    }

    private static void printDealerOutcomeState(OutcomeResults outcomeResults) {
        Map<Outcome, Integer> outcomes = outcomeResults.getOutcomes();
        System.out.print(DEALER_NAME + PRINT_RESULTS_DELIMITER);

        for (Outcome outcome : outcomes.keySet()) {
            System.out.print(outcomes.get(outcome) + outcome.get() + SPACE_DELIMITER);
        }
        System.out.println();
    }

    private static void printPlayerOutcomeState(User user, OutcomeResults outcomeResults) {
        Map<Outcome, Integer> outcomes = outcomeResults.getOutcomes();

        for (Outcome outcome : outcomes.keySet()) {
            System.out.print(user.getName().get() + PRINT_RESULTS_DELIMITER + outcome.get());
        }
        System.out.println();
    }
}
