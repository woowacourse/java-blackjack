package blackjack.view;

import blackjack.controller.dto.CardDto;
import blackjack.controller.dto.GameResultDto;
import blackjack.controller.dto.ParticipantResponseDto;
import blackjack.controller.dto.ParticipantsResponseDto;

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

    public static void printInitGame(final ParticipantsResponseDto participantsResponseDto) {
        System.out.print(NEW_LINE);
        System.out.printf(INIT_GAME_FORMAT, joinPlayerNames(participantsResponseDto.getPlayers()));
        System.out.print(NEW_LINE);
        OutputView.printDealerHand(participantsResponseDto.getDealer());
        OutputView.printPlayersHand(participantsResponseDto.getPlayers());
    }

    private static String joinPlayerNames(final List<ParticipantResponseDto> players) {
        return players.stream()
                .map(ParticipantResponseDto::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    public static void printHandResult(final ParticipantsResponseDto participantsResponseDto) {
        System.out.print(NEW_LINE);
        System.out.print(joinDealerResult(participantsResponseDto.getDealer()));
        System.out.println(joinPlayersResult(participantsResponseDto.getPlayers()));
    }

    private static String joinDealerResult(final ParticipantResponseDto dealer) {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러")
                .append(HAND_FORMAT)
                .append(joinCards(dealer.getHand()))
                .append(SCORE_FORMAT)
                .append(dealer.getTotalScore())
                .append(NEW_LINE);
        return sb.toString();
    }

    private static String joinPlayersResult(final List<ParticipantResponseDto> players) {
        StringBuilder sb = new StringBuilder();
        for (ParticipantResponseDto player : players) {
            sb.append(player.getName())
                    .append(HAND_FORMAT)
                    .append(joinCards(player.getHand()))
                    .append(SCORE_FORMAT)
                    .append(player.getTotalScore())
                    .append(NEW_LINE);
        }
        return sb.toString();
    }

    public static void printDealerHand(final ParticipantResponseDto dealer) {
        printDealerOneCard(dealer);
    }

    public static void printPlayersHand(final List<ParticipantResponseDto> players) {
        players.forEach(OutputView::printCards);
        System.out.print(NEW_LINE);
    }

    public static void printCards(final ParticipantResponseDto player) {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName())
                .append(HAND_FORMAT)
                .append(joinCards(player.getHand()))
                .append(NEW_LINE);
        System.out.print(sb);
    }

    public static void printDealerOneCard(final ParticipantResponseDto dealer) {
        StringBuilder sb = new StringBuilder();
        sb.append(dealer.getName())
                .append(HAND_FORMAT)
                .append(formatsCardName(dealer.getHand().get(0)))
                .append(NEW_LINE);
        System.out.print(sb);
    }

    private static String joinCards(final List<CardDto> cards) {
        List<String> cardStrings = new ArrayList<>();
        for (CardDto card : cards) {
            cardStrings.add(formatsCardName(card));
        }
        return String.join(DELIMITER, cardStrings);
    }

    private static String formatsCardName(CardDto cardDto) {
        return cardDto.getNumber() + cardDto.getPattern();
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
