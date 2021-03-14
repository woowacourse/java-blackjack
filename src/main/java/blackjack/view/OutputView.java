package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gametable.Outcome;
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
        final String cards = player.getUnmodifiableCards().stream()
            .map(Card::getCardName)
            .collect(Collectors.joining(", "));

        System.out.println(player.getName() + "카드: " + cards);
    }

    public static void printParticipantsCards(ProcessDto processDto) {
        final String joinedNames = String.join(", ", processDto.names());

        System.out.printf("\n%s와 %s에게 2장의 나누었습니다.\n", Dealer.DEALER_NAME, joinedNames);

        for (Entry<String, Cards> value : processDto.cards().entrySet()) {
            System.out.println(value.getKey() + cardsToString(value.getValue()));
        }

        System.out.println();
    }

    private static String cardsToString(Cards cards) {
        return cards.getUnmodifiableList().stream()
            .map(Card::getCardName)
            .collect(Collectors.joining(", ", "카드: ", ""));
    }

    public static void printDealerGetCard() {
        System.out.println("\n" + Dealer.DEALER_NAME + "는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printCardsResult(ProcessDto processDto) {
        for (Entry<String, Cards> value : processDto.cards().entrySet()) {
            System.out.println(value.getKey() + cardsToString(value.getValue())
                + " - 결과: " + value.getValue().sumCardsForResult());
        }
    }

    public static void printOutcome(ResultDto resultDto) {
        System.out.println("\n## 최종 승패");

        printDealerOutcome(resultDto.summarizeFinalOutcomeOfPlayers());
        printPlayersOutcome(resultDto.summarizePlayersFinalOutcome());
    }

    private static void printPlayersOutcome(Map<String, Outcome> playersFinalOutcome) {
        for (Map.Entry<String, Outcome> entry : playersFinalOutcome.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getWord());
        }
    }

    private static void printDealerOutcome(Map<Outcome, Integer> dealerInfo) {
        System.out.println(Dealer.DEALER_NAME + ": "
            + dealerInfo.get(Outcome.LOSE) + "승"
            + dealerInfo.get(Outcome.WIN) + "패"
            + dealerInfo.get(Outcome.DRAW) + "무"
        );

    }

    public static void printProfit(ResultDto resultDto) {
        System.out.println();
        System.out.println("## 최종 수익");
        printDealerProfit(resultDto);
        printPlayersProfit(resultDto);
    }

    private static void printDealerProfit(ResultDto resultDto) {
        System.out.println(Dealer.DEALER_NAME + ": " + resultDto.summarizeDealerProfit());
    }

    private static void printPlayersProfit(ResultDto resultDto) {
        for (Map.Entry<String, BigDecimal> entry : resultDto.summarizePlayersProfit().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
