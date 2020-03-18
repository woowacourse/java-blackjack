package blackjack.domain.player;

import blackjack.domain.card.CardBundle;
import blackjack.domain.generic.BettingMoney;
import blackjack.domain.report.GameReport;
import blackjack.domain.report.Reportable;
import blackjack.domain.result.GameResult;

public class Dealer extends Player implements Reportable {

    private static final int HIT_VALUE = 16;

    public Dealer(CardBundle cardBundle) {
        super(cardBundle, new PlayerInfo("딜러", BettingMoney.of(0)));
    }

    @Override
    public boolean isDrawable() {
        return cardBundle.calculateScore() <= HIT_VALUE;
    }

    @Override
    public GameReport createReport(Gambler gambler) {
        GameResult gameResult = GameResult.findByCardBundles(gambler.cardBundle, this.cardBundle);
        double rate = gameResult.getRate(gambler.cardBundle);
        BettingMoney earnMoney = gambler.getResultMoney(rate);

        return new GameReport(gambler.getName(), earnMoney, gameResult);
    }
}
