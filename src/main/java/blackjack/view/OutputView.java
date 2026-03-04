package blackjack.view;

import blackjack.dto.ResultDto;
import java.util.List;

public class OutputView {
    public void print(List<String> playerNames) {
        String joinedPlayerNames = String.join(", ", playerNames);
        System.out.println("딜러와 " + joinedPlayerNames + "에게 2장을 나누었습니다.");
    }

    public void printPlayerCards(String playerName, List<String> cards) {
        String joinedCards = String.join(", ", cards);
        System.out.println(playerName + "카드: " + joinedCards);
    }

    public void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printPlayerScore(String playerName, List<String> cards, int score) {
        String joinedCards = String.join(", ", cards);
        System.out.println(playerName + "카드: " + joinedCards + " - 결과: " + score);
    }

    public void printResult(List<ResultDto> resultDtos) {
        System.out.println("## 최종 승패");

        int loseCount = (int) resultDtos.stream().filter(ResultDto::win).count();
        System.out.println("딜러: " + (resultDtos.size() - loseCount) + "승 " + loseCount + "패");

        resultDtos.forEach(this::printResult);
    }

    public void printResult(ResultDto resultDto) {
        if (resultDto.win()) {
            System.out.println(resultDto.name() + ": 승");
            return;
        }
        System.out.println(resultDto.name() + ": 패");
    }
}
