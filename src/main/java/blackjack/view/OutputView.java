package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.game.Score;
import blackjack.domain.game.ScoreBoard;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.domain.user.name.UserName;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printUsersCard(final Dealer dealer, final Players players) {
        final List<String> playerNames = extractPlayerNames(players);
        final String dealerName = dealer.getName().getName();

        System.out.println(dealerName + "와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");

        printUserCards(dealer);
        System.out.println();
        players.getPlayers().forEach(player -> {
            printUserCards(player);
            System.out.println();
        });
    }

    public void printUserCards(final User user) {
        System.out.print(user.getName().getName() + "카드: " + getCards(user.showCards()));
    }

    private String getCards(final List<Card> cards) {
        return cards.stream().map(this::toCardView)
                .collect(Collectors.joining(", "));
    }

    private String toCardView(final Card card) {
        final CardNumber number = card.getNumber();
        final CardShape shape = card.getShape();

        return number.getName() + shape.getView();
    }

    private static List<String> extractPlayerNames(final Players players) {
        return players.getPlayers().stream()
                .map(Player::getName)
                .map(UserName::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void printDealerDraw() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printCardResult(ScoreBoard scoreBoard) {
        System.out.println();
        scoreBoard.getParticipants().forEach(user -> {
            printUserCards(user);
            System.out.println(" - 결과: " + scoreBoard.get(user.getName()));
        });
        System.out.println();
    }

    public void printGameResult(final Map<Score, Integer> dealerScore, final Map<UserName, Score> playerScore) {
        System.out.println("## 최종 승패");
        StringBuilder stringBuilder = new StringBuilder();
        for (final Score score : dealerScore.keySet()) {
            final Integer count = dealerScore.get(score);
            stackResult(stringBuilder, score, count);
        }

        System.out.println("딜러: " + stringBuilder);

        playerScore.keySet().forEach(userName -> {
            System.out.println(userName.getName() + ": " + playerScore.get(userName).getView());
        });
    }

    private static void stackResult(final StringBuilder stringBuilder, final Score score, final Integer count) {
        if (count != 0) {
            stringBuilder.append(count).append(score.getView()).append(" ");
        }
    }
}
