package balckjack.domain;

import balckjack.strategy.CardPicker;
import balckjack.util.StringUtil;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Players {

    private static final java.util.regex.Pattern NAMES_FORMAT = Pattern.compile("[^,]+(,[^,]+)+");
    private final List<Player> players;

    public Players(String names) {
        validatePlayerNames(names);
        List<String> splitNames = StringUtil.split(names, ",");
        validateDuplicateName(splitNames);
        players = splitNames
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

    private void validateDuplicateName(List<String> names) {
        List<String> duplicateNames = names.stream()
            .filter(i -> Collections.frequency(names, i) > 1)
            .distinct()
            .collect(Collectors.toList());
        if (duplicateNames.size() > 0) {
            throw new IllegalArgumentException(
                String.format("중복되는 이름이 존재합니다. : %s", String.join(", ", duplicateNames)));
        }
    }

    public List<String> getPlayerNames() {
        return players.stream()
            .map(Player::getName)
            .map(Name::getValue)
            .collect(Collectors.toList());
    }

    public void initHit(CardPicker cardPicker) {
        players.forEach(player -> player.initHit(cardPicker));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
