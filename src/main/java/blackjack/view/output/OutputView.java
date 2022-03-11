package blackjack.view.output;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.result.MatchStatus;
import blackjack.dto.MatchResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.view.utils.Delimiter;

public class OutputView {

    private static final String MESSAGE_OF_REQUEST_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String MESSAGE_FORMAT_OF_DISTRIBUTE_TWO_CARDS = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String MESSAGE_FORMAT_OF_FINAL_SCORE = "%s: %s - 결과: %d";
    private static final String MESSAGE_FORMAT_OF_REQUEST_CONTINUABLE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String MESSAGE_OF_DEALER_DRAW_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String MESSAGE_OF_MATCH_RESULT_TITLE = "## 최종 승패";

    private void printMessage(final String message) {
        System.out.println(message);
    }

    public void printMessageOfRequestPlayerNames() {
        printMessage(MESSAGE_OF_REQUEST_PLAYER_NAMES);
    }

    public void printDealerFirstCard(final String dealerFirstCard) {
        printMessage(Delimiter.COLON.joinWith(List.of("딜러", dealerFirstCard)));
    }

    public void printMessageOfPlayerNames(final List<ParticipantDto> playerDtos) {
        final List<String> playerNames = playerDtos.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.toUnmodifiableList());
        final String combinedPlayerNames = Delimiter.COMMA.joinWith(playerNames);
        printMessage(String.format(MESSAGE_FORMAT_OF_DISTRIBUTE_TWO_CARDS, combinedPlayerNames));
    }

    public void printFinalScores(final List<ParticipantDto> participantDtos) {
        for (final ParticipantDto participantDto : participantDtos) {
            final String playerName = participantDto.getName();
            final String distributedCards = Delimiter.COMMA.joinWith(participantDto.getCardNames());
            final int score = participantDto.getScore();

            final String message = String.format(MESSAGE_FORMAT_OF_FINAL_SCORE, playerName, distributedCards, score);
            printMessage(message);
        }
    }

    public void printDistributedCards(final ParticipantDto participantDto) {
        final String playerName = participantDto.getName();
        final String distributedCards = Delimiter.COMMA.joinWith(participantDto.getCardNames());
        printMessage(Delimiter.COLON.joinWith(List.of(playerName, distributedCards)));
    }

    public void printMessageOfRequestContinuable(final String playerName) {
        printMessage(String.format(MESSAGE_FORMAT_OF_REQUEST_CONTINUABLE, playerName));
    }

    public void printMessageOfDealerDrawCard() {
        printMessage(MESSAGE_OF_DEALER_DRAW_CARD);
    }

    public void printMatchResult(final MatchResultDto resultDto) {
        printMessage(MESSAGE_OF_MATCH_RESULT_TITLE);
        printMatchResultOfDealer(resultDto.getResultOfDealer());
        printMatchResultOfPlayers(resultDto.getResultOfPlayers());
    }

    private void printMatchResultOfDealer(final Map<MatchStatus, Integer> resultOfDealer) {
        final String dealerResultString = resultOfDealer.entrySet().stream()
                .map(entry -> entry.getValue() + entry.getKey().getName())
                .collect(Collectors.joining(" "));

        printMessage(Delimiter.COLON.joinWith(List.of("딜러", dealerResultString)));
    }

    private void printMatchResultOfPlayers(Map<String, MatchStatus> resultOfPlayers) {
        for (Map.Entry<String, MatchStatus> entry : resultOfPlayers.entrySet()) {
            final String playerName = entry.getKey();
            final String matchStatusName = entry.getValue().getName();
            printMessage(Delimiter.COLON.joinWith(List.of(playerName, matchStatusName)));
        }
    }

}
