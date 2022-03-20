package blackjack;

import static blackjack.view.OutputView.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.PlayersBettingAmount;
import blackjack.domain.Selection;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Drawable;
import blackjack.domain.card.HoldCards;
import blackjack.domain.player.BettingAmount;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame {

    public BlackjackGame() {}

    public void run() {
        Deck deck = Deck.create();
        List<Name> participantsName = requestNames();
        Participants participants = requestParticipants(deck, participantsName);
        PlayersBettingAmount playersBettingAmount = requestBettingAmount(participants.getPlayers());
        printParticipantsCards(participants);

        takeTurnParticipants(participantsName, participants, deck);
        takeTurnDealer(participants, deck);

        printParticipantsResult(participants);
        printScoreResult(playersBettingAmount.getResult(participants.findDealer()));
    }

    private static List<Name> requestNames() {
        try {
            return InputView.requestNames()
                .stream()
                .map(Name::new)
                .collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            printException(exception);
            return requestNames();
        }
    }

    private Participants requestParticipants(Deck deck, List<Name> participantsName) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer(HoldCards.drawTwoCards(deck)));
        participants.addAll(requestPlayers(participantsName, deck));
        return new Participants(participants);
    }

    private static List<Player> requestPlayers(List<Name> names, Deck deck) {
        try {
            return names.stream()
                .map(name -> new Player(name, HoldCards.drawTwoCards(deck)))
                .collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            printException(exception);
            return requestPlayers(names, deck);
        }
    }

    private static PlayersBettingAmount requestBettingAmount(List<Player> players) {
        Map<Player, BettingAmount> profitResult = new HashMap<>();
        for (Player player : players) {
            profitResult.put(player, requestBettingAmount(player));
        }
        return new PlayersBettingAmount(profitResult);
    }

    private static BettingAmount requestBettingAmount(Player player) {
        try {
            return new BettingAmount(InputView.requestBettingAmount(player.getName()));
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestBettingAmount(player);
        }
    }

    private void takeTurnParticipants(List<Name> participantsName, Participants participants, Drawable deck) {
        for (Name name : participantsName) {
            takeTurnParticipant(participants, deck, name);
        }
    }

    private void takeTurnParticipant(Participants participants, Drawable deck, Name name) {
        Participant participant = participants.findParticipant(name);
        while (participant.canHit() && requestSelection(participant) == Selection.YES) {
            participant.drawCard(deck);
            printCards(participant);
        }
    }

    private void takeTurnDealer(Participants participants, Drawable deck) {
        Participant dealer = participants.findDealer();
        while (dealer.canHit()) {
            printDealerDrawMessage();
            dealer.drawCard(deck);
        }
    }

    private Selection requestSelection(Participant participant) {
        try {
            return Selection.from(InputView.requestDrawCommand(participant));
        } catch (IllegalArgumentException exception) {
            printException(exception);
            return requestSelection(participant);
        }
    }
}
