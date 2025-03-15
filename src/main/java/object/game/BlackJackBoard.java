package object.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import object.card.Card;
import object.card.CardDeck;
import object.participant.Dealer;
import object.participant.Participant;
import object.participant.Player;

public class BlackJackBoard {
    private final Map<Participant, CardDeck> cardDeckOfParticipant;
    private final CardDeck playingCard;

    public BlackJackBoard(final List<Participant> participants) {
        this.playingCard = CardDeck.generateFullPlayingCard();
        this.cardDeckOfParticipant = initializeCardDeckOfParticipant(participants);
    }

    public static BlackJackBoard generateOf(Dealer dealer, List<Player> players) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);

        return new BlackJackBoard(participants);
    }

    public void drawTwoCards() {
        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            CardDeck cardDeck = entry.getValue();

            Card firstCard = playingCard.draw();
            Card secondCard = playingCard.draw();
            cardDeck.addCard(firstCard);
            cardDeck.addCard(secondCard);
        }
    }

    public void drawCardTo(Participant participant) {
        Card drawedCard = playingCard.draw();
        CardDeck ownedCardDeck = cardDeckOfParticipant.get(participant);
        ownedCardDeck.addCard(drawedCard);
    }

    public boolean ableToDraw(Participant participant) {
        Score score = getScoreOf(participant);
        return participant.isAbleToDraw(score.getScore());
    }

    public void shufflePlayingCard() {
        playingCard.shuffle();
    }

    public Score getScoreOf(Participant participant) {
        CardDeck ownedCardDeck = cardDeckOfParticipant.get(participant);
        return ownedCardDeck.getScore();
    }

    public void calculateBattleResult() {
        Entry<Participant, CardDeck> cardDeckOfDealer = cardDeckOfParticipant.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isDealer())
                .findFirst()
                .orElseThrow();

        Participant dealer = cardDeckOfDealer.getKey();
        Score dealerScore = getScoreOf(dealer);

        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            Participant participant = entry.getKey();
            updateBattleResultBetween(dealer, participant, dealerScore);
        }
    }

    public void calculateDealerProfit() {
        Dealer dealer = (Dealer) getDealer();
        List<Participant> players = getPlayers();

        int totalPlayersProfit = 0;
        for (Participant player : players) {
            totalPlayersProfit += player.getProfit();
        }

        dealer.addEarnedMoney(totalPlayersProfit);
    }

    public CardDeck getCardDeckOf(Participant participant) {
        CardDeck cardDeck = cardDeckOfParticipant.get(participant);
        return new CardDeck(cardDeck);
    }

    public Map<Participant, CardDeck> getCardDeckOfParticipant() {
        return Collections.unmodifiableMap(cardDeckOfParticipant);
    }

    public CardDeck getPlayingCard() {
        return playingCard;
    }

    public List<Participant> getParticipants() {
        return cardDeckOfParticipant.keySet().stream()
                .toList();
    }

    public List<Participant> getPlayers() {
        return cardDeckOfParticipant.keySet().stream()
                .filter(participant -> !participant.isDealer())
                .toList();
    }

    public Participant getDealer() {
        return cardDeckOfParticipant.keySet().stream()
                .filter(Participant::isDealer)
                .findFirst()
                .orElseThrow();
    }

    private Map<Participant, CardDeck> initializeCardDeckOfParticipant(final List<Participant> participants) {
        return participants.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        participant -> CardDeck.generateEmptySet(),
                        (existing, replacement) -> replacement,
                        LinkedHashMap::new));

    }

    private void updateBattleResultBetween(Participant dealer, Participant participant, Score dealerScore) {
        if (participant.isDealer()) {
            return;
        }

        Score playerScore = getScoreOf(participant);
        GameResult playerGameResult = GameResult.getGameResultOfPlayer(playerScore, dealerScore);
        GameResult dealerGameResult = GameResult.getOppositeGameResult(playerGameResult);

        participant.applyGameRecord(playerGameResult);
        dealer.applyGameRecord(dealerGameResult);
    }
}
