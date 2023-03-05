package blackjack.view;

import blackjack.domain.game.ResultGame;
import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.List;

public class OutputView {

    private static final String NEW_LINE = "\n";
    private static final String COMMA = ", ";
    private static final String COLON = " : ";
    private static final String BLANK = " ";
    private static final String DEALER_AND_PLAYER_DELIMITER = "와 ";
    private static final String GIVE_TWO_CARD_MESSAGE = "에게 두 장을 나누었습니다.";
    private static final String NOTICE_TOTAL_SCORE_UNDER_SIXTEEN_MESSAGE = "는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String RESULT_DELIMITER = " - 결과 : ";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String WIN_MESSAGE = "승";
    private static final String TIE_MESSAGE = "무";
    private static final String LOSE_MESSAGE = "패";

    public void printHandOutStart(final Participants participants) {
        printSplitMessage(participants);
        printAllParticipantCard(participants);
    }

    private void printSplitMessage(final Participants participants) {
        final String dealerName = participants.getDealerName();
        final List<String> playerNames = participants.getPlayerNames();

        String message = dealerName + DEALER_AND_PLAYER_DELIMITER +
                String.join(COMMA, playerNames) + GIVE_TWO_CARD_MESSAGE;
        System.out.println(NEW_LINE + message);
    }

    public void printAllParticipantCard(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        printParticipantCard(dealer, dealer.getOneCard());
        for (final Player player : players) {
            printParticipantCard(player);
        }
    }

    public void printParticipantCard(final Participant participant) {
        final List<String> cardNames = participant.getCardNames();

        printParticipantCard(participant, cardNames);
    }

    public void printParticipantCard(final Participant participant, final List<String> cards) {
        final String name = participant.getName();

        System.out.print(NEW_LINE + name + COLON + String.join(COMMA, cards));
    }

    public void printDealerDrawCard(final Participant participant) {
        String name = participant.getName();

        System.out.println(NEW_LINE + name + NOTICE_TOTAL_SCORE_UNDER_SIXTEEN_MESSAGE);
    }

    public void printScore(final Participant participant) {
        int score = participant.getTotalScore();

        System.out.printf(RESULT_DELIMITER + score);
    }

    public void printResult(final Participants participants, final ResultGame resultGame) {
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerResult(dealer, resultGame);
        for (Player player : players) {
            printPlayerResult(player, resultGame);
        }
    }

    private void printDealerResult(final Dealer dealer, final ResultGame resultGame) {
        final String dealerName = dealer.getName();
        final int win = resultGame.getDealerCount(WinTieLose.WIN);
        final int tie = resultGame.getDealerCount(WinTieLose.TIE);
        final int lose = resultGame.getDealerCount(WinTieLose.LOSE);

        System.out.println(dealerName + COLON
                + win + WIN_MESSAGE + BLANK
                + tie + TIE_MESSAGE + BLANK
                + lose + LOSE_MESSAGE);
    }

    private void printPlayerResult(final Player player, final ResultGame resultGame) {
        final String playerName = player.getName();
        final String result = resultGame.getPlayerResult(player).getValue();

        System.out.println(playerName + COLON + result);
    }

    public void printAllCardsAndScores(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        printParticipantCardsAndScores(dealer);
        for (Player player : players) {
            printParticipantCardsAndScores(player);
        }
    }

    private void printParticipantCardsAndScores(final Participant participant) {
        printParticipantCard(participant);
        printScore(participant);
    }
}
