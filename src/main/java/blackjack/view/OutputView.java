package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Money;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER_BETWEEN_CARDS = ", ";
    private static final String DELIMITER = ": ";
    private static final String DEALER = "딜러";
    private static final String CARD = "카드";
    private static final String RESULT = " - 결과: ";
    private static final String OUTPUT_DISTRIBUTE_MESSAGE = System.lineSeparator() + "%s와 %s에게 2장을 나누었습니다.";
    private static final String OUTPUT_DEALER_STATUS_MESSAGE = System.lineSeparator() + DEALER + "는 16이하라 한장의 카드를 더 받았습니다.";

    private static final String FINAL_RESULT = System.lineSeparator() + "## 최종 수익";


    public void printInitialCardsGuideMessage(final List<String> playerNameList) {
        final String playerNames = String.join(DELIMITER_BETWEEN_CARDS, playerNameList);
        System.out.printf((OUTPUT_DISTRIBUTE_MESSAGE) + System.lineSeparator(), DEALER, playerNames);
    }

    public void printInitialCardsOfDealer(final Card dealerInitialCard) {
        System.out.println(DEALER + DELIMITER + convertCard(dealerInitialCard));
    }

    public void printInitialCardsOfPlayers(final String playerName, final List<Card> playerCards) {
        System.out.println(playerName + CARD + DELIMITER + convertCards(playerCards)+ System.lineSeparator());
    }

    private String convertCards(final List<Card> cards) {
        return cards.stream()
                .map(this::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));
    }

    private String convertCard(final Card card) {
        final String convertedSymbol = OutputViewSymbol.from(card.getDenomination())
                .getPrintSymbol();
        final String convertedShape = OutputViewShape.from(card.getShape())
                .getPrintShape();

        return convertedSymbol + convertedShape;
    }

    public void printCurrentCardsOfPlayer(final String playerName, final List<Card> playerCards) {
        final String cards = convertCards(playerCards);
        System.out.println(playerName + DELIMITER + cards);
    }

    public void printDealerCardDrawMessage() {
        System.out.println(OUTPUT_DEALER_STATUS_MESSAGE);
    }

    public void printFinalStatusOfDealer(final List<Card> dealerCards, final int dealerScore) {
        final String cards = convertCards(dealerCards);
        System.out.println();
        System.out.println(DEALER + " " + CARD + DELIMITER + cards + RESULT + dealerScore);
    }

    public void printFinalStatusOfPlayer(final String playerName, final List<Card> playerCard, final int playerScore) {
        System.out.println(playerName + DELIMITER + convertCards(playerCard) + RESULT + playerScore);
    }

    public void printProfitOfGameParticipants(final Money revenueOfDealer, final Map<String, Money> revenueOfPlayers) {
        System.out.println(FINAL_RESULT);
        System.out.println(DEALER + DELIMITER + (int) revenueOfDealer.getValue());
        revenueOfPlayers.forEach((name, money) -> System.out.println(name + DELIMITER + (int) money.getValue()));
    }

    public void printError(final String exception) {
        System.out.println(exception);
    }

}
