package blackjack.controller;

import static blackjack.view.InputView.*;
import static blackjack.view.OutputView.*;
import static java.util.stream.Collectors.*;

import java.util.List;

import blackjack.domain.Game;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.deckstrategy.ShuffleDeck;
import blackjack.domain.participant.Betting;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
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
        validatePlayersPresent(game.getPlayers());
        while (game.findHitPlayer().isPresent()) {
            Player player = game.findHitPlayer().get();
            game.drawPlayerCard(player, requestHitOrStay(player.getName()));
            printParticipantCards(player);
        }
    }

    private void validatePlayersPresent(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalStateException("플레이어가 존재하지 않습니다.");
        }
    }

    private void finalParticipantsCards(List<Participant> participants) {
        for (Participant participant : participants) {
            printParticipantCardsWithScore(participant);
        }
    }
}