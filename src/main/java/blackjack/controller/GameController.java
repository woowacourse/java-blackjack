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
    public static final String NO = "n";
    public static final String YES = "y";
    private final InputView inputView = new InputView(new Scanner(System.in));

    public void start() {
        Round round = initializeGame();
        RoundStatusDto roundStatusDto = getRoundStatusDto(round);
        OutputView.showInitialStatus(roundStatusDto);
        addPlayersCardOrPass(round);
        addDealerCard(round);
        showFinalStatus(round);
        OutputView.showOutcomes(new Result(round.getDealer(), round.getPlayers()));
    }

    private void showFinalStatus(Round round) {
        OutputView.showFinalStatus(new RoundStatusDto(round.getDealerName(),
                round.getDealerCardStatus(),
                round.getPlayers().stream()
                        .map(this::getPlayerStatusDto)
                        .collect(Collectors.toList()),
                round.getDealer().calculateScore(GAME_OVER_SCORE)));
    }

    private RoundStatusDto getRoundStatusDto(Round round) {
        RoundStatusDto roundStatusDto = new RoundStatusDto(round.getDealerName(),
                round.getDealerCardStatus(),
                round.getPlayers()
                        .stream()
                        .map(this::getPlayerStatusDto)
                        .collect(Collectors.toList()),
                round.getDealer().calculateScore(GAME_OVER_SCORE));
        return roundStatusDto;
    }

    private void addDealerCard(Round round) {
        if (round.addDealerCard()) {
            OutputView.showDealerAddCard(Dealer.TURN_OVER_COUNT);
        }
    }

    private void addPlayersCardOrPass(Round round) {
        List<Player> players = round.getPlayers();
        for (Player player : players) {
            addCardOrPass(round, player);
        }
    }

    private PlayerStatusDto getPlayerStatusDto(Player player) {
        return new PlayerStatusDto(player.getName(), player.getCardsStatus(), player.calculateScore(GAME_OVER_SCORE));
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
            player.addCard(round.drawExtraCard());
            OutputView.showPlayCardStatus(player.getName(), player.getCardsStatus());
        }
    }

    private Round initializeGame() {
        List<String> playerNames = inputView.getPlayerNames();
        Dealer dealer = new Dealer();
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        isDuplicatePlayers(players);
        Round round = new Round(Card.getShuffledCards(), dealer, players);
        return round;
    }

    private void isDuplicatePlayers(List<Player> players) {
        if (players.stream().distinct().count() != players.size()) {
            throw new IllegalArgumentException("플레이어가 중복됩니다!");
        }
    }
}
