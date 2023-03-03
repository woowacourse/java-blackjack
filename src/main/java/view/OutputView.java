package view;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printInitialMessage(final List<String> names) {
        String dealerName = names.get(0);
        String playerNames = names.stream().skip(1).collect(Collectors.joining(", "));
        System.out.printf("%s와 %s에게 2장을 나누었습니다." + System.lineSeparator(), dealerName, playerNames);
    }

    public void printAllState(final Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        Card dealerCard = dealer.getCards().get(0);
        String dealerCardDisplay = String.format("%s: %s%s", dealer.getName(), dealerCard.getValue(),
                dealerCard.getShape());
        System.out.println(dealerCardDisplay);

        for (Player player : players) {
            printSingleState(player);
        }
    }

    public void printSingleState(final Participant participant) {
        System.out.println(formatCardState(participant));
    }

    public void printFinalState(final Participants participants) {
        String finalFormat = "%s - 결과: %d" + System.lineSeparator();
        Dealer dealer = participants.getDealer();
        System.out.printf(finalFormat, formatCardState(dealer), dealer.calculateScore());
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            System.out.printf(finalFormat, formatCardState(player), player.calculateScore());
        }
    }

    private String formatCardState(final Participant participant) {
        String cards = participant.getCards().stream()
                .map(playerCard -> String.format("%s%s", playerCard.getValue(), playerCard.getShape()))
                .collect(Collectors.joining(", "));
        return String.format("%s카드: %s", participant.getName(), cards);
    }

    public void printFillDealerCards() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }


}
