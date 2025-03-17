package domain.user;

import domain.CardDeck;
import domain.GameResult;
import domain.TrumpCard;
import java.util.List;

public class Player extends User {


    private final String name;
    private final Betting betting;

    public Player(final String name, final Betting betting) {
        this.name = name;
        this.betting = betting;
        validate(name);
    }

    @Override
    public boolean isImpossibleDraw() {
        return cardDeck.isImpossibleDraw(CardDeck.MAX_SCORE);
    }

    @Override
    public List<TrumpCard> openCard() {
        return this.cardDeck.getAllCard();
    }

    @Override
    public List<TrumpCard> openAllCard() {
        return this.cardDeck.getAllCard();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    private void validate(final String name) {
        if (name.equals("dealer") || name.equals("딜러")) {
            throw new IllegalArgumentException("dealer 혹은 딜러는 이름으로 사용할 수 없습니다.");
        }
    }

    public Betting getBetting() {
        return betting;
    }

    public long calculateBettingResult(final GameResult gameResult) {
        return betting.calculateBetting(gameResult);
    }
}
