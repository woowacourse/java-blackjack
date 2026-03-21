package blackjack.view.input;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FakeInputView implements InputView {

    private final List<String> playerNames;
    private final Queue<Integer> bettingAmounts;
    private final Queue<Boolean> moreCardAnswers;

    public FakeInputView(List<String> playerNames, List<Integer> bettingAmounts, List<Boolean> moreCardAnswers) {
        this.playerNames = playerNames;
        this.bettingAmounts = new LinkedList<>(bettingAmounts);
        this.moreCardAnswers = new LinkedList<>(moreCardAnswers);
    }

    @Override
    public List<String> readPlayersName() {
        return playerNames;
    }

    @Override
    public Integer readBettingAmount(String playerName) {
        return bettingAmounts.poll();
    }

    @Override
    public boolean readMoreCard(String playerName) {
        return Boolean.TRUE.equals(moreCardAnswers.poll());
    }

}
