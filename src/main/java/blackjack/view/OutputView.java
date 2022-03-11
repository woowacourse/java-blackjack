package blackjack.view;

import blackjack.model.Card;
import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;
import blackjack.model.player.Player;
import blackjack.model.Result;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String DISTRIBUTE_CARD_MSG = "%s와 %s에게 2장을 나누었습니다.%n";
    public static final String CARD_DISPLAY_MSG = "%s: %s%n";
    public static final String PLAYER_CARD_OPEN_MSG = "%s카드: %s%n";
    public static final String DEALER_TAKE_CARD_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    public static final String DISPLAY_RESULT_MSG = "%s 카드: %s - 결과: %d%n";
    public static final String GAME_RESULT_GUIDE_MSG = "## 최종 승패";
    public static final String DEALER_GAME_RESULT_MSG = "딜러: %d승 %d무 %d패%n";

    private OutputView() {}

    public static void printOpenCard(Dealer dealer, List<Gamer> gamers) {
        System.out.printf("\n" + DISTRIBUTE_CARD_MSG, dealer.name(), gamerNames(gamers));
        System.out.printf(CARD_DISPLAY_MSG, dealer.name(), openCards(dealer.openCards()));
        for (Player gamer : gamers) {
            System.out.printf(PLAYER_CARD_OPEN_MSG, gamer.name(), openCards(gamer.openCards()));
        }
    }

    private static String gamerNames(List<Gamer> gamers) {
        return gamers.stream()
            .map(Player::name)
            .collect(Collectors.joining(", "));
    }

    private static String openCards(List<Card> openCard) {
        return openCard.stream()
            .map(OutputView::cardText)
            .collect(Collectors.joining(", "));
    }

    private static String cardText(Card card) {
        return card.rank().symbol() + card.suit().symbol();
    }

    public static void printCard(Player player) {
        System.out.printf(CARD_DISPLAY_MSG, player.name(), takenCards(player));
    }

    private static String takenCards(Player player) {
        return player.cards().stream()
            .map(OutputView::cardText)
            .collect(Collectors.joining(", "));
    }

    public static void printDealerTakeCardMessage() {
        System.out.println("\n" + DEALER_TAKE_CARD_MSG);
    }

    public static void printTotalScore(Dealer dealer, List<Gamer> gamers) {
        System.out.printf("\n" + DISPLAY_RESULT_MSG, dealer.name(), takenCards(dealer), dealer.score().getValue());
        for (Player player : gamers) {
            System.out.printf(DISPLAY_RESULT_MSG, player.name(), takenCards(player), player.score().getValue());
        }
    }

    public static void printDealerRecord(Map<Result, Integer> result) {
        System.out.println("\n" + GAME_RESULT_GUIDE_MSG);
        System.out.printf(DEALER_GAME_RESULT_MSG,
                result.getOrDefault(Result.WIN, 0),
                result.getOrDefault(Result.DRAW, 0),
                result.getOrDefault(Result.LOSS, 0));
    }

    public static void printGamerRecord(String name, String result) {
        System.out.printf(CARD_DISPLAY_MSG, name, result);
    }
}
