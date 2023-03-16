package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.Betting;
import blackjack.domain.game.GameResult;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;

public class OutputView {

    private static final String NEW_LINE = "\n";
    private static final String COMMA = ", ";
    private static final String COLON = ": ";
    private static final String GIVE_TWO_CARD_MESSAGE = "에게 두 장을 나누었습니다.";
    private static final String NOTICE_TOTAL_SCORE_UNDER_SIXTEEN_MESSAGE = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String RESULT_DELIMITER = " - 결과 : ";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String ERROR_PREFIX_MESSAGE = "[ERROR] ";

    public void printInitialCards(final Participants participants) {
        System.out.println(getParticipantsList(participants) + GIVE_TWO_CARD_MESSAGE);

        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayers();

        printDealerNameAndCard(dealer, dealer.getCard(0));
        players.forEach(this::printParticipantNameAndCards);
    }

    private String getParticipantsList(final Participants participants) {
        return String.join(COMMA, participants.getNames());
    }

    private void printDealerNameAndCard(final Participant dealer, final Card card) {
        System.out.println(NEW_LINE + dealer.getName() + COLON + card.getCardName());
    }

    public void printParticipantNameAndCards(final Participant participant) {
        System.out.println(getNameAndCards(participant));
    }

    private String getNameAndCards(final Participant participant) {
        final List<String> cardNames = participant.getCardNames();

        return participant.getName() + COLON + String.join(COMMA, cardNames);
    }

    public void printAllCardsAndScore(final Participants participants) {
        System.out.printf(NEW_LINE);
        participants.getAll().forEach(this::printCardsAndScore);
    }

    private void printCardsAndScore(final Participant participant) {
        System.out.println(getNameAndCards(participant) + RESULT_DELIMITER + getScore(participant));
    }

    private String getScore(final Participant participant) {
        return String.valueOf(participant.getScore().getValue());
    }

    public void printDealerDrawCard() {
        System.out.println(NOTICE_TOTAL_SCORE_UNDER_SIXTEEN_MESSAGE);
    }

    public void printBettingResult(final Participants participants, final GameResult resultGame) {
        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayers();

        System.out.println(NEW_LINE + FINAL_RESULT_MESSAGE);
        printDealerResult(dealer, resultGame);
        for (final Participant player : players) {
            printPlayerResult(player, resultGame);
        }
    }

    private void printDealerResult(final Participant dealer, final GameResult resultGame) {
        final String dealerName = dealer.getName();
        final Betting dealerProfit = resultGame.getDealerResult();

        System.out.println(dealerName + COLON + dealerProfit.getValue());
    }

    private void printPlayerResult(final Participant player, final GameResult resultGame) {
        final String playerName = player.getName();
        final Betting money = resultGame.getPlayerResult(player);

        System.out.println(playerName + COLON + money.getValue());
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX_MESSAGE + e.getMessage());
    }
}
