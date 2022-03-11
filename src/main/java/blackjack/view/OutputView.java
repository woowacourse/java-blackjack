package blackjack.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import blackjack.domain.result.WinningResult;
import blackjack.dto.MatchResultDto;
import blackjack.dto.ParticipantDto;

public class OutputView {

    private void printMessage(final String message) {
        System.out.println(message);
    }

    public void printMessageOfRequestPlayerNames() {
        printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printMessageOfPlayerStatuses(ParticipantDto dealerDto, List<ParticipantDto> playerDtos) {
        printMessageOfPlayerNames(playerDtos);
        printListOfDistributedCards(dealerDto, playerDtos);
    }

    private void printMessageOfPlayerNames(final List<ParticipantDto> playerDtos) {
        final String combinedPlayerNames = playerDtos.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.joining(", "));
        printMessage("딜러와 " + combinedPlayerNames + " 에게 2장의 카드를 나누었습니다.");
    }


    private void printListOfDistributedCards(final ParticipantDto dealerDto, final List<ParticipantDto> playerDtos) {
        printDistributedCards(dealerDto);
        for (final ParticipantDto participantDto : playerDtos) {
            printDistributedCards(participantDto);
        }
    }

    public void printScores(final List<ParticipantDto> participantDtos) {
        for (final ParticipantDto participantDto : participantDtos) {
            final String playerName = participantDto.getName();
            final String cardDetails = makeStringOfDistributedCards(participantDto);
            final int score = participantDto.getScore();
            printMessage(playerName + ": " + cardDetails + " - 결과: " + score);
        }
    }

    public void printDistributedCards(final ParticipantDto participantDto) {
        final String playerName = participantDto.getName();
        final String cardDetails = makeStringOfDistributedCards(participantDto);
        printMessage(playerName + ": " + cardDetails);
    }

    public void printMessageOfRequestContinuable(final String playerName) {
        printMessage(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printMessageOfDealerDrawCard() {
        printMessage("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void printMatchResult(final MatchResultDto resultDto) {
        printMessage("## 최종 승패");

        final Map<WinningResult, Integer> dealerResult = resultDto.getDealerResult();

        final String dealerResultString = dealerResult.entrySet().stream()
                .map(entry -> entry.getValue() + entry.getKey().getName())
                .collect(Collectors.joining(" "));
        printMessage("딜러: " + dealerResultString);

        final Map<String, WinningResult> playerResult = resultDto.getPlayerResult();
        for (Map.Entry<String, WinningResult> entry : playerResult.entrySet()) {
            printMessage(entry.getKey() + ": " + entry.getValue().getName());
        }
    }

    private String makeStringOfDistributedCards(final ParticipantDto participantDto) {
        return String.join(", ", participantDto.getCardNames());
    }
}
