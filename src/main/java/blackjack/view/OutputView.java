package blackjack.view;

import blackjack.domain.WinningResult;
import blackjack.domain.card.Card;
import blackjack.dto.ParticipantDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private void printMessage(final String message) {
        System.out.println(message);
    }

    public void printMessageOfRequestPlayerNames() {
        printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printMessageOfPlayerStatuses(List<ParticipantDto> participantDtos) {
        printMessageOfPlayerNames(participantDtos);
        printListOfDistributedCards(participantDtos);
    }

    private void printMessageOfPlayerNames(final List<ParticipantDto> participantDtos) {
        final String combinedPlayerNames = participantDtos.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.joining(", "));
        printMessage("딜러와" + combinedPlayerNames + "에게 2장의 카드를 나누었습니다.");
    }


    private void printListOfDistributedCards(final List<ParticipantDto> participantDtos) {
        for (ParticipantDto participantDto : participantDtos) {
            printDistributedCards(participantDto);
        }
    }

    private void printScores(final List<ParticipantDto> participantDtos) {
        for (ParticipantDto participantDto : participantDtos) {
            final String playerName = participantDto.getName();
            final String cardDetails = makeStringOfDistributedCards(participantDto);
            final int score = participantDto.getScore();
            System.out.println(playerName + ": " + cardDetails + " - 결과: " + score);

        }
    }

    public void printDistributedCards(ParticipantDto participantDto) {
        final String playerName = participantDto.getName();
        final String cardDetails = makeStringOfDistributedCards(participantDto);
        System.out.println(playerName + ": " + cardDetails);
    }

    public void printMessageOfRequestContinuable(final String playerName) {
        printMessage(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printMessageOfDealerDrawCard() {
        printMessage("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printWinningResults(final Map<String, WinningResult> winningResults) {
        printMessage("## 최종 승패");

        // TODO 딜러 승패 출력
        // TODO WinningResult 도메인 만들기

        for (Map.Entry<String, WinningResult> entry : winningResults.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName());
        }
    }

    private String makeStringOfDistributedCards(final ParticipantDto participantDto) {
        final List<String> playerCardDetails = new ArrayList<>();
        for (Card card : participantDto.getCards()) {
            playerCardDetails.add(card.getNumber() + card.getPattern());
        }
        return String.join(", ", playerCardDetails);
    }
}
