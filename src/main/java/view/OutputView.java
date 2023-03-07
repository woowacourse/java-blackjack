package view;

import view.dto.PlayerDTO;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

    public void printPlayersInfoWhenGameStarted(PlayerDTO dealer, List<PlayerDTO> players) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        players.forEach(player -> stringJoiner.add(player.getPlayerName()));
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", dealer.getPlayerName(), stringJoiner);

        printPlayerCardWithName(dealer);
        for (PlayerDTO player : players) {
            printPlayerCardWithName(player);
        }
    }

    public void printPlayerCardWithName(PlayerDTO player) {
        System.out.println(makePlayerCardMessageWithName(player));
    }

    public String makePlayerCardMessageWithName(PlayerDTO player) {
        String cardStr = player.getPlayerName() + "카드: ";
        StringJoiner stringJoiner = new StringJoiner(", ");

        player.getCards().forEach(stringJoiner::add);

        return cardStr + stringJoiner;
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerRecord(PlayerDTO dealer, Map<String, Integer> dealerRecord) {
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

    public void printGameScore(PlayerDTO dealerParameter, List<PlayerDTO> playersParameter) {
        printScore(dealerParameter);
        for (PlayerDTO playerDTO : playersParameter) {
            printScore(playerDTO);
        }
    }

    private void printScore(PlayerDTO playerDTO) {
        System.out.println(makePlayerCardMessageWithName(playerDTO) + " - 결과 : " + playerDTO.getResult());
    }
}
