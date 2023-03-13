package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.result.CardResult;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Players {

    private static final String PLAYER_HAS_DUPLICATE_EXCEPTION_MESSAGE = "플레이어의 이름은 중복일 수 없습니다.";
    private static final String NUMBER_OF_PLAYER_OVER_LIMIT_EXCEPTION_MESSAGE = "플레이어의 이름은 5개까지만 입력해야 합니다.";
    private static final String NOT_CONTAIN_USER_BY_NAME_EXCEPTION_MESSAGE = "해당 이름의 유저를 찾을 수 없습니다.";
    private static final int NUMBER_OF_PLAYER_LIMIT = 5;

    private final List<Player> players;

    public Players(final List<String> playerNames, final Queue<CardGroup> firstCardGroups) {
        validateNumberOfPlayerOverLimit(playerNames);
        validatePlayerHasDuplicate(playerNames);
        this.players = playerNames.stream()
                .map(name -> new Player(name, firstCardGroups.poll()))
                .collect(Collectors.toUnmodifiableList());
    }

    private void validatePlayerHasDuplicate(final List<String> playerNames) {
        if (playerNames.stream().distinct().count() != playerNames.size()) {
            throw new IllegalArgumentException(PLAYER_HAS_DUPLICATE_EXCEPTION_MESSAGE);
        }
    }

    private void validateNumberOfPlayerOverLimit(final List<String> playerNames) {
        if (playerNames.size() > NUMBER_OF_PLAYER_LIMIT) {
            throw new IllegalArgumentException(NUMBER_OF_PLAYER_OVER_LIMIT_EXCEPTION_MESSAGE);
        }
    }

    public Map<Name, CardGroup> getFirstOpenCardGroup() {
        final Map<Name, CardGroup> firstOpenCardGroup = new LinkedHashMap<>();
        players.forEach(player ->
                firstOpenCardGroup.put(player.getName(), player.getFirstOpenCardGroup()));
        return Collections.unmodifiableMap(firstOpenCardGroup);
    }


    public CardGroup getCardGroupBy(final Name name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_CONTAIN_USER_BY_NAME_EXCEPTION_MESSAGE))
                .getCardGroups();
    }

    public List<Name> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void drawCard(final Name name, final Card card) {
        players.stream()
                .filter(player -> player.isSameName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_CONTAIN_USER_BY_NAME_EXCEPTION_MESSAGE))
                .drawCard(card);
    }

    public Map<Name, CardResult> getPlayerNameAndCardResults() {
        final Map<Name, CardResult> playerNameAndCardResults = new LinkedHashMap<>();
        players.forEach(player -> playerNameAndCardResults.put(player.getName(),
                new CardResult(player.getCardGroups(), player.getScore())));
        return Collections.unmodifiableMap(playerNameAndCardResults);
    }

    public Map<Name, Double> getPlayerNameAndProfitRates(final Dealer dealer) {
        final Map<Name, Double> playerNameAndProfitRates = new LinkedHashMap<>();
        players.forEach(player -> playerNameAndProfitRates.put(player.getName(),
                player.calculateProfitRate(dealer)));
        return Collections.unmodifiableMap(playerNameAndProfitRates);
    }

    public boolean isDrawable(final Name playerName) {
        return players.stream()
                .filter(player -> player.isSameName(playerName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_CONTAIN_USER_BY_NAME_EXCEPTION_MESSAGE))
                .isDrawable();
    }
}
