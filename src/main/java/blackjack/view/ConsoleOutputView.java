package blackjack.view;

import blackjack.BlackjackTable;
import blackjack.card.Card;
import blackjack.gambler.Dealer;
import blackjack.gambler.Player;
import blackjack.gambler.Players;
import java.util.List;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printInitialGameSettings(BlackjackTable table) {
        Players players = table.getPlayers();
        Dealer dealer = table.getDealer();

        String joinedPlayers = String.join(", ", players.getPlayerNames());
        System.out.println("\n딜러와 " + joinedPlayers + "에게 2장을 나누었습니다.");

        System.out.println("딜러카드: " + processCardsInfo(dealer.openInitialCards()));
        for (String playerName : players.getPlayerNames()) {
            printPlayerCards(table, playerName);
        }
    }

    @Override
    public void printDealerOneMoreCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    @Override
    public void printPlayerCards(BlackjackTable gameTable, String playerName) {
        Player player = gameTable.findPlayer(playerName);
        System.out.println(player.getPlayerName() + "카드: " + processCardsInfo(player.openCards()));
    }

    @Override
    public void printGameResult(BlackjackTable gameTable) {
        Dealer dealer = gameTable.getDealer();
        List<Player> players = gameTable.getPlayers().getPlayers();

        System.out.println();
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + dealer.getBetAmount());
        for (Player player : players) {
            System.out.println(player.getPlayerName() + ": " + player.getBetAmount());
        }
    }

    @Override
    public void printGameSummary(BlackjackTable gameTable) {
        Players players = gameTable.getPlayers();
        Dealer dealer = gameTable.getDealer();
        System.out.println("딜러카드: " + processCardsInfo(dealer.openCards()) + " - 결과: " + dealer.sumCardScores());
        for (Player player : players.getPlayers()) {
            System.out.println(player.getPlayerName() + "카드: " + processCardsInfo(player.openCards()) + " - 결과: "
                    + player.sumCardScores());
        }
    }

    private String processCardsInfo(List<Card> cards) {
        return String.join(", ", cards.stream()
                .map(this::processCardInfo)
                .toList());
    }

    private String processCardInfo(Card card) {
        return card.getRank().getName() + card.getSuit().getName();
    }
}
