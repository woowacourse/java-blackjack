package blackjack.view;

import blackjack.domain.participant.dto.CardResponse;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String OUTPUT_DISTRIBUTE_MESSAGE = "딜러와 {0}에게 2장을 나누었습니다.";

    private static final String OUTPUT_DEALER_STATUS_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DELIMITER_BETWEEN_CARDS = ", ";
    private static final String DELIMITER = ": ";
    private static final String DEALER = "딜러";
    private static final String CARD = "카드";
    private static final String RESULT = " - 결과: ";

    public void printInitialCards(final CardResponse dealerCard,
            final Map<String, List<CardResponse>> playerNameToCards) {
        printInitialMessage(playerNameToCards);
        printInitialDealerCard(dealerCard);
        playerNameToCards.forEach(this::printInitialPlayerCard);
        System.out.println();
    }

    private void printInitialMessage(final Map<String, List<CardResponse>> playerNameToCards) {
        final String playerNames = String.join(DELIMITER_BETWEEN_CARDS, playerNameToCards.keySet());
        System.out.println();
        System.out.println(MessageFormat.format(OUTPUT_DISTRIBUTE_MESSAGE, playerNames));
    }

    private void printInitialDealerCard(final CardResponse cardResponse) {
        System.out.println(DEALER + DELIMITER + convertCard(cardResponse));
    }

    private void printInitialPlayerCard(final String name, final List<CardResponse> cards) {
        final String card = cards.stream()
                .map(this::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));

        System.out.println(name + CARD + DELIMITER + card);
    }

    private String convertCard(final CardResponse cardResponse) {
        final String convertedSymbol = cardResponse.getSymbol();
        final String convertedShape = cardResponse.getShape();

        return convertedSymbol + convertedShape;
    }

    public void printCardStatusOfPlayer(final String planerName, final List<CardResponse> playerCardsResponse) {
        final String cards = playerCardsResponse
                .stream()
                .map(this::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));
        System.out.println(planerName + DELIMITER + cards);
    }

    public void printDealerCardDrawMessage() {
        System.out.println();
        System.out.println(OUTPUT_DEALER_STATUS_MESSAGE);
    }

    public void printFinalStatusOfDealer(final int score, final List<CardResponse> dealerCards) {
        final String cards = dealerCards
                .stream()
                .map(this::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));
        System.out.println();
        System.out.println(DEALER + " " + CARD + DELIMITER + cards + RESULT + score);
    }

    public void printFinalStatusOfPlayers(final Map<String, List<CardResponse>> playersCardsResponse,
            final Map<String, Integer> playersScore) {
        playersScore.keySet().forEach(name -> {
            printFinalPlayerCard(name, playersCardsResponse.get(name));
            printFinalPlayerScore(playersScore.get(name));
        });
    }


    private void printFinalPlayerCard(final String name, final List<CardResponse> cards) {
        final String card = cards.stream()
                .map(this::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));

        System.out.print(name + CARD + DELIMITER + card);
    }

    private void printFinalPlayerScore(final int score) {
        System.out.println(RESULT + score);
    }

    public void printFinalMoney(final Map<String, Integer> calculatePlayersMoney) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.println(DEALER + DELIMITER + calculateDealerMoney(calculatePlayersMoney));
        calculatePlayersMoney.forEach((name, money) -> System.out.println(name + DELIMITER + money));
    }

    private String calculateDealerMoney(final Map<String, Integer> calculatePlayersMoney) {
        int dealerMoney = 0;
        for (final int money : calculatePlayersMoney.values()) {
            dealerMoney -= money;
        }
        return String.valueOf(dealerMoney);
    }
}
