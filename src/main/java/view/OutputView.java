package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.Card;
import domain.Dealer;
import domain.Game;
import domain.dto.BettingResultDto;
import domain.dto.CardContentDto;
import domain.dto.FinalCardDto;
import domain.dto.MatchResultDto;
import domain.enums.MatchCase;

public final class OutputView {
    public static void displayCardDistribution(List<String> names) {
        String nameContent = String.join(", ", names);
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", Dealer.DEALER_NAME, nameContent);
    }

    public static void displayCardContents(List<CardContentDto> cardContentDto) {
        for (CardContentDto dto : cardContentDto) {
            displayCardContent(dto);
        }
    }

    public static void displayCardContent(CardContentDto dto) {
        System.out.printf("%s카드: %s\n", dto.name(), String.join(", ", dto.cards()));
    }

    public static void displayDealerCard() {
        System.out.printf("%s는 %d 이하라 한장의 카드를 더 받았습니다.\n", Dealer.DEALER_NAME, Dealer.ADDITIONAL_THRESHOLD);
    }

    public static void displayFinalCard(List<FinalCardDto> finalCardDto) {
        for (FinalCardDto dto : finalCardDto) {
            System.out.printf("%s카드: %s - 결과: %d\n", dto.name(), String.join(", ", dto.cards()), dto.total());
        }
    }

    // 사이클 1의 결과값
    public static void displayMatchResult(MatchResultDto matchResultDto) {
        Map<MatchCase, Integer> dealerMap = matchResultDto.dealerResult();
        Map<String, MatchCase> playerMap = matchResultDto.playerResult();

        System.out.printf("## 최종 승패\n%s: ", Dealer.DEALER_NAME);
        for (Map.Entry<MatchCase, Integer> matchCase : dealerMap.entrySet()) {
            System.out.printf("%d%s ", matchCase.getValue().intValue(), matchCase.getKey().getReversedKorResult());
        }

        for (Map.Entry<String, MatchCase> playerName : playerMap.entrySet()) {
            System.out.printf("\n%s: %s", playerName.getKey(), playerName.getValue().getKorResult());
        }
    }

    // 사이클 2의 결과값
    public static void displayBettingResult(BettingResultDto resultDto) {
        Map<String, Integer> resultMap = resultDto.bettingResult();

        System.out.println("\n## 최종 수익");
        System.out.printf("%s: %d\n", Dealer.DEALER_NAME, resultDto.totalMoney());

        for (Map.Entry<String, Integer> playerName : resultMap.entrySet()) {
            System.out.printf("%s: %d\n", playerName.getKey(), playerName.getValue());
        }
    }

    public static void printError(String error) {
        System.out.println(error);
    }
}
