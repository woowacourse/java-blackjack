package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.Card;
import domain.Game;
import domain.MatchCase;
import domain.dto.BettingResultDto;
import domain.dto.CardContentDto;
import domain.dto.FinalCardDto;
import domain.dto.MatchResultDto;

public final class OutputView {
    public static void displayCardDistribution(List<String> names) {
        String nameContent = String.join(", ", names);
        System.out.printf("%s가 %s에게 2장을 나누었습니다.\n", Game.DEALER_NAME, nameContent);
    }

    public static void displayCardContent(List<CardContentDto> cardContentDto) {
        for (CardContentDto dto : cardContentDto) {
            List<String> cardContents = new ArrayList<>();
            for (Card card : dto.cards()) {
                cardContents.add(card.getCardRank().getName() + card.getCardShape().getName());
            }
            System.out.printf("%s카드: %s\n", dto.name(), String.join(", ", cardContents));
        }

    }

    public static void displayDealerCard() {
        System.out.printf("%s는 %d 이하라 한장의 카드를 더 받았습니다.\n",Game.DEALER_NAME, Game.ADDITIONAL_THRESHOLD);

    }

    public static void displayFinalCard(List<FinalCardDto> finalCardDto) {
        for (FinalCardDto dto : finalCardDto) {
            List<String> cardContents = new ArrayList<>();
            for (Card card : dto.cards()) {
                cardContents.add(card.getCardRank().getName() + card.getCardShape().getName());
            }

            System.out.printf("%s카드: %s - 결과: %d\n", dto.name(), String.join(", ", cardContents), dto.total());
        }
    }

    // 사이클 1의 결과값
    public static void displayMatchResult(MatchResultDto matchResultDto) {
        Map<MatchCase, Integer> dealerMap = matchResultDto.dealerResult();
        Map<String, MatchCase> playerMap = matchResultDto.playerResult();

        System.out.printf("## 최종 승패\n%s: ",Game.DEALER_NAME);
        for (Map.Entry<MatchCase, Integer> matchcase : dealerMap.entrySet()) {
            System.out.printf("%d%s ", matchcase.getValue().intValue(), matchcase.getKey().getReversedKorResult());
        }

        for (Map.Entry<String, MatchCase> playerName : playerMap.entrySet()) {
            System.out.printf("\n%s: %s", playerName.getKey(),playerName.getValue().getKorResult());
        }
    }

    // 사이클 2의 결과값
    public static void displayBettingResult(BettingResultDto resultDto) {
        System.out.println("## 최종 수익");
        Map<String, Integer> resultMap = resultDto.bettingResult();
        for (Map.Entry<String, Integer> playerName : resultMap.entrySet()) {
            System.out.printf("%s: %d\n", playerName.getKey(), playerName.getValue().intValue());
        }
    }
}
