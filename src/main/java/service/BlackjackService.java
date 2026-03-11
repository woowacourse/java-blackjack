package service;

import domain.Card;
import domain.CardDeck;
import domain.Participant;
import infra.CardShuffler;
import java.util.ArrayList;
import java.util.List;

// TODO: Service가 존재해야 하는가? 책임을 더 적합한 곳으로 옮겨보자
public class BlackjackService {

    private final CardShuffler cardShuffler;

    public BlackjackService(final CardShuffler cardShuffler) {
        this.cardShuffler = cardShuffler;
    }

    public List<Card> drawCard(final CardDeck cardDeck, final int drawCount) {
        final List<Card> cards = new ArrayList<>();

        // FIXME: Deck 내부를 Deque로 수정한 뒤, 전면 수정될 부분
        for (int i = 0; i < drawCount; i++) {
            final int cardIndex = cardShuffler.shuffleCardDeck(cardDeck.getDeckSize());
            cards.add(cardDeck.getCardOf(cardIndex));
            cardDeck.removeCardOf(cardIndex);
        }

        return cards;
    }

    public List<FinalResult> getFinalResults(final Participant dealer, final List<Participant> players) {
        final List<FinalResult> finalResults = new ArrayList<>();
        final int dealerScore = dealer.getScore();

        int dealerWinCount = 0;
        int dealerDrawCount = 0;
        int dealerLoseCount = 0;
        for (final Participant player : players) {
            final int playerScore = player.getScore();

            if (isDealerWin(dealer, player, dealerScore, playerScore)) { // 딜러 승
                dealerWinCount++;
                finalResults.add(new FinalResult(player.getName(), 0, 0, 1, false));
                continue;
            }

            if (isDealerDraw(dealer, player, dealerScore, playerScore)) { // 무승부
                dealerDrawCount++;
                finalResults.add(new FinalResult(player.getName(), 0, 1, 0, false));
                continue;
            }

            dealerLoseCount++; // 딜러 패배
            finalResults.add(new FinalResult(player.getName(), 1, 0, 0, false));
        }
        finalResults.add(
                new FinalResult(dealer.getName(), dealerWinCount, dealerDrawCount, dealerLoseCount, true));
        return finalResults;
    }

    private static boolean isDealerWin(final Participant dealer, final Participant player, final int dealerScore,
                                       final int playerScore) {
        return player.isBust()
                || (!dealer.isBust() && dealerScore > playerScore)
                || (dealer.isBlackjack() && !player.isBlackjack());
    }

    private static boolean isDealerDraw(final Participant dealer, final Participant player, final int dealerScore,
                                        final int playerScore) {
        return !dealer.isBust()
                && (dealerScore == playerScore)
                && ((player.isBlackjack() && dealer.isBlackjack())
                || (!player.isBlackjack() && !dealer.isBlackjack()));
    }

}
