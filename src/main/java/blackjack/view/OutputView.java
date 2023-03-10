package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.Money;
import blackjack.domain.user.Score;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String DEALER_DEFAULT_NAME = "딜러";
    public static final String DELIMITER_COMMA = ", ";
    public static final String DELIMITER_COLON = ": ";

    public void printInitCards(final DealerDto dealer, final List<PlayerDto> players) {
        final List<String> playerNames = extractPlayerNames(players);
        final String dealerName = dealer.getName();

        System.out.println(dealerName + "와 " + String.join(DELIMITER_COMMA, playerNames) + "에게 2장을 나누었습니다.");

        printDealerCards(dealer);
        players.forEach(player -> {
            printParticipantCards(player.getName(), player.getCards());
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

    public void printDealerCards(final DealerDto dealer) {
        List<Card> cards = dealer.getCards();
        Card dealerCard = cards.get(0);
        System.out.println(dealer.getName() + "카드: " + toCardView(dealerCard));
    }

    private static List<String> extractPlayerNames(final List<PlayerDto> players) {
        return players.stream()
                .map(PlayerDto::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void printDealerDraw() {
        System.out.println(DEALER_DEFAULT_NAME + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printCardResult(final DealerDto dealer, final List<PlayerDto> players) {
        System.out.println();
        System.out.println(dealer.getName() + "카드: " + toCardsView(dealer.getCards()) + " - 결과: " + toScoreView(dealer.getScore()));
        players.forEach(player -> System.out.println(player.getName() + "카드: " + toCardsView(player.getCards()) + " - 결과: " + toScoreView(player.getScore())));
        System.out.println();
    }

    private String toScoreView(final Score score) {
        if (score.isBust()) {
            return "BUST!";
        }
        return String.valueOf(score.getValue());
    }

    public void printGameResult(final Map<String, Money> dealerMoney, final Map<String, Money> playerMoney) {
        System.out.println("## 최종 수익");

        for (final String dealerName : dealerMoney.keySet()) {
            System.out.println(dealerName + DELIMITER_COLON + dealerMoney.get(dealerName).getValue());
        }

        playerMoney.keySet().forEach(userName -> System.out.println(userName + DELIMITER_COLON + playerMoney.get(userName).getValue()));
    }

    private static void stackResult(final StringBuilder stringBuilder, final GameResult score, final Integer count) {
        if (count != 0) {
            stringBuilder.append(count).append(score.getView()).append(" ");
        }
    }
}
