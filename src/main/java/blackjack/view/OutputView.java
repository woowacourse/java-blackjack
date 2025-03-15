package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameProfits;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.Profit;

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
        playersList.forEach(player ->
                System.out.printf("%s - 결과: %d\n", getCardsMessage(player.getName(), player.getCards()), player.getGameScore().getValue()));
    }

    public void printGameResults(Players players, GameProfits gameProfits) {
        List<Gambler> gamblers = players.getGamblers();
        Dealer dealer = players.getDealer();
        System.out.println("\n## 최종 수익");
        System.out.printf("%s: %s\n", dealer.getName(), getDealerProfit(gameProfits));
        gamblers.forEach(gambler ->
                System.out.println(gambler.getName() + ": " + getGamblerProfit(gambler, gameProfits)));
    }

    public String getDealerProfit(GameProfits gameProfits) {
        Profit profit = gameProfits.getDealerProfit();
        return profit.getValue() + "";
    }

    private String getGamblerProfit(Gambler gambler, GameProfits gameProfits) {
        Profit profit = gameProfits.getGameResult(gambler);
        return profit.getValue() + "";
    }

    private String getCardsMessage(String name, List<Card> cards) {
        String cardsMessage = cards.stream().map(this::getCardMessage).collect(Collectors.joining(", "));
        return String.format("%s카드: %s", name, cardsMessage);
    }

    private String getCardMessage(Card card) {
        return CardNumberView.getNumberMessage(card.number()) +
                CardShapeView.getShapeMessage(card.shape());
    }

    private List<Player> getDealerGamblerList(Dealer dealer, List<Gambler> gamblers) {
        List<Player> allPlayers = new ArrayList<>();
        allPlayers.add(dealer);
        allPlayers.addAll(gamblers);
        return allPlayers;
    }
}
