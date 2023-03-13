package blackjack.domain;

import blackjack.strategy.CardPicker;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Players {

    private static final java.util.regex.Pattern NAMES_FORMAT = Pattern.compile("[^,]+(,[^,]+)+");
    private final List<Player> players;

    public Players(String names) {
        validatePlayerNames(names);
        List<String> splitNames = Arrays.stream(names.split(",")).collect(Collectors.toList());
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
        Set<String> distinctNames = new HashSet<>();
        Set<String> duplicateNames = names.stream()
            .filter(name -> !distinctNames.add(name))
            .collect(Collectors.toSet());
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

    public List<CardDeck> getPlayersDeck() {
        return players.stream()
            .map(Player::getCardDeck)
            .collect(Collectors.toList());
    }

    public void initHit(CardPicker cardPicker) {
        players.forEach(player -> player.distributeCards(cardPicker));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
