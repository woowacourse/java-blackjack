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
import blackjack.view.dto.DealerStateResponse;
import blackjack.view.dto.ParticipantResponse;
import blackjack.view.dto.PlayerResultResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class BlackJackController {

    private static final int INIT_DRAW_COUNT = 2;
    private static final int DEALER_LIMIT = 16;
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
        dealCards(participants, deck);

        cardDraw(participants.getPlayers(), deck);
        cardDraw(participants.getDealer(), deck);

        printResult(participants);
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

    private void cardDraw(final List<Player> players, final Deck deck) {
        for (final Player player : players) {
            cardDraw(player, deck);
        }
    }

    private void cardDraw(final Player player, final Deck deck) {
        while (player.isDrawable() && inputView.readDrawState(player.getName())) {
            player.drawCard(deck.draw());
            outputView.printHandedCardsWithoutScore(ParticipantResponse.from(player));
        }
    }

    private void cardDraw(final Dealer dealer, final Deck deck) {
        if (dealer.isDrawable()) {
            dealer.drawCard(deck.draw());
            outputView.printDealerCardDrawn(DealerStateResponse.from(dealer));
        }
    }

    private void printResult(final Participants participants) {
        final List<ParticipantResponse> participantResponses = getParticipantResponses(participants.getParticipants());
        participantResponses.forEach(outputView::printHandedCardsWithScore);

        final Dealer dealer = participants.getDealer();
        final List<PlayerResultResponse> player = getPlayerResultResponses(dealer, participants.getPlayers());

        outputView.printResult(dealer.getName(), player);
    }

    private List<ParticipantResponse> getParticipantResponses(final List<Participant> participants) {
        return participants.stream()
                .map(ParticipantResponse::from)
                .collect(Collectors.toList());
    }

    public List<PlayerResultResponse> getPlayerResultResponses(final Dealer dealer, final List<Player> players) {
        return players.stream()
                .map(player -> PlayerResultResponse.of(player, dealer.compareScoreTo(player).reverseResult()))
                .collect(Collectors.toList());
    }
}
