package blackjack.domain.result;

import java.util.List;

import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.rule.BasicRule;

public abstract class Result {
    protected static final String LINE_BREAK = System.lineSeparator();

    protected ScoreBoard scoreBoard;
    protected Participant dealer;
    protected List<Participant> players;

    public Result(final Participants participants) {
        scoreBoard = new ScoreBoard(participants);
        dealer = participants.getDealer();
        players = participants.getPlayers();
    }

    protected void decideWinner(final Participant player) {
        if (BasicRule.isBusted(player.score())) {
            execute(player, Status.LOSE);
            return;
        }
        if (BasicRule.isBusted(dealer.score())) {
            execute(player, Status.WIN);
            return;
        }
        compareToDealer(player);
    }

    private void compareToDealer(final Participant player) {
        int dealerScore = scoreBoard.get(dealer);
        int playerScore = scoreBoard.get(player);
        if (dealerScore > playerScore) {
            execute(player, Status.LOSE);
        }
        if (dealerScore == playerScore) {
            execute(player, Status.DRAW);
        }
        if (dealerScore < playerScore) {
            execute(player, Status.WIN);
        }
    }

    abstract void execute(final Participant player, final Status playerStatus);
}
