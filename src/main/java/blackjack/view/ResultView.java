package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.Result;
import blackjack.domain.player.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    private static final String CARD_MARK_MESSAGE = "카드: ";
    private static final String RESULT_MARK_MESSAGE = " - 결과: ";
    private static final String FINAL_RESULT_MESSAGE = "##최종 승패";
    private static final String DEALER_MARK_MESSAGE = "딜러: ";
    private static final String DEALER_RECEIVE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String COLON = ": ";

    public static void printDealerAndUserCards(List<Player> users, Player dealer) {
        printDealerCard(dealer);
        printUsersCards(users);
    }

    private static void printUsersCards(List<Player> users) {
        for (Player user : users) {
            printUserCards(user);
        }
    }

    public static void printUserCards(Player user) {
        System.out.println(makeUserCardsToString(user));
    }

    private static String makeUserCardsToString(Player user) {
        StringBuilder sb = new StringBuilder();
        List<String> userCards = user.getCards().stream()
                .map(card -> card.getCardNumberType() + card.getCardPattern())
                .collect(Collectors.toList());
        sb.append(user.getName())
                .append(CARD_MARK_MESSAGE)
                .append(String.join(", ", userCards));
        return sb.toString();
    }

    private static void printDealerCard(Player dealer) {
        System.out.print(dealer.getName() + CARD_MARK_MESSAGE);
        String dealerCard = dealer.getCards().stream()
                .map(card -> card.getCardNumberType() + card.getCardPattern())
                .findFirst()
                .orElseThrow();
        System.out.println(dealerCard);
    }

    public static void printDealerReceiveCard() {
        System.out.println();
        System.out.println(DEALER_RECEIVE_CARD_MESSAGE);
    }

    public static void printTotalCardResult(Player dealer, List<Player> users) {
        System.out.println();
        System.out.println(makeUserCardsToString(dealer) + RESULT_MARK_MESSAGE + dealer.getTotalScore());
        for (Player user : users) {
            System.out.println(makeUserCardsToString(user) + RESULT_MARK_MESSAGE + user.getTotalScore());
        }
    }

    public static void printGameResult(GameResult gameResult) {
        System.out.println();
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerGameResult(gameResult.getDealerResult());
        printUsersGameResult(gameResult.getUserResult());
    }

    private static void printDealerGameResult(Map<Result, Integer> dealerResult) {
        System.out.print(DEALER_MARK_MESSAGE);
        for (Result result : Result.values()) {
            System.out.print(dealerResult.get(result) + result.getResult() + " ");
        }
        System.out.println();
    }

    private static void printUsersGameResult(Map<String, Result> userResult) {
        for (String userName : userResult.keySet()) {
            System.out.println(userName + COLON + userResult.get(userName).getResult());
        }
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
