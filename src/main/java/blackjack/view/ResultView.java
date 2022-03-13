package blackjack.view;

import blackjack.domain.player.Player;
import blackjack.domain.player.Name;
import blackjack.domain.player.Players;
import blackjack.domain.card.Card;
import blackjack.domain.Outcome;
import blackjack.domain.OutcomeResults;
import blackjack.domain.Results;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    private static final String PRINT_INIT_CARD_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String PRINT_DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PRINT_OUTCOME_RESULTS_MESSAGE = "## 최종 승패";

    private static final String PRINT_INIT_DEALER_CARDS_FORMAT = "딜러: %s";
    private static final String PRINT_GAMER_CARDS_FORMAT = "%s카드: %s";
    private static final String PRINT_GAMER_RESULT_FORMAT = PRINT_GAMER_CARDS_FORMAT + " - 결과: %d";

    private static final String DEALER_NAME = "딜러";
    private static final String CARD_DELIMITER = ", ";
    private static final String PRINT_RESULTS_DELIMITER = ": ";
    private static final String SPACE_DELIMITER = " ";

    public static void printInitCard(Players players) {
        Player dealer = players.getDealer();
        List<Player> participants = players.getParticipants();

        System.out.println(String.format(PRINT_INIT_CARD_FORMAT, printParticipantsName(participants)));
        printInitDealerCard(dealer);
        printParticipantsCard(participants);
    }

    private static String printParticipantsName(List<Player> participants) {
        return participants.stream()
                .map(Player::getName)
                .map(Name::get)
                .collect(Collectors.joining(CARD_DELIMITER));
    }

    private static void printInitDealerCard(Player dealer) {
        List<Card> dealerCards = dealer.getCards().get();
        System.out.printf((PRINT_INIT_DEALER_CARDS_FORMAT) + System.lineSeparator(),
                getNumberAndType(dealerCards.get(0)));
    }

    private static void printParticipantsCard(List<Player> participants) {
        for (Player participant : participants) {
            printPlayerCard(participant);
        }
        System.out.println();
    }

    public static void printPlayerCard(Player player) {
        String cards = player.getCards().get().stream()
                .map(ResultView::getNumberAndType)
                .collect(Collectors.joining(CARD_DELIMITER));

        System.out.printf(PRINT_GAMER_CARDS_FORMAT + System.lineSeparator(), player.getName().get(), cards);
    }

    private static String getNumberAndType(Card card) {
        return card.getCardNumber().getNumber() + card.getType().getName();
    }

    public static void printDealerHitMessage() {
        System.out.println(PRINT_DEALER_HIT_MESSAGE);
    }

    public static void printCardsResults(Players players) {
        Player dealer = players.getDealer();
        List<Player> participants = players.getParticipants();

        printResult(dealer);
        participants.forEach(ResultView::printResult);
        System.out.println();
    }

    private static void printResult(Player dealer) {
        String cards = dealer.getCards().get().stream()
                .map(ResultView::getNumberAndType)
                .collect(Collectors.joining(CARD_DELIMITER));

        System.out.printf(PRINT_GAMER_RESULT_FORMAT + System.lineSeparator(), dealer.getName().get(), cards,
                dealer.getScore());
    }

    public static void printOutcomeResults(Results results) {
        System.out.println(PRINT_OUTCOME_RESULTS_MESSAGE);

        OutcomeResults dealerResult = results.getDealerResult();
        printDealerOutcomeState(dealerResult);

        Map<Player, OutcomeResults> outcomeResults = results.getPlayerResults();
        for (Player player : outcomeResults.keySet()) {
            printPlayerOutcomeState(player, outcomeResults.get(player));
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

    private static void printPlayerOutcomeState(Player player, OutcomeResults outcomeResults) {
        Map<Outcome, Integer> outcomes = outcomeResults.getOutcomes();

        for (Outcome outcome : outcomes.keySet()) {
            System.out.print(player.getName().get() + PRINT_RESULTS_DELIMITER + outcome.get());
        }
        System.out.println();
    }
}
