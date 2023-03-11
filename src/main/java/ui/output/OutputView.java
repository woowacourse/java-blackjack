package ui.output;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String DIVIDE_CARDS_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

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

    private static String rendDealerFirstCardStatus(final List<CardResponse> cardResponses) {
        final CardResponse cardResponse = cardResponses.get(0);
        return cardResponse.getName() + cardResponse.getShape();
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

    private static String rendCardStatus(final List<CardResponse> cardResponses) {
        return cardResponses.stream()
                .map(cardResponse -> cardResponse.getName() + cardResponse.getShape())
                .collect(joining(", "));
    }

    public void printBetResult() {
        System.out.println(System.lineSeparator() + "## 최종 수익");
    }

    public void printDealerBetResult(String dealerName, long totalBet) {
        printBet(dealerName, -totalBet);
    }

    public void printPlayerBetResult(PurseResponse purseResponse) {
        final Map<String, Long> purses = purseResponse.getPurses();
        purses.keySet()
                .forEach(playerName -> printBet(playerName, purses.get(playerName)));
    }

    private void printBet(String name, long bet) {
        System.out.printf("%s: %s%n", name, bet);
    }
}
