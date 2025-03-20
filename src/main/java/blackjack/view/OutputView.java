package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.game.ReceivedCards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    public void outputFirstCardDistributionResult(Players players, Dealer dealer) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine(String.format(
                "딜러와 %s에게 2장을 나누었습니다.",
                players.getPlayers().stream()
                        .map(Player::getName)
                        .collect(Collectors.joining(", "))
        ));
        customStringBuilder.appendLine(outputPlayerCardStatus(
                "딜러",
                generateCardName(dealer.getReceivedCards().getFirstCard()))
        );
        players.getPlayers().forEach(player ->
                customStringBuilder.appendLine(outputPlayerCardStatus(
                        player.getName(),
                        generateCardNames(player.getReceivedCards())
                )));
        customStringBuilder.print();
    }

    public String generateCardNames(ReceivedCards receivedCards) {
        return receivedCards.stream()
                .map(OutputView::generateCardName)
                .collect(Collectors.joining(", "));
    }

    public static String generateCardName(Card card) {
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
        players.getPlayers()
                .forEach(player -> customStringBuilder.appendLine(String.format("%s - 결과 : %d",
                        outputPlayerCardStatus(player.getName(),
                                generateCardNames(player.getReceivedCards())),
                        player.calculatePoint())));
        customStringBuilder.print();
    }

    public void outputDealerGetCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void outputDealerCardFinish() {
        System.out.println("딜러는 17이상이라 더이상 카드를 받을 수 없습니다.");
    }

    public void printPlayerBust(String name) {
        System.out.println(String.format("%s는 bust입니다.", name));
    }

    public void outputFinalProfit(Map<Player, Double> bettingResult, int dealerResult) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine("## 최종 수익");
        customStringBuilder.appendLine(String.format("딜러 : %d", dealerResult));
        for (Entry<Player, Double> entry : bettingResult.entrySet()) {
            customStringBuilder.appendLine(String.format("%s : %.0f원", entry.getKey().getName(), entry.getValue()));
        }
        customStringBuilder.print();
    }
}
