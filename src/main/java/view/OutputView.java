package view;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Cards;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutputView {

    public void printErrorMessage(RuntimeException exception) {
        System.out.printf("[ERROR] %s", exception.getMessage());
    }

    public void printHandoutCards(List<Card> dealerCards, Set<Player> players) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", getPlayerNamesText(players));
        System.out.printf("딜러카드: %s\n", getCardsText(dealerCards));
        for (Player player : players) {
            System.out.printf("%s카드: %s\n", player.getName(), getCardsText(player.getCards()));
        }
    }

    public void printPlayerCards(Player player) {
        System.out.printf("%s카드: %s\n", player.getName(), getCardsText(player.getCards()));
    }

    public void printDealerDrawCount(int count) {
        System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n", count);
    }

    public void printDealerCardsAndResult(Cards cards, final int score) {
        System.out.printf("딜러카드: %s - 결과: %d\n", getCardsText(cards), score);
    }

    public void printCardsAndResult(String name, Cards cards, final int score) {
        System.out.printf("%s카드: %s - 결과: %d\n", name, getCardsText(cards), score);
    }

    public void printResult(Map<GameResult, Integer> dealerResult, Map<Player, GameResult> playerResult) {
        System.out.println("## 최종 승패");
        String dealerResultText = getDealerResultText(dealerResult);
        System.out.println(dealerResultText);
        playerResult.keySet().forEach(player -> {
            System.out.printf("%s: %s\n", player.getName(), getGameResultText(playerResult.get(player)));
        });
    }

    private String getDealerResultText(Map<GameResult, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder("딜러:");
        if (dealerResult.containsKey(GameResult.WIN)) {
            stringBuilder.append(String.format(" %d승", dealerResult.get(GameResult.WIN)));
        }
        if (dealerResult.containsKey(GameResult.DRAW)) {
            stringBuilder.append(String.format(" %d무", dealerResult.get(GameResult.DRAW)));
        }
        if (dealerResult.containsKey(GameResult.LOSE)) {
            stringBuilder.append(String.format(" %d패", dealerResult.get(GameResult.LOSE)));
        }
        return stringBuilder.toString();
    }

    private String getPlayerNamesText(Set<Player> players) {
        List<String> names = players.stream().map(Player::getName).toList();
        return String.join(", ", names);
    }

    private String getGameResultText(GameResult gameResult) {
        return switch (gameResult) {
            case LOSE -> "패";
            case WIN -> "승";
            case DRAW -> "무";
        };
    }

    private String getCardsText(Cards cards) {
        List<String> formatted = cards.getValues().stream()
                .map(this::getCardText)
                .toList();
        return String.join(", ", formatted);
    }

    private String getCardsText(List<Card> cards) {
        List<String> formatted = cards.stream()
                .map(this::getCardText)
                .toList();
        return String.join(", ", formatted);
    }

    private String getCardText(Card card) {
        return String.format("%s%s", getNumberText(card.getCardNumber()), getShapeText(card.getCardShape()));
    }

    private String getNumberText(CardNumber cardNumber) {
        return switch (cardNumber) {
            case A -> "A";
            case KING -> "K";
            case QUEEN -> "Q";
            case JACK -> "J";
            default -> String.valueOf(cardNumber.value);
        };
    }

    private String getShapeText(CardShape shape) {
        return switch (shape) {
            case HEART -> "하트";
            case SPADE -> "스페이드";
            case CLOVER -> "클로버";
            case DIAMOND -> "다이아몬드";
        };
    }

    public void printRevenueResult(final int dealerRevenue, Map<Player, Integer> playerRevenues) {
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d\n", dealerRevenue);
        for (Player player : playerRevenues.keySet()) {
            System.out.printf("%s: %d\n", player.getName(), playerRevenues.get(player));
        }
    }
}
