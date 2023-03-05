package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.CardsResponse;
import blackjack.view.dto.DealerResultResponse;
import blackjack.view.dto.DealerStateResponse;
import blackjack.view.dto.ParticipantResponse;
import blackjack.view.dto.PlayerResultResponse;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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

        final ParticipantResponse dealerResponse = getHiddenDealerResponse(participants.getDealer());
        final List<ParticipantResponse> playerResponse = getParticipantResponses(participants.getPlayers());

        outputView.printDealCards(dealerResponse, playerResponse, INITIAL_DRAW_COUNT);
    }

    private ParticipantResponse getHiddenDealerResponse(final Dealer dealer) {
        final List<Card> hiddenCards = dealer.getCards().subList(0, INITIAL_DRAW_COUNT - 1);
        final CardsResponse cardsResponse = new CardsResponse(-1, getCardInfos(hiddenCards));
        return new ParticipantResponse(dealer.getName(), cardsResponse);
    }

    private List<String> getCardInfos(final List<Card> cards) {
        return cards.stream()
                .map(card -> card.getNumberName() + card.getSuitName())
                .collect(Collectors.toList());
    }

    private List<ParticipantResponse> getParticipantResponses(final List<? extends Participant> participants) {
        return participants.stream()
                .map(this::getParticipantResponse)
                .collect(Collectors.toList());
    }

    private ParticipantResponse getParticipantResponse(final Participant participant) {
        final CardsResponse cardsResponse = new CardsResponse(
                participant.getScore(), getCardInfos(participant.getCards())
        );
        return new ParticipantResponse(participant.getName(), cardsResponse);
    }

    private void drawCard(final List<Player> players, final Deck deck) {
        for (final Player player : players) {
            drawCard(player, deck);
        }
    }

    private void drawCard(final Player player, final Deck deck) {
        while (player.isDrawable() && inputView.readMoreDraw(player.getName())) {
            player.drawCard(deck.draw());
            outputView.printHandedCardsWithoutScore(getParticipantResponse(player));
        }
    }

    private void drawCard(final Dealer dealer, final Deck deck) {
        if (dealer.isDrawable()) {
            dealer.drawCard(deck.draw());
            outputView.printDealerDrawn(new DealerStateResponse(true, dealer.getMaximumDrawableScore()));
        }
    }

    private void printResult(final Participants participants) {
        final List<ParticipantResponse> participantResponses = getParticipantResponses(participants.getParticipants());
        outputView.printHandedCardsWithScore(participantResponses);

        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        final List<PlayerResultResponse> playerResult = getPlayerResults(dealer, players);
        final DealerResultResponse dealerResult = getDealerResult(dealer, playerResult);

        outputView.printFinalResult(dealerResult, playerResult);
    }

    private List<PlayerResultResponse> getPlayerResults(final Dealer dealer, final List<Player> players) {
        return players.stream()
                .map(player -> new PlayerResultResponse(player.getName(), dealer.showResult(player.getScore())))
                .collect(Collectors.toList());
    }

    private DealerResultResponse getDealerResult(final Dealer dealer, final List<PlayerResultResponse> playerResults) {
        final Map<Result, Integer> dealerResult = initResult();
        for (final PlayerResultResponse playerResult : playerResults) {
            final Result result = playerResult.getResult().reverse();
            dealerResult.put(result, dealerResult.get(result) + 1);
        }
        return new DealerResultResponse(dealer.getName(), dealerResult);
    }

    private Map<Result, Integer> initResult() {
        final Map<Result, Integer> initResult = new EnumMap<>(Result.class);
        for (final Result result : Result.values()) {
            initResult.put(result, 0);
        }
        return initResult;
    }
}
