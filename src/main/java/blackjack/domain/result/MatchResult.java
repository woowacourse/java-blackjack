package blackjack.domain.result;

import blackjack.domain.participant.Player;

public enum MatchResult {
    WIN("승"), LOSE("패"), TIE("무");

    private final String name;

    MatchResult(String name) {
        this.name = name;
    }

    public static MatchResult match(Player player, int dealerCardSum) {
        if (player.isBust() || dealerCardSum > player.getHandTotal()) {
            return MatchResult.LOSE;
        }

        if (dealerCardSum == player.getHandTotal()) {
            return MatchResult.TIE;
        }

        if (dealerCardSum < player.getHandTotal()) {
            return MatchResult.WIN;
        }

        throw new IllegalArgumentException("입력값이 잘못되었습니다. 승무패를 계산할 수 없습니다.");
    }

    public String getName() {
        return name;
    }

    public MatchResult reverse() {
        if (WIN == this) {
            return LOSE;
        }
        if (LOSE == this) {
            return WIN;
        }
        return this;
    }
}
