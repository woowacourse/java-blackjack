package blackjack.service;

import blackjack.domain.Dealer;
import blackjack.domain.Hands;
import blackjack.domain.Participant;
import blackjack.domain.ParticipantsName;
import blackjack.domain.Participants;
import blackjack.domain.Score;
import blackjack.domain.WinStatus;
import blackjack.domain.WinningResult;
import blackjack.dto.CardDTO;
import blackjack.dto.FinalResultDTO;
import blackjack.dto.StartCardsDTO;
import blackjack.dto.WinningResultDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    public static final String DEALER_SIGNAL = "DEALER";
    private final Participants participants;
    private final Dealer dealer;

    public BlackjackGame(final Participants participants) {
        this.participants = participants;
        this.dealer = (Dealer) Participant.from(DEALER_SIGNAL);
    }

    public StartCardsDTO start() {
        dealer.shuffleDeck();
        dealer.addStartCard();

        int playersCardCount = participants.count() * 2;
        participants.divideCard(dealer.drawCards(playersCardCount));

        return getStartCards();
    }

    private StartCardsDTO getStartCards() {
        Hands dealerHands = dealer.getOpenedHands();
        Map<ParticipantsName, Hands> playersCard = participants.getPlayerHands();
        playersCard.put(dealer.getName(), dealerHands);

        return StartCardsDTO.of(playersCard);
    }

    public FinalResultDTO getFinalResults() {
        Hands dealerHands = dealer.getHands();
        Score dealerScore = dealer.calculate();

        Map<ParticipantsName, Hands> playersHands = participants.getPlayerHands();
        Map<ParticipantsName, Score> playersScores =  participants.getPlayerScores();

        playersHands.put(dealer.getName(), dealerHands);
        playersScores.put(dealer.getName(), dealerScore);

        return FinalResultDTO.of(playersHands, playersScores);
    }


    public WinningResultDTO getWinningResults() {
        Map<ParticipantsName, WinStatus> rawWinningResult = participants.determineWinStatus(dealer.calculate());

        Map<String, String> winningResults = new LinkedHashMap<>();
        rawWinningResult.forEach((key, value) -> winningResults.put(key.getName(), value.name()));

        WinningResult winningResult = new WinningResult(rawWinningResult);
        Map<WinStatus, Integer> dealerResult = winningResult.summarizeDealerResult();

        return WinningResultDTO.of(winningResults, dealerResult);
    }

    public boolean isDealerBlackjack() {
        return dealer.getStatus().isBlackjack();
    }

    public List<ParticipantsName> getParticipantsName() {
        return participants.getNames();
    }

    public void addCardToParticipant(final ParticipantsName name) {
        participants.addCardTo(name, dealer.drawCard());
    }

    public boolean isAlive(final ParticipantsName name) {
        return participants.isAlive(name) || !participants.isNotBlackjack(name); // TODO : 부정
    }

    public int giveDealerMoreCard() {
        int count = 0;
        while (dealer.needMoreCard()) {
            dealer.addCard();
            count++;
        }
        return count;
    }

    public List<CardDTO> getCardsOf(final ParticipantsName name) {
        Hands hands = participants.getCardsOf(name);
        return hands.getCards().stream()
                .map(CardDTO::from)
                .toList();
    }
}
