package blackjack.view;

import blackjack.response.CardResponse;
import blackjack.response.DealerScoreResponse;
import blackjack.response.FinalResultResponse;
import blackjack.response.InitialCardResponse;
import blackjack.response.PlayerCardsResponse;
import blackjack.response.PlayersCardsResponse;
import blackjack.response.PlayersCardsResponse.CardsScore;
import blackjack.response.ResultTypeResponse;
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

    public void printInitialCards(final InitialCardResponse initialCardResponse) {
        final Map<String, List<CardResponse>> playerNameToCards = initialCardResponse.getPlayerNameToCards();
        printInitialMessage(playerNameToCards);
        printInitialDealerCard(initialCardResponse.getDealerCard());
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

    public void printCardStatusOfPlayer(final PlayerCardsResponse playerCardsResponse) {
        final String name = playerCardsResponse.getName();
        final String cards = playerCardsResponse.getCards()
                .stream()
                .map(this::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));
        System.out.println(name + DELIMITER + cards);
    }

    public void printDealerCardDrawMessage() {
        System.out.println();
        System.out.println(OUTPUT_DEALER_STATUS_MESSAGE);
    }

    public void printFinalStatusOfDealer(final DealerScoreResponse dealerScoreResponse) {
        final String cards = dealerScoreResponse.getCards()
                .stream()
                .map(this::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));
        System.out.println();
        System.out.println(DEALER + " " + CARD + DELIMITER + cards + RESULT + dealerScoreResponse.getScore());
    }

    public void printFinalStatusOfPlayers(final PlayersCardsResponse playersCardsResponse) {
        playersCardsResponse.getPlayerNameToResult()
                .forEach(this::printFinalStatusOfPlayer);
    }

    private void printFinalStatusOfPlayer(final String name, final CardsScore cardsScore) {
        final String cards = cardsScore.getCards()
                .stream()
                .map(this::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));
        System.out.println(name + CARD + DELIMITER + cards + RESULT + cardsScore.getScore());
    }

    public void printFinalResult(final FinalResultResponse finalResultDto) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.print(DEALER + DELIMITER);
        printDealerResult(finalResultDto.getDealerResult());
        printPlayersResult(finalResultDto.getPlayersToResult());
    }

    private void printDealerResult(final Map<ResultTypeResponse, Long> dealerResult) {
        printDealer(dealerResult, ResultTypeResponse.from("WIN"));
        printDealer(dealerResult, ResultTypeResponse.from("DRAW"));
        printDealer(dealerResult, ResultTypeResponse.from("LOSE"));
        System.out.println();
    }

    private void printPlayersResult(final Map<String, ResultTypeResponse> playersResult) {
        playersResult.forEach(this::printPlayerResult);
    }

    private void printPlayerResult(final String name, final ResultTypeResponse resultTypeResponse) {
        System.out.println(name + DELIMITER + resultTypeResponse.getResult());
    }

    private void printDealer(
            final Map<ResultTypeResponse, Long> dealerResult,
            final ResultTypeResponse resultTypeResponse) {

        if (dealerResult.containsKey(resultTypeResponse)) {
            System.out.print(
                    dealerResult.get(resultTypeResponse) + resultTypeResponse.getResult());
        }
    }

    public void printError(final Exception exception) {
        System.out.println(exception.getMessage());
    }
}
