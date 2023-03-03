package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.ParticipantResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class BlackJackController {

    private static final int INIT_DRAW_COUNT = 2;
    private static final Stack<Card> TRUMP;

    static {
        final Stack<Card> pack = new Stack<>();
        for (final Suit suit : Suit.values()) {
            for (final Number number : Number.values()) {
                pack.add(new Card(number, suit));
            }
        }
        TRUMP = pack;
    }

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Participants participants = gatherParticipants();
        final Deck deck = DeckFactory.createWithCount(TRUMP, 1);
        deck.shuffle();

    }

    private Participants gatherParticipants() {
        final List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        participants.addAll(createPlayers());
        return new Participants(participants);
    }

    private List<Player> createPlayers() {
        final List<String> playerNames = inputView.readPlayerNames();
        return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void dealCards(Participants participants, Deck deck) {
        participants.drawCard(deck, INIT_DRAW_COUNT);
        final ParticipantResponse dealerResponse = ParticipantResponse.from(participants.getDealer());
        final List<ParticipantResponse> playerResponse = getPlayerResponse(participants.getPlayers());
        outputView.printDealCards(dealerResponse, playerResponse, INIT_DRAW_COUNT);
    }

    private List<ParticipantResponse> getPlayerResponse(final List<Player> players) {
        return players.stream()
                .map(ParticipantResponse::from)
                .collect(Collectors.toList());
    }
}
