package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.UserScoreDto;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String DRAW_MESSAGE = "딜러와 %s에게 2장의 나누었습니다.\n";
    private static final String CARD_FORMAT = "%s카드: %s";
    private static final String DEALER = "딜러";
    private static final String DELIMITER = ", ";
    private static final String ADD_DEALER_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String SCORE_FORMAT = " - 결과: %d";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 수익";
    private static final String REVENUE_FORMAT = "%s: %.3f\n";

    public static void printDrawMessage(List<String> userNames) {
        System.out.printf(DRAW_MESSAGE, String.join(DELIMITER, userNames));
    }

    public static void printTotalUserCards(CardDto dealerCardDto, List<PlayerDto> playerDtos) {
        System.out.printf((CARD_FORMAT) + "%n", DEALER, dealerCardDto.getCardInfo());
        for (PlayerDto playerDto : playerDtos) {
            printPlayerCard(playerDto);
        }
    }

    public static void printPlayerCard(PlayerDto playerDto) {
        String cards = String.join(DELIMITER, playerDto.getCards());
        System.out.printf((CARD_FORMAT) + "%n", playerDto.getName(), cards);
    }

    public static void printAddDealerCard() {
        System.out.println(ADD_DEALER_CARD_MESSAGE);
    }

    public static void printTotalResult(List<UserScoreDto> userScoreDtos) {
        for (UserScoreDto userScoreDto : userScoreDtos) {
            String cards = String.join(DELIMITER, userScoreDto.getCards());
            System.out.printf(CARD_FORMAT + SCORE_FORMAT + "%n", userScoreDto.getName(), cards, userScoreDto.getScore());
        }
    }

    public static void printFinalResult(Map<String, Double> userProfit) {
        System.out.println(FINAL_RESULT_MESSAGE);
        System.out.printf(REVENUE_FORMAT, DEALER, userProfit.get(DEALER));

        for (Map.Entry<String, Double> entry : userProfit.entrySet()) {
            showPlayerProfit(entry);
        }
    }

    private static void showPlayerProfit(Map.Entry<String, Double> entry) {
        if (!entry.getKey().equals(DEALER)) {
            System.out.printf(REVENUE_FORMAT, entry.getKey(), entry.getValue());
        }
    }
}
