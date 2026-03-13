package controller;

import domain.match.GameResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import mapper.DealerMapper;
import mapper.PlayersMapper;
import domain.card.CardDistributor;
import mapper.ProfitResultMapper;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CardDistributor cardDistributor;

    public BlackJackController(InputView inputView, OutputView outputView, CardDistributor cardDistributor) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardDistributor = cardDistributor;
    }

    public void playGame() {
        Dealer dealer = new Dealer();
        Players players = readPlayersUntilValid();
        readPlayerBetAmountUntilValid(players);

        playBlackJack(players, dealer);

        outputView.showHandResultsOfParticipants(DealerMapper.toDto(dealer), PlayersMapper.toPlayersDto(players));
        outputView.showProfitResult(ProfitResultMapper.toDto(new GameResult(players, dealer)));
    }

    private void playBlackJack(Players players, Dealer dealer) {
        cardDistributor.dealInitialCardsToParticipants(dealer, players);
        outputView.showInitialHandsOfParticipants(DealerMapper.toDto(dealer), PlayersMapper.toPlayersDto(players));

        for (Player player : players.getPlayers()) {
            playPlayerTurn(player);
        }

        playDealerTurn(dealer);
    }

    private void playPlayerTurn(Player player) {
        while (!player.isBust()) {
            if (!inputView.readPlayerToHitUntilValid(player.getName())) {
                break;
            }

            cardDistributor.dealCardTo(player);
            outputView.showPlayerHand(PlayersMapper.toPlayerDto(player));
        }
    }

    private void playDealerTurn(Dealer dealer) {
        while (dealer.shouldHit()) {
            outputView.showDealerHitMessage();
            cardDistributor.dealCardTo(dealer);
            outputView.showDealerHand(DealerMapper.toDto(dealer));
        }

        outputView.showDealerStandMessage();
    }

    private Players convertAndCreatePlayers(List<String> names) {
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();

        return new Players(players);
    }

    private Players readPlayersUntilValid() {
        while (true) {
            try {
                List<String> names = inputView.readPlayers();
                return convertAndCreatePlayers(names);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void readPlayerBetAmountUntilValid(Players players) {
        while (true) {
            try {
                for (Player player : players.getPlayers()) {
                    player.placeBet(inputView.readPlayerBetAmount(player.getName()));
                }

                return;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
