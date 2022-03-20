package domain.participant;

import domain.card.Card;
import domain.result.WinOrLose;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private static final String CANT_FIND_PLAYER_ERROR_MESSAGE_FORMAT = "[Error] \"%s\" : 플레이어를 찾을 수 없습니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public Player findByName(Name name) {
        return players.stream()
                .filter(player -> player.isNameMatch(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(CANT_FIND_PLAYER_ERROR_MESSAGE_FORMAT, name.getName())
                ));
    }

    public void addCardByName(Name name, Card card) {
        findByName(name).addCard(card);
    }

    public String showHandByName(Name name) {
        return findByName(name).showHand();
    }

    public int getBestScoreByName(Name name) {
        return findByName(name).calculateBestScore();
    }

    public boolean isBustByName(Name name) {
        return findByName(name).isBust();
    }

    public boolean isNotAllBust() {
        long bustPlayerCount = players.stream()
                .filter(Player::isBust)
                .count();
        return bustPlayerCount != players.size();
    }

    public boolean isUpperBoundScoreByName(Name name) {
        return findByName(name).isUpperBoundScore();
    }

    public boolean isNeedToDrawByName(Name name) {
        return findByName(name).isNeedToDraw();
    }

    public boolean isBlackJackByName(Name name) {
        return findByName(name).isBlackJack();
    }

    public List<Name> getNames() {
        return players.stream().map(Player::getName).collect(Collectors.toList());
    }

    public Map<Name, WinOrLose> compareWinOrLose(Dealer dealer) {
        Map<Name, WinOrLose> playerResult = new LinkedHashMap<>();
        players.forEach(player -> playerResult.put(player.getName(), player.compareWinOrLose(dealer)));
        return playerResult;
    }
}
