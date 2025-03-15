package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetSystem {

    private final Map<Gamer, Integer> beforeBetRecord;
    private final Map<Gamer, Integer> afterBetRecord;

    public BetSystem() {
        this.beforeBetRecord = new LinkedHashMap<>();
        this.afterBetRecord = new LinkedHashMap<>();
        this.afterBetRecord.put(new Dealer(), 0);
    }

    public void betting(final Player player, final int betAmount) {
        validateBetAmount(betAmount);
        beforeBetRecord.put(player, betAmount);
        afterBetRecord.put(player, 0);
    }

    private void validateBetAmount(final int betAmount) {
        if (betAmount < 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0원 이상이어야 합니다.");
        }
    }

    public Map<Gamer, Integer> calculateProfit(final Dealer dealer, final List<Player> players) {
        for (Player player : players) {
            MatchResult dealerMatchResult = dealer.getMatchResult(player);
            if (dealerMatchResult.equals(MatchResult.LOSE)) {
                int betAmount = beforeBetRecord.get(player);

                Integer dealerBetAmount = afterBetRecord.get(dealer);

                dealerBetAmount = dealerBetAmount - betAmount;

                afterBetRecord.put(player, betAmount);
                afterBetRecord.put(dealer, dealerBetAmount);
            }

            if (dealerMatchResult.equals(MatchResult.WIN)) {

                int betAmount = beforeBetRecord.get(player);

                Integer dealerBetAmount = afterBetRecord.get(dealer);
                dealerBetAmount = dealerBetAmount + betAmount;

                afterBetRecord.put(dealer, dealerBetAmount);
                afterBetRecord.put(player, -betAmount);
            }

            //플레이어가 블랙잭일때
            if (dealerMatchResult.equals(MatchResult.BLACKJACK_LOSE)) {
                //플레이어가 블랙잭이면 딜러는 플레이어에게 베팅금액의 1.5배를 줘야 한다.
                int betAmount = beforeBetRecord.get(player);
                int blackjackBetAmount = Math.toIntExact(Math.round(betAmount * 1.5));
                betAmount += blackjackBetAmount;

                //딜러는 자신의 금액에서 딜러에게 준 만큼 뺴야한다.
                int dealerAmount = afterBetRecord.get(dealer);
                dealerAmount -= blackjackBetAmount;

                afterBetRecord.put(player, betAmount);
                afterBetRecord.put(dealer, dealerAmount);
            }
        }

        return afterBetRecord;
    }
}
