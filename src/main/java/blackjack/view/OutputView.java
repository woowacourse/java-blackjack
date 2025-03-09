package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.GameResults;
import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitCards(Players players) {
        String gamblersMessage = players.getGamblers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", gamblersMessage);
        printCards(players);
    }

    public void printCards(Players players) {
        List<Player> playersList = getDealerGamblerList(players.getDealer(), players.getGamblers());
        playersList.forEach(this::printCardsMessage);
        System.out.println();
    }

    public void printCardsMessage(Player player) {
        System.out.println(getCardsMessage(player.getName(), player.getOpenedCards()));
    }

    public void printDealerHitAndDealCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printTotalCardsMessage(Players players) {
        List<Player> playersList = getDealerGamblerList(players.getDealer(), players.getGamblers());
        System.out.println();
        System.out.println();
        playersList.forEach(player -> System.out.printf("%s - 결과: %d\n", getCardsMessage(player.getName(), player.getCards()), player.calculateCardNumber()));
    }

    public void printGameResults(Players players, GameResults gameResults) {
        List<Player> gamblers = players.getGamblers();
        Player dealer = players.getDealer();
        System.out.println("## 최종 승패");
        System.out.printf("%s: %s\n", dealer.getName(), getDealerWinLoseMessage(gameResults));
        gamblers.forEach(gambler ->
                System.out.println(gambler.getName() + ": " + getGamblerWinLoseMessage(gambler, gameResults)));
    }

    public String getDealerWinLoseMessage(GameResults gameResults) {
        StringBuilder dealerWinLoseRate = new StringBuilder();
        if (gameResults.getDealerWin() > 0) {
            dealerWinLoseRate.append(String.format("%d승 ", gameResults.getDealerWin()));
        }
        if (gameResults.getDealerDraw() > 0) {
            dealerWinLoseRate.append(String.format("%d무 ", gameResults.getDealerDraw()));
        }
        if (gameResults.getDealerLose() > 0) {
            dealerWinLoseRate.append(String.format("%d패 ", gameResults.getDealerLose()));
        }
        return dealerWinLoseRate.toString();
    }

    private String getGamblerWinLoseMessage(Player gambler, GameResults gameResults) {
        GameResult result = gameResults.getGameResult(gambler);
        return GameResultView.getShapeMessage(result);
    }

    private String getCardsMessage(String name, List<Card> cards) {
        String cardsMessage = cards.stream().map(this::getCardMessage).collect(Collectors.joining(", "));
        return String.format("%s카드: %s", name, cardsMessage);
    }

    private String getCardMessage(Card card) {
        return CardNumberView.getNumberMessage(card.getNumber()) +
                CardShapeView.getShapeMessage(card.getShape());
    }

    private List<Player> getDealerGamblerList(Player dealer, List<Player> gamblers) {
        List<Player> allPlayers = new ArrayList<>();
        allPlayers.add(dealer);
        allPlayers.addAll(gamblers);
        return allPlayers;
    }
}
