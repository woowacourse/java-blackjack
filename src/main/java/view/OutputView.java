package view;

import domain.*;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printParticipantCardsInfo(Dealer dealer, List<Player> players, int cardCount) {
        List<String> names = players.stream().map(Player::getName).toList();
        String namesText = String.join(", ", names);
        System.out.printf("딜러와 %s에게 %d장을 나누었습니다.\n", namesText, cardCount);
        printDealerCard(dealer);
        for (Player player : players) {
            printPlayerCards(player);
        }
    }

    public void printPlayerCards(Player player) {
        System.out.printf("%s카드: %s\n", player.getName(), getCardsText(player.getCards()));
    }

    public void printDealerDrawCount(int count) {
        System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n", count);
    }

    public void printCardsAndResult(String name, Cards cards, int result) {
        System.out.printf("%s카드: %s - 결과: %d\n", name, getCardsText(cards), result);
    }

    public void printResult(Map<GameResult, Integer> dealerResult, Map<Player, GameResult> playerResult) {
        System.out.println("## 최종 승패");
        StringBuilder stringBuilder = new StringBuilder("딜러: ");
        if (dealerResult.containsKey(GameResult.WIN)) {
            stringBuilder.append(String.format("%d승", dealerResult.get(GameResult.WIN)));
        }
        if (dealerResult.containsKey(GameResult.DRAW)) {
            stringBuilder.append(String.format(" %d무", dealerResult.get(GameResult.DRAW)));
        }
        if (dealerResult.containsKey(GameResult.LOSE)) {
            stringBuilder.append(String.format(" %d패", dealerResult.get(GameResult.LOSE)));
        }
        System.out.println(stringBuilder);
        playerResult.keySet().forEach(player -> {
            System.out.printf("%s: %s\n", player.getName(), getGameResultText(playerResult.get(player)));
        });
    }

    private String getGameResultText(GameResult gameResult) {
        return switch (gameResult) {
            case LOSE -> "패";
            case WIN -> "승";
            case DRAW -> "무";
        };
    }

    private void printDealerCard(Dealer dealer) {
        System.out.printf("딜러카드: %s\n", getDealerCardText(dealer.getCards()));
    }

    private String getDealerCardText(Cards cards) {
        return getCardText(cards.getCards().getFirst());
    }

    private String getCardsText(Cards cards) {
        List<String> formatted = cards.getCards().stream()
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
            default -> String.valueOf(cardNumber.getCoordinates().getFirst());
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
}
