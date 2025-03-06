package view;

import domain.Card;
import domain.CardDeck;
import domain.CardNumber;
import domain.Dealer;
import domain.Player;
import domain.Players;
import java.util.List;

public class OutputView {

    public void printInitialGame(Dealer dealer, List<Player> players) {
        String playerNames = formatPlayerNames(players);
        System.out.printf("딜러와 %s에게 " + CardDeck.DRAW_COUNT_WHEN_START + "장을 나누었습니다.%n", playerNames);
        String dealerHand = formatHand(dealer.getInitCard());
        System.out.printf("딜러카드: %s%n", dealerHand);
        for (Player player : players) {
            System.out.printf("%s카드: %s%n", player.getName(), formatHand(player.getHand().getCards()));
        }
    }

    public void printPlayerCard(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), formatHand(player.getHand().getCards()));
    }

    public void printGameResult(Dealer dealer, Players players) {
        String dealerResult = formatHand(dealer.getHand().getCards());
        System.out.printf("딜러카드: %s - 결과: %d%n", dealerResult, dealer.calculateTotalCardNumber());
        for (Player player : players.getPlayers()) {
            String playerResult = formatHand(player.getHand().getCards());
            System.out.printf("%s카드: %s - 결과: %d%n", player.getName(), playerResult, player.calculateTotalCardNumber());
        }
    }

    private String formatPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .reduce((a, b) -> a + ", " + b)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 플레이어 포맷팅에 실패하였습니다."));
    }

    private String formatHand(List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        List<CardNumber> honorCards = CardNumber.getHonorCard();
        for (Card card : cards) {
            if (honorCards.contains(card.getCardNumber())) {
                stringBuilder.append(card.getCardNumber().name().charAt(0))
                        .append(card.getPattern().getPattern())
                        .append(", ");
                continue;
            }
            stringBuilder.append(card.getCardNumber().getNumber())
                    .append(card.getPattern().getPattern())
                    .append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
