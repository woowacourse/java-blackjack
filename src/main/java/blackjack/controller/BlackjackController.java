package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import blackjack.util.DtoAssembler;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.PlayerDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackjackController {

    private final Cards cards;

    public BlackjackController(Cards cards) {
        this.cards = cards;
    }

    public void run() {
        Game game = gameInitialize();

        drawCardToPlayers(game);

        printCurrentDeckAndScore(game.getGamersAsList(), game.getDealer());

        OutputView.printResult(game.getDealerResult(), game.getGamerResult());
    }

    private Game gameInitialize() {
        Dealer dealer = new Dealer();
        Gamers gamers = new Gamers(InputView.getGamerNamesFromUser());
        Game game = new Game(cards, dealer, gamers);

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
        List<String> names = game.getGamerNames();
        for (String gamerName : names) {
            drawCardToGamer(game, gamerName);
        }
    }

    private void drawCardToGamer(Game game, String gamerName) {
        while (InputView.getYesOrNo(gamerName) && game.drawCardToGamer(gamerName)) {
            OutputView.printPlayersDeckState(
                DtoAssembler.createPlayerDto(
                    game.findGamerByName(gamerName)
                )
            );
        }
    }

    private void drawCardToDealer(Game game) {
        if (game.drawCardToDealer()) {
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
