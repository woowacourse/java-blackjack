package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {

    enum WinOrLose {
        WIN, LOSE;

        public static WinOrLose compare(Dealer dealer, Participant participant) {
            if (participant.score().isMoreCloseToBlackjack(dealer.score())) {
                return WIN;
            }
            return LOSE;
        }
    }

    private static final int NEGATIVE_NUM = -1;
    private final Dealer dealer;
    private final Participants participants;
    private final Map<Participant, WinOrLose> participantResult;

    public Result(Dealer dealer, Participants participants) {
        this.dealer =dealer;
        this.participants = participants;
        this.participantResult = new HashMap<>();
        initResult(this.dealer, this.participants);
    }

    private void initResult(Dealer dealer, Participants participants) {
        for (Participant participant : participants.participants()) {
            putResult(dealer, participant);
        }
    }

    private void putResult(Dealer dealer, Participant participant) {
        if (participant.isBlackJack()) {
            participantResult.put(participant, WinOrLose.WIN);
        }

        if (participant.isBust()) {
            participantResult.put(participant, WinOrLose.LOSE);
        }

        if (WinOrLose.compare(dealer, participant) == WinOrLose.LOSE) {
            participantResult.put(participant, WinOrLose.LOSE);
        }

        participantResult.put(participant, WinOrLose.WIN);
    }

    public List<WinningMoney> winningMoneyResult() {
        List<WinningMoney> winningMonies = participantsMoneyResult();
        int sumOfWinningMoney = winningMonies
            .stream()
            .mapToInt(WinningMoney::winningMoney)
            .sum();

        int dealerWinningMoney = sumOfWinningMoney * NEGATIVE_NUM;

        winningMonies.add(WinningMoney.of(dealer.name(), dealerWinningMoney));
        return winningMonies;
    }

    private List<WinningMoney> participantsMoneyResult() {
        return participantResult.entrySet()
            .stream()
            .map(this::mapToWinningMoney)
            .collect(Collectors.toList());
    }

    private WinningMoney mapToWinningMoney(Map.Entry<Participant, WinOrLose> entry) {
        Participant participant = entry.getKey();
        int money = participant.winningMoney();
        if (entry.getValue() == WinOrLose.LOSE) {
            money *= NEGATIVE_NUM;
        }
        return WinningMoney.of(participant.name(), money);
    }

}
