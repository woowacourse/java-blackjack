package domain.user;

import domain.card.Card;
import domain.game.Deck;
import domain.game.HitCommand;
import view.dto.PlayerDTO;

import java.util.function.Consumer;
import java.util.function.Function;

public class Player {

    private final PlayerName playerName;
    private final Hand hand;

    public Player(String playerName, Hand hand) {
        this.playerName = new PlayerName(playerName);
        this.hand = hand;
    }

    public void draw(Card card) {
        hand.add(card);
    }

    public void hitByCommand(Function<String, String> inputCommand, Consumer<PlayerDTO> outputPlayer, Deck deck) {
        HitCommand hitCommand;
        boolean hittable;
        do {
            hitCommand = HitCommand.findCommand(inputCommand.apply(playerName.getValue()));
            hittable = hitByCondition(outputPlayer, deck, hitCommand);
        } while (hitCommand == HitCommand.Y && hittable);
    }

    private boolean hitByCondition(Consumer<PlayerDTO> outputPlayer, Deck deck, HitCommand hitCommand) {
        if (hitCommand == HitCommand.N) {
            return false;
        }

        draw(deck.serve());
        outputPlayer.accept(PlayerDTO.from(this));

        return isBust();
    }

    public int sumHand() {
        return hand.sumCardNumbers();
    }

    public boolean isBust() {
        return hand.isOverBlackjack();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public Hand getHand() {
        return hand;
    }
}
