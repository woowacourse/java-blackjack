package blackjack.view;

import blackjack.domain.CardDTO;
import blackjack.domain.StartCardsDTO;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutputView {
    public void printStartCards(final StartCardsDTO startCardsDTO) {
        printStartPreMessage(startCardsDTO.playersCard().keySet());
        printDealerCard(startCardsDTO.dealerCard());
        printPlayersCard(startCardsDTO.playersCard());
    }

    private void printStartPreMessage(final Set<String> names) {
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", String.join(", ", names));
    }

    private void printDealerCard(final CardDTO cardDTO) {
        System.out.printf("딜러: %s%n", convertToCardFormat(cardDTO));
    }

    private String convertToCardFormat(final CardDTO cardDTO) {
        return CardNumberFormat.valueOf(cardDTO.number()).getNumber() +
                CardShapeFormat.valueOf(cardDTO.shape()).getName();
    }

    private void printPlayersCard(final Map<String, List<CardDTO>> playersCard) {
        playersCard.forEach(this::printPlayerCard);
    }

    private void printPlayerCard(final String name, final List<CardDTO> cards) {
        System.out.printf("%s카드: %s%n", name, convertToCardsFormat(cards));
    }

    private String convertToCardsFormat(final List<CardDTO> cards) {
        return String.join(", ", cards.stream().map(this::convertToCardFormat).toList());
    }

}
