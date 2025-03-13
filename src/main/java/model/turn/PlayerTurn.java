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
    private boolean isSurrender;

    public PlayerTurn(Player player, Betting betting) {
        super(player);
        this.player = player;
        this.betting = betting;
        this.isSurrender = false;
    }

    public void chooseAtOnePlayerChoice(Deck deck) {
        OutputView.printHitResult(player);
        PlayerChoice playerChoice = InputView.readChoice(player);
        if (playerChoice.equals(PlayerChoice.HIT)){
            processHit(deck);
        }
        if (playerChoice.equals(PlayerChoice.DOUBLE_DOWN)){
            int additionalBet = InputView.inputAdditionalBet();
            processDoubleDown(deck, additionalBet);
        }
        if (playerChoice.equals(PlayerChoice.SURRENDER)){

        }
    }

    private void processHit(Deck deck){
        player.receiveCard(deck.pick());
        if (!player.isBust()){
            chooseAtOnePlayerChoice(deck);
        }
    }

    private void processDoubleDown(Deck deck, int additionalBet) {
        player.receiveCard(deck.pick());
        betting.addBet(additionalBet);
    }

    public Player getPlayer() {
        return player;
    }
}
