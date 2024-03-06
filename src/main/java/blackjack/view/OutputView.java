package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.dto.PlayerDto;
import java.util.List;

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
        System.out.println("딜러와 "  + String.join(", ", playerNames) + " 에게 2장을 나누었습니다.");
    }
}
