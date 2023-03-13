package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DEALER_DEFAULT_NAME = "딜러";
    private static final String DELIMITER_COMMA = ", ";
    private static final String DELIMITER_COLON = ": ";
    private static final int BUST_SCORE = -1;

    public void printInitCards(final DealerDto dealer, final List<PlayerDto> players) {
        final List<String> playerNames = extractPlayerNames(players);
        final String dealerName = dealer.getName();

        System.out.println(dealerName + "와 " + String.join(DELIMITER_COMMA, playerNames) + "에게 2장을 나누었습니다.");

        printDealerCards(dealer);
        players.forEach(player -> {
            printParticipantCards(player.getName(), player.getCards());
        });
    }

    public void printParticipantCards(final String name, final List<CardDto> cards) {
        System.out.println(name + "카드: " + toCardsView(cards));
    }

    private String toCardsView(final List<CardDto> cards) {
        return cards.stream()
                .map(this::toCardView)
                .collect(Collectors.joining(DELIMITER_COMMA));
    }

    private String toCardView(final CardDto card) {
        return card.getNumber() + card.getShape();
    }

    public void printDealerCards(final DealerDto dealer) {
        List<CardDto> cards = dealer.getCards();
        CardDto dealerCard = cards.get(0);
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

    private String toScoreView(final int score) {
        if (score == BUST_SCORE) {
            return "BUST!";
        }
        return String.valueOf(score);
    }

    public void printGameResult(final Map<String, Integer> dealerMoney, final Map<String, Integer> playerMoney) {
        System.out.println("## 최종 수익");

        for (final String dealerName : dealerMoney.keySet()) {
            System.out.println(dealerName + DELIMITER_COLON + dealerMoney.get(dealerName));
        }

        playerMoney.keySet().forEach(userName -> System.out.println(userName + DELIMITER_COLON + playerMoney.get(userName)));
    }
}
