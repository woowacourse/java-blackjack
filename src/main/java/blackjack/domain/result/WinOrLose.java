package blackjack.domain.result;

import blackjack.domain.participant.Player;

public enum WinOrLose {
    WIN("승"), LOSE("패"), TIE("무");

    private final String name;

    WinOrLose(String name) {
        this.name = name;
    }

    public static WinOrLose match(Player player, int dealerCardSum) {
        if (player.isBust() || dealerCardSum > player.getCardSum()) {
            return WinOrLose.LOSE;
        }

        if (dealerCardSum == player.getCardSum()) {
            return WinOrLose.TIE;
        }

        if (dealerCardSum < player.getCardSum()) {
            return WinOrLose.WIN;
        }

        throw new IllegalArgumentException("입력값이 잘못되었습니다. 승무패를 계산할 수 없습니다.");
    }

    public String getName() {
        return name;
    }

    public WinOrLose reverse() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return this;
    }
}
