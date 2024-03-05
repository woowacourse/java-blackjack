package view;

import domain.Card;
import domain.Deck;
import domain.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void printSetting(Player dealer, List<Player> playerList) {
        String playerNameToString = playerList.stream()
                .map(player -> player.getName().getName())
                .collect(Collectors.joining(", "));
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", dealer.getName().getName(),playerNameToString);
        printDealerCard(dealer);
        playerList.forEach(this::printCurrentCard);
    }

    private void printDealerCard(Player dealer) {
        Card dealerFirstCard = dealer.getDeck().getCards().get(0);
        String firstCardToString = dealerFirstCard.getRank().getRankName() + dealerFirstCard.getShape().getShapeName();
        System.out.printf("%s: %s\n", dealer.getName().getName(), firstCardToString);
    }
    public void printCurrentCard(Player player) {
        System.out.printf("%s카드 : %s\n", player.getName().getName(), deckToString(player));
    }

    private static String deckToString(Player player) {
        Deck deck = player.getDeck();
        return deck.getCards()
                .stream()
                .map(card -> card.getRank().getRankName() + card.getShape().getShapeName())
                .collect(Collectors.joining(", "));
    }


}
