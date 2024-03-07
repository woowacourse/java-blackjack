package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Hands;
import blackjack.domain.ParticipantName;
import blackjack.domain.Participants;
import blackjack.domain.Score;
import blackjack.domain.WinStatus;
import blackjack.domain.WinningResult;
import blackjack.dto.FinalResultDTO;
import blackjack.dto.StartCardsDTO;
import blackjack.dto.WinningResultDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final Participants participants;
    private final Dealer dealer;

    public BlackjackGame(final Participants participants) {
        this.participants = participants;
        this.dealer = new Dealer(Deck.create());
    }

    public StartCardsDTO start() {
        dealer.shuffleDeck();
        dealer.addStartCard();

        final int playersCardCount = participants.count() * 2;
        participants.divideCard(dealer.drawCards(playersCardCount));

        return getStartCards();
    }

    private StartCardsDTO getStartCards() {
        final Map<ParticipantName, Hands> playersCard = participants.getPlayerHands();

        final Hands dealerHands = dealer.getOpenedHands();
        playersCard.put(dealer.getName(), dealerHands);

        return StartCardsDTO.of(playersCard);
    }

    public void addCardToParticipant(final ParticipantName name) {
        participants.addCardTo(name, dealer.drawCard());
    }

    public int giveDealerMoreCard() {
        int count = 0;

        while (dealer.needMoreCard()) {
            dealer.addCard();
            count++;
        }

        return count;
    }

    public FinalResultDTO getFinalResults() {
        Map<ParticipantName, Hands> participantsHands = participants.getPlayerHands();
        Map<ParticipantName, Score> participantsScores =  participants.getPlayerScores();

        final Hands dealerHands = dealer.getHands();
        final Score dealerScore = dealer.calculate();

        participantsHands.put(dealer.getName(), dealerHands);
        participantsScores.put(dealer.getName(), dealerScore);

        return FinalResultDTO.of(participantsHands, participantsScores);
    }

    public WinningResultDTO getWinningResults() {
        final Map<ParticipantName, WinStatus> rawPlayerWinningResult = participants.determineWinStatus(dealer.calculate());

        final Map<String, String> playerWinningResults = new LinkedHashMap<>();
        rawPlayerWinningResult.forEach((key, value) -> playerWinningResults.put(key.getName(), value.name()));

        final WinningResult winningResult = new WinningResult(rawPlayerWinningResult);
        final Map<WinStatus, Integer> dealerWinningResult = winningResult.summarizeDealerResult();

        return WinningResultDTO.of(playerWinningResults, dealerWinningResult);
    }

    public boolean isPlayerAliveByName(final ParticipantName name) {
        return participants.isNotDead(name);
    }

    public boolean isNotDealerWin() {
        return dealer.isNotBlackjack();
    }

    public List<Card> getCardsOf(final ParticipantName name) {
        final Hands hands = participants.getCardsOf(name);

        return hands.getCards();
    }

    public List<ParticipantName> getParticipantsName() {
        return participants.getNames();
    }
}
