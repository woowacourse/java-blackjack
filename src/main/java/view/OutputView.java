package view;

import view.dto.PlayerParameter;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

    public void printPlayersInfoWhenGameStarted(PlayerParameter dealer, List<PlayerParameter> players) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        players.forEach(player -> stringJoiner.add(player.getPlayerName()));
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", dealer.getPlayerName(), stringJoiner);

        printPlayerCardWithName(dealer);
        for (PlayerParameter player : players) {
            printPlayerCardWithName(player);
        }
    }

    public void printPlayerCardWithName(PlayerParameter player) {
        System.out.println(makePlayerCardMessageWithName(player));
    }

    public String makePlayerCardMessageWithName(PlayerParameter player) {
        String cardStr = player.getPlayerName() + "카드: ";
        StringJoiner stringJoiner = new StringJoiner(", ");

        player.getCards().forEach(stringJoiner::add);

        return cardStr + stringJoiner;
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerRecord(PlayerParameter dealer, Map<String, Integer> dealerRecord) {
        System.out.println("## 최종 승패");
        System.out.print(dealer.getPlayerName() + " : ");

        for (String gameResult : dealerRecord.keySet()) {
            if (dealerRecord.get(gameResult) != 0) {
                System.out.print(dealerRecord.get(gameResult) + gameResult);
            }
        }
        System.out.println();
    }

    public void printPlayerRecord(Map<String, String> gameResultMap) {
        for (String player : gameResultMap.keySet()) {
            System.out.println(player + " : " + gameResultMap.get(player));
        }
    }

    public void printGameScore(PlayerParameter dealerParameter, List<PlayerParameter> playersParameter) {
        printScore(dealerParameter);
        for (PlayerParameter playerParameter : playersParameter) {
            printScore(playerParameter);
        }
    }

    private void printScore(PlayerParameter playerParameter) {
        System.out.println(makePlayerCardMessageWithName(playerParameter) + " - 결과 : " + playerParameter.getResult());
    }
}
