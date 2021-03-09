package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Game;
import blackjack.domain.GameResult;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import blackjack.util.CardFactory;
import blackjack.util.DtoAssembler;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.PlayerDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackController {

    public void run() {
        Game game = gameInitialize();

        drawCardToPlayers(game);

        printCurrentDeckAndScore(game.getGamersAsList(), game.getDealer());

        GameResult gameResult = game.gameResult();

        OutputView.printResult(gameResult.dealerResult(), gameResult.gamerResult());
    }

    private Game gameInitialize() {
        Dealer dealer = new Dealer();
        Gamers gamers = new Gamers(InputView.getGamerNamesFromUser());
        Cards cards = new Cards(CardFactory.getNormalCards());
        Game game = Game.of(cards, dealer, gamers);

        List<PlayerDto> playerDtos = DtoAssembler.createPlayerDtos(game.getGamersAsList());
        PlayerDto dealerDto = DtoAssembler.createPlayerDto(dealer);

        OutputView.printDrawResult(playerDtos);
        OutputView.printDealerAndPlayersDeckState(dealerDto, playerDtos);

        return game;
    }

    private void drawCardToPlayers(Game game) {
        drawCardToGamers(game);
        drawCardToDealer(game);
    }

    private void drawCardToGamers(Game game) {
        game.getGamersAsList()
            .forEach(gamer -> drawCardToGamer(game, gamer));
    }

    private void drawCardToGamer(Game game, Player player) {
        while (game.isPlayerDrawable(player) && InputView.getYesOrNo(player.getName())) {
            game.drawCardToPlayer(player);
            OutputView.printPlayersDeckState(DtoAssembler.createPlayerDto(player));
        }
    }

    private void drawCardToDealer(Game game) {
        if (game.isPlayerDrawable(game.getDealer())) {
            game.drawCardToPlayer(game.getDealer());
            OutputView.dealerDrawsCard();
        }
    }

    private void printCurrentDeckAndScore(List<Player> gamers, Player dealer) {
        List<Player> allPlayers = new ArrayList<>(Collections.singletonList(dealer));
        allPlayers.addAll(gamers);

        OutputView.printCurrentDeckAndScore(
            allPlayers.stream()
                .map(DtoAssembler::createPlayerDto)
                .collect(toList())
        );
    }

}
