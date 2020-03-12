package blackjack.view;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BlackJackResult;
import blackjack.dto.GamersResultDto;

import java.util.Map;

public class OutputView {

    private static final String INITIAL_CARD_FORMAT = "%s와 %s에게 2장의 카드를 나누었습니다.";
    private static final String CARD_STATUS_FORMAT = "%s 카드 : %s";
    private static final String DEALER_DREW_A_CARD_MESSAGE = "딜러는 16이하라 1장의 카드를 더 받았습니다.";
    private static final String CARD_RESULT_FORMAT = "%s 카드 : %s - 결과 : %d";
    private static final String RESULT_MESSAGE = "## 최종 승패";
    private static final String GAMERS_RESULT_FORMAT = "%s : %s";

    public static void printInitialCards(Dealer dealer, Players players) {
        System.out.print(System.lineSeparator());
        System.out.println(String.format(INITIAL_CARD_FORMAT, dealer.getName(), players.getNames()));
        printInitialHand(dealer, players);
        System.out.print(System.lineSeparator());
    }

    public static void printPlayerHand(Player player) {
        System.out.println(String.format(CARD_STATUS_FORMAT, player.getName(), player.getCardStatus()));
    }

    public static void printDealerDrewCard() {
        System.out.print(System.lineSeparator());
        System.out.println(DEALER_DREW_A_CARD_MESSAGE);
    }

    public static void printGamerScore(Dealer dealer, Players players) {
        System.out.println(System.lineSeparator());
        printCardResult(dealer);
        for (Player player : players) {
            printCardResult(player);
        }
    }

    private static void printCardResult(Gamer gamer) {
        System.out.println(String.format(CARD_RESULT_FORMAT, gamer.getName(), gamer.getCardStatus(), gamer.calculateSum()));
    }

    public static void printGamersResult(GamersResultDto gamersResultDto) {
        System.out.print(System.lineSeparator());
        System.out.println(RESULT_MESSAGE);
        printDealerResult(gamersResultDto.getDealerResult());
        printPlayersResult(gamersResultDto.getPlayersResult());
    }

    private static void printInitialHand(Dealer dealer, Players players) {
        printDealerInitialHand(dealer);
        for (Player player : players) {
            printPlayerHand(player);
        }
    }

    private static void printDealerInitialHand(Dealer dealer) {
        System.out.println(String.format(CARD_STATUS_FORMAT, dealer.getName(), dealer.getInitialCardStatus()));
    }

    private static void printDealerResult(Map<BlackJackResult, Integer> dealerResult) {
        System.out.println(String.format(GAMERS_RESULT_FORMAT, "딜러", makeDealerResult(dealerResult)));
    }

    private static void printPlayersResult(Map<Player, BlackJackResult> playersResult) {
        for (Map.Entry<Player, BlackJackResult> entry : playersResult.entrySet()) {
            System.out.println(String.format(GAMERS_RESULT_FORMAT, entry.getKey().getName(), entry.getValue().getKoreanName()));
        }
    }

    private static String makeDealerResult(Map<BlackJackResult, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<BlackJackResult, Integer> entry : dealerResult.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            stringBuilder.append(entry.getValue() + entry.getKey().getKoreanName());
        }
        return stringBuilder.toString();
    }
}

