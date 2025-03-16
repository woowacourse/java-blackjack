package controller.command;

import model.card.Deck;
import model.participant.Player;

public class HitCommand implements PlayerCommand{
    private final Player player;
    private final Deck deck;

    public HitCommand(Player player, Deck deck) {
        this.player = player;
        this.deck = deck;
    }

    @Override
    public void execute() {
        player.receiveCard(deck.pick());
    }
}
