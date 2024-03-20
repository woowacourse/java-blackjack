package participant.player;

import card.CardDeck;
import card.Hand;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import participant.dealer.Dealer;
import resultJudge.GameResult;
import resultJudge.ResultJudge;

public class Players {

    private static final int MINIMUM_PLAYER_RANGE = 2;
    private static final int MAXIMUM_PLAYER_RANGE = 8;

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<Name> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public void playBlackJackGame(Predicate<Name> isHitFunction, CardDeck cardDeck,
                                  BiConsumer<Name, Hand> printStatusFunction) {
        for (Player player : players) {
            play(isHitFunction, cardDeck, printStatusFunction, player);
        }
    }

    private void play(Predicate<Name> isHitFunction, CardDeck cardDeck, BiConsumer<Name, Hand> printStatusFunction,
                      Player player) {
        while (!player.isBust() && isHitFunction.test(player.getName())) {
            player.hit(cardDeck.pickCard());

            printStatusFunction.accept(player.getName(), player.getCards());
        }
    }

    public Map<Name, Integer> getPlayerResults(Dealer dealer) {
        Map<Name, Integer> playerResult = new LinkedHashMap<>();

        for (Player player : players) {
            GameResult gameResult = ResultJudge.judge(player, dealer);
            BetMoney betMoney = player.getBetMoney();

            playerResult.put(player.getName(), betMoney.betMoneyResult(gameResult.getMoneyReturnPercent()));
        }

        return playerResult;
    }

    private void validate(List<Player> players) {
        validatePlayerCountRange(players);
        validateHasDuplicateName(players);
    }

    private void validateHasDuplicateName(List<Player> players) {
        int uniqueNameCount = countPlayerUniqueName(getPlayerNames(players));

        if (players.size() != uniqueNameCount) {
            throw new IllegalArgumentException("참가자는 중복된 이름을 가질 수 없습니다.");
        }
    }

    private List<String> getPlayerNames(List<Player> players) {
        return players.stream()
                .map(player -> player.getName().getValue())
                .toList();
    }

    private int countPlayerUniqueName(List<String> players) {
        return new HashSet<>(players).size();
    }

    private void validatePlayerCountRange(List<Player> players) {
        if (players.size() < MINIMUM_PLAYER_RANGE || MAXIMUM_PLAYER_RANGE < players.size()) {
            throw new IllegalArgumentException(
                    "참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
        }
    }
}
