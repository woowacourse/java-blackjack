package blackjack.service;

import blackjack.domain.ResultType;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.response.CardResponse;
import blackjack.response.DealerScoreResponse;
import blackjack.response.FinalResultResponse;
import blackjack.response.InitialCardResponse;
import blackjack.response.PlayerCardsResponse;
import blackjack.response.PlayersCardsResponse;
import blackjack.response.PlayersCardsResponse.CardsScore;
import blackjack.response.ResultTypeResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Participants participants;
    private final Deck deck;

    private BlackJackGame(final Players players, final DeckFactory deckFactory) {
        participants = new Participants(players, new Dealer());
        deck = deckFactory.generate();
    }

    public static BlackJackGame of(final List<String> playerNames, final DeckFactory deckFactory) {
        return new BlackJackGame(Players.from(playerNames), deckFactory);
    }

    public void distributeInitialCard() {
        participants.distributeInitialCards(deck);
    }

    public boolean isPlayerDrawable(final String playerName) {
        return participants.isPlayerDrawable(playerName);
    }

    public void drawPlayerCard(final String playerName) {
        participants.drawPlayerCard(playerName, deck.removeCard());
    }

    public void drawDealerCard() {
        participants.drawDealerCard(deck.removeCard());
    }

    public boolean isDealerDrawable() {
        return participants.isDealerDrawable();
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public InitialCardResponse getInitialCardResponse() {
        return InitialCardResponse.of(participants.getPlayers(), participants.getDealer());
    }

    public PlayerCardsResponse getPlayerCardsResponse(final String playerName) {
        final Player player = participants.findPlayerByName(playerName);
        final List<CardResponse> cardResponses = player.getCards().stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
        return new PlayerCardsResponse(player.getName(), cardResponses);
    }

    public DealerScoreResponse getDealerScoreResponse() {
        final Dealer dealer = participants.getDealer();
        final var cards = dealer.getCards().stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
        return new DealerScoreResponse(cards, dealer.currentScore());
    }

    public PlayersCardsResponse getPlayersCardsResponse() {
        final Map<String, CardsScore> playerNameToResult = participants.getPlayers().getPlayers().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> CardsScore.of(player.currentScore(), player),
                        (x, y) -> y,
                        LinkedHashMap::new)
                );
        return new PlayersCardsResponse(playerNameToResult);
    }

    public FinalResultResponse createFinalResultResponse() {
        final Map<String, ResultType> playersToResult = participants.calculateFinalResult();
        final Map<String, ResultTypeResponse> playersToResultResponse = generatePlayersResult(playersToResult);
        final Map<ResultTypeResponse, Long> dealerResult = generateDealerResult(playersToResult);
        return new FinalResultResponse(playersToResultResponse, dealerResult);
    }

    private LinkedHashMap<String, ResultTypeResponse> generatePlayersResult(
            final Map<String, ResultType> playersToResult) {
        return playersToResult.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        result -> ResultTypeResponse.from(result.getValue().getOppositeResult()),
                        (x, y) -> y,
                        LinkedHashMap::new));
    }

    private Map<ResultTypeResponse, Long> generateDealerResult(final Map<String, ResultType> playersToResult) {
        return playersToResult.entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        result -> ResultTypeResponse.from(result.getValue()),
                        Collectors.counting()));
    }
}
