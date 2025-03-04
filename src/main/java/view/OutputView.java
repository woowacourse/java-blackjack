package view;

import domain.Card;
import domain.Dealer;
import domain.Player;
import domain.Players;
import java.util.List;

public class OutputView {

    public void printInitCards(Dealer dealer, Players players) {
        String str = players.getPlayers()
                .stream()
                .map(Player::getName)
                .reduce((a, b) -> String.join(", ", a, b))
                .get();
        System.out.println("딜러와 " + str + "에게 2장을 나누었습니다.");

        Card dealerCard = dealer.getOwnedCards().get(0);
        System.out.printf("딜러카드: %s%s\n",
                dealerCard.getNumber().getValue(),
                dealerCard.getShape().getValue());
        List<Player> participants = players.getPlayers();
        for (Player player : participants) {
            List<Card> ownedCards = player.getOwnedCards();
            Card card1 = ownedCards.get(0);
            Card card2 = ownedCards.get(1);
            System.out.printf("%s카드: %s%s %s%s\n",
                    player.getName(),
                    card1.getNumber().getValue(),
                    card1.getShape().getValue(),
                    card2.getNumber().getValue(),
                    card2.getShape().getValue());
        }
    }
}
