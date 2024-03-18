package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.dto.ParticipantHandDto;
import blackjack.dto.ParticipantSettlementDto;
import blackjack.dto.ParticipantsHandDto;
import blackjack.dto.PlayersNameDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printInitialDealMessage(PlayersNameDto playersNameDto) {
        List<String> playerNames = playersNameDto.names();
        String joinedPlayerNames = String.join(", ", playerNames);
        String message = String.format("%s와 %s에게 2장을 나누었습니다.%n", Dealer.DEALER_NAME, joinedPlayerNames);
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

    public void printSettlement(ParticipantSettlementDto participantSettlementDto) {
        StringBuilder stringBuilder = new StringBuilder("\n##최종 수익\n");
        Map<String, Integer> settlements = participantSettlementDto.settlement();

        for (Map.Entry<String, Integer> settlement : settlements.entrySet()) {
            stringBuilder.append(String.format("%s: %d%n", settlement.getKey(), settlement.getValue()));
        }
        System.out.println(stringBuilder);
    }
}
