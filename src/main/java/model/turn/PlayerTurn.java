package model.turn;

import model.Betting;
import model.PlayerChoice;
import model.card.Deck;
import model.participant.Player;
import view.InputView;
import view.OutputView;

public class PlayerTurn extends Turn{
    private final Player player;
    private final Betting betting;
    private PlayerChoice playerChoice;

    public PlayerTurn(Player player, Betting betting) {
        super(player);
        this.player = player;
        this.betting = betting;
    }

    public void chooseAtOnePlayerTurn(Deck deck) {
        OutputView.printHitResult(player);
        PlayerChoice playerChoice = InputView.readChoice(player);
        if (playerChoice.equals(PlayerChoice.HIT)){
            processHit(deck);
        }
        if (playerChoice.equals(PlayerChoice.DOUBLE_DOWN)){
            int additionalBet = InputView.inputAdditionalBet();
            processDoubleDown(playerTurn, additionalBet);
        }
    }

    private void processHit(Deck deck){
        player.receiveCard(deck.pick());
        if (!player.isBust()){
            chooseAtOnePlayerTurn(deck);
        }
    }

    private void processDoubleDown(Deck deck, int additionalBet) {
        player.receiveCard(deck.pick());
        player.runDoubleDownBet(additionalBet);
    }

    public Player getPlayer() {
        return player;
    }
}
