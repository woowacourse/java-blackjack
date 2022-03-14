package domain.participant;

import domain.card.Card;
import domain.result.Versus;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {

    private static final String NOT_ENOUGH_CARDS_FOR_INIT_PLAYER_ERROR_MESSAGE = "[Error] 초기화할 카드가 모자랍니다.";
    private static final String NOT_DEALER_BLACK_JACK_SITUATION_ERROR_MESSAGE = "[Error] 딜러가 BlackJack 이 아닙니다.";
    private static final String CANT_FIND_PLAYER_ERROR_MESSAGE = "[Error] 플레이어를 찾을 수 없습니다.";
    private static final int SINGLE_ELEMENT = 1;
    private static final int FIND_FIRST_INDEX = 0;

    private final List<Player> players;

    public Players(List<Name> names, List<List<Card>> initCards) {
        validateInitCardsForPlayers(names, initCards);
        this.players = IntStream.range(0, names.size())
                .mapToObj(i -> new Player(names.get(i), initCards.get(i)))
                .collect(Collectors.toList());
    }

    private void validateInitCardsForPlayers(List<Name> names, List<List<Card>> initCards) {
        if (names.size() > initCards.size()) {
            throw new IllegalArgumentException(NOT_ENOUGH_CARDS_FOR_INIT_PLAYER_ERROR_MESSAGE);
        }
    }

    public Player findByName(Name name) {
        List<Player> matchNamePlayers = players.stream()
                .filter(player -> player.isNameMatch(name))
                .collect(Collectors.toList());
        validateNameForFindByName(matchNamePlayers);
        return matchNamePlayers.get(FIND_FIRST_INDEX);
    }

    private void validateNameForFindByName(List<Player> matchNamePlayers) {
        if (matchNamePlayers.size() != SINGLE_ELEMENT) {
            throw new IllegalArgumentException(CANT_FIND_PLAYER_ERROR_MESSAGE);
        }
    }

    public List<Name> getNames() {
        return players.stream().map(Player::getName).collect(Collectors.toList());
    }

    public void addCardByName(Name name, Card card) {
        findByName(name).addCard(card);
    }

    public String showHandByName(Name name) {
        return findByName(name).showHand();
    }

    public int getBestScoreByName(Name name) {
        return findByName(name).getBestScore();
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

    public boolean isBlackJackByName(Name name) {
        return findByName(name).isBlackJack();
    }

    public boolean isMaxScoreByName(Name name) {
        return findByName(name).isMaxScore();
    }

    public Map<Name, Versus> getResultAtDealerBlackJack(Dealer dealer) {
        validateDealerIsBlackJack(dealer);
        Map<Name, Versus> playerResult = new LinkedHashMap<>();
        players.forEach(player -> playerResult.put(player.getName(), player.compareAtDealerBlackJack()));
        return playerResult;
    }

    private void validateDealerIsBlackJack(Dealer dealer) {
        if (!dealer.isBlackJack()) {
            throw new IllegalStateException(NOT_DEALER_BLACK_JACK_SITUATION_ERROR_MESSAGE);
        }
    }

    public Map<Name, Versus> getResultAtFinal(Dealer dealer) {
        Map<Name, Versus> playerResult = new LinkedHashMap<>();
        players.forEach(player -> playerResult.put(player.getName(), player.compareAtFinal(dealer)));
        return playerResult;
    }
}
