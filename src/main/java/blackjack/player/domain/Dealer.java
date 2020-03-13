package blackjack.player.domain;

import blackjack.card.domain.CardBundle;
import blackjack.player.domain.report.GameReport;

public class Dealer extends Player {

    private static final int HIT_VALUE = 16;

    public Dealer(CardBundle cardBundle) {
        super(cardBundle, "딜러");
    }

    @Override
    public boolean isDrawable() {
        return cardBundle.calculateScore() <= HIT_VALUE;
    }

    @Override
    public GameReport createReport(Player player) {
        return new GameReport(player.name, this.cardBundle.compare(player.cardBundle));
    }
}
