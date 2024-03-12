package view;

import domain.Dealer;
import dto.*;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printInitialDealMessage(PlayersNameDto playersNameDto) {
        List<String> playerNames = playersNameDto.names();
        String joinedPlayerNames = String.join(", ", playerNames);
        String message = String.format("%s와 %s에게 2장을 나누었습니다.", Dealer.DEALER_NAME, joinedPlayerNames);
        System.out.println(message);
    }

    public void printInitialHand(ParticipantsHandDto participantsHandDto) {
        List<ParticipantHandDto> participantHandDtos = participantsHandDto.playerHands();
        StringBuilder stringBuilder = new StringBuilder();

        for (ParticipantHandDto participantHandDto : participantHandDtos) {
            stringBuilder.append(buildInitialHand(participantHandDto));
        }

        System.out.println(stringBuilder);
    }

    private String buildInitialHand(ParticipantHandDto participantHandDto) {
        if (participantHandDto.name().equals(Dealer.DEALER_NAME)) {
            return participantHandDto.name() + ": " + participantHandDto.hands().get(0) + "\n";
        }

        return buildPlayerHands(participantHandDto) + "\n";
    }

    private String buildPlayerHands(ParticipantHandDto participantHandDto) {
        String hands = String.join(", ", participantHandDto.hands());
        return String.format("%s : %s", participantHandDto.name(), hands);
    }

    public void printHandAfterHit(ParticipantHandDto participantHandDto) {
        System.out.println(buildPlayerHands(participantHandDto));
    }

    public void printDealerHitMessage() {
        String message = String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", Dealer.DEALER_NAME);
        System.out.println(message);
    }

    public void printFinalHandAndScore(ParticipantsHandDto participantsHandDto) {
        System.out.println();
        List<ParticipantHandDto> participantHandDtos = participantsHandDto.playerHands();
        for (ParticipantHandDto participantHandDto : participantHandDtos) {
            System.out.println(buildPlayerHandsWithResult(participantHandDto));
        }
    }

    private String buildPlayerHandsWithResult(ParticipantHandDto participantHandDto) {
        return String.format("%s - 결과: %s",
                buildPlayerHands(participantHandDto), participantHandDto.score());
    }

    public void printWinLoss(DealerWinLossDto dealerWinLossDto, PlayersWinLossDto playersWinLossDto) {
        System.out.println("\n## 최종 승패");
        printDealerWinLoss(dealerWinLossDto);
        printPlayerWinLoss(playersWinLossDto);
    }

    private void printDealerWinLoss(DealerWinLossDto dealerWinLossDto) {
        String dealerWinLoss = String.format("%s: %d승 %d패 ",
                Dealer.DEALER_NAME, dealerWinLossDto.winCount(), dealerWinLossDto.lossCount());

        StringBuilder stringBuilder = new StringBuilder(dealerWinLoss);
        if (dealerWinLossDto.pushCount() != 0) {
            String pushCount = String.format("%d무", dealerWinLossDto.pushCount());
            stringBuilder.append(pushCount);
        }
        System.out.println(stringBuilder);
    }

    private void printPlayerWinLoss(PlayersWinLossDto playersWinLossDto) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> playerResults = playersWinLossDto.playerResults();
        for (Map.Entry<String, String> playerResult : playerResults.entrySet()) {
            stringBuilder.append(String.format("%s: %s%n", playerResult.getKey(), playerResult.getValue()));
        }
        System.out.println(stringBuilder);
    }
}
