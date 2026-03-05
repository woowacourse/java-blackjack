package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameResults;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialDeal(final Players players, final Dealer dealer) {
        String playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", playerNames);
        System.out.printf("딜러카드: %s%n", dealer.getCards().get(0).getDisplayName());
        players.getPlayers().forEach(this::printPlayerCards);
    }

    public void printPlayerCards(final Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), formatCards(player.getCards()));
    }

    public void printDealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCards(final Players players, final Dealer dealer) {
        System.out.println();
        printParticipantFinalCards(dealer.getName(), dealer.getCards(), dealer.calculateScore());
        players.getPlayers().forEach(player ->
                printParticipantFinalCards(player.getName(), player.getCards(), player.calculateScore())
        );
    }

    public void printFinalResults(final GameResults gameResults) {
        System.out.println("\n## 최종 승패");
        printDealerResult(gameResults.getDealerResults());
        gameResults.getPlayerResults().forEach(this::printPlayerResult);
    }

    private void printParticipantFinalCards(final String name, final List<Card> cards, final int score) {
        System.out.printf("%s카드: %s - 결과: %d%n", name, formatCards(cards), score);
    }

    private void printDealerResult(final Map<GameResult, Integer> dealerResults) {
        StringBuilder sb = new StringBuilder("딜러: ");
        if (dealerResults.containsKey(GameResult.WIN)) {
            sb.append(dealerResults.get(GameResult.WIN)).append("승 ");
        }
        if (dealerResults.containsKey(GameResult.DRAW)) {
            sb.append(dealerResults.get(GameResult.DRAW)).append("무 ");
        }
        if (dealerResults.containsKey(GameResult.LOSE)) {
            sb.append(dealerResults.get(GameResult.LOSE)).append("패");
        }
        System.out.println(sb.toString().trim());
    }

    private void printPlayerResult(final Player player, final GameResult result) {
        String resultText = switch (result) {
            case WIN -> "승";
            case DRAW -> "무";
            case LOSE -> "패";
        };
        System.out.printf("%s: %s%n", player.getName(), resultText);
    }

    private String formatCards(final List<Card> cards) {
        return cards.stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
    }
}
