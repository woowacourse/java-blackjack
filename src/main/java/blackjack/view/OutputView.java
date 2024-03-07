package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.dto.PlayerDto;
import blackjack.domain.dto.PlayerResultDto;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printPlayerInitialCards(List<PlayerDto> playerDtos) {
        StringBuilder stringBuilder = new StringBuilder();

        playerDtos.forEach(playerDto ->
                stringBuilder.append(playerDto.name())
                        .append("카드: ")
                        .append(generateCardDescription(playerDto.cards()))
                        .append(System.lineSeparator())
        );

        System.out.println(stringBuilder);
    }

    public void printDealerInitialCard(Card dealerCard) {
        String card = dealerCard.getValueDescription() + dealerCard.getShapeDescription();
        System.out.println("딜러: " + card);
    }

    private String generateCardDescription(List<Card> cards) {
        List<String> list = cards.stream()
                .map(card -> (card.getValueDescription()) + (card.getShapeDescription()))
                .toList();
        return String.join(", ", list);
    }

    public void printInitialMessage(List<String> playerNames) {
        System.out.println("딜러와 " + String.join(", ", playerNames) + " 에게 2장을 나누었습니다.");
    }

    public void printPlayerCard(PlayerDto playerDto) {
        String name = playerDto.name();
        System.out.println(name + "카드: " + generateCardDescription(playerDto.cards()));
    }

    public void printExtraDealerDraw(int extraDrawCount) {
        System.out.println("딜러는 16이하라 " + extraDrawCount + "장의 카드를 더 받았습니다.");
    }

    public void printCardStatus(PlayerResultDto dealerResult, List<PlayerResultDto> playerResultDtos) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("딜러 카드: ")
                .append(generateCardDescription(dealerResult.getCards()))
                .append(" - 결과: ")
                .append(dealerResult.score())
                .append(System.lineSeparator());

        playerResultDtos.forEach(playerResultDto ->
                stringBuilder.append(playerResultDto.getName())
                        .append("카드: ")
                        .append(generateCardDescription(playerResultDto.getCards()))
                        .append(" - 결과: ")
                        .append(playerResultDto.score())
                        .append(System.lineSeparator())
        );

        System.out.println(stringBuilder);
    }

    public void printDealerResult(Map<GameResult, Integer> dealerResult) {
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + dealerResult.get(GameResult.WIN) + "승" + dealerResult.get(GameResult.LOSE) + "패"
                + dealerResult.get(GameResult.DRAW) + "무");
    }

    public void printPlayerResult(String playerName, GameResult gameResult) {
        System.out.println(playerName + ": " + gameResult.getDescription());
    }
}
