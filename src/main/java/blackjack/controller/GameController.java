package blackjack.controller;

import blackjack.domain.Round;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.PlayerStatusDto;
import blackjack.view.dto.RoundStatusDto;

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
        OutputView.showInitialStatus(RoundStatusDto.toDto(round));

        addPlayersCardOrPass(round);
        addDealerCard(round);
        OutputView.showFinalStatus(RoundStatusDto.toDto(round));
        OutputView.showOutComes(round.findResults());
    }

    private Round initializeRound() {
        List<String> playerNames = inputView.getPlayerNames();
        Dealer dealer = new Dealer();
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        Deck deck = Deck.generateByRandomCard();
        return new Round(deck, dealer, players);
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
            round.addPlayerCard(player);
            OutputView.showPlayCardStatus(new PlayerStatusDto(player.getName(), player.getCards(), player.getScore()));
        }
    }

    private void addDealerCard(final Round round) {
        if (round.addDealerCard()) {
            OutputView.showDealerAddCard(Dealer.TURN_OVER_COUNT);
        }
    }
}
