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

    public void printPlayersInfoWhenGameStarted(final Dealer dealer, final List<Player> players) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        players.forEach(player -> stringJoiner.add(player.getPlayerName().getName()));
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", dealer.getPlayerName().getName(), stringJoiner);

        printPlayerCardWithName(dealer);
        for (Player player : players) {
            printPlayerCardWithName(player);
        }
    }

    public void printPlayerCardWithName(final Player player) {
        System.out.println(makePlayerCardMessageWithName(player));
    }

    public String makePlayerCardMessageWithName(final Player player) {
        String cardStr = player.getPlayerName().getName() + "카드: ";
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Card card : player.getCardPool().getCards()) {
            stringJoiner.add(CardNumberMapper.ofCardNumber(card.getNumber()) + CardTypeMapper.ofCardType(card.getType()));
        }
        return cardStr + stringJoiner;
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerRecord(final Dealer dealer, final Map<GameResult, Integer> dealerRecord) {
        System.out.println("## 최종 승패");
        System.out.print(dealer.getPlayerName().getName() + " : ");

        for (GameResult gameResult : dealerRecord.keySet()) {
            if (dealerRecord.get(gameResult) != 0) {
                System.out.print(dealerRecord.get(gameResult) + GameResultMapper.getGameResult(gameResult));
            }
        }
        System.out.println();
    }

    public void printPlayerRecord(final Map<Player, GameResult> gameResultMap) {
        for (Player player : gameResultMap.keySet()) {
            System.out.println(player.getPlayerName().getName() + " : " + GameResultMapper.getGameResult(gameResultMap.get(player)));
        }
    }

    public void printGameScore(final Dealer dealer, final List<Player> players) {
        printScore(dealer);
        for (Player player : players) {
            printScore(player);
        }
    }

    private void printScore(final Player player) {
        System.out.println(makePlayerCardMessageWithName(player) + " - 결과 : " + player.sumCardPool());
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
