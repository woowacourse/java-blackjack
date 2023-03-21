package domain.user;

import domain.card.Card;
import domain.card.GameDeck;
import domain.card.Score;
import domain.dto.UserDto;
import exception.DuplicatedPlayerNameException;
import exception.InputPlayerNameSizeException;
import exception.NoPlayerHasThatNameException;
import exception.ThereIsNoPlayerUnfinishedException;

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
            throw new DuplicatedPlayerNameException();
        }
    }

    private void validateNamesSize(List<String> names) {
        if (names.size() > MAX_PLAYER_SIZE) {
            throw new InputPlayerNameSizeException(MAX_PLAYER_SIZE);
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
                .orElseThrow(NoPlayerHasThatNameException::new);
    }

    public UserDto getReadyPlayerDto() {
        return players.stream()
                .filter(player -> !player.hasResult())
                .map(Player::getUserDto)
                .findFirst()
                .orElseThrow(ThereIsNoPlayerUnfinishedException::new);
    }

    public UserDto getPlayerDtoByName(Name name) {
        return players.stream()
                .filter(player -> player.isName(name))
                .map(Player::getUserDto)
                .findFirst()
                .orElseThrow(NoPlayerHasThatNameException::new);
    }

    public List<UserDto> getAllPlayerDtos() {
        List<UserDto> playerDtos = new ArrayList<>();
        players.forEach(player -> playerDtos.add(player.getUserDto()));
        return playerDtos;
    }
}
