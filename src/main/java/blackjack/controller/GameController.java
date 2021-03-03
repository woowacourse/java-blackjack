package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Round;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.RoundInitializeStatusDto;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameController {
    public static final int GAME_OVER_SCORE = 21;
    public static final String NO = "n";
    public static final String YES = "y";

    private final InputView inputView = new InputView(new Scanner(System.in));

    public void start() {
        Round round = initializeGame();
        OutputView.showInitialStatus(new RoundInitializeStatusDto(round.getDealer(), round.getPlayers()));
        List<Player> players = round.getPlayers();
        for (Player player : players) {
            addCardOrPass(round, player);
        }
        if (round.addDealerCard()) {
            OutputView.showDealerAddCard(Dealer.TURN_OVER_COUNT);
        }
    }

    private void addCardOrPass(Round round, Player player) {
        String answer = "";
        while (!player.isGameOver(GAME_OVER_SCORE) && !answer.equals(NO)) {
            answer = inputView.getCardOrPass(player.getName());
            addCardOrPassByInput(round, player, answer);
        }
    }

    private void addCardOrPassByInput(Round round, Player player, String answer) {
        if (answer.equals(YES)) {
            player.addCard(round.makeOneCard());
            OutputView.showPlayCardStatus(player.getName(), player.getCards());
        }
    }

    private Round initializeGame() {
        List<String> playerNames = inputView.getPlayerNames();
        Dealer dealer = new Dealer();
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        Round round = new Round(Card.getShuffledCards(), dealer, players);
        round.initialize();
        return round;
    }
}
