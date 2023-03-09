package blackjack.model.participant;

import blackjack.model.ResultState;
import blackjack.model.WinningResult;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.HandCard;
import blackjack.model.state.DealerInitialState;
import blackjack.model.state.DrawState;
import blackjack.model.state.ParticipantState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    public static final int DEALER_HIT_NUMBER = 16;
    public static final int FIRST_CARD = 0;

    public Dealer() {super(new Name("딜러"), new DealerInitialState());}

    public Dealer(ParticipantState currentState) {
        super(new Name("딜러"), currentState);
    }

    public Dealer(ParticipantState currentState, HandCard handCard) {
        super(new Name("딜러"), currentState, handCard);
    }

    @Override
    public void draw(CardDeck cardDeck) {
        if (currentState instanceof DrawState) {
            this.currentState = ((DrawState) currentState).turnDealerDrawState();
        }
        this.currentState = currentState.draw(cardDeck, this.handcard);
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

    public Map<String, WinningResult> participantWinningResults(Players players) {
        Map<String, WinningResult> playerResults = new HashMap<>();
        WinningResult totalResult = new WinningResult();

        for (int i = 0; i < players.getPlayerCount(); i++) {
            WinningResult playerResult = players.getWinningResultById(i, this.cardScore());
            playerResults.put(players.getNameById(i), playerResult);
            totalResult = totalResult.merge(players.getWinningResultById(i, this.cardScore()));
        }
        playerResults.put(this.getName(), new WinningResult(totalResult.getLose(), totalResult.getDraw(), totalResult.getWin()));
        return playerResults;
    }

    @Override
    public Map<String, List<Card>> firstDistributedCard() {
        if ((handcard.size() == 0)) {
            throw new IllegalStateException("카드를 분배 받지 않은 상태입니다.");
        }
        Map<String, List<Card>> firstCardUnits = new HashMap<>();
        firstCardUnits.put(getName(), List.of(handcard.getCards().get(FIRST_CARD)));
        return firstCardUnits;
    }
}
