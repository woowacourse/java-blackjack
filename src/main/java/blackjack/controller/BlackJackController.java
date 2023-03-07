package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerResultResponse;
import blackjack.view.dto.DealerStateResponse;
import blackjack.view.dto.ParticipantResponse;
import blackjack.view.dto.PlayerResultResponse;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private static final int INITIAL_DRAW_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Participants participants = new Participants(new Dealer(), gatherPlayers());
        final Deck deck = Deck.createUsingTrump(1);

        dealCards(participants, deck);

        drawCard(participants.getPlayers(), deck);
        drawCard(participants.getDealer(), deck);

        printResult(participants);
    }

    private List<Player> gatherPlayers() {
        final List<String> playerNames = inputView.readPlayerNames();
        return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void dealCards(final Participants participants, final Deck deck) {
        participants.drawCard(deck, INITIAL_DRAW_COUNT);

        final ParticipantResponse dealerResponse = ParticipantResponse
                .hiddenForDealer(participants.getDealer(), INITIAL_DRAW_COUNT);
        final List<ParticipantResponse> playerResponse = getParticipantResponses(
                participants.getPlayers());

        outputView.printDealCards(dealerResponse, playerResponse, INITIAL_DRAW_COUNT);
    }

    private List<ParticipantResponse> getParticipantResponses(
            final List<? extends Participant> participants
    ) {
        return participants.stream()
                .map(ParticipantResponse::from)
                .collect(Collectors.toList());
    }

    private void drawCard(final List<Player> players, final Deck deck) {
        players.forEach(player -> drawCard(player, deck));
    }

    private void drawCard(final Player player, final Deck deck) {
        while (player.isDrawable() && inputView.readMoreDraw(player.getName())) {
            player.drawCard(deck.draw());
            outputView.printHandedCardsWithoutScore(ParticipantResponse.from(player));
        }
    }

    private void drawCard(final Dealer dealer, final Deck deck) {
        if (dealer.isDrawable()) {
            dealer.drawCard(deck.draw());
            outputView.printDealerDrawn(
                    new DealerStateResponse(true, dealer.getMaximumDrawableScore()));
        }
    }

    private void printResult(final Participants participants) {
        final List<ParticipantResponse> participantResponses = getParticipantResponses(
                participants.getParticipants());
        outputView.printHandedCardsWithScore(participantResponses);

        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        final List<PlayerResultResponse> playerResults = getPlayerResults(dealer, players);
        final DealerResultResponse dealerResult = DealerResultResponse.of(dealer, playerResults);

        outputView.printFinalResult(dealerResult, playerResults);
    }

    private List<PlayerResultResponse> getPlayerResults(
            final Dealer dealer,
            final List<Player> players
    ) {
        return players.stream()
                .map(player -> PlayerResultResponse.of(dealer, player))
                .collect(Collectors.toList());
    }
}
