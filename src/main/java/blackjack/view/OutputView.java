package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.ResultType;
import blackjack.domain.result.model.ProfitDto;
import blackjack.domain.result.model.WinningDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.view.ResultTypeWordMapper.resultToKorean;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String INITIAL_DEAL_INFO_MSG = "딜러와 %s에게 2장의 나누었습니다.";
    private static final String CARD_STATUS_INFO_MSG = "%s카드: %s";
    private static final String ADDITIONAL_DEALER_CARD_MSG = "딜러 카드의 합이 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_FINAL_INFO_MSG = "%s카드: %s - 결과: %d";
    private static final String FINAL_RESULT_ANNOUNCE_MSG = "## 최종 승패";
    private static final String FINAL_RESULT_MSG = "%s: %s";

    public static <T extends Player> void printInitialStatus(Players<T> players, Dealer dealer) {
        System.out.println();
        System.out.println(String.format(INITIAL_DEAL_INFO_MSG, String.join(DELIMITER, players.names())));

        printCardsStatus(dealer.name(), dealer.showFirstCard());

        for (Player player : players.getPlayers()) {
            List<String> cardsInfo = player.showCards();
            printCardsStatus(player.name(), cardsInfo);
        }

        System.out.println();
    }

    public static void printDealerGetMoreCard() {
        System.out.println();
        System.out.println(ADDITIONAL_DEALER_CARD_MSG);
    }

    public static <T extends Player> void printFinalStatus(Players<T> players, Dealer dealer) {
        System.out.println();
        printCardsStatus(dealer.name(), dealer.showCards(), dealer.computeScore());

        for (Player player : players.getPlayers()) {
            printCardsStatus(player.name(), player.showCards(), player.computeScore());
        }
    }

    public static void printCardsStatus(String name, List<String> cardsInfo) {
        String cardInfo = String.join(DELIMITER, cardsInfo);
        System.out.println(String.format(CARD_STATUS_INFO_MSG, name, cardInfo));
    }

    private static void printCardsStatus(String name, List<String> cardsInfo, int score) {
        String cardInfo = String.join(DELIMITER, cardsInfo);
        System.out.println(String.format(CARD_FINAL_INFO_MSG, name, cardInfo, score));
    }

    public static void printFinalResult(String dealerName, Map<ResultType, Long> dealerResult, List<WinningDto> playerDtos) {
        System.out.println();
        System.out.println(FINAL_RESULT_ANNOUNCE_MSG);

        String dealerRecord = Arrays.stream(ResultType.values())
                .filter(dealerResult::containsKey)
                .map(type -> dealerResult.get(type) + resultToKorean(type))
                .collect(Collectors.joining(DELIMITER));

        System.out.println(String.format(FINAL_RESULT_MSG, dealerName, dealerRecord));

        for (WinningDto dto : playerDtos) {
            System.out.println(String.format(FINAL_RESULT_MSG, dto.getName(), resultToKorean(dto.getResultType())));
        }
    }

    public static void printFinalProfit(String dealerName, double dealerProfit, List<ProfitDto> playerDtos) {
        System.out.println();
        System.out.println(FINAL_RESULT_ANNOUNCE_MSG);

        System.out.println(String.format(FINAL_RESULT_MSG, dealerName, dealerProfit));

        for (ProfitDto dto : playerDtos) {
            System.out.println(String.format(FINAL_RESULT_MSG, dto.getName(), dto.getProfit()));
        }
    }


}
