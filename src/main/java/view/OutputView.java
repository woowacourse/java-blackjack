package view;

import domain.card.Card;
import domain.participant.Dealer;
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

    public void printInitialState(final Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        Card dealerCard = dealer.getCards().get(0);
        String dealerCardDisplay = String.format("%s: %s%s", dealer.getName(), dealerCard.getValue(),
                dealerCard.getShape());
        System.out.println(dealerCardDisplay);
        for (Player player : players) {
            printSinglePlayerCards(player);
        }
    }

    public void printSinglePlayerCards(final Player player) {
        List<Card> playerCards = player.getCards();
        String cards = playerCards.stream()
                .map(playerCard -> String.format("%s%s", playerCard.getValue(), playerCard.getShape()))
                .collect(Collectors.joining(", "));
        String playerCardDisplay = String.format("%s카드: %s", player.getName(), cards);
        System.out.println(playerCardDisplay);
    }
}
