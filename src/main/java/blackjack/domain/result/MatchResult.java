package blackjack.domain.result;

import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackJack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.State;
import blackjack.domain.state.Stay;

import java.util.Arrays;

public enum MatchResult {
    WIN("승") {
        @Override
        boolean match(State playerState, State dealerState) {
            if (playerState instanceof BlackJack && !(dealerState instanceof BlackJack)) {
                return true;
            }
            if (playerState instanceof Stay && dealerState instanceof Bust) {
                return true;
            }
            Cards playerCard = playerState.getCards();
            Cards dealerCard = dealerState.getCards();
            return playerState instanceof Stay && playerCard.isWin(dealerCard);
        }
    },
    LOSE("패") {
        @Override
        boolean match(State playerState, State dealerState) {
            if (dealerState instanceof BlackJack && !(playerState instanceof BlackJack)) {
                return true;
            }
            if (dealerState instanceof Stay && playerState instanceof Bust) {
                return true;
            }
            Cards playerCard = playerState.getCards();
            Cards dealerCard = dealerState.getCards();
            return dealerState instanceof Stay && dealerCard.isWin(playerCard);
        }
    },
    DRAW("무") {
        @Override
        boolean match(State playerState, State dealerState) {
            if (playerState instanceof Bust && dealerState instanceof Bust) {
                return true;
            }
            Cards playerCard = playerState.getCards();
            Cards dealerCard = dealerState.getCards();
            return playerCard.isDraw(dealerCard);
        }
    };

    private final String result;

    MatchResult(String result) {
        this.result = result;
    }

    abstract boolean match(State playerState, State dealerState);

    public static MatchResult getPlayerMatchResult(State playerState, State dealerState) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.match(playerState, dealerState))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static MatchResult getDealerMatchResultByPlayer(MatchResult matchResult) {
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
