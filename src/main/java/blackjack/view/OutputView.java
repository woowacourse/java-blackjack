package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.game.ReceivedCards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import blackjack.model.player.Player;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String NAME_DELIMITER = ", ";
    private static final String LINE = System.lineSeparator();

    public void outputFirstCardDistributionResult(final Participants participants, final Dealer dealer) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine(makeFirstDistributionPlayersText(participants));
        customStringBuilder.appendLine(makeDealerCardStatusText(generateCardName(dealer.getReceivedCards().getFirstCard())));
        for (Participant participant : participants.getParticipants()) {
            customStringBuilder.appendLine(makeParticipantCardStatusText(participant.getName(), generateCardNames(participant.getReceivedCards())));
        }
        customStringBuilder.print();
    }

    private static String makeFirstDistributionPlayersText(final Participants participants) {
        return String.format(
                "%n딜러와 %s에게 2장을 나누었습니다.",
                participants.getParticipants()
                        .stream()
                        .map(Participant::getName)
                        .collect(Collectors.joining(NAME_DELIMITER))
        );
    }

    public String generateCardNames(final ReceivedCards receivedCards) {
        return receivedCards.getCards()
                .stream()
                .map(this::generateCardName)
                .collect(Collectors.joining(", "));
    }

    public String generateCardName(final Card card) {
        return String.format("%s%s", card.getCardType().getSymbol(), card.getShape().getName());
    }

    public void outputPlayerCardStatus(final Player player) {
        if (player instanceof Dealer) {
            System.out.println(makeDealerCardStatusText(generateCardNames(player.getReceivedCards())));
            return;
        }
        Participant participant = (Participant) player;
        System.out.println(makeParticipantCardStatusText(participant.getName(), generateCardNames(participant.getReceivedCards())));
    }

    public String makeDealerCardStatusText(final String cards) {
        return this.makeParticipantCardStatusText("딜러", cards);
    }

    public String makeParticipantCardStatusText(final String name, final String cards) {
        return String.format("%s 카드: %s", name, cards);
    }

    public void outputFinalCardStatus(final Dealer dealer, final Participants participants) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine(String.format("%s - 결과 : %d",
                makeDealerCardStatusText(generateCardNames(dealer.getReceivedCards())), dealer.calculatePoint()));
        for (Participant participant : participants.getParticipants()) {
            customStringBuilder.appendLine(String.format("%s - 결과 : %d",
                    makeParticipantCardStatusText(participant.getName(), generateCardNames(participant.getReceivedCards())),
                    participant.calculatePoint())
            );
        }
        customStringBuilder.print();
    }

    public void outputDealerGetCard() {
        System.out.println(LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void outputDealerCardFinish() {
        System.out.println(LINE + "딜러는 17이상이라 더이상 카드를 받을 수 없습니다." + LINE);
    }

    public void outputFinalWinningMoney(final Participants participants, final int dealerMoney, final Map<Participant, Integer> winningMoney) {
        System.out.println("## 최종 수익");
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine(String.format("딜러: %d", dealerMoney));
        for (Participant participant : participants.getParticipants()) {
            customStringBuilder.appendLine(String.format("%s: %s", participant.getName(), winningMoney.get(participant)));
        }
        customStringBuilder.print();
    }


    public void outputParticipantBust(final String name) {
        System.out.println(String.format("%s는 bust입니다.", name));
    }
}
