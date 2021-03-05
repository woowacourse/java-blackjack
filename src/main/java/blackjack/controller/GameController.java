package blackjack.controller;

import blackjack.domain.Result;
import blackjack.domain.Round;
import blackjack.domain.card.Card;
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
        Round round = initializeGame();
        RoundStatusDto roundStatusDto = getRoundStatusDto(round);
        OutputView.showInitialStatus(roundStatusDto);
        addPlayersCardOrPass(round);
        addDealerCard(round);
        showFinalStatus(round);
        OutputView.showOutComes(Result.finishGame(round.getDealer(), round.getPlayers()));
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

    private RoundStatusDto getRoundStatusDto(final Round round) {
        RoundStatusDto roundStatusDto = new RoundStatusDto(round.getDealerName(),
                round.getDealerCardStatus(),
                round.getPlayers()
                        .stream()
                        .map(this::getPlayerStatusDto)
                        .collect(Collectors.toList()),
                round.getDealer().calculateScore(GAME_OVER_SCORE));
        return roundStatusDto;
    }

    private PlayerStatusDto getPlayerStatusDto(final Player player) {
        return new PlayerStatusDto(player.getName(), player.getCardsStatus(), player.calculateScore(GAME_OVER_SCORE));
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

    private void showFinalStatus(final Round round) {
        OutputView.showFinalStatus(new RoundStatusDto(round.getDealerName(),
                round.getDealerCardStatus(),
                round.getPlayers().stream()
                        .map(this::getPlayerStatusDto)
                        .collect(Collectors.toList()),
                round.getDealer().calculateScore(GAME_OVER_SCORE)));
    }
}
