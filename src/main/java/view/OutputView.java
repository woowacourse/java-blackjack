package view;

import static domain.game.GameResult.DRAW;
import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;
import static domain.participants.Dealer.BUST_THRESHOLD;

import domain.card.Card;
import domain.game.BettingResultAmount;
import domain.game.BettingStatistics;
import domain.game.GameResult;
import domain.game.GameStatistics;
import domain.participants.Gamer;
import domain.participants.PlayerName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final Map<GameResult, String> GAME_RESULT = Map.of(
            WIN, "승",
            DRAW, "무",
            LOSE, "패");

    public void printInitialState(Map<PlayerName, Gamer> playersInfo, Card dealerCard) {
        List<PlayerName> playerNames = new ArrayList<>(playersInfo.keySet());
        List<String> usernames = playerNames.stream()
                .map(PlayerName::username)
                .toList();
        String names = String.join(", ", usernames);

        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", names);
        System.out.printf("딜러카드: %s\n", dealerCard.getCardName());

        for (PlayerName playerName : playerNames) {
            Gamer player = playersInfo.get(playerName);
            List<Card> cards = player.getCards();
            printGamerCards(playerName.username(), cards);
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

    public void printFinalState(Map<PlayerName, Gamer> playersInfo, Gamer dealer) {
        System.out.println();
        printGamerCards("딜러", dealer.getCards());
        printGamerScore(dealer);
        playersInfo.forEach((key, value) -> {
            printGamerCards(key.username(), value.getCards());
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
        Map<PlayerName, GameResult> results = gameStatistics.getResults();
        results.forEach((key, value) -> {
            System.out.printf("%s: %s\n", key.username(), GAME_RESULT.get(value));
        });
        System.out.println();
    }

    public void printBettingStatistics(BettingStatistics bettingStatistics) {
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d\n", bettingStatistics.calculateDealerBettingResult().getMoney());
        Map<PlayerName, BettingResultAmount> bettingResult = bettingStatistics.getBettingResult();
        bettingResult.forEach((key, value) -> System.out.printf("%s: %d\n", key.username(), value.getMoney()));
    }

    private void printGamerScore(Gamer gamer) {
        System.out.printf(" - 결과: %d\n", gamer.calculateScore());
    }
}
