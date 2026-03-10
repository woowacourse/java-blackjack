package view;

import domain.dto.BlackjackResultDto;
import domain.dto.CardDto;
import domain.dto.FinalPlayerCardDto;
import domain.dto.PlayerCardDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void displayCardDistribution(List<String> names) {
        String nameContent = String.join(", ", names);
        System.out.printf("딜러가 %s에게 2장을 나누었습니다.\n", nameContent);
    }

    public void displayCardContents(List<PlayerCardDto> playerCardDtos) {
        for (PlayerCardDto dto : playerCardDtos) {
            List<String> cardContents = new ArrayList<>();
            for (CardDto card : dto.cards()) {
                cardContents.add(card.rankName() + card.shapeName());
            }

            System.out.printf("%s카드: %s\n", dto.name(), String.join(", ", cardContents));
        }
    }

    public void displayDealerCard(int hitThreshold) {
        System.out.println("딜러는 " + hitThreshold + "이하라 한장의 카드를 더 받았습니다.");
    }

    public void displayFinalCard(List<FinalPlayerCardDto> finalPlayerCardDtos) {
        for (FinalPlayerCardDto dto : finalPlayerCardDtos) {
            List<String> cardContents = new ArrayList<>();
            for (CardDto card : dto.cards()) {
                cardContents.add(card.rankName() + card.shapeName());
            }

            System.out.printf("%s카드: %s - 결과: %d\n", dto.name(), String.join(", ", cardContents), dto.total());
        }

    }

    public void displayMatchResult(BlackjackResultDto resultDto) {
        System.out.printf("## 최종 승패\n딜러: %d승 %d패\n", resultDto.winCount(), resultDto.loseCount());
        Map<String, String> resultMap = resultDto.matchResultMap().resultMap();
        for (String playerName : resultMap.keySet()) {
            System.out.printf("%s: %s\n", playerName, resultMap.get(playerName));
        }
    }
}
