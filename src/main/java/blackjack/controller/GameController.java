package blackjack.controller;

import static blackjack.view.InputView.*;
import static blackjack.view.OutputView.*;
import static java.util.stream.Collectors.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.Name;
import blackjack.domain.card.deckstrategy.ShuffleDeck;
import blackjack.domain.game.Betting;
import blackjack.domain.game.CardDeck;
import blackjack.domain.game.Game;
import blackjack.domain.game.Participant;
import blackjack.domain.game.Player;
import blackjack.view.OutputView;

public final class GameController {

    public void play() {
        Game game = initGame();
        drawCards(game);
        endGame(game);
    }

    private Game initGame() {
        List<Name> names = getNames();
        Game game = new Game(new CardDeck(new ShuffleDeck()), getBettings(names));
        printInitResult(names);
        firstDrawResult(game.getParticipants());
        return game;
    }

    private List<Name> getNames() {
        return requestPlayerNames().stream()
            .map(Name::of)
            .collect(toUnmodifiableList());
    }

    private Map<Name, Betting> getBettings(List<Name> names) {
        return names.stream()
            .collect(toMap(name -> name, name -> new Betting(inputBettingMoney(name)),
                (bettingA, bettingB) -> bettingB, LinkedHashMap::new));
    }

    private void firstDrawResult(List<Participant> participants) {
        for (Participant participant : participants) {
            OutputView.printParticipantCards(participant);
        }
        printEmptyLine();
    }

    private void drawCards(Game game) {
        drawPlayerCards(game);
        printDealerDrawCardCount(game.drawDealerCards());
    }

    private void drawPlayerCards(Game game) {
        for (Player player : game.getPlayers()) {
            keepDrawing(game, player);
        }
    }

    private void keepDrawing(Game game, Player player) {
        while (player.isDrawable()) {
            game.drawPlayerCard(player, requestHitOrStay(player.getName()));
            printParticipantCards(player);
        }
    }

    private void endGame(Game game) {
        finalParticipantsCards(game.getParticipants());
        printFinalRevenues(game.getRevenues());
    }

    private void finalParticipantsCards(List<Participant> participants) {
        for (Participant participant : participants) {
            printParticipantCardsWithScore(participant);
        }
    }
}