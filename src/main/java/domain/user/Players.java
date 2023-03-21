package domain.user;

import domain.card.Card;
import domain.card.GameDeck;
import domain.card.Score;
import domain.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final int MAX_PLAYER_SIZE = 5;

    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names) {
        validateDuplicatedName(names);
        validateNamesSize(names);

        names.forEach(name -> players.add(new Player(name)));
    }

    private void validateDuplicatedName(List<String> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private void validateNamesSize(List<String> names) {
        if (names.size() > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 최대 5명입니다.");
        }
    }

    public void setUpGame(GameDeck gameDeck) {
        players.forEach(player -> player.receiveCards(gameDeck.drawForFirstTurn()));
    }

    public boolean hasReadyPlayer() {
        return players.stream()
                .anyMatch(player -> !player.hasResult());
    }

    public boolean hasPlayerResult(Name name) {
        return findPlayerByName(name).hasResult();
    }

    public void drawOneMoreCard(Name name, Card card) {
        findPlayerByName(name).receiveCard(card);
    }

    public void doStay(Name name) {
        findPlayerByName(name).doStay();
    }

    public List<Card> getCards(Name name) {
        return findPlayerByName(name).getCards();
    }

    public Score getScore(Name name) {
        return findPlayerByName(name).getScore();
    }

    public double getProfitRatio(Name name) {
        return findPlayerByName(name).getProfitRatio();
    }

    public List<Name> getAllNames() {
        return players.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public Player findPlayerByName(Name name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 User 이름입니다."));
    }

    public UserDto getReadyPlayerDto() {
        return players.stream()
                .filter(player -> !player.hasResult())
                .map(Player::getUserDto)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 게임이 미완료된 플레이어가 없습니다."));
    }

    public UserDto getPlayerDtoByName(Name name) {
        return players.stream()
                .filter(player -> player.isName(name))
                .map(Player::getUserDto)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 플레이어 이름이 검색되었습니다."));
    }

    public List<UserDto> getAllPlayerDtos() {
        List<UserDto> playerDtos = new ArrayList<>();
        players.forEach(player -> playerDtos.add(player.getUserDto()));
        return playerDtos;
    }
}
