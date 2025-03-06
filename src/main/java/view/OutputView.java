package view;

import static domain.Dealer.BUST_THRESHOLD;
import static domain.GameResult.DRAW;
import static domain.GameResult.LOSE;
import static domain.GameResult.WIN;

import domain.Card;
import domain.GameResult;
import domain.GameStatistics;
import domain.Gamer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final Map<GameResult, String> GAME_RESULT = Map.of(
            WIN, "승",
            DRAW, "무",
            LOSE, "패");

    public void printInitialState(Map<String, Gamer> playersInfo, Card dealerCard) {
        List<String> usernames = new ArrayList<>(playersInfo.keySet());
        String names = String.join(", ", usernames);

        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", names);
        System.out.printf("딜러카드: %s\n", dealerCard.getCardName());

        for (String username : usernames) {
            Gamer player = playersInfo.get(username);
            List<Card> cards = player.getCards();
            printGamerCards(username, cards);
            System.out.println();
        }
    }

    public void printGamerCards(String username, List<Card> cards) {
        String userCards = String.join(", ", cards.stream()
                .map(Card::getCardName)
                .toList());
        System.out.printf("%s카드: %s", username, userCards);
    }

    public void printDealerDrawMoreCard() {
        System.out.printf("딜러는 %s이하라 카드를 더 받았습니다.\n", BUST_THRESHOLD);
    }

    public void printFinalState(Map<String, Gamer> playersInfo, Gamer dealer) {
        printGamerCards("딜러", dealer.getCards());
        printGamerScore(dealer);
        playersInfo.forEach((key, value) -> {
            printGamerCards(key, value.getCards());
            printGamerScore(value);
        });
        System.out.println();
    }

    public void printGameStatistics(GameStatistics gameStatistics) {
        System.out.println("##최종 승패");
        int dealerDrawCount = gameStatistics.getDealerDrawCount();
        if (dealerDrawCount == 0) {
            System.out.printf("\n딜러: %d승 %d패\n", gameStatistics.getDealerWinCount(),
                    gameStatistics.getDealerLoseCount());
        }
        if (dealerDrawCount != 0) {
            System.out.printf("딜러: %d승 %d무 %d패\n", gameStatistics.getDealerWinCount(), dealerDrawCount,
                    gameStatistics.getDealerLoseCount());
        }
        Map<String, GameResult> results = gameStatistics.getResults();
        results.forEach((key, value) -> {
            System.out.printf("%s: %s\n", key, GAME_RESULT.get(value));
        });
    }

    private void printGamerScore(Gamer gamer) {
        System.out.printf(" - 결과: %d\n", gamer.calculateScore());
    }
}
