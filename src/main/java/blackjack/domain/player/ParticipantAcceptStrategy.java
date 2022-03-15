package blackjack.domain.player;

import blackjack.view.InputView;

public class ParticipantAcceptStrategy implements AcceptStrategy {

    @Override
    public boolean accept(String name) {
        return InputView.oneMoreCard(name);
    }
}
