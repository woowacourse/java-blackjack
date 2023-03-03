package view;

import domain.card.Card;
import domain.user.Dealer;
import domain.game.GameResult;
import domain.user.Player;
import view.mapper.CardNumberMapper;
import view.mapper.CardTypeMapper;
import view.mapper.GameResultMapper;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

    public void printPlayersInfoWhenGameStarted(Dealer dealer, List<Player> players) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        players.forEach(player -> stringJoiner.add(player.getPlayerName().getValue()));
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", dealer.getPlayerName().getValue(), stringJoiner);

        printPlayerCardWithName(dealer);
        for (Player player : players) {
           printPlayerCardWithName(player);
        }
    }

    public void printPlayerCardWithName(Player player) {
        System.out.println(makePlayerCardMessageWithName(player));
    }

    public String makePlayerCardMessageWithName(Player player) {
        String cardStr = player.getPlayerName().getValue() + "카드: ";
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Card card : player.getCardPool().getCards()) {
            stringJoiner.add(CardNumberMapper.getCardNumber(card.getNumber()) + CardTypeMapper.getCardName(card.getType()));
        }
        return cardStr + stringJoiner;
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
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

    public void printPlayerRecord(Map<Player, GameResult> gameResultMap) {
        for (Player player : gameResultMap.keySet()) {
            System.out.println(player.getPlayerName().getValue() + " : " + GameResultMapper.getGameResult(gameResultMap.get(player)));
        }
    }

    public void printGameScore(Dealer dealer, List<Player> players) {
        printScore(dealer);
        for (Player player : players) {
            printScore(player);
        }
    }

    private void printScore(Player player) {
        System.out.println(makePlayerCardMessageWithName(player) + " - 결과 : " + player.sumCardPool());
    }
}
