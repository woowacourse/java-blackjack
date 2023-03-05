package blackjack.model.participant;

import blackjack.model.ResultState;
import blackjack.model.WinningResult;
import blackjack.model.card.CardDeck;
import blackjack.model.state.DrawState;
import blackjack.model.state.State;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    public Dealer(State currentState) {
        super(new Name("딜러"), currentState);
    }

    @Override
    public void draw(CardDeck cardDeck) {
        if (currentState instanceof DrawState) {
            this.currentState = ((DrawState) currentState).turnDealerDrawState();
        }
        this.currentState = currentState.draw(cardDeck);
    }

    @Override
    public void changeToStand() {
        if (currentState instanceof DrawState) {
            this.currentState = ((DrawState) currentState).turnStandState();
        }
    }

    @Override
    public ResultState resultState() {
        if (this.isBlackjack()) {
            return ResultState.BLACKJACK;
        }
        if (this.isBust()) {
            return ResultState.DEALER_BUST;
        }
        return ResultState.STAND;
    }


    public Map<String, WinningResult> winningResults(List<Player> players) {
        Map<String, WinningResult> results = new HashMap<>();
        WinningResult totalResult = new WinningResult();

        for (Player player : players) {
            WinningResult playerResult = player.winningResult(this.cardScore());
            results.put(player.getName(), playerResult);
            totalResult = totalResult.merge(playerResult);
        }

        WinningResult dealerResult = new WinningResult(totalResult.getLose(), totalResult.getDraw(), totalResult.getWin());
        results.put(this.getName(), dealerResult);
        return results;
    }
}
