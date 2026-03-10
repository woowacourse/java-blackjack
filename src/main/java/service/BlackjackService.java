package service;

import domain.Card;
import domain.Deck;
import domain.Participant;
import domain.CardShuffler;
import java.util.ArrayList;
import java.util.List;

public class BlackjackService {

    private final CardShuffler cardShuffler;

    public BlackjackService(CardShuffler cardShuffler) {
        this.cardShuffler = cardShuffler;
    }

    public List<Card> drawCard(Deck deck, int drawCount) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < drawCount; i++) {
            int cardIndex = cardShuffler.getRandomCardIndex(deck.getDeckSize());
            cards.add(deck.getCardOf(cardIndex));
            deck.removeCardOf(cardIndex);
        }

        return cards;
    }

    public List<FinalResult> getFinalResults(Participant dealer, List<Participant> players) {
        List<FinalResult> finalResults = new ArrayList<>();
        int dealerScore = dealer.getScore();

        int dealerWinCount = 0;
        int dealerDrawCount = 0;
        int dealerLoseCount = 0;
        for (Participant player : players) {
            int playerScore = player.getScore();

            if (isDealerWin(dealer, player, dealerScore, playerScore)) {
                dealerWinCount++;
                finalResults.add(new FinalResult(player.getName(), 0, 0, 1, false));
                continue;
            }

            if (isDealerDraw(dealer, player, dealerScore, playerScore)) {
                dealerDrawCount++;
                finalResults.add(new FinalResult(player.getName(), 0, 1, 0, false));
                continue;
            }

            dealerLoseCount++; // 그외에 경우는 딜러 패배
            finalResults.add(new FinalResult(player.getName(), 1, 0, 0, false));
        }
        finalResults.add(
                new FinalResult(dealer.getName(), dealerWinCount, dealerDrawCount, dealerLoseCount, true));
        return finalResults;
    }

    private static boolean isDealerWin(Participant dealer, Participant player, int dealerScore, int playerScore) {
        return player.isBust()
                || (!dealer.isBust() && dealerScore > playerScore)
                || (dealer.isBlackjack() && !player.isBlackjack());
    }

    private static boolean isDealerDraw(Participant dealer, Participant player, int dealerScore, int playerScore) {
        return !dealer.isBust()
                && (dealerScore == playerScore)
                && ((player.isBlackjack() && dealer.isBlackjack())
                || (!player.isBlackjack() && !dealer.isBlackjack()));
    }

}
