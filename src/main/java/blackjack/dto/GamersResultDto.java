package blackjack.dto;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BlackJackResult;
import blackjack.domain.result.PlayerResultMatcher;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class GamersResultDto {

    private final Map<BlackJackResult, Integer> dealerResult;
    private final Map<Player, BlackJackResult> playersResult;

    protected GamersResultDto(Map<BlackJackResult, Integer> dealerResult, Map<Player, BlackJackResult> playersResult) {
        this.dealerResult = dealerResult;
        this.playersResult = playersResult;
    }

    public Map<BlackJackResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<Player, BlackJackResult> getPlayersResult() {
        return playersResult;
    }
}