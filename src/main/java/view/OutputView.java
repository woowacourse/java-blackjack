package view;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import model.GameResult;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.player.Dealer;
import model.player.Player;

public class OutputView {

    private static final String DIVIDE_CARD_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String RECEIVED_CARD_MESSAGE = "%s 카드 : %s";
    private static final String GAME_RESULT_MESSAGE = "%s : %s";
    private static final String PLAYER_CARD_SUM_MESSAGE = " - 결과: %d";
    private static final String DEALER_ADD_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_PROMPT_MESSAGE = "## 최종 승패";

    public void printStartBlackJack(List<Player> players, Dealer dealer) {
        printPlayerNames(players);
        printPlayerCards(players, dealer);
    }

    private void printPlayerNames(List<Player> players) {
        String names = String.join(", ", players.stream().map(Player::getName).toList());
        System.out.println(System.lineSeparator() + DIVIDE_CARD_MESSAGE.formatted(names) + System.lineSeparator());
    }

    private void printPlayerCards(List<Player> players, Dealer dealer) {
        System.out.println(playerCardMessage(dealer, 1));
        for (Player player : players) {
            System.out.println(playerCardMessage(player));
        }
    }

    public void printPlayerCardMessage(Player player) {
        System.out.println(playerCardMessage(player));
    }

    private String playerCardMessage(Player player) {
        return playerCardMessage(player, player.getCards().size());
    }

    private String playerCardMessage(Player player, int printCardCounts) {
        List<Card> cards = player.getCards();
        int skipCount = cards.size() - printCardCounts;
        String cardNames = String.join(", ", cards.stream().skip(skipCount).map(this::cardToString).toList());
        return RECEIVED_CARD_MESSAGE.formatted(player.getName(), cardNames);
    }

    private String cardToString(Card card) {
        String cardNumber = cardNumberToString(card.getCardNumber());
        String cardShape = cardShapeToString(card.getCardShape());
        return cardNumber + cardShape;
    }

    private String cardNumberToString(CardNumber cardNumber) {//TODO : enumMap
        if (cardNumber == CardNumber.ACE) {
            return "A";
        }
        if (cardNumber == CardNumber.QUEEN) {
            return "Q";
        }
        if (cardNumber == CardNumber.KING) {
            return "K";
        }
        if (cardNumber == CardNumber.JACK) {
            return "J";
        }
        return String.valueOf(cardNumber.getNumber());
    }

    private String cardShapeToString(CardShape cardShape) {//TODO : enumMap
        if (cardShape == CardShape.SPACE) {
            return "스페이드";
        }
        if (cardShape == CardShape.CLOVER) {
            return "클로버";
        }
        if (cardShape == CardShape.HEART) {
            return "하트";
        }
        return "다이아몬드";
    }

    public void printResult(List<Player> players, Dealer dealer) {
        System.out.println();
        System.out.println(playerCardMessage(dealer) + PLAYER_CARD_SUM_MESSAGE.formatted(dealer.sumCardNumbers()));
        for (Player player : players) {
            System.out.println(playerCardMessage(player) + PLAYER_CARD_SUM_MESSAGE.formatted(player.sumCardNumbers()));
        }
    }

    public void printDealerAddCard() {
        System.out.println(System.lineSeparator() + DEALER_ADD_CARD_MESSAGE);
    }

    public void printGameResult(Map<Player, GameResult> results) {
        System.out.println(System.lineSeparator() + GAME_RESULT_PROMPT_MESSAGE);

        Map<GameResult, Long> dealerGameResult = results.values().stream()
                .collect(groupingBy(gameResult -> gameResult, counting()));

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<GameResult, Long> gameResult : dealerGameResult.entrySet()) {
            stringBuilder.append(gameResult.getValue()).append(dealerGameResultToString(gameResult.getKey()));
        }

        System.out.println(GAME_RESULT_MESSAGE.formatted("딜러", stringBuilder.toString()));
        for (Map.Entry<Player, GameResult> result : results.entrySet()) {
            System.out.println(
                    GAME_RESULT_MESSAGE.formatted(result.getKey().getName(), gameResultToString(result.getValue())));
        }
    }

    private String gameResultToString(GameResult gameResult) {
        if (gameResult == GameResult.WIN) {
            return "승";
        }
        if (gameResult == GameResult.LOSE) {
            return "패";
        }
        return "무";
    }

    private String dealerGameResultToString(GameResult gameResult) {
        if (gameResult == GameResult.WIN) {
            return "패";
        }
        if (gameResult == GameResult.LOSE) {
            return "승";
        }
        return "무";
    }

}
