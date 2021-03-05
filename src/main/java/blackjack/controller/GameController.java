package blackjack.controller;

import blackjack.domain.Round;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static blackjack.domain.Round.GAME_OVER_SCORE;

public class GameController {
    private static final String NO = "n";
    private static final String YES = "y";

    private final InputView inputView = new InputView(new Scanner(System.in));

    public void start() {
        Round round = initializeRound();
        round.initialize();
        OutputView.showInitialStatus(round.getRoundStatus());

        addPlayersCardOrPass(round);
        addDealerCard(round);
        OutputView.showFinalStatus(round.getRoundStatus());
        OutputView.showOutComes(round.finishGame());
    }

    private Round initializeRound() {
        List<String> playerNames = inputView.getPlayerNames();
        Dealer dealer = new Dealer();
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return Round.generateWithRandomCards(dealer, players);
    }

    private void addPlayersCardOrPass(final Round round) {
        List<Player> players = round.getPlayers();
        players.forEach(player -> addCardOrPass(round, player));
    }

    private void addCardOrPass(final Round round, final Player player) {
        String answer = "";
        while (!player.isGameOver(GAME_OVER_SCORE) && !answer.equals(NO)) {
            answer = inputView.getCardOrPass(player.getName());
            addCardOrPassByInput(round, player, answer);
        }
    }

    private void addCardOrPassByInput(final Round round, final Player player, final String answer) {
        if (answer.equals(YES)) {
            player.addCard(round.makeOneCard());
            OutputView.showPlayCardStatus(player.getName(), player.getCards());
        }
    }

    private void addDealerCard(final Round round) {
        if (round.addDealerCard()) {
            OutputView.showDealerAddCard(Dealer.TURN_OVER_COUNT);
        }
    }
}
