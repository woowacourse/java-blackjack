package view;

import domain.game.GamePoint;
import domain.result.ParticipantsFinalResult;
import domain.result.GameResult;
import domain.card.Card;
import domain.game.Hand;
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
    public static final String CARD_FORMAT_NUMBER_AND_SHAPE = "%s%s";

    public void printInitialStatus(final Dealer dealer, final Players players) {
        System.out.print(System.lineSeparator());
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n",
                dealer.getName().getValue(),
                getPlayerNames(players));
        printDealerAndPlayersStatus(dealer, players);
    }

    private String getPlayerNames(final Players players) {
        return players.getPlayers()
                .stream()
                .map(user -> user.getName().getValue())
                .collect(Collectors.joining(DELIMITER));
    }

    private void printDealerAndPlayersStatus(final Dealer dealer, final Players players) {
        System.out.println(getInitialDealerStatus(dealer));
        for (Player player : players.getPlayers()) {
            System.out.println(getParticipantStatus(player));
        }
        System.out.print(System.lineSeparator());
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

    private String getCardStringOf(final Card hand) {
        return makeCardString(hand);
    }

    private String getCardStringOf(final Hand hand) {
        return hand.getCards().stream()
                .map(this::makeCardString)
                .collect(Collectors.joining(DELIMITER));
    }

    private String makeCardString(final Card card) {
        return String.format(CARD_FORMAT_NUMBER_AND_SHAPE,
                TranslationUtil.translateFaceNumber(card.getCardNumberValue()),
                TranslationUtil.translateShape(card.getShape()));
    }

    public void printPlayerCards(final Player player) {
        System.out.printf(
                DEFAULT_FORMAT,
                player.getName().getValue(),
                getCardStringOf(player.getCards())
        );
        System.out.print(System.lineSeparator());
    }

    public void printAdditionalCardCount(final int cardCount, final boolean haveAdditionalCard) {
        if (haveAdditionalCard) {
            System.out.printf("\n딜러는 16 이하라 %d장의 카드를 더 받았습니다.\n", cardCount);
            System.out.print(System.lineSeparator());
            return;
        }
        System.out.println("\n딜러는 17 이상이라 카드를 받지 못했습니다.\n");
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
                getGamePoint(participant.calculatePoint()));
    }

    private String getGamePoint(final GamePoint gamePoint) {
        final int point = gamePoint.getPoint();
        return TranslationUtil.translatePoint(point);
    }

    public void printFinalResult(final Dealer dealer, ParticipantsFinalResult participantsFinalResult) {
        System.out.println("\n## 최종 수익");
        printDealerResult(dealer, participantsFinalResult);
        printPlayersResult(participantsFinalResult.getResult());
    }

    private void printDealerResult(final Dealer dealer, final ParticipantsFinalResult participantsFinalResult) {
        System.out.printf(FINAL_RESULT_FORMAT, dealer.getName().getValue(), participantsFinalResult.getDealerProfit());
    }

    private void printPlayersResult(final Map<GameResult, List<Player>> gameResult) {
        for (Map.Entry<GameResult, List<Player>> entry : gameResult.entrySet()) {
            printPlayerResult(entry);
        }
    }

    private void printPlayerResult(final Map.Entry<GameResult, List<Player>> entry) {
        final List<Player> players = entry.getValue();
        for (Player player : players) {
            System.out.printf(
                    FINAL_RESULT_FORMAT,
                    player.getName().getValue(),
                    entry.getKey().calculateProfit(player)
            );
        }
    }
}
