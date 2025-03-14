package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.game.PlayerResult;
import blackjack.model.game.ReceivedCards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void outputFirstCardDistributionResult(Players players, Dealer dealer) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine(String.format(
                "딜러와 %s에게 2장을 나누었습니다.",
                players.getParticipants().stream()
                        .map(Player::getName)
                        .collect(Collectors.joining(", "))
        ));
        customStringBuilder.appendLine(outputPlayerCardStatus(
                "딜러",
                generateCardName(dealer.getReceivedCards().getFirstCard()))
        );
        players.getParticipants().stream()
                .forEach(participant -> customStringBuilder.appendLine(outputPlayerCardStatus(
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
        return String.format("%s%s", card.getCardType().getDetail(), card.getShape().getDetail());
    }

    public void printPlayerCardStatus(String name, Participant participant) {
        System.out.println(outputPlayerCardStatus(name, generateCardNames(participant.getReceivedCards())));
    }

    public String outputPlayerCardStatus(String name, String cards) {
        return String.format("%s카드: %s", name, cards);
    }

    public void outputFinalCardStatus(Dealer dealer, Players players) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine(String.format("%s - 결과 : %d",
                outputPlayerCardStatus("딜러", generateCardNames(dealer.getReceivedCards())), dealer.calculatePoint()));
        players.getParticipants()
                .forEach(participant -> customStringBuilder.appendLine(String.format("%s - 결과 : %d",
                        outputPlayerCardStatus(participant.getName(),
                                generateCardNames(participant.getReceivedCards())),
                        participant.calculatePoint())));
        customStringBuilder.print();
    }

    public void outputDealerGetCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void outputDealerCardFinish() {
        System.out.println("딜러는 17이상이라 더이상 카드를 받을 수 없습니다.");
    }

    public void outputFinalResult(Map<Player, PlayerResult> gameResult, int dealerWin, int dealerLose) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine("## 최종 승패");
        customStringBuilder.appendLine(String.format("딜러: %d승 %d패", dealerWin, dealerLose));
        gameResult.keySet()
                .forEach(
                        participant -> {
                            customStringBuilder.appendLine(
                                    String.format("%s : %s", participant.getName(),
                                            gameResult.get(participant)));
                        }
                );
        customStringBuilder.print();
    }


    public void printParticipantBust(String name) {
        System.out.println(String.format("%s는 bust입니다.", name));
    }
}
