package model.view;

import java.util.List;
import model.card.CardType;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;

public class OutputView {

    public OutputView() {
    }

    public void printDistributeCards(Dealer dealer, Players players) {
        System.out.print(dealer.getName() + "와 ");
        List<String> playerNames = players.getPlayers().stream().map(Player::getName).toList();
        System.out.print(String.join(", ", playerNames));
        System.out.println("에게 2장을 나누었습니다.");
    }

    public void printCardsStock(String name, List<CardType> cards) {
        System.out.println(name + "카드: " + String.join(", ", cards.stream().map(CardType::card).toList()));
    }
}
