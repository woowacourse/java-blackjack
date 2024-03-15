package controller;

import domain.betting.Bet;
import domain.betting.Bets;
import domain.betting.Profit;
import domain.card.CardDeck;
import domain.game.BlackJackGame;
import domain.game.Command;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.PlayerName;
import domain.player.PlayerNames;
import dto.PlayerDto;
import dto.ProfitDto;
import dto.ScoreDto;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() throws IOException {
        PlayerNames playerNames = initPlayerNames();
        Bets bets = initBets(playerNames);
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck);
        BlackJackGame blackJackGame = new BlackJackGame(playerNames, dealer);

        printInitialDealAndHand(blackJackGame);
        repeatHitUntilStand(blackJackGame);
        printResultHandAndScore(blackJackGame);
        printGameProfits(blackJackGame, bets);
    }

    private PlayerNames initPlayerNames() throws IOException {
        List<String> inputNames = inputView.readPlayerNames();

        return new PlayerNames(inputNames);
    }

    private Bets initBets(final PlayerNames playerNames) throws IOException {
        Map<PlayerName, Bet> playerBets = new LinkedHashMap<>();
        List<String> names = playerNames.names();
        List<Integer> bets = inputView.readBettingMoney(names);

        for (int i = 0; i < names.size(); i++) {
            playerBets.put(new PlayerName(names.get(i)), new Bet(bets.get(i)));
        }

        return new Bets(playerBets);
    }

    private void printInitialDealAndHand(final BlackJackGame blackJackGame) {
        List<Player> initialParticipants = blackJackGame.getEveryParticipants();
        List<PlayerDto> playerInitDtos = PlayerDto.from(initialParticipants);

        outputView.printInitialDeal(playerInitDtos);
        outputView.printInitialHand(playerInitDtos);
    }


    private void repeatHitUntilStand(final BlackJackGame blackJackGame) throws IOException {
        for (Player player : blackJackGame.getPlayers()) {
            repeatHitUntilPlayerStand(blackJackGame, player);
        }
        repeatHitUntilDealerStand(blackJackGame);
    }

    private void repeatHitUntilPlayerStand(final BlackJackGame blackJackGame, Player player) throws IOException {
        boolean hitting = false;
        while (player.isHittable() && Command.from(inputView.readCommand(player.getName().name())).isProceed()) {
            blackJackGame.hitPlayer(player);
            outputView.printHandAfterHit(PlayerDto.from(player));
            hitting = true;
        }
        if (!hitting) {
            outputView.printInitialHand(PlayerDto.from(player));
        }
    }

    private void repeatHitUntilDealerStand(final BlackJackGame blackJackGame) {
        while (blackJackGame.hitDealer()) {
            outputView.printDealerHitMessage();
        }
    }

    private void printResultHandAndScore(final BlackJackGame blackJackGame) {
        outputView.printFinalHandAndScore(PlayerDto.from(blackJackGame.getEveryParticipants())
                , ScoreDto.from(blackJackGame.getScores()));
    }

    private void printGameProfits(final BlackJackGame blackJackGame, Bets bets) {
        Map<Player, Profit> playerProfits = blackJackGame.getProfits(bets);
        Entry<Player, Integer> dealerProfit = blackJackGame.getDealerProfit(bets);
        outputView.printProfits(ProfitDto.from(playerProfits, dealerProfit));
    }
}
