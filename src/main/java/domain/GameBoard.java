package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.BattleResult;
import domain.participant.Participant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameBoard {
    private final Map<Participant, CardDeck> cardDeckOfParticipant;
    private final CardDeck gameCardDeck;

    public GameBoard(final List<Participant> participants) {
        this.gameCardDeck = CardDeck.generateFullSet();
        this.cardDeckOfParticipant = initializeCardDeckOfParticipant(participants);
    }

    private Map<Participant, CardDeck> initializeCardDeckOfParticipant(final List<Participant> participants) {
        return participants.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        participant -> CardDeck.generateEmptySet()));
    }

    public void drawTwoCards() {
        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            CardDeck cardDeck = entry.getValue();

            Card firstCard = gameCardDeck.draw();
            Card secondCard = gameCardDeck.draw();
            cardDeck.addCard(firstCard);
            cardDeck.addCard(secondCard);
        }
    }

    public void drawCardTo(Participant participant) {
        Card drawedCard = gameCardDeck.draw();
        CardDeck ownedCardDeck = cardDeckOfParticipant.get(participant);
        ownedCardDeck.addCard(drawedCard);
    }

    public CardDeck getCardDeckOf(Participant participant) {
        CardDeck cardDeck = cardDeckOfParticipant.get(participant);
        return new CardDeck(cardDeck);
    }

    public Map<Participant, CardDeck> getCardDeckOfParticipant() {
        return Collections.unmodifiableMap(cardDeckOfParticipant);
    }

    public CardDeck getPlayingCard() {
        return gameCardDeck;
    }

    public boolean ableToDraw(Participant participant) {
        int score = getScoreOf(participant);
        return participant.ableToDraw(score);
    }

    public int getScoreOf(Participant participant) {
        CardDeck ownedCardDeck = cardDeckOfParticipant.get(participant);
        List<Card> ownedCards = ownedCardDeck.getCards();
        int totalScore = 0;
        int aceCounts = 0;
        for (Card card : ownedCards) {
            totalScore += card.getNumber();
            if (card.isAceCard()) {
                aceCounts++;
            }
        }
        while (aceCounts-- > 0) {
            if (totalScore + 10 <= 21) {
                totalScore += 10;
            }
        }
        return totalScore;
    }

    public void calculateBattleResult() {
        Entry<Participant, CardDeck> cardDeckOfDealer = cardDeckOfParticipant.entrySet()
                .stream()
                .filter(entry -> !entry.getKey().areYouPlayer())
                .findFirst()
                .orElseThrow();

        Participant dealer = cardDeckOfDealer.getKey();
        int dealerScore = getScoreOf(dealer);
        Map<BattleResult, Integer> battleResultOfDealer = dealer.getBattleResult();

        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            Participant participant = entry.getKey();
            if (!participant.areYouPlayer()) {
                continue;
            }

            int score = getScoreOf(participant);
            if (dealerScore > score) {
                Map<BattleResult, Integer> battleResult = participant.getBattleResult();
                battleResult.merge(BattleResult.LOSE, 1, Integer::sum);
                battleResultOfDealer.merge(BattleResult.WIN, 1, Integer::sum);
                continue;
            }
            if (dealerScore < score) {
                Map<BattleResult, Integer> battleResult = participant.getBattleResult();
                battleResult.merge(BattleResult.WIN, 1, Integer::sum);
                battleResultOfDealer.merge(BattleResult.LOSE, 1, Integer::sum);
                continue;
            }
            Map<BattleResult, Integer> battleResult = participant.getBattleResult();
            battleResult.merge(BattleResult.DRAW, 1, Integer::sum);
            battleResultOfDealer.merge(BattleResult.DRAW, 1, Integer::sum);
        }
    }
}
