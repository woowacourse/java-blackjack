package blackjack.dto;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public record BettingMoneyDto(Map<String, Integer> bettingMoneyResult) {
    public static BettingMoneyDto from(Dealer dealer, Players players) {
        Map<String, Integer> bettingMoneyResult = new LinkedHashMap<>();
        bettingMoneyResult.put(dealer.getName().getValue(), dealer.getMoney().getValue());
        for (int i = 0; i < players.size(); i++) {
            bettingMoneyResult.put(players.getOnePlayerName(i).getValue(), players.getOnePlayerMoney(i).getValue());
        }
        return new BettingMoneyDto(bettingMoneyResult);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> nameAndMoney : bettingMoneyResult.entrySet()) {
            sb.append(nameAndMoney.getKey()).append(": ").append(nameAndMoney.getValue()).append("\n");
        }
        return sb.toString();
    }
}
