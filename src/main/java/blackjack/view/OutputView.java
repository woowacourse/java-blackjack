package blackjack.view;

import blackjack.domain.GameResultDto;
import blackjack.domain.carddeck.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String INIT_GAME_FORMAT = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String HAND_FORMAT = " 카드: ";
    private static final int DEALER_HIT_LIMIT = 16;
    private static final int BUST_SCORE = 21;
    private static final String SCORE_FORMAT = " - 결과: ";

    public static void printInitGame(final List<Player> players, Dealer dealer) {
        System.out.print(NEW_LINE);
        System.out.printf(INIT_GAME_FORMAT, joinPlayerNames(players));
        System.out.print(NEW_LINE);
        OutputView.printDealerHand(dealer);
        OutputView.printPlayersHand(players);
    }

    private static String joinPlayerNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    public static void printHandResult(List<Player> players, Dealer dealer) {
        System.out.print(NEW_LINE);
        System.out.print(joinDealerResult(dealer));
        System.out.println(joinPlayersResult(players));
    }

    private static String joinDealerResult(Dealer dealer) {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러")
                .append(HAND_FORMAT)
                .append(joinCards(dealer.toHandList()))
                .append(SCORE_FORMAT)
                .append(dealer.getTotalScore().getValue())
                .append(NEW_LINE);
        return sb.toString();
    }

    private static String joinPlayersResult(List<Player> players) {
        StringBuilder sb = new StringBuilder();
        for (Player player : players) {
            sb.append(player.getName())
                    .append(HAND_FORMAT)
                    .append(joinCards(player.toHandList()))
                    .append(SCORE_FORMAT)
                    .append(player.getTotalScore().getValue())
                    .append(NEW_LINE);
        }
        return sb.toString();
    }

    public static void printDealerHand(final Dealer dealer) {
        printCards(dealer);
    }

    public static void printPlayersHand(final List<Player> players) {
        players.forEach(OutputView::printCards);
        System.out.print(NEW_LINE);
    }

    public static void printCards(final Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName())
                .append(HAND_FORMAT)
                .append(joinCards(player.toHandList()))
                .append(NEW_LINE);
        System.out.print(sb);
    }

    public static void printCards(final Dealer dealer) {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러")
                .append(HAND_FORMAT)
                .append(formatsCardName(dealer.toHandList().get(0)))
                .append(NEW_LINE);
        System.out.print(sb);
    }

    private static String joinCards(final List<Card> cards) {
        List<String> cardStrings = new ArrayList<>();
        for (Card card : cards) {
            cardStrings.add(formatsCardName(card));
        }
        return String.join(DELIMITER, cardStrings);
    }

    private static String formatsCardName(Card card) {
        return card.getNumberName() + card.getPatternName();
    }

    public static void printDealerHit() {
        System.out.print(NEW_LINE);
        System.out.printf("딜러는 %d이하라 한장의 카드를 더 받았습니다.", DEALER_HIT_LIMIT);
        System.out.print(NEW_LINE);
    }

    public static void printGameResult(List<GameResultDto> gameResultDtos) {
        System.out.println("## 최종 수익");
        gameResultDtos.forEach(gameResultDto -> {
            System.out.printf("%s: %.1f", gameResultDto.getName(), gameResultDto.getEarning());
            System.out.print(NEW_LINE);
        });
    }

    public static void printPlayerBurst(final String playerName) {
        System.out.printf("%s의 점수 총합이 " + BUST_SCORE + "점을 넘어 버스트 되었습니다.", playerName);
        System.out.print(NEW_LINE);
    }

    public static void printPlayerBlackjack(final String playerName) {
        System.out.printf("%s 가(이) Blackjack 입니다!!", playerName);
        System.out.print(NEW_LINE);
    }

    public static void printException(final Throwable e) {
        System.out.println(e.getMessage());
    }
}
