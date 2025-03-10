package view;

import java.util.List;
import java.util.Map;

public class OutputView {

  private static final String PLAYER_NAME_DELIMITER = ", ";

  public void printDealIntroduce(final List<String> playerNames) {
    final String names = String.join(PLAYER_NAME_DELIMITER, playerNames);
    System.out.printf("딜러와 %s에게 2장을 나누었습니다." + System.lineSeparator(), names);
  }

  public void printDealerHitResult(final String card) {
    System.out.printf("딜러카드: %s" + System.lineSeparator(), card);
  }

  public void printPlayersHand(final List<Map.Entry<String, List<String>>> players) {
    players.forEach(player -> printPlayerHand(player.getKey(), player.getValue()));
  }

  public void printPlayerHand(final String name, final List<String> card) {
    final String cards = String.join(PLAYER_NAME_DELIMITER, card);
    System.out.printf("%s카드: %s" + System.lineSeparator(), name, cards);
  }

  public void printDealerHit() {
    System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
  }

  public void printPlayerRoundResult(final String name, final List<String> cards, final int score) {
    final String joinedCards = String.join(PLAYER_NAME_DELIMITER, cards);
    System.out.printf("%s카드: %s - 결과: %d" + System.lineSeparator(), name, joinedCards, score);
  }

  public void printRoundResultIntroduce() {
    System.out.println("## 최종 승패");
  }

  public void printRoundResultOnDealer(final Map<Boolean, Integer> result) {
    System.out.printf("딜러: %d승, %d패" + System.lineSeparator(), result.get(true), result.get(false));
  }


  public void printRoundResultOnPlayers(final String name, final String result) {
    System.out.printf("%s: %s" + System.lineSeparator(), name, result);
  }
}
