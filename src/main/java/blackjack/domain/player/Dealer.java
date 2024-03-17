package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.cardgame.Status;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    private final Map<Player, Integer> playerBetting = new LinkedHashMap<>();

    public Dealer() {
        super(NAME);
    }

    public boolean isMoreCardNeeded() {
        return this.hand.calculateScore() <= HIT_THRESHOLD;
    }

    public Card getFirstCard() {
        try {
            return hand.getAllCards().get(0);
        } catch (IndexOutOfBoundsException entry) {
            throw new RuntimeException("[ERROR] 딜러가 카드를 갖고 있지 않습니다.");
        }
    }

    // TODO: Dealer가 너무 많은 책임을 갖지는 않는지?
    public void bet(final Player player, final int bettingAmount) {
        playerBetting.put(player, bettingAmount);
    }

    /**
     * TODO: 딜러의 수익은 어떻게 계산할지???
     *      Dealder의 필드로 Map<Player, Integer> 를 가지도록 변경하고, 메서드로 제공?
     */

    /**
     * TODO: 반드시 Dealer에게 먼저 bet()을 한 뒤에 호출해야 함. bet()을 하지 않는다면 비어있게 됨
     */
    public Map<Player, Integer> findPlayerProfits() {
        return playerBetting.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> findBettingResult(entry.getKey(), entry.getValue())
                ));
    }

    private int findBettingResult(final Player player, final int bettingAmount) {
        final Status status = judgePlayerStatus(player);

        if (Status.WIN.equals(status)) {
            return bettingAmount;
        }
        if (Status.PUSH.equals(status)) {
            return 0;
        }
        if (Status.LOSE.equals(status)) {
            return -bettingAmount;
        }
        if (Status.BLACKJACK.equals(status)) {
            return bettingAmount + bettingAmount / 2;
        }

        throw new IllegalStateException("[ERROR] 유효하지 않은 경우의 수 입니다.");
    }

    private Status judgePlayerStatus(final Player player) {
        if (player.isBust()) {
            return Status.LOSE;
        }
        if (this.isBust()) {
            return Status.WIN;
        }
        if (this.getScore() == player.getScore()) {
            return Status.PUSH;
        }
        if (player.isBlackjack()) {
            return Status.BLACKJACK;
        }
        if (this.getScore() < player.getScore()) {
            return Status.WIN;
        }
        return Status.LOSE;
    }
}
