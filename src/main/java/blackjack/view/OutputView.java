package blackjack.view;

import blackjack.domain.Outcome;
import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.dto.ProcessDto;
import blackjack.dto.ResultDto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printCards(Player player) {
        final String cards = String.join(", ", player.getCards().getUnmodifiableCardNames());

        System.out.printf("%s카드: %s\n", player.getName(), cards);
    }

    public static void printCards(ProcessDto processDto) {
        final String joinedNames = String.join(", ", processDto.names());

        System.out.printf("\n%s와 %s에게 2장의 나누었습니다.\n", Dealer.DEALER_NAME, joinedNames);

        for (Entry<String, Cards> value : processDto.cards().entrySet()) {
            System.out.println(value.getKey() + cardsToString(value.getValue()));
        }

        System.out.println();
    }

    private static String cardsToString(Cards cards) {
        return cards.getUnmodifiableCardNames().stream()
                .collect(Collectors.joining(", ", "카드: ", ""));
    }

    public static void printDealerGetCard() {
        System.out.printf("\n%s는 16이하라 한장의 카드를 더 받았습니다.\n\n", Dealer.DEALER_NAME);
    }

    public static void printCardsResult(ProcessDto processDto) {
        for (Entry<String, Cards> value : processDto.cards().entrySet()) {
            System.out.printf("%s%s - 결과: %s\n",
                    value.getKey(), cardsToString(value.getValue()), value.getValue().sumCardsForResult());
        }
    }

    public static void printOutcome(ResultDto resultDto) {
        System.out.println("\n## 최종 승패");

        printDealerOutcome(resultDto.summarizeFinalOutcomeOfPlayers());
        printPlayersOutcome(resultDto.summarizePlayersFinalOutcome());
    }

    private static void printPlayersOutcome(Map<String, Outcome> playersFinalOutcome) {
        for (Map.Entry<String, Outcome> entry : playersFinalOutcome.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue().getWord());
        }
    }

    private static void printDealerOutcome(Map<Outcome, Integer> dealerInfo) {
        System.out.printf("%s: %s승%s패%s무\n", Dealer.DEALER_NAME,
                dealerInfo.get(Outcome.LOSE), dealerInfo.get(Outcome.WIN), dealerInfo.get(Outcome.DRAW));
    }

    public static void printProfit(ResultDto resultDto) {
        System.out.println();
        System.out.println("## 최종 수익");
        printDealerProfit(resultDto);
        printPlayersProfit(resultDto);
    }

    private static void printDealerProfit(ResultDto resultDto) {
        System.out.printf("%s: %s\n", Dealer.DEALER_NAME, resultDto.summarizeDealerProfit());
    }

    private static void printPlayersProfit(ResultDto resultDto) {
        for (Map.Entry<String, BigDecimal> entry : resultDto.summarizePlayersProfit().entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
    }

}
