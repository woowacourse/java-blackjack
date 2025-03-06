package blackjack.view;

import blackjack.model.Card;
import blackjack.model.Dealer;
import blackjack.model.Participant;
import blackjack.model.ParticipantResult;
import blackjack.model.ReceivedCards;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void outputFirstCardDistributionResult(List<Participant> participants, Dealer dealer) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine(String.format(
                "딜러와 %s에게 2장을 나누었습니다.",
                participants.stream()
                        .map(Participant::getName)
                        .collect(Collectors.joining(", "))
        ));
        customStringBuilder.appendLine(outputPlayerCardStatus(
                "딜러",
                generateCardName(dealer.getReceivedCards().getFirstCard()))
        );
        participants.forEach(participant -> customStringBuilder.appendLine(outputPlayerCardStatus(
                participant.getName(),
                generateCardNames(participant.getReceivedCards())
        )));
        customStringBuilder.print();
    }

    public String generateCardNames(ReceivedCards receivedCards) {
        return receivedCards.stream()
                .map(this::generateCardName)
                .collect(Collectors.joining(", "));
    }

    public String generateCardName(Card card) {
        return String.format("%s%s", card.getCardType().name(), card.getShape().name());
    }

    public String outputPlayerCardStatus(String name, String cards) {
        return String.format("%s카드: %s", name, cards);
    }

    public void outputFinalCardStatus(Dealer dealer, List<Participant> participants) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine(String.format("%s - 결과 : %d",
                outputPlayerCardStatus("딜러", generateCardNames(dealer.getReceivedCards())), dealer.calculatePoint()));
        for (Participant participant : participants) {
            customStringBuilder.appendLine(String.format("%s - 결과 : %d",
                    outputPlayerCardStatus(participant.getName(), generateCardNames(participant.getReceivedCards())),
                    participant.calculatePoint()));
        }
        customStringBuilder.print();
    }

    public void outputDealerGetCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void outputDealerCardFinish() {
        System.out.println("딜러는 17이상이라 더이상 카드를 받을 수 없습니다.");
    }

    public void outputFinalResult(Dealer dealer, List<Participant> participants) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        System.out.println("## 최종 승패");

        Map<ParticipantResult, Integer> winLoseResult = new HashMap<>(
                Map.of(ParticipantResult.WIN, 0, ParticipantResult.LOSE, 0));
        for (Participant participant : participants) {
            ParticipantResult participantResult = ParticipantResult.of(dealer, participant);
            customStringBuilder.appendLine(String.format("%s: %s", participant.getName(), participantResult.name()));
            winLoseResult.merge(participantResult, 1, Integer::sum);
        }
        System.out.println(String.format("딜러: %d승 %d패", winLoseResult.get(ParticipantResult.LOSE),
                winLoseResult.get(ParticipantResult.WIN)));
        customStringBuilder.print();
    }


}
