package domain;

import domain.participant.Dealer;
import domain.participant.Participant;

public class Referee {

    private final GameStatistics gameStatistics;

    public Referee() {
        this.gameStatistics = new GameStatistics();
    }

    public GameStatistics judge(Participants participants) {
        participants.playDealerTurn(dealer ->
                participants.playPlayerTurn(player -> {
                    judgePlayerResult(dealer, player);
                    judgeDealerResult(dealer, player);
                })
        );
        return gameStatistics;
    }

    private void judgePlayerResult(Dealer dealer, Participant player) {
        GameResult playerResult = GameResult.judge(dealer.getScore(), player.getScore());
        gameStatistics.addPlayerResult(player, playerResult);
    }

    private void judgeDealerResult(Dealer dealer, Participant player) {
        GameResult dealerResult = GameResult.judge(player.getScore(), dealer.getScore());
        gameStatistics.addDealerResult(dealerResult);
    }
}
