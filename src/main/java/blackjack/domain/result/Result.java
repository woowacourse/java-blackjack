package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public enum Result {

    WIN("승리", ((dealer, player) -> (!player.isBusted() && player.isCloserToBestScore(dealer.calculateBestScore())) || (dealer
            .isBusted()) && !player.isBusted())),
    LOSE("패배", ((dealer, player) -> player.isBusted() || (!dealer.isBusted() && !player.isCloserToBestScore(dealer.calculateBestScore())))),
    TIE("무승부", ((dealer, player) -> dealer.calculateBestScore() == player.calculateBestScore())),
    Blackjack("블랙잭", ((dealer, player) -> !dealer.isBlackjack() && player.isBlackjack()))
    ;

    private final String result;
    private final BiPredicate<Dealer, Player> condition;

    Result(String result, BiPredicate<Dealer, Player> condition) {
        this.result = result;
        this.condition = condition;
    }

    public String getResult() {
        return result;
    }

    public static Result of(Dealer dealer, Player player) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(dealer, player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("판단할 수 없는 상황입니다."));
    }

    public static Map<Player, Result> createJudgeTable(Dealer dealer, Players players) {
        Map<Player, Result> resultTable = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            resultTable.put(player, Result.of(dealer, player));
        }
        return resultTable;
    }
}
