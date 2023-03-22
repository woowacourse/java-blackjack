package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.game.Deck;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private static final int MAX_PLAYER_NUMBER = 6;
    private static final int MIN_PLAYER_NUMBER = 1;
    private static final String OVER_RANGE_MESSAGE =
            "사용자 수는 " + MIN_PLAYER_NUMBER + " 이상 " + MAX_PLAYER_NUMBER + " 이하여야 합니다. 현재 : %s 명입니다";
    private static final String ERROR_NOT_EXIST_PLAYER = "존재하지 않는 플레이어입니다";

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> playerNames) {
        validatePlayerNames(playerNames);
        List<Player> players = createPlayers(playerNames);
        return new Players(players);
    }

    private static void validatePlayerNames(List<String> playerNames) {
        validateNull(playerNames);
        validatePlayerCount(playerNames);
        validateDuplicate(playerNames);
    }

    private static void validateNull(List<String> playerNames) {
        if (playerNames == null) {
            throw new IllegalArgumentException("사용자 이름이 입력되지 않았습니다");
        }
    }

    private static void validatePlayerCount(List<String> playerNames) {
        if (MAX_PLAYER_NUMBER < playerNames.size() || playerNames.size() < MIN_PLAYER_NUMBER) {
            throw new IllegalArgumentException(String.format(OVER_RANGE_MESSAGE, playerNames.size()));
        }
    }

    private static void validateDuplicate(List<String> playerNames) {
        if (Set.copyOf(playerNames)
                .size() != playerNames.size()) {
            throw new IllegalArgumentException("사용자의 이름이 중복됩니다.");
        }
    }

    private static List<Player> createPlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void placeBetsByName(String playerName, BettingMoney bettingMoney) {
        players.stream()
                .filter(player -> player.hasName(playerName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_EXIST_PLAYER))
                .bet(bettingMoney);

    }

    public void distributeInitialCards(Deck deck) {
        for (Player player : players) {
            player.drawInitialCards(deck.popCard(), deck.popCard());
        }
    }

    public List<String> findPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    private Player findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.hasName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_EXIST_PLAYER));
    }

    public boolean isPlayerDrawable(String playerName) {
        return findPlayerByName(playerName)
                .isDrawable();
    }

    public void drawCardOfPlayerByName(String playerName, Card card) {
        Player targetPlayer = findPlayerByName(playerName);

        targetPlayer.drawCard(card);
    }

    public void stayCardOfPlayerByName(String playerName) {
        findPlayerByName(playerName).stay();
    }

    public List<Card> findCardsOfPlayerByName(String playerName) {
        return findPlayerByName(playerName).getCards();
    }


    public Map<String, List<Card>> findPlayerNameToCards() {
        return players.stream()
                .collect(Collectors.toMap(Player::getName,
                        Player::getCards,
                        (x, y) -> y,
                        LinkedHashMap::new));
    }

    public Score findScoreOfPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.hasName(playerName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_EXIST_PLAYER))
                .currentScore();
    }

    public Map<String, Money> findRevenueOfPlayers(Dealer dealer) {
        Map<String, Money> result = new LinkedHashMap<>();
        for (Player player : players) {
            ResultType resultOfPlayer = player.findResult(dealer);
            double profit = resultOfPlayer.getProfitRateOfPlayer();
            Money earnings = player.getBettingMoney()
                    .multiple(profit);
            result.put(player.getName(), earnings);
        }
        return result;
    }
}
