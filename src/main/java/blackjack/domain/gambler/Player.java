package blackjack.domain.gambler;

import blackjack.domain.BettingMoney;
import blackjack.domain.Name;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.GamblerCards;
import blackjack.domain.result.CardsResult;
import blackjack.util.NullChecker;
import java.util.List;

public final class Player {

    private static final int BASES_SCORE_CAN_DRAW = 21;
    private static final int DEFAULT_DRAW_COUNT = 1;

    private final Name name;
    private final BettingMoney bettingMoney;
    private final GamblerCards gamblerCards;

    public Player(Name name, BettingMoney bettingMoney) {
        this(name, bettingMoney, new GamblerCards());
    }

    public Player(Name name, BettingMoney bettingMoney, GamblerCards gamblerCards) {
        NullChecker.validateNotNull(name, bettingMoney, gamblerCards);
        this.name = name;
        this.bettingMoney = bettingMoney;
        this.gamblerCards = gamblerCards;
    }

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, DEFAULT_DRAW_COUNT);
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            gamblerCards.add(cardDeck.draw());
        }
    }

    public boolean canDrawCard() {
        return getCardsResult().isEqualOrUnderScore(BASES_SCORE_CAN_DRAW);
    }

    public Name getName() {
        return name;
    }

    public CardsResult getCardsResult() {
        return gamblerCards.getResult();
    }

    public List<String> getCardsInfos() {
        return gamblerCards.getInfos();
    }

    public Integer getBettingMoneyMultiply(double profitRatio) {
        return bettingMoney.multiply(profitRatio);
    }
}
