package domain.participant;

import domain.card.Card;
import domain.result.Result;
import domain.result.Versus;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {

    private static final String NOT_DEALER_BLACK_JACK_SITUATION_ERROR_MESSAGE = "[Error] 딜러가 BlackJack 이 아닙니다.";
    private static final String CANT_FIND_PLAYER_ERROR_MESSAGE = "[Error] 플레이어를 찾을 수 없습니다.";

    private final List<Player> players;

    public Players(List<Name> names, List<List<Card>> initCards) {
        this.players = IntStream.range(0, names.size())
                .mapToObj(i -> new Player(names.get(i), initCards.get(i)))
                .collect(Collectors.toList());
    }

    public Player findByName(Name name) {
        return players.stream()
                .filter(player -> player.isNameMatch(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CANT_FIND_PLAYER_ERROR_MESSAGE));
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

    public List<Name> getNames() {
        return players.stream().map(Player::getName).collect(Collectors.toList());
    }

    public Result getResultAtDealerBlackJack(Dealer dealer) {
        validateDealerIsBlackJack(dealer);
        Map<Name, Versus> playerResult = new LinkedHashMap<>();
        players.forEach(player -> playerResult.put(player.getName(), player.compareAtDealerBlackJack()));
        return new Result(playerResult);
    }

    private void validateDealerIsBlackJack(Dealer dealer) {
        if (!dealer.isBlackJack()) {
            throw new IllegalStateException(NOT_DEALER_BLACK_JACK_SITUATION_ERROR_MESSAGE);
        }
    }

    public Result getResultAtFinal(Dealer dealer) {
        Map<Name, Versus> playerResult = new LinkedHashMap<>();
        players.forEach(player -> playerResult.put(player.getName(), player.compareAtFinal(dealer)));
        return new Result(playerResult);
    }
}
