package blackjack.domain.game;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.domain.user.name.UserName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoard {

    private final Map<User, Integer> scoreBoard = new HashMap<>();

    public ScoreBoard(final Dealer dealer, final Players players) {
        initScoreBoard(dealer, players);
    }

    private void initScoreBoard(final Dealer dealer, final Players players) {
        scoreBoard.put(dealer, ScoreReferee.calculateScore(dealer.showCards()));
        players.getPlayers().forEach(player -> scoreBoard.put(player, ScoreReferee.calculateScore(player.showCards())));
    }

    public void writeScore(final User user, final int score) {
        scoreBoard.put(user, score);
    }

    public List<User> getParticipants() {
        return new ArrayList<>(scoreBoard.keySet());
    }

    private User getUserByName(final UserName queryName) {
        return scoreBoard.keySet().stream()
                .filter(user -> user.getName().equals(queryName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름을 찾을 수 없습니다."));
    }

    public int get(final UserName name) {
        validateParticipants(name);
        return scoreBoard.get(getUserByName(name));
    }

    private void validateParticipants(final UserName name) {
        if (!scoreBoard.containsKey(getUserByName(name))) {
            throw new IllegalArgumentException("참가한 유저의 점수만 검색할 수 있습니다" + System.lineSeparator() + "이름:" + name);
        }
    }
}
