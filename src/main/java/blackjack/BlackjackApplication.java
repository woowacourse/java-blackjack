package blackjack;

import blackjack.domain.config.BlackjackConsoleBootstrap;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Profit;
import blackjack.dto.DealerHitDto;
import blackjack.dto.GameResultDtos;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDtos;
import blackjack.view.BlackjackView;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackApplication {
    public static void main(String[] args) {
        BlackjackApplication application = BlackjackConsoleBootstrap.createApplication();
        application.run();
    }

    private final BlackjackView view;
    private final BlackjackGame game;

    public BlackjackApplication(BlackjackView view, BlackjackGame game) {
        this.view = view;
        this.game = game;
    }

    public void run() {
        initialDeal();

        playPlayersTurn();
        playDealerTurn();

        printScore();
        printProfit();
    }

    private void initialDeal() {
        game.initialDeal();
        view.printInitialDeal(InitialDealDtos.of(game.getDealer(), game.getPlayers()));
    }

    private void playPlayersTurn() {
        game.getPlayers().forEach(this::playTurn);
    }

    private void playTurn(Player player) {
        while (player.canHit() && view.isHitAnswer(player.getName())) {
            game.hit(player);
            view.printPlayerCards(ParticipantCardsDto.from(player));
        }
    }

    private void playDealerTurn() {
        int dealerHitCount = game.playDealerTurn();
        view.printDealerHit(
            DealerHitDto.of(game.getDealer(), dealerHitCount));
    }

    private void printScore() {
        view.printScore(
            ParticipantScoreDtos.of(game.getDealer(), game.getPlayers()));
    }

    private void printProfit() {
        Map<Player, Profit> playerResults = calculatePlayerProfits();
        view.printResult(GameResultDtos.of(playerResults));
    }

    private Map<Player, Profit> calculatePlayerProfits() {
        return game.getPlayers().stream()
            .collect(Collectors.toMap(
                player -> player,
                game::calculateProfit
            ));
    }
}
