package blackjack.domain.participant.rule;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class PlayerRule implements DrawRule<Player> {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    @Override
    public void draw(Player participant, Deck deck) {
        while (participant.isDrawable() && inputView.isPlayerWantDraw(participant.getName())) {
            participant.add(deck.draw());
            outputView.printPlayerCards(participant);
        }
    }
}
