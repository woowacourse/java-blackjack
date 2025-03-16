package controller.command;

import model.betting.Betting;
import model.card.Deck;
import model.participant.Player;
import view.InputView;

public class DoubleDownCommand implements PlayerCommand{
    private final Player player;
    private final Deck deck;
    private final Betting betting;

    public DoubleDownCommand(Player player, Deck deck, Betting betting) {
        this.player = player;
        this.deck = deck;
        this.betting = betting;
    }

    @Override
    public void execute() {
        int additionalBet = InputView.inputAdditionalBet();
        betting.addBet(additionalBet);
        player.receiveCard(deck.pick());
    }
}
