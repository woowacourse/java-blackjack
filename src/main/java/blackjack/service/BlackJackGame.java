package blackjack.service;

import blackjack.domain.BlackJackRule;
import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.response.CardResponse;
import blackjack.response.ResultTypeResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Participants participants;
    private final Deck deck;
    private final BlackJackRule blackJackRule;

    private BlackJackGame(final Players players, final DeckFactory deckFactory, final BlackJackRule blackJackRule) {
        participants = new Participants(players, new Dealer());
        deck = deckFactory.generate();
        this.blackJackRule = blackJackRule;
    }

    public static BlackJackGame of(
            final List<String> playerNames,
            final DeckFactory deckFactory,
            final BlackJackRule blackJackRule) {
        return new BlackJackGame(Players.from(playerNames), deckFactory, blackJackRule);
    }

    public void distributeInitialCard() {
        participants.distributeInitialCards(deck);
    }

    public boolean isPlayerDrawable(final String playerName) {
        return participants.isPlayerDrawable(playerName);
    }

    public void drawPlayerCard(final String playerName) {
        participants.drawPlayerCard(playerName, deck.popCard());
    }

    public void drawDealerCard() {
        participants.drawDealerCard(deck.popCard());
    }

    public boolean isDealerDrawable() {
        return participants.isDealerDrawable();
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public CardResponse getDealerFirstCard() {
        final Card dealerFirstCard = participants.getDealer().getFirstCard();
        return CardResponse.from(dealerFirstCard);
    }

    public Map<String, List<CardResponse>> getPlayerCards() {
        final Map<String, List<CardResponse>> playerCards = new HashMap<>();
        for (final String playerName : getPlayerNames()) {
            final List<CardResponse> cardResponses = participants.getPlayerCards(playerName).stream()
                    .map(CardResponse::from)
                    .collect(Collectors.toList());
            playerCards.put(playerName, cardResponses);
        }
        return playerCards;
    }

    public Map<String, Integer> getPlayerScores() {
        final Map<String, Integer> playerScores = new HashMap<>();
        for (final String playerName : getPlayerNames()) {
            final Player player = participants.findPlayerByName(playerName);
            playerScores.put(playerName, player.currentScore());
        }
        return playerScores;
    }

    public List<CardResponse> getPlayerCardsResponse(final String playerName) {
        final Player player = participants.findPlayerByName(playerName);
        return player.getCards().stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    public List<CardResponse> getDealerCardsResponse() {
        final Dealer dealer = participants.getDealer();
        return dealer.getCards().stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    public int getDealerScore() {
        return participants.getDealer().currentScore();
    }

    public Map<ResultTypeResponse, Long> getDealerResult() {
        final ParticipantResults participantResults = new ParticipantResults();
        final Dealer dealer = participants.getDealer();
        participants.getPlayers().getPlayers().forEach(player -> {
            final ResultType resultType = blackJackRule.calculateDealerResult(dealer, player);
            participantResults.addPlayerResult(player.getName(), resultType);
        });
        final Map<String, ResultType> playersToResult = participantResults.getPlayerNameToResultType();
        return playersToResult.entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        result -> ResultTypeResponse.from(result.getValue()),
                        Collectors.counting()));
    }

    public Map<String, ResultTypeResponse> generatePlayersResult() {
        final ParticipantResults participantResults = new ParticipantResults();
        final Dealer dealer = participants.getDealer();
        participants.getPlayers().getPlayers().forEach(player -> {
            final ResultType resultType = blackJackRule.calculateDealerResult(dealer, player).getOppositeResult();
            participantResults.addPlayerResult(player.getName(), resultType);
        });
        final Map<String, ResultType> playersToResult = participantResults.getPlayerNameToResultType();
        return playersToResult.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        result -> ResultTypeResponse.from(result.getValue()),
                        (x, y) -> y,
                        LinkedHashMap::new));
    }

    private static class ParticipantResults {

        private final Map<String, ResultType> playerNameToResultType = new HashMap<>();

        void addPlayerResult(final String playerName, final ResultType resultType) {
            playerNameToResultType.put(playerName, resultType);
        }

        Map<String, ResultType> getPlayerNameToResultType() {
            return playerNameToResultType;
        }
    }
}
