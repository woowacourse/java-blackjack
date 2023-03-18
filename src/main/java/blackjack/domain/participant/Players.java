package blackjack.domain.participant;

import blackjack.domain.game.Deck;
import blackjack.util.StringUtil;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Players {

    private static final String DELIMITER = ",";

    private static final java.util.regex.Pattern NAMES_FORMAT = Pattern.compile("[^,]+(,[^,]+)+");
    private final List<Player> players;

    public Players(String names) {
        validatePlayerNames(names);

        players = StringUtil.split(names, DELIMITER)
            .stream()
            .map(Player::new)
            .collect(Collectors.toList());
    }

    private void validatePlayerNames(String names) {
        if (isNull(names)) {
            throw new IllegalArgumentException("이름에 아무것도 들어오지 않았습니다.");
        }
        if (isFormat(names)) {
            throw new IllegalArgumentException("이름이 형식과 맞지 않습니다");
        }
    }

    private boolean isNull(String names) {
        return names == null;
    }

    private boolean isFormat(String names) {
        return !NAMES_FORMAT.matcher(names).matches();
    }

    public void initHit(Deck deck) {
        players.forEach((player) -> player.initHit(deck));
    }

    public void bet(List<Integer> amounts) {
        for (int index = 0; index < players.size(); index++) {
            players.get(index).bet(amounts.get(index));
        }
    }

    public List<Integer> getAmounts() {
        return players.stream()
                .map(player -> player.getAmount().getValue())
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}
