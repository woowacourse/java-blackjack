package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.game.ParticipantResult;
import blackjack.model.game.ReceivedCards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import blackjack.model.player.Player;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void outputFirstCardDistributionResult(Participants participants, Dealer dealer) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine(String.format("딜러와 %s에게 2장을 나누었습니다.",
                participants.stream()
                        .map(Participant::getName)
                        .collect(Collectors.joining(", "))
        ));
        customStringBuilder.appendLine(outputPlayerCardStatus(
                "딜러",
                generateCardName(dealer.getInitialCard())
        ));
        participants.stream().forEach(participant -> customStringBuilder.appendLine(outputPlayerCardStatus(
                participant.getName(),
                generateCardNames(participant.getReceivedCards())
        )));
        customStringBuilder.print();
    }

    public void printPlayerCardStatus(String name, Player player) {
        System.out.println(outputPlayerCardStatus(name, generateCardNames(player.getReceivedCards())));
    }

    public void outputDealerGetCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerCardDone() {
        System.out.println("딜러는 17이상이라 더이상 카드를 받을 수 없습니다.");
    }

    public void printFinalCardStatus(Dealer dealer, Participants participants) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine(String.format("%s - 결과 : %d",
                outputPlayerCardStatus("딜러", generateCardNames(dealer.getReceivedCards())),
                dealer.calculatePoint()
        ));
        participants.forEach(participant -> customStringBuilder.appendLine(String.format("%s - 결과 : %d",
                outputPlayerCardStatus(participant.getName(), generateCardNames(participant.getReceivedCards())),
                participant.calculatePoint()))
        );
        customStringBuilder.print();
    }

    public void printFinalResult(Dealer dealer, Participants participants) {
        Map<ParticipantResult, Integer> winLoseResult = new EnumMap<>(ParticipantResult.class);
        Arrays.stream(ParticipantResult.values())
                .forEach(participantResult -> winLoseResult.put(participantResult, 0));
        CustomStringBuilder playerContents = new CustomStringBuilder();
        CustomStringBuilder dealerContents = new CustomStringBuilder();
        dealerContents.appendLine("## 최종 승패");
        participants.stream().forEach(participant -> {
            ParticipantResult participantResult = ParticipantResult.of(dealer, participant);
            playerContents.appendLine(String.format("%s: %s", participant.getName(), participantResult.getDetail()));
            winLoseResult.merge(participantResult, 1, Integer::sum);
        });
        dealerContents.appendLine(String.format("딜러: %d승 %d패",
                winLoseResult.get(ParticipantResult.LOSE),
                winLoseResult.get(ParticipantResult.WIN))
        );
        dealerContents.print();
        playerContents.print();
    }


    public void printParticipantBust(String name) {
        System.out.println(String.format("%s는 bust입니다.", name));
    }

    private String generateCardNames(ReceivedCards receivedCards) {
        return receivedCards.stream()
                .map(this::generateCardName)
                .collect(Collectors.joining(", "));
    }

    private String generateCardName(Card card) {
        return String.format("%s%s", card.getCardType().getDetail(), card.getShape().getDetail());
    }

    private String outputPlayerCardStatus(String name, String cards) {
        return String.format("%s카드: %s", name, cards);
    }
}
