package blackjack.controller;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;

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
import blackjack.domain.result.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerResultResponse;
import blackjack.view.dto.DealerStateResponse;
import blackjack.view.dto.ParticipantResponse;
import blackjack.view.dto.PlayerResultResponse;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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

        final BlackJackGame blackJackGame = new BlackJackGame(participants, deck);

        drawCardForPlayers(blackJackGame);
        drawCardForDealer(blackJackGame);

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

    private void printResult(final Participants participants) {
        final List<ParticipantResponse> participantResponses = getParticipantResponses(participants.getParticipants());
        participantResponses.forEach(outputView::printHandedCardsWithScore);

        final DealerResultResponse dealer = getDealerResultResponse(participants.getDealer(), participants.getPlayers());
        final List<PlayerResultResponse> player = getPlayerResultResponses(participants.getDealer(), participants.getPlayers());

        outputView.printResult(dealer, player);
    }

    private List<ParticipantResponse> getParticipantResponses(final List<Participant> participants) {
        return participants.stream()
                .map(ParticipantResponse::from)
                .collect(Collectors.toList());
    }

    private DealerResultResponse getDealerResultResponse(final Dealer dealer, final List<Player> players) {
        Map<Result, Integer> resultMap = initResultMap();
        for (Player player : players) {
            Result dealerResult = dealer.compareScoreTo(player);
            Integer currentResultCount = resultMap.get(dealerResult);
            resultMap.replace(dealerResult, currentResultCount + 1);
        }
        return DealerResultResponse.of(dealer, resultMap);
    }

    private Map<Result, Integer> initResultMap() {
        Map<Result, Integer> resultMap = new EnumMap<>(Result.class);
        resultMap.put(WIN, 0);
        resultMap.put(DRAW, 0);
        resultMap.put(LOSE, 0);
        return resultMap;
    }

    private List<PlayerResultResponse> getPlayerResultResponses(final Dealer dealer, final List<Player> players) {
        return players.stream()
                .map(player -> PlayerResultResponse.of(player, dealer.compareScoreTo(player).reverseResult()))
                .collect(Collectors.toList());
    }
}
