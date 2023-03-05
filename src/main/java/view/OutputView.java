package view;

import domain.game.GamePoint;
import domain.game.GameResult;
import domain.game.Result;
import domain.card.Card;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {

    private static final String DELIMITER = ", ";
    private static final String DEFAULT_FORMAT = "%s 카드: %s";
    private static final String FINAL_RESULT_FORMAT = "%s: %s\n";

    public void printInitialStatus(final Dealer dealer, final Players players) {
        System.out.print(System.lineSeparator());
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n",
                dealer.getName().getValue(),
                getPlayerNames(players));

        System.out.println(getInitialDealerStatus(dealer));

        for (Player player : players.getPlayers()) {
            System.out.println(getParticipantStatus(player));
        }
    }

    private String getInitialDealerStatus(final Dealer dealer) {
        return String.format(
                DEFAULT_FORMAT,
                dealer.getName().getValue(),
                getCardStringOf(dealer.getFirstCard()));
    }

    private String getParticipantStatus(final Participant participant) {
        return String.format(
                DEFAULT_FORMAT,
                participant.getName().getValue(),
                getCardStringOf(participant.getCards()));
    }

    private String getPlayerNames(final Players players) {
        return players.getPlayers()
                .stream()
                .map(user -> user.getName().getValue())
                .collect(Collectors.joining(DELIMITER));
    }

    private String getCardStringOf(final Cards cards) {
        return cards.getCards().stream()
                .map(this::makeCardString)
                .collect(Collectors.joining(DELIMITER));
    }

    private String makeCardString(final Card card) {
        return String.format("%s%s",
                TranslationUtil.translateFaceNumber(card.getCardNumberValue()),
                TranslationUtil.translateShape(card.getShape()));
    }

    public void printPlayerCards(final Player player) {
        final Cards cards = player.getCards();
        System.out.printf(
                DEFAULT_FORMAT,
                player.getName().getValue(),
                getCardStringOf(cards)
        );
        System.out.print(System.lineSeparator());
    }

    public void printAdditionalCardCountOfDealer(final int cardCount) {
        if (cardCount == 0) {
            System.out.println("\n딜러는 17 이상이라 카드를 받지 못했습니다.\n");
        }
        if (cardCount > 0) {
            System.out.printf("\n딜러는 16 이하라 %d장의 카드를 더 받았습니다.\n", cardCount);
        }
    }

    public void printStatus(final Dealer dealer, final Players players) {
        printParticipantResult(dealer);
        for (Player player : players.getPlayers()) {
            printParticipantResult(player);
        }
    }

    private void printParticipantResult(final Participant participant) {
        System.out.printf("%s - 결과: %s\n",
                getParticipantStatus(participant),
                printGamePoint(participant.calculatePoint()));
    }

    private String printGamePoint(final GamePoint gamePoint) {
        final int point = gamePoint.getPoint();
        return TranslationUtil.translatePoint(point);
    }

    public void printFinalResult(final Dealer dealer, GameResult gameResult) {
        System.out.println("\n## 최종 승패");
        printDealerResult(dealer, gameResult.getDealerResult());
        printPlayersResult(gameResult.getPlayerResult());
    }

    private void printDealerResult(final Dealer dealer, final Map<Result, Integer> gameResult) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Result, Integer> entry : gameResult.entrySet()) {
            result.append(
                    String.format(
                            "%d%s ",
                            entry.getValue(),
                            TranslationUtil.translateResult(entry.getKey())
                    )
            );
        }
        System.out.printf(FINAL_RESULT_FORMAT, dealer.getName().getValue(), result);
    }

    private void printPlayersResult(final Map<Result, List<Player>> playerResult) {
        for (Map.Entry<Result, List<Player>> entry : playerResult.entrySet()) {
            final List<Player> players = entry.getValue();
            for (Player player : players) {
                System.out.printf(
                        FINAL_RESULT_FORMAT,
                        player.getName().getValue(),
                        TranslationUtil.translateResult(entry.getKey())
                );
            }
        }
    }
}
