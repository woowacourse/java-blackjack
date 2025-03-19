package blackjack.view;

import blackjack.gametable.BlackjackGame;
import blackjack.gametable.card.Card;
import blackjack.gametable.gambler.Dealer;
import blackjack.gametable.gambler.Player;
import blackjack.gametable.gambler.Players;
import java.util.List;

public class OutputView {

    public void printInitialGameSettings(BlackjackGame table) {
        Players players = table.getPlayers();
        Dealer dealer = table.getDealer();

        String joinedPlayers = String.join(", ", players.getPlayerNames());
        System.out.println("\n딜러와 " + joinedPlayers + "에게 2장을 나누었습니다.");

        System.out.println("딜러카드: " + processCardsInfo(dealer.openInitialCards()));
        for (String playerName : players.getPlayerNames()) {
            printPlayerCards(table, playerName);
        }
    }

    public void printDealerOneMoreCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printPlayerCards(BlackjackGame gameTable, String playerName) {
        Player player = gameTable.findPlayer(playerName);
        System.out.println(player.getPlayerName() + "카드: " + processCardsInfo(player.openCards()));
    }

    public void printGameResult(BlackjackGame gameTable) {
        Dealer dealer = gameTable.getDealer();
        List<Player> players = gameTable.getPlayers().getPlayers();

        System.out.println();
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + dealer.getBetAmount());
        for (Player player : players) {
            System.out.println(player.getPlayerName() + ": " + player.getBetAmount());
        }
    }

    public void printGameSummary(BlackjackGame gameTable) {
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
