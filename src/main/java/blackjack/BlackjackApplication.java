package blackjack;

import blackjack.domain.card.Hand;
import blackjack.domain.card.ShuffledCardsGenerator;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.BlackjackGameReferee;
import blackjack.domain.game.GameResult;
import blackjack.domain.participants.Bet;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.PlayerGroup;
import blackjack.domain.participants.PlayerNames;
import blackjack.dto.DealerHitDto;
import blackjack.dto.GameResultDtos;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDtos;
import blackjack.view.BlackjackView;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackApplication {
    public static void main(String[] args) {
        BlackjackView view = new BlackjackView(new InputView(), new OutputView());
        PlayerNames playerNames = PlayerNames.from(view.readPlayers());

        List<Player> players = playerNames.names().stream()
            .map(name -> new Player(
                name,
                new Hand(),
                new Bet(view.readBetAmount(name.getValue()))))
            .toList();

        BlackjackGame game = BlackjackGame.create(
            new ShuffledCardsGenerator(),
            new BlackjackGameReferee(),
            new PlayerGroup(players)
        );
        new BlackjackApplication(view, game).run();
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
}
