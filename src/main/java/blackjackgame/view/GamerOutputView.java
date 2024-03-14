package blackjackgame.view;

import blackjackgame.domain.card.CardName;
import blackjackgame.domain.card.CardType;
import blackjackgame.dto.CardDTO;
import blackjackgame.dto.GamerDTO;
import java.util.List;
import java.util.stream.Collectors;

public class GamerOutputView {
    private static final String JOIN_DELIMITER = ", ";
    private static final String CARD_POSTFIX = "카드";
    private static final String DEALER_NAME = "딜러";
    private static final Integer DEALER_FIRST_SHOW_CARD_COUNT = 1;

    private GamerOutputView() {
    }

    private static String generateGamerOutput(GamerDTO dealerDTO) {
        List<CardDTO> cardDTOS = dealerDTO.getHoldingCardsDto();
        return cardDTOS.stream()
                .map(cardDTO -> getCardType(cardDTO.getCardType()) + getCardName(cardDTO.getCardName()))
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static String generateGamerOutputWithShowingCardCount(GamerDTO dealerDTO, int showingCardCount) {
        List<CardDTO> cardDTOS = dealerDTO.getHoldingCardsDto();
        return cardDTOS.stream()
                .map(cardDTO -> getCardType(cardDTO.getCardType()) + getCardName(cardDTO.getCardName()))
                .limit(showingCardCount)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    public static void printOutputWithoutSummationCardPoint(GamerDTO gamerDTO) {
        String name = gamerDTO.getName();
        String nameOutput = name + CARD_POSTFIX;
        String cardsOutput = generateGamerOutput(gamerDTO);
        if (name.equals(DEALER_NAME)) {
            cardsOutput = generateGamerOutputWithShowingCardCount(gamerDTO, DEALER_FIRST_SHOW_CARD_COUNT);
        }
        System.out.printf("%s: %s%n", nameOutput, cardsOutput);
    }

    public static void generateOutputWithSummationCardPoint(GamerDTO gamerDTO) {
        String nameOutput = gamerDTO.getName() + CARD_POSTFIX;
        String cardsOutput = generateGamerOutput(gamerDTO);
        String summationCardPointOutput = "결과: %d".formatted(gamerDTO.getSummationCardPoint());
        System.out.printf("%s: %s - %s%n", nameOutput, cardsOutput, summationCardPointOutput);
    }

    private static String getCardType(CardType cardType) {
        return cardType.getCardType();
    }

    private static String getCardName(CardName cardName) {
        return cardName.getCardName();
    }
}
