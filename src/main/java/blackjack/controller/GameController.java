package blackjack.controller;

import static blackjack.view.InputView.*;
import static blackjack.view.OutputView.*;
import static java.util.stream.Collectors.*;

import java.util.List;

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
        List<Name> names = getNames();
        Game game = initGame(names, getBettings(names));
        printDrawResult(game.getParticipants());

        drawPlayerCards(game);
        printDealerDrawCardCount(game.drawDealerCards());

        finalParticipantsCards(game.getParticipants());
        printFinalRevenues(game.getRevenues());
    }

    private List<Name> getNames() {
        return requestPlayerNames().stream()
            .map(Name::of)
            .collect(toUnmodifiableList());
    }

    private List<Betting> getBettings(List<Name> names) {
        return names.stream()
            .map(name -> new Betting(name, inputBettingMoney(name)))
            .collect(toUnmodifiableList());
    }

    private Game initGame(List<Name> names, List<Betting> bettings) {
        printInitResult(names);
        return new Game(new CardDeck(new ShuffleDeck()), names, bettings);
    }

    private void printDrawResult(List<Participant> participants) {
        for (Participant participant : participants) {
            OutputView.printParticipantCards(participant);
        }
        printEmptyLine();
    }

    private void drawPlayerCards(Game game) {
        if (game.RunningPlayer().isPresent()) {
            keepDrawing(game, game.RunningPlayer().get());
        }
    }

    private void keepDrawing(Game game, Player player) {
        while (player.isDrawable()) {
            game.drawPlayerCard(player, requestHitOrStay(player.getName()));
            printParticipantCards(player);
        }
    }

    private void finalParticipantsCards(List<Participant> participants) {
        for (Participant participant : participants) {
            printParticipantCardsWithScore(participant);
        }
    }
}