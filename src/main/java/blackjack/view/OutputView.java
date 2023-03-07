package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.game.GameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String DEALER_DEFAULT_NAME = "딜러";
    public static final String DELIMITER_COMMA = ", ";
    public static final String DELIMITER_COLON = ": ";

    public void printInitCards(final Dealer dealer, final Players players) {
        final List<String> playerNames = extractPlayerNames(players);
        final String dealerName = dealer.getName();

        System.out.println(dealerName + "와 " + String.join(DELIMITER_COMMA, playerNames) + "에게 2장을 나누었습니다.");

        printDealerCards(dealer);
        players.getPlayers().forEach(player -> {
            printParticipantCards(player.getName(), player.showCards());
        });
    }

    public void printParticipantCards(final String name, final List<Card> cards) {
        System.out.println(name + "카드: " + toCardsView(cards));
    }

    private String toCardsView(final List<Card> cards) {
        return cards.stream()
                .map(this::toCardView)
                .collect(Collectors.joining(DELIMITER_COMMA));
    }

    private String toCardView(final Card card) {
        final CardNumber number = card.getNumber();
        final CardShape shape = card.getShape();

        return number.getView() + shape.getView();
    }

    public void printDealerCards(final Dealer dealer) {
        List<Card> cards = dealer.showCards();
        Card dealerCard = cards.get(0);
        System.out.println(dealer.getName() + "카드: " + toCardView(dealerCard));
    }

    private static List<String> extractPlayerNames(final Players players) {
        return players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void printDealerDraw() {
        System.out.println(DEALER_DEFAULT_NAME + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printCardResult(final Dealer dealer, final Players players) {
        System.out.println();
        System.out.println(dealer.getName() + "카드: " + toCardsView(dealer.showCards()) + " - 결과: " + dealer.getScore());
        players.getPlayers().forEach(player ->
                System.out.println(player.getName() + "카드: " + toCardsView(player.showCards()) + " - 결과: " + player.getScore())
        );
        System.out.println();
    }

    public void printGameResult(final Map<GameResult, Integer> dealerResult, final Map<String, GameResult> playerScore) {
        System.out.println("## 최종 승패");

        StringBuilder stringBuilder = new StringBuilder();
        for (final GameResult score : dealerResult.keySet()) {
            final Integer count = dealerResult.get(score);
            stackResult(stringBuilder, score, count);
        }

        System.out.println(DEALER_DEFAULT_NAME + DELIMITER_COLON + stringBuilder);

        playerScore.keySet().forEach(userName -> System.out.println(userName + DELIMITER_COLON + playerScore.get(userName).getView()));
    }

    private static void stackResult(final StringBuilder stringBuilder, final GameResult score, final Integer count) {
        if (count != 0) {
            stringBuilder.append(count).append(score.getView()).append(" ");
        }
    }
}
