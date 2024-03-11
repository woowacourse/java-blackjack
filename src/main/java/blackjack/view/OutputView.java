package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.dto.PlayerDto;
import blackjack.domain.dto.PlayerResultDto;
import blackjack.view.description.ShapeDescription;
import blackjack.view.description.ValueDescription;
import java.util.List;

public class OutputView {
    public void printPlayerInitialCards(List<PlayerDto> playerDtos) {
        StringBuilder stringBuilder = new StringBuilder();

        playerDtos.forEach(playerDto ->
                stringBuilder.append(playerDto.name())
                        .append("카드: ")
                        .append(generateCardsDescription(playerDto.cards()))
                        .append(System.lineSeparator())
        );

        System.out.println(stringBuilder);
    }

    public void printDealerInitialCard(Card dealerCard) {
        System.out.println("딜러: " + generateCardDescription(dealerCard));
    }

    private String generateCardsDescription(List<Card> cards) {
        List<String> list = cards.stream()
                .map(this::generateCardDescription)
                .toList();
        return String.join(", ", list);
    }

    private String generateCardDescription(Card card) {
        String shapeDescription = ShapeDescription.getDescription(card.shape());
        String valueDescription = ValueDescription.getDescription(card.value());
        return shapeDescription + valueDescription;
    }

    public void printInitialMessage(List<String> playerNames) {
        System.out.println("딜러와 " + String.join(", ", playerNames) + " 에게 2장을 나누었습니다.");
    }

    public void printPlayerCard(PlayerDto playerDto) {
        String name = playerDto.name();
        System.out.println(name + "카드: " + generateCardsDescription(playerDto.cards()));
    }

    public void printExtraDealerDraw(int extraDrawCount) {
        System.out.println("딜러는 16이하라 " + extraDrawCount + "장의 카드를 더 받았습니다.");
    }

    public void printCardStatus(PlayerResultDto dealerResult, List<PlayerResultDto> playerResultDtos) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("딜러 카드: ")
                .append(generateCardsDescription(dealerResult.getCards()))
                .append(" - 결과: ")
                .append(dealerResult.score())
                .append(System.lineSeparator());

        playerResultDtos.forEach(playerResultDto ->
                stringBuilder.append(playerResultDto.getName())
                        .append("카드: ")
                        .append(generateCardsDescription(playerResultDto.getCards()))
                        .append(" - 결과: ")
                        .append(playerResultDto.score())
                        .append(System.lineSeparator())
        );

        System.out.println(stringBuilder);
    }

    public void printDealerProfit(int dealerProfit) {
        System.out.println("## 최종 수익");
        printPlayerProfit("딜러", dealerProfit);
    }

    public void printPlayerProfit(String playerName, int profit) {
        System.out.println(playerName + ": " + profit);
    }
}
