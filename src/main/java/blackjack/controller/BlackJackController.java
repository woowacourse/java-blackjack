package blackjack.controller;

import blackjack.domain.BlackJackGame;
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
import blackjack.view.dto.DealerResultResponse;
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

        final BlackJackGame blackJackGame = startBlackJackGame(participants, deck);

        drawCardForPlayers(blackJackGame);
        drawCardForDealer(blackJackGame);

        printResult(blackJackGame);
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

    private BlackJackGame startBlackJackGame(final Participants participants, final Deck deck) {
        final BlackJackGame blackJackGame = new BlackJackGame(participants, deck, INIT_DRAW_COUNT);
        final ParticipantResponse dealerResponse = ParticipantResponse.from(blackJackGame.dealer());
        final List<ParticipantResponse> playerResponses = getPlayerResponses(blackJackGame.players());
        outputView.printDealCards(dealerResponse, playerResponses, INIT_DRAW_COUNT);
        return blackJackGame;
    }

    private List<ParticipantResponse> getPlayerResponses(final List<Player> players) {
        return players.stream()
                .map(ParticipantResponse::from)
                .collect(Collectors.toList());
    }

    private void drawCardForPlayers(final BlackJackGame blackJackGame) {
        while (blackJackGame.existDrawablePlayer()) {
            final Player drawablePlayer = blackJackGame.findDrawablePlayer();
            final boolean drawState = inputView.readDrawState(drawablePlayer.getName());
            blackJackGame.drawOrNot(drawState, drawablePlayer);
        }
    }

    private void drawCardForDealer(final BlackJackGame blackJackGame) {
        if (blackJackGame.isDealerDrawable()) {
            final Dealer dealer = blackJackGame.dealer();
            blackJackGame.drawOrNot(true, dealer);
            outputView.printDealerCardDrawn(DealerStateResponse.from(dealer));
        }
    }

    private void printResult(final BlackJackGame blackJackGame) {
        final Participants participants = blackJackGame.participants();
        final Dealer dealer = blackJackGame.dealer();
        final List<Player> players = blackJackGame.players();

        final List<ParticipantResponse> participantResponses = getParticipantResponses(participants.getParticipants());
        participantResponses.forEach(outputView::printHandedCardsWithScore);

        final DealerResultResponse dealerResultResponse = getDealerResultResponse(blackJackGame);
        final List<PlayerResultResponse> playerResultResponses = getPlayerResultResponses(dealer, players);

        outputView.printResult(dealerResultResponse, playerResultResponses);
    }

    private List<ParticipantResponse> getParticipantResponses(final List<Participant> participants) {
        return participants.stream()
                .map(ParticipantResponse::from)
                .collect(Collectors.toList());
    }

    private DealerResultResponse getDealerResultResponse(final BlackJackGame blackJackGame) {
        final Dealer dealer = blackJackGame.dealer();
        final List<Player> players = blackJackGame.players();
        return DealerResultResponse.of(dealer, dealer.getDealerResult(players));
    }

    private List<PlayerResultResponse> getPlayerResultResponses(final Dealer dealer, final List<Player> players) {
        return players.stream()
                .map(player -> PlayerResultResponse.of(player, dealer.compareScoreTo(player).reverseResult()))
                .collect(Collectors.toList());
    }
}
