package view;

import domain.card.Card;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.game.GameResult;
import domain.state.Outcome;
import domain.participant.Player;
import domain.participant.Players;
import java.util.stream.Collectors;
import message.IOMessage;

public class ResultView {
    public void printLineBreak() {
        System.out.println();
    }

    public void printPlayerCards(String playerName, String cardNames) {
        System.out.println(playerName + "카드: " + cardNames);
    }

    public void printPlayerBust(String playerName) {
        System.out.println(playerName + "는 버스트!");
    }

    public void printDealerDrawMessage() {
        System.out.println(IOMessage.DEALER_HIT_ONE_CARD.message());
    }

    public void printDealerBust() {
        System.out.println("딜러는 버스트!");
    }

    public void printGameStartMessage(Players players, Dealer dealer) {
        System.out.println();
        printGameStart(players, dealer);
        System.out.println();
    }

    public void printGameStart(Players players, Dealer dealer) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", joinPlayerNames(players));
        System.out.printf("딜러카드: %s%n", formatCardName(dealer.getCardList().get(0)));
        players.forEachPlayer(player -> System.out.printf(
                "%s카드: %s%n",
                player.getName(),
                joinCardNames(player.getCardList())
        ));
    }

    private String formatCardName(final Card card) {
        return card.getRank().symbol() + card.getSuit().suit();
    }

    public void printResult(Players players, Dealer dealer) {
        System.out.println("딜러카드: " + joinCardNames(dealer.getCardList()) + " - 결과: " + dealer.getResult());
        players.forEachPlayer(player -> System.out.printf(
                "%s카드: %s - 결과: %s%n",
                player.getName(),
                joinCardNames(player.getCardList()),
                player.getResult()
        ));
    }

    public void printWinner(Players players, GameResult gameResult) {
        System.out.println();
        System.out.println(IOMessage.WINNING_STATISTICS.message());
        printDealerOutcome(gameResult);
        printPlayerOutcomes(players, gameResult);
    }

    private String joinPlayerNames(Players players) {
        final StringBuilder playerNames = new StringBuilder();
        players.forEachPlayer(player -> {
            if (playerNames.length() > 0) {
                playerNames.append(", ");
            }
            playerNames.append(player.getName());
        });
        return playerNames.toString();
    }

    public String joinCardNames(Hand cardList) {
        return cardList.getCards().stream()
                .map(this::formatCardName)
                .collect(Collectors.joining(", "));
    }

    private void printDealerOutcome(GameResult gameResult) {
        System.out.print("딜러: ");
        for (Outcome outcome : Outcome.values()) {
            System.out.print(gameResult.getDealerCount(outcome) + outcome.result() + " ");
        }
        System.out.println();
    }

    private void printPlayerOutcomes(Players players, GameResult gameResult) {
        players.forEachPlayer(player -> printPlayerOutcome(player, gameResult));
    }

    private void printPlayerOutcome(Player player, GameResult gameResult) {
        System.out.println(player.getName() + ": " + gameResult.getPlayerOutcome(player.getName()).result());
    }
}
