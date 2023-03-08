package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Referee {

    private final List<Result> playersResult = new ArrayList<>();
    private final List<Integer> dealerResult = new ArrayList<>();

    public Referee(Participants participants) {
        judgePlayersResult(participants);
        judgeDealerResult();
    }

    private void judgePlayersResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            if (player.isBust()) {
                playersResult.add(new Result(player.getName(), WinResult.LOSE));
                continue;
            }
            playersResult.add(new Result(player.getName(), dealer.judge(player)));
        }
    }

    private void judgeDealerResult() {
        addResult(WinResult.LOSE);
        addResult(WinResult.PUSH);
        addResult(WinResult.WIN);
    }

    private void addResult(WinResult winResult) {
        int count = (int) playersResult.stream()
                .map(Result::getWinResult)
                .filter(result -> result == winResult)
                .count();
        dealerResult.add(count);
    }

    public List<Result> getPlayersResult() {
        return new ArrayList<>(playersResult);
    }

    public List<Integer> getDealerResult() {
        return new ArrayList<>(dealerResult);
    }
}
