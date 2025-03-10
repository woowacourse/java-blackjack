package view;

import domain.BlackJackGame;
import domain.GameResult;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    public void printErrorMessage(RuntimeException exception) {
        System.out.printf("[ERROR] %s", exception.getMessage());
    }

    public void printParticipantsHand(BlackJackGame game) {
        System.out.printf("%n딜러와 %s에게 %d장을 나누었습니다.%n", createPlayerNames(game.getPlayers()),
                game.getDealer().getCards().getCards().size());
        System.out.printf("딜러카드: %s%n", getCardText(game.getDealer().getCards().getCards().getFirst()));
        game.getPlayers().getPlayers().forEach(this::printPlayerCards);
        printEmptyLine();
    }

    private String createPlayerNames(Players players) {
        return players.getPlayers().stream().map(Player::getName).collect(Collectors.joining(", "));
    }

    public void printPlayerCards(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), getCardsText(player.getCards()));
    }

    public void printDealerDrawCount(int count) {
        if (count > 0) {
            System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n", count);
        }
    }

    public void printPlayersCardAndSum(Players players) {
        Set<Player> allPlayers = players.getPlayers();
        allPlayers.forEach(this::printCardsAndResult);
    }

    public void printDealerCardsAndResult(Dealer dealer) {
        System.out.printf("딜러카드: %s - 결과: %d\n", getCardsText(dealer.getCards()), dealer.getCardScore());
    }

    public void printCardsAndResult(Player player) {
        System.out.printf("%s카드: %s - 결과: %d\n", player.getName(), getCardsText(player.getCards()),
                player.getCardScore());
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
        return stringBuilder.toString();
    }

    private String getGameResultText(GameResult gameResult) {
        return switch (gameResult) {
            case LOSE -> "패";
            case WIN -> "승";
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

    private void printEmptyLine() {
        System.out.println();
    }
}
