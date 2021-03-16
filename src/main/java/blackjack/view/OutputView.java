package blackjack.view;

import blackjack.domain.Outcome;
import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.dto.PlayerCardsDto;
import blackjack.dto.ProcessDto;
import blackjack.dto.ResultDto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    public static final String DELIMITER = ", ";
    public static final String PROFIT_FORMAT = "%s: %s\n";
    public static final String DEALER_OUTCOME_FORMAT = "%s: %s승%s패%s무\n";
    public static final String FINAL_RESULT_WORDS = "## 최종 수익";
    public static final String FINAL_OUTCOME_WORDS = "\n## 최종 승패";
    public static final String CARDS_RESULT_FORMAT_WITH_SUM = "%s%s - 결과: %s\n";
    public static final String DEALER_GOT_CARD_FORMAT = "\n%s는 16이하라 한장의 카드를 더 받았습니다.\n\n";
    public static final String CARDS_RESULT_FORMAT = "%s카드: %s\n";
    public static final String INITIAL_CARDS_MESSAGE = "\n%s와 %s에게 2장의 나누었습니다.\n";

    private OutputView() {
    }

    public static void printPlayerCards(PlayerCardsDto playerInfo) {
        final String cards = String.join(DELIMITER, playerInfo.getCards().getUnmodifiableCardNames());

        System.out.printf(CARDS_RESULT_FORMAT, playerInfo.getName(), cards);
    }

    public static void printInitialCards(ProcessDto processDto) {
        final String joinedNames = String.join(DELIMITER, processDto.names());

        System.out.printf(INITIAL_CARDS_MESSAGE, Dealer.DEALER_NAME, joinedNames);

        for (Entry<String, Cards> value : processDto.cards().entrySet()) {
            System.out.println(value.getKey() + cardsToString(value.getValue()));
        }

        System.out.println();
    }

    private static String cardsToString(Cards cards) {
        return cards.getUnmodifiableCardNames().stream()
                .collect(Collectors.joining(DELIMITER, "카드: ", ""));
    }

    public static void printDealerGetCard() {
        System.out.printf(DEALER_GOT_CARD_FORMAT, Dealer.DEALER_NAME);
    }

    public static void printCardsResult(ProcessDto processDto) {
        for (Entry<String, Cards> value : processDto.cards().entrySet()) {
            System.out.printf(CARDS_RESULT_FORMAT_WITH_SUM,
                    value.getKey(), cardsToString(value.getValue()), value.getValue().sumCardsForResult());
        }
    }

    public static void printOutcome(ResultDto resultDto) {
        System.out.println(FINAL_OUTCOME_WORDS);

        printDealerOutcome(resultDto.summarizeFinalOutcomeOfPlayers());
        printPlayersOutcome(resultDto.summarizePlayersFinalOutcome());
    }

    private static void printPlayersOutcome(Map<String, Outcome> playersFinalOutcome) {
        for (Map.Entry<String, Outcome> entry : playersFinalOutcome.entrySet()) {
            System.out.printf(PROFIT_FORMAT, entry.getKey(), entry.getValue().getWord());
        }
    }

    private static void printDealerOutcome(Map<Outcome, Integer> dealerInfo) {
        System.out.printf(DEALER_OUTCOME_FORMAT, Dealer.DEALER_NAME,
                dealerInfo.get(Outcome.LOSE), dealerInfo.get(Outcome.WIN), dealerInfo.get(Outcome.DRAW));
    }

    public static void printProfit(ResultDto resultDto) {
        System.out.println();
        System.out.println(FINAL_RESULT_WORDS);
        printDealerProfit(resultDto);
        printPlayersProfit(resultDto);
    }

    private static void printDealerProfit(ResultDto resultDto) {
        System.out.printf(PROFIT_FORMAT, Dealer.DEALER_NAME, resultDto.summarizeDealerProfit());
    }

    private static void printPlayersProfit(ResultDto resultDto) {
        for (Map.Entry<String, BigDecimal> entry : resultDto.summarizePlayersProfit().entrySet()) {
            System.out.printf(PROFIT_FORMAT, entry.getKey(), entry.getValue());
        }
    }

}
