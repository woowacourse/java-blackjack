package blackjack.model.participant;

import blackjack.model.ResultState;
import blackjack.model.WinningResult;
import blackjack.model.card.Card;
import blackjack.model.card.HandCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    public static final int DEALER_HIT_NUMBER = 16;
    public static final int FIRST_CARD = 0;

    public Dealer() {super(new Name("딜러"));}

    public Dealer(HandCard handCard) {
        super(new Name("딜러"), handCard);
    }

    @Override
    public Map<String, List<Card>> firstDistributedCard() {
        if (handcard.isEmpty()) {
            throw new IllegalStateException("카드를 분배 받지 않은 상태입니다.");
        }
        Map<String, List<Card>> firstCardUnits = new HashMap<>();
        firstCardUnits.put(getName(), List.of(handcard.getCards().get(FIRST_CARD)));
        return firstCardUnits;
    }

    @Override
    public boolean isFinished() {
        return (isBlackjack() || isBust() || isStand());
    }

    public Map<String, WinningResult> participantWinningResults(Players players) {
        Map<String, WinningResult> playerResults = new HashMap<>();
        WinningResult totalResult = new WinningResult();

        for (int playerId: players.getPlayerIds()) {
            WinningResult playerResult = players.getWinningResultById(playerId, this.cardScore());
            playerResults.put(players.getNameById(playerId), playerResult);
            totalResult = totalResult.merge(players.getWinningResultById(playerId, this.cardScore()));
        }
        playerResults.put(this.getName(), new WinningResult(totalResult.getLose(), totalResult.getDraw(), totalResult.getWin()));
        return playerResults;
    }

    @Override
    protected ResultState resultState() {
        if (this.isBlackjack()) {
            return ResultState.BLACKJACK;
        }
        if (this.isBust()) {
            return ResultState.DEALER_BUST;
        }
        return ResultState.STAND;
    }

    private boolean isStand() {
        int validScore = handcard.bigScore();
        if (validScore > BLACKJACK_NUMBER) {
            validScore = handcard.smallScore();
        }
        return validScore > DEALER_HIT_NUMBER;
    }
}
