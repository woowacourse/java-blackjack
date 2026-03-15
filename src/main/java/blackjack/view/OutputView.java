package blackjack.view;

import blackjack.domain.game.FinalIncome;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.Map;

public class OutputView {
    private static final String lineSeparator = System.lineSeparator();

    public void printInitDraw(Players players, Dealer dealer) {
        System.out.printf(lineSeparator + "딜러와 %s에게 2장을 나누었습니다." + lineSeparator,
                String.join(", ", players.getNames()));
        System.out.printf("딜러카드: %s" + lineSeparator, dealer.getFirstCardNames());

        for (Player player : players.getPlayers()) {
            printCard(player);
        }
    }

    public void printCard(Player player) {
        System.out.printf("%s카드: %s" + lineSeparator, player.getName(), player.getCardNames());
    }

    public void printDealerDraw() {
        System.out.println(lineSeparator + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCardResult(Dealer dealer, Players players) {
        System.out.printf(lineSeparator + "딜러카드: %s - 결과: %d" + lineSeparator,
                String.join(", ", dealer.getCardNames()), dealer.getTotalPoint());
        for (Player player : players.getPlayers()) {
            System.out.printf("%s카드: %s - 결과: %d" + lineSeparator, player.getName(), player.getCardNames(),
                    player.getTotalPoint());
        }
    }

    public void printFinalGameResult(FinalIncome finalIncome) {
        System.out.println("\n## 최종 수익");
        System.out.println("딜러: " + finalIncome.getDealerIncome());

        for (Map.Entry<Player, Integer> entry : finalIncome.getPlayerIncomeResults().entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
    }

}
