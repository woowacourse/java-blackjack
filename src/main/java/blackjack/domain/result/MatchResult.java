package blackjack.domain.result;

import blackjack.domain.participant.Player;

public enum MatchResult {
    WIN("승"), LOSE("패"), TIE("무");

    private final String name;

    MatchResult(String name) {
        this.name = name;
    }

    // todo 플레이어가 가지고 있어야 하나?? dealerCardSum 만 넘겨주면 되자나?
    public static MatchResult match(Player player, int dealerCardSum) {
        // todo dealerCardSum > player.getHandTotal() 플레이어에게 메시지 보내기
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
