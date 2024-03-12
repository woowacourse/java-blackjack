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
        if (participantHandDto.name().equals("딜러")) {
            return participantHandDto.name() + ": " + participantHandDto.hands().get(0) + "\n";
        }

        return buildPlayerCards(participantHandDto) + "\n";
    }

    public void printHandAfterHit(ParticipantHandDto participantHandDto) {
        System.out.println(buildPlayerCards(participantHandDto));
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalHandAndScore(ParticipantsHandDto participantsHandDto) {
        System.out.println();
        List<ParticipantHandDto> participantHandDtos = participantsHandDto.playerHands();

        for (ParticipantHandDto participantHandDto : participantHandDtos) {
            System.out.println(buildPlayerCardsWithResult(participantHandDto));
        }
    }

    private String buildPlayerCardsWithResult(ParticipantHandDto participantHandDto) {
        return buildPlayerCards(participantHandDto) + " - 결과: " + participantHandDto.score();
    }

    private String buildPlayerCards(ParticipantHandDto participantHandDto) {
        String hands = String.join(", ", participantHandDto.hands());
        return participantHandDto.name() + ": " + hands;
    }

    public void printWinLoss(DealerWinLossDto dealerWinLossDto, PlayersWinLossDto playersWinLossDto) {
        System.out.println("\n## 최종 승패");
        printDealerWinLoss(dealerWinLossDto);
        printPlayerWinLoss(playersWinLossDto);
    }

    private void printDealerWinLoss(DealerWinLossDto dealerWinLossDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러: ");
        stringBuilder.append(dealerWinLossDto.winCount() + "승 ");
        stringBuilder.append(dealerWinLossDto.lossCount() + "패 ");
        if (dealerWinLossDto.pushCount() != 0) {
            stringBuilder.append(dealerWinLossDto.winCount() + "무");
        }
        System.out.println(stringBuilder);
    }

    private void printPlayerWinLoss(PlayersWinLossDto playersWinLossDto) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> playerResult : playersWinLossDto.playerResults().entrySet()) {
            stringBuilder.append(playerResult.getKey() + ": " + playerResult.getValue() + "\n");
        }
        System.out.println(stringBuilder);
    }
}
