package blackjack.view;

import blackjack.controller.dto.GamersResultDto;
import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.Profit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INITIAL_CARD_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String CARD_STATUS_FORMAT = "%s 카드 : %s";
    private static final String DEALER_DREW_A_CARD_MESSAGE = "딜러는 16이하라 1장의 카드를 더 받았습니다.";
    private static final String CARD_STATUS_AND_RESULT_FORMAT = "%s 카드 : %s - 결과 : %d";
    private static final String PROFIT_MESSAGE = "## 최종 수익";
    private static final String GAMERS_RESULT_FORMAT = "%s : %s";
    private static final String CARD_FORMAT = "%s%s";
    private static final String DELIMITER = ", ";

    public static void printInitialCards(Dealer dealer, Players players) {
        System.out.print(System.lineSeparator());
        System.out.println(String.format(INITIAL_CARD_FORMAT, players.getNames().stream().collect(Collectors.joining(DELIMITER))));
        printInitialHand(dealer, players);
        System.out.print(System.lineSeparator());
    }

    public static void printPlayerHand(Player player) {
        System.out.println(String.format(CARD_STATUS_FORMAT, player.getName(), makeHandResult(player.getHand())));
    }

    public static void printDealerDrewCard() {
        System.out.print(System.lineSeparator() + DEALER_DREW_A_CARD_MESSAGE);
    }

    public static void printGamerScore(Dealer dealer, Players players) {
        System.out.println(System.lineSeparator());
        printCardStatusAndResult(dealer);
        for (Player player : players) {
            printCardStatusAndResult(player);
        }
    }

    public static void printGamersProfit(GamersResultDto gamersResultDto) {
        System.out.println(System.lineSeparator() + PROFIT_MESSAGE);
        System.out.println(makeGamersResult(gamersResultDto.getGamersResult()));
    }

    private static void printInitialHand(Dealer dealer, Players players) {
        System.out.println(String.format(CARD_STATUS_FORMAT, dealer.getName(), dealer.getOpenCard().toString()));
        for (Player player : players) {
            printPlayerHand(player);
        }
    }

    private static void printCardStatusAndResult(Gamer gamer) {
        System.out.println(String.format(CARD_STATUS_AND_RESULT_FORMAT, gamer.getName(), makeHandResult(gamer.getHand()), gamer.handScore().getNumber()));
    }

    private static String makeHandResult(List<Card> cards) {
        return cards.stream()
                .map(card -> String.format(CARD_FORMAT, card.getCardSymbol(), card.getCardType().getKoreanName()))
                .collect(Collectors.joining(DELIMITER));
    }

    private static String makeGamersResult(Map<Gamer, Profit> gamersResult) {
        return gamersResult.entrySet().stream()
                .map(entry -> String.format(GAMERS_RESULT_FORMAT, entry.getKey().getName(), entry.getValue().getProfit()))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}