package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.Arrays;

public enum MatchResult {

    WIN("승"){
        @Override
        boolean match(Player player, Dealer dealer) {
            if(player.isBlackjack() && !dealer.isBlackjack()){
                return true;
            }
            if(dealer.isBust()){
                return true;
            }
            return player.isStay() && player.score() > dealer.score();
        }
    },
    LOSE("패"){
        @Override
        boolean match(Player player, Dealer dealer) {
            if(player.isBust()){
                return true;
            }
            if(!player.isBlackjack() && dealer.isBlackjack()){
                return true;
            }
            return dealer.isStay() && dealer.score() > player.score();
        }
    },
    DRAW("무"){
        @Override
        boolean match(Player player, Dealer dealer) {
            if(player.isBlackjack() && dealer.isBlackjack()){
                return true;
            }
            return player.score() == dealer.score();
        }
    };

    private final String result;
    abstract boolean match(Player player, Dealer dealer);

    MatchResult(String result) {
        this.result = result;
    }

    public static MatchResult matchPlayerAndDealer(Player player, Dealer dealer) {
        return Arrays.stream(values())
              .filter(matchResult -> matchResult.match(player, dealer))
              .findAny()
              .orElseThrow(IllegalArgumentException::new);
    }

    public static MatchResult reverseMatchResult(MatchResult matchResult) {
        if (matchResult.equals(MatchResult.WIN)) {
            return MatchResult.LOSE;
        }
        if (matchResult.equals(MatchResult.LOSE)) {
            return MatchResult.WIN;
        }
        return MatchResult.DRAW;
    }

    public String getResult() {
        return result;
    }
}
