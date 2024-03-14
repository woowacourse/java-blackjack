package blackjackgame.domain.gamers;

import blackjackgame.domain.blackjack.DealerRandomCardDrawStrategy;
import blackjackgame.domain.blackjack.GameResult;
import blackjackgame.domain.blackjack.GameResultCalculator;
import blackjackgame.domain.blackjack.HoldingCards;
import blackjackgame.domain.card.Deck;

public class Gamer {
    private static final String DEALER_NAME = "딜러";
    private static final double DEALER_BET_MONEY = 0.0;

    private final CardHolder cardHolder;
    private final BetMaker betMaker;

    private Gamer(CardHolder cardHolder, BetMaker betMaker) {
        this.cardHolder = cardHolder;
        this.betMaker = betMaker;
    }

    public static Gamer dealer() {
        return new Gamer(new CardHolder(DEALER_NAME, HoldingCards.of()),
                new BetMaker(DEALER_NAME, DEALER_BET_MONEY));
    }

    public static Gamer createByNameAndBetMoney(String name, Double betMoney) {
        CardHolder cardHolder = new CardHolder(name, HoldingCards.of());
        BetMaker betMaker = new BetMaker(name, betMoney);

        return new Gamer(cardHolder, betMaker);
    }

    public void cardHolderDraw(Deck deck) {
        cardHolder.draw(deck, new DealerRandomCardDrawStrategy(cardHolder));
    }

    public void cardHolderDraw(Deck deck, int execution_count) {
        cardHolder.draw(deck, new DealerRandomCardDrawStrategy(cardHolder), execution_count);
    }

    public Double getGameProfit(Gamer otherGamer) {
        GameResult gamerGameResult = GameResultCalculator.calculate(cardHolder, otherGamer.getCardHolder());
        return betMaker.getBetMoney() * gamerGameResult.getTimes();
    }

    public String getRawGamerName() {
        return cardHolder.getRawName();
    }

    public CardHolder getCardHolder() {
        return cardHolder;
    }
}
