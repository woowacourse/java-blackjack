package controller;

import controller.command.DoubleDownCommand;
import controller.command.HitCommand;
import controller.command.PlayerCommand;
import controller.command.SurrenderCommand;
import model.PlayerChoice;
import model.betting.Betting;
import model.card.Deck;
import model.participant.Player;
import view.InputView;

public class GameManager {

    public static void playPlayerTurn(Player player, Deck deck, Betting betting) {
        PlayerChoice playerChoice = InputView.readFirstChoice(player);
        PlayerCommand command = getPlayerCommand(player, deck, betting, playerChoice);
        command.execute();
        if (playerChoice == PlayerChoice.HIT) {
            handleContinuousHit(player, deck);
        }
    }

    private static PlayerCommand getPlayerCommand(Player player,
                                                  Deck deck,
                                                  Betting betting,
                                                  PlayerChoice playerChoice) {
        if (playerChoice == PlayerChoice.HIT) {
            return new HitCommand(player, deck);
        }
        if (playerChoice == PlayerChoice.DOUBLE_DOWN) {
            return new DoubleDownCommand(player, deck, betting);
        }
        if (playerChoice == PlayerChoice.SURRENDER) {
            return new SurrenderCommand(betting);
        }
        if (playerChoice == PlayerChoice.STAND) {
            return () -> {};
        }
        throw new IllegalArgumentException("잘못된 선택입니다.");
    }

    private static void handleContinuousHit(Player player, Deck deck) {
        while (!player.isBust()) {
            PlayerChoice playerChoice = InputView.readHitOrStand(player);
            if (playerChoice == PlayerChoice.STAND) {
                break;
            }
            new HitCommand(player, deck).execute();
        }
    }
}
