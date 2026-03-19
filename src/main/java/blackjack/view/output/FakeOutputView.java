package blackjack.view.output;

import blackjack.dto.CardResult;
import blackjack.dto.ProfitResult;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class FakeOutputView implements OutputView {

    private final List<String> logs = new ArrayList<>();

    @Override
    public void printInitialSettingsDoneMessage(String dealerName, List<String> playersName) {
        logs.add("");
        logs.add(dealerName + "와 " + stringJoinWithComma(playersName) + "에게 2장을 나누었습니다.");
    }

    @Override
    public void printCards(String playerName, List<String> cards) {
        logs.add(playerName + "카드: " + stringJoinWithComma(cards));
    }

    @Override
    public void printCardsWithScore(CardResult cardResult) {
        logs.add(cardResult.participantName()
                + "카드: " + stringJoinWithComma(cardResult.cardsName())
                + " - 결과: " + cardResult.score());
    }

    @Override
    public void printGetMoreCardsMessageForDealer(String dealerName) {
        logs.add("");
        logs.add(dealerName + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    @Override
    public void printResult(ProfitResult profitResult) {
        logs.add("");
        logs.add("## 최종 수익");
        logs.add("딜러: " + profitResult.calculateProfitOfDealer());
        profitResult.profitResult().forEach((playerName, profit) ->
                logs.add(playerName + ": " + profit)
        );
    }

    @Override
    public void println() {
        logs.add("");
    }

    public List<String> logs() {
        return logs;
    }

    private String stringJoinWithComma(List<String> strings) {
        StringJoiner stringJoiner = new StringJoiner(",");
        strings.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

}
