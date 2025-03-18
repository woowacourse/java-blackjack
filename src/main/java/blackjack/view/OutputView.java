package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.game.ParticipantResult;
import blackjack.model.game.ReceivedCards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import blackjack.model.player.Player;
import java.util.List;
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
                generateCardNames(dealer.getInitialCards())
        ));
        participants.stream().forEach(participant -> customStringBuilder.appendLine(outputPlayerCardStatus(
                participant.getName(),
                generateCardNames(participant.getInitialCards())
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
        CustomStringBuilder stringBuilder = new CustomStringBuilder();
        stringBuilder.appendLine("## 최종 수익");
        stringBuilder.appendLine(String.format("딜러: %d", dealer.calculateProfitAmount(participants)));
        participants.stream().forEach(participant -> {
            ParticipantResult participantResult = participant.duelWith(dealer);
            stringBuilder.appendLine(String.format("%s: %s", participant.getName(), participant.calculateProfitAmount(participantResult)));
        });
        stringBuilder.print();
    }


    public void printParticipantBust(String name) {
        System.out.println(String.format("%s는 bust입니다.", name));
    }

    private String generateCardNames(ReceivedCards receivedCards) {
        return receivedCards.stream()
                .map(this::generateCardName)
                .collect(Collectors.joining(", "));
    }

    private String generateCardNames(List<Card> cards) {
        return cards.stream()
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
