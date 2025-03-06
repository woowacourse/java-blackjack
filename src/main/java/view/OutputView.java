package view;

import domain.Player;
import domain.TrumpCard;
import java.util.List;

public class OutputView {

    public void printInitialCards(List<Player> players, TrumpCard dealerFirstCard) {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러와 ");
        players.forEach(player -> sb.append(player.getName()).append(", "));
        sb.delete(sb.length() - 2, sb.length());
        sb.append("에게 2장을 나누었습니다.\n");
        System.out.println(sb);

        System.out.printf("딜러카드: %s\n", getCardInfo(dealerFirstCard));

        players.forEach(player -> {
            List<TrumpCard> cards = player.retrieveInitialCards();
            System.out.printf("%s카드: %s, %s\n", player.getName(),
                    getCardInfo(cards.getFirst()),
                    getCardInfo(cards.getLast()));
        });
    }

    private String getCardInfo(TrumpCard card) {
        return card.getRank().getTitle() + card.getSuit().getTitle();
    }
}
