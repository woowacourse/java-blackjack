package blackjack.domain.result;

import blackjack.domain.participant.Player;

public enum WinOrLose {
    WIN("승"), LOSE("패"), TIE("무");

    private final String name;

    WinOrLose(String name) {
        this.name = name;
    }

    public static WinOrLose match(Player player, int dealerCardSum) {
        if (player.isBust() || dealerCardSum > player.sumCard()) {
            return WinOrLose.LOSE;
        }

        if (dealerCardSum == player.sumCard()) {
            return WinOrLose.TIE;
        }

        if (dealerCardSum < player.sumCard()) {
            return WinOrLose.WIN;
        }

        throw new IllegalArgumentException("입력값이 잘못되었습니다. 승무패를 계산할 수 없습니다.");
    }

    public String getName() {
        return name;
    }

    public WinOrLose reverse() {
        if (WIN == this) {
            return LOSE;
        }
        if (LOSE == this) {
            return WIN;
        }
        return this;
    }
}
