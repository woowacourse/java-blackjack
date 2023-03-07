package domain.user;

import domain.card.Card;
import domain.game.Deck;
import domain.game.HitCommand;
import view.dto.PlayerInfoDTO;

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

    public void hitByCommand(Function<String, String> inputCommand, Consumer<PlayerInfoDTO> outputPlayer, Deck deck) {
        boolean hittable;
        do {
            hittable = hitByCondition(outputPlayer, inputCommand, deck);
        } while (hittable);
    }

    private boolean hitByCondition(Consumer<PlayerInfoDTO> outputPlayer, Function<String, String> inputCommand, Deck deck) {
        HitCommand hitCommand = HitCommand.findCommand(inputCommand.apply(playerName.getValue()));
        if (hitCommand == HitCommand.N) {
            return false;
        }

        draw(deck.serve());
        outputPlayer.accept(PlayerInfoDTO.from(this));

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
