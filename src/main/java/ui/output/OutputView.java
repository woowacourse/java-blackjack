package ui.output;

import controller.BlackJackGameResponse;
import controller.HandResponse;
import controller.UserResponse;
import model.user.Result;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static controller.GameResponseRenderer.rendCardStatus;
import static controller.GameResponseRenderer.rendDealerFirstCardStatus;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static model.user.Result.judge;

public class OutputView {
    private static final String DIVIDE_CARDS_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_NAME = "딜러";

    public void printErrorMessage(final String message) {
        System.out.printf("%n[ERROR] %s%n", message);
    }


    public void printDivideTwoCard(final BlackJackGameResponse blackJackGameResponse) {
        String playersNameForm = blackJackGameResponse.getPlayerResponses().stream()
                .map(UserResponse::getName)
                .collect(joining(", "));

        System.out.println(System.lineSeparator() + String.format(DIVIDE_CARDS_MESSAGE, playersNameForm));
    }

    public void printFirstCardStatus(final BlackJackGameResponse blackJackGameResponse) {
        printFirstDealerCardStatus(blackJackGameResponse.getDealerResponse());
        printFirstPlayerCardStatus(blackJackGameResponse.getPlayerResponses());
        System.out.print(System.lineSeparator());
    }
    private void printFirstDealerCardStatus(final UserResponse dealerResponse) {
        System.out.printf("%s: %s%n", dealerResponse.getName(), rendDealerFirstCardStatus(dealerResponse.getCardResponse()));
    }

    private void printFirstPlayerCardStatus(List<UserResponse> playerResponses) {
        playerResponses.forEach(this::printUserCardStatus);
    }

    public void printPlayerCardStatus(final UserResponse playerResponse) {
        printUserCardStatus(playerResponse);
    }

    public void printUserCardStatus(final UserResponse userResponse) {
        System.out.printf("%s: %s%n", userResponse.getName(), rendCardStatus(userResponse.getCardResponse()));
    }

    public void printReceiveCardForDealer() {
        System.out.println(System.lineSeparator() + DEALER_GET_CARD + System.lineSeparator());
    }

    public void printScoreBoard(final BlackJackGameResponse blackJackGameResponse) {
        printDealerCardStatus(blackJackGameResponse.getDealerResponse());
        printPlayersScoreBoard(blackJackGameResponse.getPlayerResponses());
    }

    public void printDealerCardStatus(UserResponse dealerResponse) {
        printResult(dealerResponse.getName(), dealerResponse.getHandResponse());
    }

    private void printPlayersScoreBoard(List<UserResponse> playerResponses) {
        for (UserResponse playerResponse : playerResponses) {
            printResult(playerResponse.getName(), playerResponse.getHandResponse());
        }
    }

    private void printResult(final String name, final HandResponse handResponse) {
        System.out.printf("%s: %s - 결과: %s%n", name, rendCardStatus(handResponse.getCardResponse()), handResponse.getTotalValue());
    }

    public void printFinalResult(final BlackJackGameResponse blackJackGameResponse) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        printDealerFinalResult(blackJackGameResponse);
        printPlayersFinalResult(blackJackGameResponse);
    }

    private void printDealerFinalResult(final BlackJackGameResponse blackJackGameResponse) {

        final UserResponse dealerResponse = blackJackGameResponse.getDealerResponse();
        final List<UserResponse> playerResponses = blackJackGameResponse.getPlayerResponses();

        final Map<Result, Long> dealerFinalResult = createDealerFinalResult(dealerResponse, playerResponses);
        printDealerFinalResultForm(dealerFinalResult);
        System.out.print(System.lineSeparator());
    }

    private Map<Result, Long> createDealerFinalResult(final UserResponse dealerResponse, final List<UserResponse> playerResponses) {
        final int dealerTotalValue = dealerResponse.getTotalValue();
        return playerResponses.stream()
                .map(playerResponse -> judge(playerResponse.getTotalValue(), dealerTotalValue))
                .collect(groupingBy(Function.identity(), counting()));
    }

    private void printDealerFinalResultForm(final Map<Result, Long> dealerResult) {
        System.out.printf("%s: ", DEALER_NAME);
        for (final Result result : Result.values()) {
            printDealerFinalResultForm(dealerResult, result);
        }
    }

    private void printDealerFinalResultForm(final Map<Result, Long> dealerResult, final Result result) {
        final String scoreName = result.getName();

        if (dealerResult.containsKey(result)) {
            System.out.printf("%s%s ", dealerResult.get(result), scoreName);
        }
    }

    private void printPlayersFinalResult(final BlackJackGameResponse blackJackGameResponse) {
        final List<UserResponse> playerResponses = blackJackGameResponse.getPlayerResponses();
        final UserResponse dealerResponse = blackJackGameResponse.getDealerResponse();
        final int dealerTotalValue = dealerResponse.getTotalValue();

        playerResponses.forEach(playerResponse ->
                System.out.printf("%s: %s%n",
                        playerResponse.getName(),
                        judge(dealerTotalValue, playerResponse.getTotalValue()).getName()));
    }
}
