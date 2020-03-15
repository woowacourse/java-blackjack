package blackjack.player.domain;

import blackjack.card.domain.CardBundle;
import blackjack.card.domain.GameResult;
import blackjack.player.domain.report.GameReport;
import blackjack.player.domain.report.Reportable;

public class Dealer extends Player implements Reportable {

    private static final int HIT_VALUE = 16;

    public Dealer(CardBundle cardBundle) {
        super(cardBundle, "딜러");
    }

    @Override
    public boolean isDrawable() {
        return cardBundle.calculateScore() <= HIT_VALUE;
    }

    @Override
    public GameReport createReport(Player gambler) {
        GameResult gameResult = GameResult.findByComparing(gambler.cardBundle, this.cardBundle);
        return new GameReport(gambler.name, gameResult);
    }
}
