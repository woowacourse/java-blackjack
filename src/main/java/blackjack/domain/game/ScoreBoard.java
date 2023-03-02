package blackjack.domain.game;

import blackjack.domain.user.name.DealerName;
import blackjack.domain.user.name.UserName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoard {

    private final Map<UserName, Integer> scoreBoard = new HashMap<>();

    ScoreBoard(final DealerName dealerName, final List<UserName> playerNames) {
        initScoreBoard(dealerName, playerNames);
    }

    private void initScoreBoard(final DealerName dealerName, final List<UserName> playerNames) {
        scoreBoard.put(dealerName, 0);
        playerNames.forEach(name -> scoreBoard.put(name, 0));
    }

    public void writeScore(final UserName user, final int score) {
        scoreBoard.put(user, score);
    }

    public int get(final UserName name) {

        validateParticipants(name);
        return scoreBoard.get(name);
    }

    private void validateParticipants(final UserName name) {
        if (!scoreBoard.containsKey(name)) {
            throw new IllegalArgumentException("참가한 유저의 점수만 검색할 수 있습니다" + System.lineSeparator() + "이름:" + name);
        }
    }
}
