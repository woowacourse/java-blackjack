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

    private GamerOutputView() {
    }

    private static String mapToString(CardType cardType) {
        if (cardType == CardType.HEART) {
            return "하트";
        }
        if (cardType == CardType.SPADE) {
            return "스페이드";
        }
        if (cardType == CardType.CLOVER) {
            return "클로버";
        }
        if (cardType == CardType.DIAMOND) {
            return "다이아몬드";
        }
        throw new IllegalArgumentException("잘못된 카드 타입입니다.");
    }

    private static String mapToString(CardName cardName) {
        if (cardName == CardName.ACE) {
            return "A";
        }
        if (cardName == CardName.JACK) {
            return "J";
        }
        if (cardName == CardName.QUEEN) {
            return "Q";
        }
        if (cardName == CardName.KING) {
            return "K";
        }
        return String.valueOf(cardName.getCardNumber());
    }

    private static String generateGamerOutputOnlyOneCard(GamerDTO gamerDTO) {
        List<CardDTO> cardDTOS = gamerDTO.getHoldingCardsDto();
        return cardDTOS.stream()
                .map(cardDTO -> mapToString(cardDTO.getCardType()) + mapToString(cardDTO.getName()))
                .limit(1)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static String generateGamerOutput(GamerDTO dealerDTO) {
        List<CardDTO> cardDTOS = dealerDTO.getHoldingCardsDto();
        return cardDTOS.stream()
                .map(cardDTO -> mapToString(cardDTO.getCardType()) + mapToString(cardDTO.getName()))
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    public static void printOutputWithoutSummationCardPoint(GamerDTO gamerDTO) {
        String name = gamerDTO.getName();
        String nameOutput = name + CARD_POSTFIX;
        String cardsOutput = generateGamerOutput(gamerDTO);
        if (name.equals(DEALER_NAME)) {
            cardsOutput = generateGamerOutputOnlyOneCard(gamerDTO);
        }
        System.out.printf("%s: %s%n", nameOutput, cardsOutput);
    }

    public static void generateOutputWithSummationCardPoint(GamerDTO gamerDTO) {
        String nameOutput = gamerDTO.getName() + CARD_POSTFIX;
        String cardsOutput = generateGamerOutput(gamerDTO);
        String summationCardPointOutput = "결과: %d".formatted(gamerDTO.getSummationCardPoint());
        System.out.printf("%s: %s - %s%n", nameOutput, cardsOutput, summationCardPointOutput);
    }
}
