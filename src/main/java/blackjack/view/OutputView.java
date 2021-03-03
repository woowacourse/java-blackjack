package blackjack.view;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import blackjack.controller.PlayerResultDto;
import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutputView {

  private static final String DRAW_RESULT_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다.";
  private static final String DECK_STATE_FORMAT = "%s 카드: %s" + System.lineSeparator();
  private static final String DEALER_DRAWABLE_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
  private static final String PLAYER_RESULT_FORMAT =
      "%s 카드 : %s - 결과 : %d" + System.lineSeparator();
  private static final String DELIMITER = ",";
  private static final String FINAL_RESULT = "##최종승패";
  private static final String DEALER_FINAL_RESULT = "딜러: %d승 %d패";
  private static final String PLAYER_FINAL_RESULT = "%s: %s" + System.lineSeparator();
  private static final String WIN = "승";
  private static final String LOSE = "패";

  public static void printDrawResult(List<Player> players) {
    System.out.printf(DRAW_RESULT_FORMAT,
        players.stream()
            .map(Player::getName)
            .collect(joining(DELIMITER)));
    System.out.println();
  }


  public static void printDealerAndPlayersDeckState(Dealer dealer, List<Player> gamersDeck) {
    System.out.printf(DECK_STATE_FORMAT, dealer.getName(), dealer.getDeck().get(0).toString());

    gamersDeck.forEach(OutputView::printPlayersDeckState);
  }

  public static void printPlayersDeckState(Player player) {
        System.out.printf(DECK_STATE_FORMAT, player.getName(), player.getDeck().stream()
                .map(Card::toString).collect(joining(DELIMITER))
        );
  }

  public static void dealerDrawsCard() {
    System.out.println(DEALER_DRAWABLE_MESSAGE);
  }

  public static void printCurrentDeckAndScore(List<PlayerResultDto> resultDtos) {
    for (PlayerResultDto resultDto : resultDtos) {
      String cards = resultDto.getCardList()
          .stream()
          .map(Card::toString)
          .collect(joining(DELIMITER));
      System.out.printf(PLAYER_RESULT_FORMAT, resultDto.getName(), cards, resultDto.getScore());
    }
  }

  public static void printResult(GameResult gameResult) {
    System.out.println(FINAL_RESULT);
    System.out.println(dealerResultToString(gameResult.getDealerResult()));

    gameResult.getGamerResult().forEach(
        (name, result) -> System.out.printf(PLAYER_FINAL_RESULT, name, booleanToWinOrLose(result)));
  }

  private static String dealerResultToString(List<Boolean> dealerResult) {
    Map<Boolean, Long> map = dealerResult.stream()
        .collect(groupingBy(Function.identity(), Collectors.counting()));

    return String.format(
        DEALER_FINAL_RESULT,
        map.getOrDefault(true, 0L),
        map.getOrDefault(false, 0L)
    );
  }


  private static String booleanToWinOrLose(boolean b) {
    if (b) {
      return WIN;
    }

    return LOSE;
  }


  public static void printMessage(String message) {
    System.out.println(message);
  }
}
