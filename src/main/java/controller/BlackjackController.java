package controller;

import domain.PlayerFactory;
import domain.card.Cards;
import domain.player.Players;
import dto.RequestPlayerNameDTO;
import dto.ResponsePlayerDTO;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    public static void run() {
        RequestPlayerNameDTO requestPlayerNameDTO = InputView.inputPlayerName();
        PlayerFactory playerFactory = PlayerFactory.getInstance();
        Cards cards = new Cards();
        Players players = playerFactory.createPlayers(cards, requestPlayerNameDTO.getPlayerName());
        OutputView.printInitialResult(ResponsePlayerDTO.getResult(players));
    }
}
