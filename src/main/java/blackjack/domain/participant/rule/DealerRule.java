package blackjack.domain.participant.rule;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.view.OutputView;

public class DealerRule implements DrawRule<Dealer> {

    private final OutputView outputView = new OutputView();

    @Override
    public void draw(Dealer participant, Deck deck) {
        while (participant.isDrawable()) {
            participant.add(deck.draw());
            outputView.printDealerDraw();
        }
    }
}
