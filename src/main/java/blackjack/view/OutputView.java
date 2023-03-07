package blackjack.view;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String OUTPUT_DISTRIBUTE_MESSAGE = System.lineSeparator() + "딜러와 %s에게 2장을 나누었습니다.";

    private static final String OUTPUT_DEALER_STATUS_MESSAGE = System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DELIMITER_BETWEEN_CARDS = ", ";
    private static final String DELIMITER = ": ";
    private static final String DEALER = "딜러";
    private static final String CARD = "카드";
    private static final String RESULT = " - 결과: ";

    public void printInitialCards(final Card dealerCard, final Map<String, List<Card>> playerNameToCards) {
        printInitialMessage(playerNameToCards);
        printInitialDealerCard(dealerCard);
        playerNameToCards.forEach((playerName, playerCards) -> System.out.println(convertPlayerCard(playerName, playerCards)));
        System.out.println();
    }

    private void printInitialMessage(final Map<String, List<Card>> playerNameToCards) {
        final String playerNames = String.join(DELIMITER_BETWEEN_CARDS, playerNameToCards.keySet());
        System.out.println(String.format(OUTPUT_DISTRIBUTE_MESSAGE, playerNames));
    }

    private void printInitialDealerCard(final Card card) {
        System.out.println(DEALER + DELIMITER + convertCard(card));
    }

    private String convertPlayerCard(final String name, final List<Card> cards) {
        final String card = cards.stream()
                .map(this::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));

        return name + CARD + DELIMITER + card;
    }

    private String convertCard(final Card card) {
        final String convertedSymbol = OutputViewSymbol.from(card.getSymbol())
                .getPrintSymbol();
        final String convertedShape = OutputViewShape.from(card.getShape())
                .getPrintShape();

        return convertedSymbol + convertedShape;
    }

    public void printCardStatusOfPlayer(final String playerName, final List<Card> playerCards) {
        final String cards = playerCards
                .stream()
                .map(this::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));

        System.out.println(playerName + DELIMITER + cards);
    }

    public void printDealerCardDrawMessage() {
        System.out.println(OUTPUT_DEALER_STATUS_MESSAGE);
    }

    public void printFinalStatusOfDealer(final List<Card> dealerCards, final int dealerScore) {
        final String cards = dealerCards
                .stream()
                .map(this::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));
        System.out.println();
        System.out.println(DEALER + " " + CARD + DELIMITER + cards + RESULT + dealerScore);
    }

    public void printFinalStatusOfPlayers(final Map<Map<String, List<Card>>, Integer> statusOfPlayers) {
        statusOfPlayers.forEach(this::printFinalStatusOfPlayer);
        System.out.println();
    }

    private void printFinalStatusOfPlayer(final Map<String, List<Card>> playerNameToCards, final Integer playerScore) {
        playerNameToCards.forEach((playerName, playerCards) -> System.out.print(convertPlayerCard(playerName, playerCards)));
        System.out.println(RESULT + playerScore);
    }

    public void printFinalResult(final Map<ResultType, Integer> dealerResult, final Map<String, ResultType> playerResult) {
        System.out.println("## 최종 승패");
        printDealerResult(dealerResult);
        printPlayersResult(playerResult);
    }

    private void printDealerResult(final Map<ResultType, Integer> dealerResult) {
        System.out.print(DEALER + DELIMITER);
        printDealer(dealerResult, ResultType.WIN);
        printDealer(dealerResult, ResultType.TIE);
        printDealer(dealerResult, ResultType.LOSE);
        System.out.println();
    }

    private void printPlayersResult(final Map<String, ResultType> playersResult) {
        playersResult.forEach(this::printPlayerResult);
    }

    private void printPlayerResult(final String name, final ResultType resultType) {
        System.out.println(name + DELIMITER +
                OutputViewResultType.from(resultType)
                        .getPrintResultType());
    }

    private void printDealer(final Map<ResultType, Integer> dealerResult, final ResultType resultType) {
        if (dealerResult.containsKey(resultType)) {
            System.out.print(dealerResult.get(resultType) + OutputViewResultType.from(resultType)
                    .getPrintResultType());
        }
    }

    public void printError(final Exception exception) {
        System.out.println(exception.getMessage());
    }


    //dto 버전
//    public void printInitialCards(final InitialCardDto initialCardDto) {
//        final Map<String, List<Card>> playerNameToCards = initialCardDto.getPlayerNameToCards();
//        printInitialMessage(playerNameToCards);
//        printInitialDealerCard(initialCardDto.getDealerCard());
//        playerNameToCards.forEach(System.out.println(this::printPlayerCard));
//    }

    //dto 버전
//    public void printCardStatusOfPlayer(final PlayerCardDto playerCardDto) {
//        final String name = playerCardDto.getName();
//        final String cards = playerCardDto.getCards()
//                .stream()
//                .map(this::convertCard)
//                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));
//        System.out.println(name + DELIMITER + cards);
//    }

    //dto버전
//    public void printFinalStatusOfDealer(final CardsScoreDto dealer) {
//        final String cards = dealer.getCards()
//                .stream()
//                .map(this::convertCard)
//                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));
//        System.out.println(DEALER + " " + CARD + DELIMITER + cards + RESULT + dealer.getScore());
//    }

//    public void printFinalStatusOfPlayers(final PlayerCardsScoreDto playerCardsScoreDto) {
//        playerCardsScoreDto.getPlayerNameToResult()
//                .forEach(this::printFinalStatusOfPlayer);
//    }
//
//    private void printFinalStatusOfPlayer(final String name, final CardsScoreDto cardsScoreDto) {
//        final String cards = cardsScoreDto.getCards()
//                .stream()
//                .map(this::convertCard)
//                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));
//        System.out.println(name + CARD + DELIMITER + cards + RESULT + cardsScoreDto.getScore());
//    }

    //dto버전
    //    public void printFinalResult(final FinalResultDto finalResultDto) {
//        System.out.println("## 최종 승패");
//        System.out.print(DEALER + DELIMITER);
//        printDealerResult(finalResultDto.getDealerResult());
//        printPlayersResult(finalResultDto.getPlayersResult());
//    }

//    public void printFinalResult(final FinalResultDto finalResultDto) {
//        System.out.println("## 최종 승패");
//        System.out.print(DEALER + DELIMITER);
//        printDealerResult(finalResultDto.getDealerResult());
//        printPlayersResult(finalResultDto.getPlayersResult());
//    }
}
