package balckjack.domain;

import balckjack.util.StringUtil;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Players {

    private static final java.util.regex.Pattern NAMES_FORMAT = Pattern.compile("[^,]+(,[^,]+)+");
    private final List<Player> players;

    public Players(String names) {
        validatePlayerNames(names);

        players = StringUtil.split(names, ",")
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
}
