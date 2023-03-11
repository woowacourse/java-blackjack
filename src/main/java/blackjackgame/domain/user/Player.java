package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.game.Result;
import java.util.List;

public class Player extends User {
    private PlayerStatus status = PlayerStatus.NORMAL;
    private final Bet bet;

    public Player(Name name, Bet bet) {
        super(name);
        this.bet = bet;
    }

    @Override
    public void receiveCard(Card card) {
        super.receiveCard(card);
        status = score.calculatePlayerStatus();
    }

    @Override
    public void receiveCards(List<Card> cards) {
        super.receiveCards(cards);
        status = score.calculatePlayerStatus();
    }

    public void applyResult(Result result) {
        bet.applyResult(result);
    }

    @Override
    public UserStatus getStatus() {
        return status;
    }

    public int getProfit() {
        return bet.profit();
    }
}
