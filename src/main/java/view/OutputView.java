package view;

import domain.Card;
import domain.Dealer;
import domain.GameResult;
import domain.Player;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

    public void printPlayersInfo(Dealer dealer, List<Player> players) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        players.forEach(player -> stringJoiner.add(player.getPlayerName().getValue()));
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", dealer.getPlayerName().getValue(), stringJoiner);

        printPlayerInfo(dealer);
        for (Player player : players) {
           printPlayerInfo(player);
        }
    }

    public void printPlayerInfo(Player player) {
        System.out.print(player.getPlayerName().getValue() + ":");
        printPlayerCard(player);
    }

    public void printPlayerCard(Player player) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Card card : player.getCardPool().getCards()) {
            stringJoiner.add(CardNumberMapper.getCardNumber(card.getNumber()) + CardTypeMapper.getCardName(card.getType()));
        }
        System.out.println(stringJoiner);
    }

    public void printPlayerCardWithName(Player player) {
        System.out.print(player.getPlayerName().getValue() + "카드: ");
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Card card : player.getCardPool().getCards()) {
            stringJoiner.add(CardNumberMapper.getCardNumber(card.getNumber()) + CardTypeMapper.getCardName(card.getType()));
        }
        System.out.println(stringJoiner);
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }

    public void printResult(Player player) {
        System.out.print(player.getPlayerName().getValue() + "카드: ");
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Card card : player.getCardPool().getCards()) {
            stringJoiner.add(CardNumberMapper.getCardNumber(card.getNumber()) + CardTypeMapper.getCardName(card.getType()));
        }
        System.out.print(stringJoiner);
        System.out.println(" - 결과 : " + player.sumCardPool());
    }

    public void printGameResult(Map<Player, GameResult> gameResultMap) {
        for (Player player : gameResultMap.keySet()) {
            System.out.println(player.getPlayerName().getValue() + " : " + GameResultMapper.getGameResult(gameResultMap.get(player)));
        }
    }

    public void printDealerRecord(Dealer dealer,Map<GameResult, Integer> dealerRecord) {
        System.out.println("## 최종 승패");
        System.out.print(dealer.getPlayerName().getValue() + " : ");

        for (GameResult gameResult : dealerRecord.keySet()) {
            if (dealerRecord.get(gameResult) != 0) {
                System.out.print(dealerRecord.get(gameResult) + GameResultMapper.getGameResult(gameResult));
            }
        }
        System.out.println();
    }
}
