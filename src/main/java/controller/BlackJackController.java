package controller;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import dto.AllPlayerResultDto;
import dto.DealerResultDto;
import dto.GameResultDto;
import dto.InitCardDto;
import dto.PlayerCardStateDto;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = Players.settingPlayers(inputView.inputPlayerNames());
        Dealer dealer = new Dealer();

        players.initCard(dealer);
        showInitialCard(players, dealer);
        playGame(players, dealer);
        showResult(players, dealer);
    }

    private void showInitialCard(Players players, Dealer dealer) {
        outputView.printInitCard(InitCardDto.makeInitCard(players, dealer));
    }

    private void playGame(Players players, Dealer dealer) {
        players.play(this::playTurn, dealer);
        while (dealer.shouldDraw()) {
            dealer.receiveCard();
            outputView.printDealerAddCard();
        }
    }

    private void playTurn(Player player, Dealer dealer) {
        while (player.isDrawable() && inputView.inputPlayerCommand(player.getName())) {
            player.receiveCard(dealer.dealCard());
            outputView.printCardsStatus(PlayerCardStateDto.makePlayerCardState(player));
        }
    }

    private void showResult(Players players, Dealer dealer) {
        outputView.printDealerScore(DealerResultDto.makeDealerResultDto(dealer));
        outputView.printPlayersScore(AllPlayerResultDto.makeAllPlayerResultDto(players));
        outputView.printResult(GameResultDto.makeGameResultDto(players, dealer), players.getNames());
    }
}
