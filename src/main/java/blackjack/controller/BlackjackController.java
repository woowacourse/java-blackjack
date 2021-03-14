package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import blackjack.domain.result.ResultOfPlayers;
import blackjack.util.DtoAssembler;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.PlayerDto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BlackjackController {

    public void run() {
        Game game = gameInitialize();
        printInitializeResult(game);

        drawCardToPlayers(game);

        printCurrentDeckAndScore(game.getGamersAsList(), game.getDealer());

        ResultOfPlayers resultOfPlayers = game.getResultOfPlayers();
        OutputView.printFinalWinAndLoseResult(resultOfPlayers.getResultOfDealer(), resultOfPlayers.getResultOfGamers());
        OutputView.printFinalRevenueResult(resultOfPlayers.getResultOfDealer(), resultOfPlayers.getResultOfGamers());
    }

    private Game gameInitialize() {
        Cards cards = new Cards(CardFactory.getNormalCards());
        cards.shuffle();

        return new Game(
                cards,
                new Dealer(),
                new Gamers(createGamerByUser())
        );
    }

    private List<Gamer> createGamerByUser() {
        return InputView.getGamerNamesFromUser().stream()
                .map(name -> new Gamer(name, InputView.getBettingMoneyFromUser(name)))
                .collect(toList());
    }

    private void printInitializeResult(Game game) {
        List<PlayerDto> playerDtos = DtoAssembler.createPlayerDtos(game.getGamersAsList());
        PlayerDto dealerDto = DtoAssembler.createPlayerDto(game.getDealer());

        OutputView.printDrawResult(playerDtos);
        OutputView.printDealerAndPlayersDeckState(dealerDto, playerDtos);
    }

    private void drawCardToPlayers(Game game) {
        drawCardToGamers(game);
        drawCardToDealer(game);
    }

    private void drawCardToGamers(Game game) {
        List<String> names = game.getGamerNames();
        for (String gamerName : names) {
            drawCardToGamer(game, gamerName);
        }
    }

    private void drawCardToGamer(Game game, String gamerName) {
        while (game.isGamerDrawable(gamerName) && InputView.getYesOrNo(gamerName)) {
            game.drawCardToPlayer(gamerName);

            OutputView.printPlayersDeckState(
                    DtoAssembler.createPlayerDto(
                            game.findGamerByName(gamerName)
                    )
            );
        }
    }

    private void drawCardToDealer(Game game) {
        if (game.isDealerDrawable()) {
            game.drawCardToDealer();
            OutputView.dealerDrawsCard();
        }
    }

    private void printCurrentDeckAndScore(List<Player> gamers, Player dealer) {
        List<Player> allPlayers = Stream.of(gamers.stream(), Stream.of(dealer))
                .flatMap(Function.identity())
                .collect(toList());

        OutputView.printCurrentDeckAndScore(getAllPlayerDtos(allPlayers));

    }

    private List<PlayerDto> getAllPlayerDtos(List<Player> allPlayers) {
        return allPlayers.stream()
                .map(DtoAssembler::createPlayerDto)
                .collect(toList());
    }

}
