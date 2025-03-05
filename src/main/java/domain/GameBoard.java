package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

        List<Card> cards = new ArrayList<>();
        List<Card> aceCards = new ArrayList<>();

        for (Card card : ownedCards) {
            if (card.isAceCard()) {
                aceCards.add(card);
                continue;
            }
            cards.add(card);
        }

        int totalScore = cards.stream()
                            .mapToInt(Card::getNumber)
                            .sum();

        for (Card aceCard : aceCards) {
            if (totalScore + 11 <= 21) {
                totalScore += 11;
                continue;
            }

            totalScore += aceCard.getNumber();
        }

        return totalScore;
    }
}
