package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public void printStartGame(List<String> playerNames) {
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", String.join(", ", playerNames));
    }

    public void printPlayerCardResult(Player player) {
        String cardResult = parseCardToString(player);
        System.out.printf("%s카드: %s%n", player.getName(), cardResult);
    }

    public void printDealerCardResult(Dealer dealer) {
        Card firstCard = dealer.openFirstCard();
        String cardResult = firstCard.denomination().getText() + firstCard.suit().getText();
        System.out.printf("딜러카드: %s%n", cardResult);
    }

    public void printAddExtraCardToDealer() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받습니다.");
    }

    public void printDealerFinalCardResult(Dealer dealer) {
        String cardResult = parseCardToString(dealer);
        System.out.println();
        System.out.printf("딜러카드: %s - 결과: %d%n", cardResult, dealer.calculateDenominations());
    }

    public void printPlayerFinalCardResult(Player player) {
        String cardResult = parseCardToString(player);
        System.out.printf("%s카드: %s - 결과 %d%n", player.getName(), cardResult, player.calculateDenominations());
    }

    public void printResultTitle() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public void printDealerResult(Map<GameResult, Integer> gameResultIntegerMap) {
        StringBuilder sb = new StringBuilder();
        for (Entry<GameResult, Integer> gameResult : gameResultIntegerMap.entrySet()) {
            sb.append(gameResult.getValue()).append(gameResult.getKey().getText());
        }

        System.out.printf("딜러: %s%n", sb.toString());
    }

    public void printPlayerResult(Player player, Dealer dealer) {
        System.out.printf("%s: %s%n", player.getName(), player.matchGame(dealer).getText());
    }

    private String parseCardToString(Participant participant) {
        return participant.getCards()
                .stream()
                .map(card -> card.denomination().getText() + card.suit().getText())
                .collect(Collectors.joining(", "));
    }
}
