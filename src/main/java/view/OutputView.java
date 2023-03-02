package view;

import domain.Card;
import domain.Dealer;
import domain.Player;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    public void printPlayersInfo(List<Player> players) {
        for (Player player : players) {
           printPlayerInfo(player);
        }
    }

    public void printPlayerCard(Player player) {
        for (Card card : player.getCardPool().getCards()) {
            System.out.print(CardNumberMapper.getCardNumber(card.getNumber()) + CardTypeMapper.getCardName(card.getType()));
        }
        System.out.println();
    }

    public void printPlayerInfo(Player player) {
        System.out.print(player.getPlayerName().getValue() + ":");
        printPlayerCard(player);
    }

    public void printResult(Player player) {
        printPlayerCard(player);
        System.out.println("결과 : " + player.sumCardPool());
    }
}
