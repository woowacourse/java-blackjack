package blackjack.view;

import blackjack.card.Card;
import blackjack.user.dealer.Dealer;
import blackjack.user.player.Player;
import blackjack.user.player.PlayerName;
import blackjack.user.player.Players;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printEnterPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 영어/한글로 입력하세요. 최대 25명 참가 가능합니다.(쉼표 기준으로 분리)");
    }

    public void printBettingAmountQuestion(final PlayerName name) {
        System.out.printf("%s의 배팅 금액은?(1만원에서 1,000만원까지 배팅 가능합니다.)%n", name.getText());
    }

    public void printInitDistributionMessage(final List<PlayerName> playerNames) {
        System.out.println();
        List<String> names = playerNames.stream()
            .map(PlayerName::getText)
            .toList();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", String.join(", ", names));
    }

    public void printDealerInitialCardResult(final Dealer dealer) {
        String cardResult = parseCardToString(dealer.openInitialCards());
        System.out.printf("딜러카드: %s%n", cardResult);
    }

    public void printPlayersInitialCardResult(final Players players) {
        for (Player player : players.getJoinedPlayers()) {
            String cardResult = parseCardToString(player.openInitialCards());
            System.out.printf("%s카드: %s%n", player.getName().getText(), cardResult);
        }
    }

    public void printPlayerCardResult(final Player player) {
        String cardResult = parseCardToString(player.getCardHand().openCards());
        System.out.printf("%s카드: %s%n", player.getName().getText(), cardResult);
    }

    public void printAddExtraCardToPlayer(final PlayerName name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name.getText());
    }

    public void printAddExtraCardToDealer() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받습니다.");
    }

    public void printDealerFinalCardResult(final Dealer dealer) {
        System.out.println();
        String cardResult = parseCardToString(dealer.getCardHand().openCards());
        System.out.printf("딜러카드: %s - 결과: %d%n", cardResult,
            dealer.getCardHand().calculateDenominations());
    }

    public void printPlayersFinalCardResult(final Players players) {
        for (Player player : players.getJoinedPlayers()) {
            String cardResult = parseCardToString(player.getCardHand().openCards());
            System.out.printf("%s카드: %s - 결과 %d%n", player.getName().getText(), cardResult,
                player.getCardHand().calculateDenominations());
        }
    }

    public void printProfitResultTitle() {
        System.out.println();
        System.out.println("## 최종 수익");
    }

    public void printDealerProfitResult(final int dealerProfitResult) {
        System.out.printf("딜러: %,d%n", dealerProfitResult);
    }

    public void printPlayerProfitResult(final Players players) {
        players.getJoinedPlayers().forEach(player ->
            System.out.printf("%s: %,d%n",
                player.getName().getText(), player.getBetAmount().getProfit()));
    }

    private String parseCardToString(final List<Card> cards) {
        return cards
            .stream()
            .map(card -> card.denomination().getText() + card.suit().getText())
            .collect(Collectors.joining(", "));
    }
}
