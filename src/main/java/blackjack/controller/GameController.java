package blackjack.controller;

import blackjack.domain.Round;
import blackjack.domain.card.Deck;
import blackjack.domain.user.AbstractUser;
import blackjack.domain.user.Dealer;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.PlayerStatusDto;
import blackjack.view.dto.RoundStatusDto;

import java.util.List;
import java.util.Scanner;

public class GameController {
    private static final String NO = "n";
    private static final String YES = "y";

    private final InputView inputView = new InputView(new Scanner(System.in));

    public void start() {
        Round round = initializeRound();
        OutputView.showInitialStatus(RoundStatusDto.toDto(round));

        addPlayersCardOrPass(round);
        addDealerCard(round);
        OutputView.showFinalStatus(RoundStatusDto.toDto(round));
        OutputView.showOutComes(round.findResults());
    }

    private Round initializeRound() {
        List<String> playerNames = inputView.getPlayerNames();

        Deck deck = Deck.generateByRandomCard();
        return Round.valueOf(deck, playerNames);
    }

    private void addPlayersCardOrPass(final Round round) {
        List<AbstractUser> players = round.getPlayers();
        players.forEach(player -> addCardOrPass(round, player));
    }

    private void addCardOrPass(final Round round, final AbstractUser player) {
        String answer = "";
        while (player.canDraw() && !answer.equals(NO)) {
            answer = inputView.getCardOrPass(player.getName());
            addCardOrPassByInput(round, player, answer);
        }
    }

    private void addCardOrPassByInput(final Round round, final AbstractUser player, final String answer) {
        if (answer.equals(YES)) {
            round.addPlayerCard(player);
            OutputView.showPlayCardStatus(new PlayerStatusDto(player.getName(), player.getState().cards().getCards(), player.getState().calculateScore()));
        }
        round.makePlayerStay(player);
    }

    private void addDealerCard(final Round round) {
        while (round.isDealerCanDraw()) {
            round.addDealerCard();
            OutputView.showDealerAddCard(Dealer.TURN_OVER_COUNT);
        }
    }
}
