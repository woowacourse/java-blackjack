package controller;

import domain.match.GameResult;
import domain.participant.Bet;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import mapper.DealerMapper;
import mapper.PlayersMapper;
import domain.card.CardDistributor;
import mapper.ProfitResultMapper;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Players convertAndCreatePlayers(Map<String, Bet> playersInfo) {
        List<Player> players = new ArrayList<>();

        for (Map.Entry<String, Bet> playerInfo : playersInfo.entrySet()) {
            Player player = new Player(playerInfo.getKey(), playerInfo.getValue());
            players.add(player);
        }

        return new Players(players);
    }

    private Players readPlayersUntilValid() {
        while (true) {
            try {
                Map<String, Bet> playersInfo = new HashMap<>();
                List<String> names = inputView.readPlayers();

                for (String name : names) playersInfo.put(name, readPlayerBetAmountUntilValid(name));

                return convertAndCreatePlayers(playersInfo);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Bet readPlayerBetAmountUntilValid(String name) {
        while (true) {
            try {
                return Bet.of(inputView.readPlayerBetAmount(name));
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
