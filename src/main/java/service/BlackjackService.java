package service;

import domain.Card;
import domain.CardDeck;
import domain.Participant;
import infra.CardShuffler;
import java.util.ArrayList;
import java.util.List;

public class BlackjackService {

    private final CardShuffler cardShuffler;

    public BlackjackService(CardShuffler cardShuffler) {
        this.cardShuffler = cardShuffler;
    }

    public List<Card> drawCard(CardDeck cardDeck, int drawCount) {

        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < drawCount; i++) {
            int cardIndex = cardShuffler.shuffleCardDeck(cardDeck.getDeckSize());
            cards.add(cardDeck.getCardOf(cardIndex));
            cardDeck.removeCardOf(cardIndex);
        }

        return cards;
    }

    public DealerFinalResult getDealerFinalResult(Participant dealer, List<Participant> players) {

        int dealerScore = dealer.getScore();

        int winCount = 0;
        int drawCount = 0;
        int loseCount = 0;
        for (Participant player : players) {
            int playerScore = player.getScore();
            if (player.isBust() || (!dealer.isBust() && dealerScore > playerScore)) {
                winCount++;
                continue;
            }

            if (!dealer.isBust() && dealerScore == playerScore) {
                drawCount++;
                continue;
            }

            loseCount++;
        }

        return new DealerFinalResult(winCount, drawCount, loseCount);
    }
}
