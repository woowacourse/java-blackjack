package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckCardGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Judge;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Blackjack {

    private static final int INIT_CARD_SIZE = 2;

    public void play() {
        final Deck deck = new Deck(new DeckCardGenerator());
        final List<Player> participants = createParticipants(InputView.responseNames(), deck);
        final Player dealer = createDealer(deck);
        final Players players = new Players(participants, dealer);

        OutputView.printPlayersInitCardInfo(players);
        decideGetMoreCard(players, deck);
        announcePlayersFinishInfo(players);
        OutputView.printGameResult(Judge.calculateGameResult(players));
    }

    private List<Player> createParticipants(final List<String> names, final Deck deck) {
        return names.stream()
                .map(name -> new Participant(makeInitCards(deck), name))
                .collect(Collectors.toList());
    }

    private Dealer createDealer(final Deck deck) {
        return new Dealer(makeInitCards(deck));
    }

    private List<Card> makeInitCards(final Deck deck) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_SIZE; i++) {
            cards.add(deck.draw());
        }
        return cards;
    }

    private void decideGetMoreCard(final Players players, final Deck deck) {
        decideParticipantsMoreCard(players, deck);
        decideDealerMoreCard(players, deck);
    }

    private void decideParticipantsMoreCard(final Players players, final Deck deck) {
        players.initParticipantPointer();
        while (!players.isParticipantPointerEnd()) {
            decideParticipantMoreCard(players, deck);
            players.moveParticipantPointer();
        }
    }

    private void decideParticipantMoreCard(final Players players, final Deck deck) {
        while (isNotOverMaxScore(players) && InputView.oneMoreCard(players.pointParticipantName())) {
            players.addPointParticipantCard(deck.draw());
            OutputView.printPlayerCardInfo(players.pointParticipant());
        }
    }

    private boolean isNotOverMaxScore(final Players players) {
        if (!players.isPointParticipantAcceptableCard()) {
            OutputView.printParticipantOverMaxScore(players.pointParticipantName());
        }
        return players.isPointParticipantAcceptableCard();
    }

    private void decideDealerMoreCard(final Players players, final Deck deck) {
        if (players.isDealerAcceptableCard()) {
            players.addDealerCard(deck.draw());
            OutputView.printDealerAcceptCard();
            return;
        }
        OutputView.printDealerDenyCard();
    }

    private void announcePlayersFinishInfo(final Players players) {
        OutputView.printPlayerFinalInfo(players.getDealer());
        OutputView.printFinishParticipantInfo(players.getParticipants());
    }
}
