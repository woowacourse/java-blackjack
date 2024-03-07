package controller;

import cardGame.CardGame;
import dealer.Dealer;
import java.util.List;
import player.Name;
import player.Player;
import player.Players;
import player.dto.PlayersCardStatusDto;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void playGame() {
        List<String> nameString = inputView.inputPlayerNames();

        List<Name> names = nameString.stream()
                .map(Name::new)
                .toList();

        Dealer dealer = new Dealer();

        Players players = Players.from(names);
        for (Player player : players.getPlayers()) {
            CardGame cardGame = new CardGame(player, dealer);
        }

        outputView.printInitCardStatus(PlayersCardStatusDto.of(players), dealer.getCards());
    }
}
