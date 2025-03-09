package domain;

import domain.card.Card;
import domain.card.GameCardDeck;
import domain.card.ParticipantCardDeck;
import domain.participant.BattleResult;
import domain.participant.Participant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameBoard {
    private static final int THRESHOLD = 21;

    private final Map<Participant, ParticipantCardDeck> cardDeckOfParticipant;
    private final GameCardDeck gameCardDeck;

    public GameBoard(final List<Participant> participants) {
        this.gameCardDeck = GameCardDeck.generateFullPlayingCard();
        this.cardDeckOfParticipant = initializeCardDeckOfParticipant(participants);
    }

    private Map<Participant, ParticipantCardDeck> initializeCardDeckOfParticipant(final List<Participant> participants) {
        return participants.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        participant -> ParticipantCardDeck.generateEmptySet(),
                        (existing, replacement) -> replacement,
                        LinkedHashMap::new));

    }

    public void drawTwoCards() {
        for (Map.Entry<Participant, ParticipantCardDeck> entry : cardDeckOfParticipant.entrySet()) {
            ParticipantCardDeck participantCardDeck = entry.getValue();

            Card firstCard = gameCardDeck.draw();
            Card secondCard = gameCardDeck.draw();
            participantCardDeck.addCard(firstCard);
            participantCardDeck.addCard(secondCard);
        }
    }

    public void drawCardTo(Participant participant) {
        Card drawedCard = gameCardDeck.draw();
        ParticipantCardDeck ownedParticipantCardDeck = cardDeckOfParticipant.get(participant);
        ownedParticipantCardDeck.addCard(drawedCard);
    }

    public boolean ableToDraw(Participant participant) {
        int score = getScoreOf(participant);
        return participant.ableToDraw(score);
    }

    public void shufflePlayingCard() {
        gameCardDeck.shuffle();
    }

    public int getScoreOf(Participant participant) {
        ParticipantCardDeck ownedParticipantCardDeck = cardDeckOfParticipant.get(participant);
        List<Card> ownedCards = ownedParticipantCardDeck.getCards();

        int totalScore = 0;
        int aceCounts = 0;

        for (Card card : ownedCards) {
            totalScore += card.getNumber();
            if (card.isAceCard()) {
                aceCounts++;
            }
        }

        while (aceCounts-- > 0) {
            if (totalScore + 10 <= THRESHOLD) {
                totalScore += 10;
            }
        }

        return totalScore;
    }

    public void calculateBattleResult() {
        Entry<Participant, ParticipantCardDeck> cardDeckOfDealer = cardDeckOfParticipant.entrySet()
                .stream()
                .filter(entry -> entry.getKey().areYouDealer())
                .findFirst()
                .orElseThrow();

        Participant dealer = cardDeckOfDealer.getKey();
        int dealerScore = getScoreOf(dealer);

        for (Map.Entry<Participant, ParticipantCardDeck> entry : cardDeckOfParticipant.entrySet()) {
            Participant participant = entry.getKey();
            updateBattleResultBetween(dealer, participant, dealerScore);
        }
    }

    private void updateBattleResultBetween(Participant dealer, Participant participant, int dealerScore) {
        if (participant.areYouDealer()) {
            return;
        }
        int score = getScoreOf(participant);
        if (score > THRESHOLD) {
            if (dealerScore > THRESHOLD) {
                updateBattleResultDraw(dealer, participant);
                return;
            }
            updateBattleResult(dealer, participant);
            return;
        }

        if (score > dealerScore) {
            updateBattleResult(participant, dealer);
            return;
        }

        if (score < dealerScore) {
            if (dealerScore > THRESHOLD) {
               updateBattleResult(participant, dealer);
               return;
            }
            updateBattleResult(dealer, participant);
            return;
        }

        updateBattleResultDraw(dealer, participant);
    }

    private void updateBattleResultDraw(Participant dealer, Participant player) {
        dealer.updateBattleResult(BattleResult.DRAW);
        player.updateBattleResult(BattleResult.DRAW);
    }

    private void updateBattleResult(Participant winner, Participant loser) {
        winner.updateBattleResult(BattleResult.WIN);
        loser.updateBattleResult(BattleResult.LOSE);
    }

    public ParticipantCardDeck getCardDeckOf(Participant participant) {
        ParticipantCardDeck participantCardDeck = cardDeckOfParticipant.get(participant);
        return new ParticipantCardDeck(participantCardDeck);
    }

    public Map<Participant, ParticipantCardDeck> getCardDeckOfParticipant() {
        return Collections.unmodifiableMap(cardDeckOfParticipant);
    }

    public GameCardDeck getPlayingCard() {
        return gameCardDeck;
    }
}
