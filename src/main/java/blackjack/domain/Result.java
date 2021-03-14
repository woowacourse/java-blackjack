package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {

    private static final int NEGATIVE_NUM = -1;
    private final Dealer dealer;
    private final Map<Participant, WinOrLose> participantResult;

    public Result(Dealer dealer, Participants participants) {
        this.dealer = dealer;
        this.participantResult = new LinkedHashMap<>();
        initResult(this.dealer, participants);
    }

    private void initResult(Dealer dealer, Participants participants) {
        for (Participant participant : participants.participants()) {
            putResult(dealer, participant);
        }
    }

    private void putResult(Dealer dealer, Participant participant) {
        if (participant.isBlackJack()) {
            participantResult.put(participant, WinOrLose.WIN);
            return;
        }

        if (participant.isBust()) {
            participantResult.put(participant, WinOrLose.LOSE);
            return;
        }

        if (dealer.isBust()) {
            participantResult.put(participant, WinOrLose.WIN);
            return;
        }

        participantResult.put(participant, WinOrLose.compare(dealer, participant));
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

    public Map<Participant, WinOrLose> winOrLoseResult() {
        return participantResult;
    }

    enum WinOrLose {
        WIN, LOSE;

        public static WinOrLose compare(Dealer dealer, Participant participant) {
            if (participant.score().isMoreCloseToBlackjack(dealer.score())) {
                return WIN;
            }
            return LOSE;
        }
    }
}
