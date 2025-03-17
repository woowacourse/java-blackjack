package view;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import game.Dealer;
import game.Player;
import game.Players;
import java.util.List;

public class OutputView {

    public void printInitialGame(Card dealerCard, List<Player> players) {
        String playerNames = formatPlayerNames(players);
        System.out.printf("%n딜러와 %s에게 " + CardDeck.DRAW_COUNT_WHEN_START + "장을 나누었습니다.%n", playerNames);
        String dealerHand = formatHand(dealerCard);
        System.out.printf("딜러카드: %s%n", dealerHand);
        for (Player player : players) {
            System.out.printf("%s카드: %s%n", player.getName(), formatHand(player.getHand().getCards()));
        }
        System.out.println();
    }

    private String formatPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .reduce((a, b) -> a + ", " + b)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 플레이어 포맷팅에 실패하였습니다."));
    }

    public void printPlayerDraw(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), formatHand(player.getHand().getCards()));
    }

    public void printHitProcess(Dealer dealer, Players players) {
        String dealerResult = formatHand(dealer.getHand().getCards());
        System.out.printf("%n딜러카드: %s - 결과: %d%n", dealerResult, dealer.calculateTotalPoints());
        for (Player player : players.getPlayers()) {
            String playerResult = formatHand(player.getHand().getCards());
            System.out.printf("%s카드: %s - 결과: %d%n", player.getName(), playerResult, player.calculateTotalPoints());
        }
        System.out.println();
    }

    private String formatHand(Card dealerCard) {
        StringBuilder stringBuilder = new StringBuilder();
        List<CardNumber> honorCards = CardNumber.getHonorCard();
        formatSingleCard(dealerCard, honorCards, stringBuilder);
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private String formatHand(List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        List<CardNumber> honorCards = CardNumber.getHonorCard();
        for (Card card : cards) {
            formatSingleCard(card, honorCards, stringBuilder);
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private void formatSingleCard(Card card, List<CardNumber> honorCards, StringBuilder stringBuilder) {
        if (honorCards.contains(card.cardNumber())) {
            stringBuilder.append(card.cardNumber().name().charAt(0))
                    .append(card.pattern().getPattern())
                    .append(", ");
            return;
        }
        stringBuilder.append(card.cardNumber().getNumber())
                .append(card.pattern().getPattern())
                .append(", ");
    }

    public void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }


    public void printDealerProfit(int dealerProfit) {
        System.out.printf("딜러: %d%n", dealerProfit);
    }

    public void printPlayersProfit(List<String> allPlayerNames, List<Integer> playerProfits) {
        for (int i = 0; i < allPlayerNames.size(); i++) {
            System.out.printf("%s: %d%n", allPlayerNames.get(i), playerProfits.get(i));
        }
    }
}
