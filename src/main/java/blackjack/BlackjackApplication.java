package blackjack;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.card.ShuffledCardsGenerator;
import blackjack.domain.participants.Player;
import blackjack.domain.game.BlackjackGameReferee;
import blackjack.domain.game.GameResult;
import blackjack.dto.DealerHitDto;
import blackjack.dto.GameResultDtos;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDtos;
import blackjack.view.BlackjackView;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackApplication {
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
        printResult();
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

    private void printResult() {
        Map<Player, GameResult> playerResults = parseResultMap();
        view.printResult(GameResultDtos.of(playerResults));
    }

    private Map<Player, GameResult> parseResultMap() {
        return game.getPlayers().stream()
            .collect(Collectors.toMap(
                player -> player,
                game::judge
            ));
    }

    public static void main(String[] args) {
        BlackjackView view = new BlackjackView(new InputView(), new OutputView());
        BlackjackGame game = BlackjackGame.create(
            new ShuffledCardsGenerator(),
            new BlackjackGameReferee(),
            view.readPlayers()
        );

        new BlackjackApplication(view, game).run();
    }
}
