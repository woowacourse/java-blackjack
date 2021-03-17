package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.user.PlayerDto;
import blackjack.domain.user.ResultDTO;
import blackjack.domain.user.User;
import blackjack.domain.user.WinningResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NAME_DELIMITER = ", ";
    public static final int DEALER_OPEN_CARD_INDEX = 0;

    public static void printInitialCards(User dealer, List<User> players) {
        printIntroMessage(dealer, players);
        printUserCard(dealer, players);
        printLine();
    }

    private static void printIntroMessage(User dealer, List<User> players) {
        String playerNames = players.stream()
            .map(User::getName)
            .collect(Collectors.joining(NAME_DELIMITER));
        System.out.printf("%n%s와 %s에게 2장의 나누었습니다.%n", dealer.getName(), playerNames);
    }

    public static void printUserCard(User dealer, List<User> players) {
        printDealerCard(dealer);
        players.forEach(OutputView::printPlayerCard);
    }

    private static void printDealerCard(User dealer) {
        Card dealerOpenCard = dealer.getCards().get(DEALER_OPEN_CARD_INDEX);
        String dealerCards =
            dealerOpenCard.getDenomination().getName() + dealerOpenCard.getSuit().getName();
        System.out.printf("%s: %s%n", dealer.getName(), dealerCards);
    }

    public static void printPlayerCard(User player) {
        String cardString = makeCardString(player.getCards());

        System.out.printf("%s의 카드: %s%n", player.getName(), cardString);
    }

    public static void printPlayerCardV2(PlayerDto playerDto) {
        String cardString = makeCardString(playerDto.getCards());

        System.out.printf("%s의 카드: %s%n", playerDto.getName(), cardString);
    }

    private static String makeCardString(List<Card> cards) {
        return cards.stream()
            .map(card -> String
                .format("%s%s", card.getDenomination().getName(), card.getSuit().getName()))
            .collect(Collectors.joining(NAME_DELIMITER));
    }

    public static void printDealerDraw(boolean hasDrawn) {
        if (hasDrawn) {
            System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
            return;
        }

        System.out.println("\n딜러는 16초과로 카드를 받지 않았습니다.\n");
    }

    public static void printLine() {
        System.out.println();
    }

    public static void printUserResult(List<ResultDTO> resultDTOS) {
        resultDTOS.forEach(resultDTO -> System.out.printf("%s 카드 : %s - 결과: %d%n",
            resultDTO.getName(),
            makeCardString(resultDTO.getCards()),
            resultDTO.getScore()));
    }

    public static void printWinningResult(List<WinningResult> winningResults) {
        System.out.println("\n## 최종 승패");
        System.out.println("딜러 : " + calculateDealerProfit(winningResults));
        winningResults.forEach(winningResult ->
            System.out.printf("%s: %s%n", winningResult.getName(),
               winningResult.getProfit()));
    }

    private static int calculateDealerProfit(List<WinningResult> winningResults) {
        return calculatePlayerTotalProfit(winningResults) * -1;
    }

    private static int calculatePlayerTotalProfit(
        List<WinningResult> winningResults) {
        return winningResults.stream()
            .mapToInt(WinningResult::getProfit)
            .sum();
    }
}
