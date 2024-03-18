package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.PlayerProfits;
import blackjack.domain.money.Profit;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {
    private static final OutputView INSTANCE = new OutputView();

    private static final String CARD_INFO_DELIMITER = ", ";
    private static final String PLAYER_NAME_DELIMITER = ", ";
    private static final String CARD_INFO_MESSAGE = "%s%s";
    private static final String PLAYER_CARD_INFO_MESSAGE = "%s카드: %s";

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printInitCardStatus(Dealer dealer, Players players) {
        List<Player> playerInfos = players.getPlayers();
        printDealMessage(playerInfos);

        printDealerInitCardHandStatusMessage(dealer);
        printPlayersInitCardHandStatusMessage(playerInfos);
    }

    private void printDealMessage(List<Player> playerInfos) {
        System.out.println(System.lineSeparator() + String.format("딜러와 %s에게 %d장을 나누었습니다.",
                createPlayerNamesText(playerInfos), Dealer.INIT_CARD_COUNT));
    }

    private void printDealerInitCardHandStatusMessage(Dealer dealer) {
        Card dealerInitCard = dealer.getInitCard();
        System.out.println(String.format("딜러: %s", createCardInfoText(dealerInitCard)));
    }

    private void printPlayersInitCardHandStatusMessage(List<Player> playerInfos) {
        for (Player playerInfo : playerInfos) {
            printCardHandStatus(playerInfo);
        }
        printLine();
    }

    private String createPlayerNamesText(List<Player> playerInfos) {
        StringJoiner playerNameJoiner = new StringJoiner(PLAYER_NAME_DELIMITER);
        for (Player playerInfo : playerInfos) {
            playerNameJoiner.add(playerInfo.getName());
        }

        return playerNameJoiner.toString();
    }

    private String createCardInfoText(Card card) {
        return String.format(CARD_INFO_MESSAGE, card.getRankName(), card.getSuitName());
    }

    public void printCardHandStatus(Player player) {
        System.out.println(
                String.format(PLAYER_CARD_INFO_MESSAGE, player.getName(), createCardsInfoText(player.getCardHand())));
    }

    private String createCardsInfoText(List<Card> cards) {
        StringJoiner cardInfoJoiner = new StringJoiner(CARD_INFO_DELIMITER);
        for (Card card : cards) {
            cardInfoJoiner.add(createCardInfoText(card));
        }

        return cardInfoJoiner.toString();
    }

    public void printDealerHitMessage() {
        System.out.println(System.lineSeparator() +
                String.format("딜러는 %d이하라 한장의 카드를 더 받았습니다.", Dealer.MAX_HIT_SCORE));
    }

    public void printTotalCardHandStatus(Dealer dealer, Players players) {
        printDealerTotalCardHandStatus(dealer);
        printPlayersTotalCardHandStatus(players);
        printLine();
    }

    private void printDealerTotalCardHandStatus(Dealer dealer) {
        printLine();
        System.out.println(String.format("딜러 카드: %s - 결과: %d",
                createCardsInfoText(dealer.getCardHand()), dealer.getScore()));
    }

    private void printPlayersTotalCardHandStatus(Players players) {
        for (Player player : players.getPlayers()) {
            System.out.println(String.format("%s카드: %s - 결과: %d",
                    player.getName(), createCardsInfoText(player.getCardHand()), player.getScore()));
        }
    }

    private void printLine() {
        System.out.println();
    }

    public void printProfits(PlayerProfits playerProfits) {
        Map<Player, Profit> profits = playerProfits.getProfits();

        System.out.println("## 최종 수익");
        System.out.println(String.format("딜러: %d", playerProfits.getDealerProfits()));
        profits.forEach(this::printProfit);
    }

    private void printProfit(Player player, Profit profit) {
        System.out.println(String.format("%s: %d", player.getName(), profit.getAmount()));
    }
}
