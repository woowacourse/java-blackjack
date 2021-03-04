package blackjack.controller;

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

public class GameController {
    public static final int GAME_OVER_SCORE = 21;
    public static final String NO = "n";
    public static final String YES = "y";

    private final InputView inputView = new InputView(new Scanner(System.in));

    public void start() {
        Round round = initializeGame();
        RoundStatusDto roundStatusDto = new RoundStatusDto(round.getDealerName(), round.getDealerCardStatus(), round.getPlayers().stream().map(this::getPlayerStatusDto).collect(Collectors.toList()), round.getDealer().calculateScore(21));
        OutputView.showInitialStatus(roundStatusDto);

        List<Player> players = round.getPlayers();
        for (Player player : players) {
            addCardOrPass(round, player);
        }
        if (round.addDealerCard()) {
            OutputView.showDealerAddCard(Dealer.TURN_OVER_COUNT);
        }
        OutputView.showFinalStatus(new RoundStatusDto(round.getDealerName(), round.getDealerCardStatus(), round.getPlayers().stream().map(this::getPlayerStatusDto).collect(Collectors.toList()), round.getDealer().calculateScore(21)));
    }

    private PlayerStatusDto getPlayerStatusDto(Player player) {
        return new PlayerStatusDto(player.getName(), player.getCardsStatus(), player.calculateScore(21));
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
