package view;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.game.Dealer;
import domain.game.GameResult;
import domain.game.Player;
import domain.game.Players;
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

    public void printPlayerCard(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), formatHand(player.getHand().getCards()));
    }

    public void printGameResult(Dealer dealer, Players players) {
        String dealerResult = formatHand(dealer.getHand().getCards());
        System.out.printf("%n딜러카드: %s - 결과: %d%n", dealerResult, dealer.calculateTotalCardNumber());
        for (Player player : players.getPlayers()) {
            String playerResult = formatHand(player.getHand().getCards());
            System.out.printf("%s카드: %s - 결과: %d%n", player.getName(), playerResult, player.calculateTotalCardNumber());
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
        if (honorCards.contains(card.getCardNumber())) {
            stringBuilder.append(card.getCardNumber().name().charAt(0))
                    .append(card.getPattern().getPattern())
                    .append(", ");
            return;
        }
        stringBuilder.append(card.getCardNumber().getNumber())
                .append(card.getPattern().getPattern())
                .append(", ");
    }

    public void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerWinningResult(int winCount, int drawCount, int loseCount) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d무 %d패%n", winCount, drawCount, loseCount);
    }

    public void printWinningResult(List<String> playerNames, List<GameResult> gameResults) {
        for (int i = 0; i < playerNames.size(); i++) {
            System.out.printf("%s: %s%n", playerNames.get(i), gameResults.get(i).getResult());
        }
    }
}
