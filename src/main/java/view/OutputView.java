package view;

import domain.BlackJackGame;
import domain.GameResult;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printErrorMessage(RuntimeException exception) {
        System.out.printf("[ERROR] %s", exception.getMessage());
    }

    public void printParticipantsHand(BlackJackGame game) {
        System.out.printf("%n딜러와 %s에게 %d장을 나누었습니다.%n", createPlayerNames(game.getPlayers()),
                game.getDealer().getHand().getCards().size());
        System.out.printf("딜러카드: %s%n", getCardText(game.getDealer().getHand().getCards().getFirst()));
        game.getPlayers().getPlayers().forEach(this::printPlayerCards);
        printEmptyLine();
    }

    private String createPlayerNames(Players players) {
        return players.getPlayers().stream().map(Player::getName).collect(Collectors.joining(", "));
    }

    public void printPlayerCards(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), getCardsText(player.getHand()));
    }

    public void printDealerDrawCount(int count) {
        if (count > 0) {
            System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다.%n%n", count);
        }
    }

    public void printPlayersCardAndSum(Players players) {
        List<Player> allPlayers = players.getPlayers();
        allPlayers.forEach(this::printCardsAndResult);
        printEmptyLine();
    }

    public void printDealerCardsAndResult(Dealer dealer) {
        System.out.printf("딜러카드: %s - 결과: %d\n", getCardsText(dealer.getHand()), dealer.calculateScore());
    }

    public void printCardsAndResult(Player player) {
        System.out.printf("%s카드: %s - 결과: %d\n", player.getName(), getCardsText(player.getHand()),
                player.calculateScore());
    }

    public void printResult(Map<GameResult, Integer> dealerResult, Map<Player, GameResult> playerResult) {
        System.out.println("## 최종 승패");
        String dealerResultText = getDealerResultText(dealerResult);
        System.out.println(dealerResultText);
        playerResult.keySet().forEach(player -> {
            System.out.printf("%s: %s\n", player.getName(), getGameResultText(playerResult.get(player)));
        });
    }

    public void printRevenue(Money money, Map<Player, Money> revenueResult) {
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %s\n", money.getValue());
        revenueResult.keySet().forEach(player -> {
            System.out.printf("%s: %s\n", player.getName(), revenueResult.get(player).getValue());
        });
    }

    private String getDealerResultText(Map<GameResult, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder("딜러: ");
        if (dealerResult.containsKey(GameResult.WIN) || dealerResult.containsKey(GameResult.BLACKJACK)) {
            stringBuilder.append(String.format("%d승",
                    dealerResult.getOrDefault(GameResult.WIN, 0) + dealerResult.getOrDefault(GameResult.BLACKJACK, 0)));
        }
        if (dealerResult.containsKey(GameResult.DRAW)) {
            stringBuilder.append(String.format(" %d무", dealerResult.get(GameResult.DRAW)));
        }
        if (dealerResult.containsKey(GameResult.LOSE)) {
            stringBuilder.append(String.format(" %d패", dealerResult.get(GameResult.LOSE)));
        }
        return stringBuilder.toString();
    }

    private String getGameResultText(GameResult gameResult) {
        return switch (gameResult) {
            case LOSE -> "패";
            case WIN, BLACKJACK -> "승";
            case DRAW -> "무";
        };
    }

    private String getCardsText(Hand hand) {
        List<String> formatted = hand.getCards().stream()
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
            default -> String.valueOf(cardNumber.getValue());
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

    private void printEmptyLine() {
        System.out.println();
    }
}
