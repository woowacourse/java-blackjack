package blackjack.service;

import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Players;
import blackjack.domain.result.Score;
import blackjack.domain.result.WinStatus;
import blackjack.domain.result.WinningResult;
import blackjack.dto.CardDTO;
import blackjack.dto.FinalHandsScoreDTO;
import blackjack.dto.StartCardsDTO;
import blackjack.dto.WinningResultDTO;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private static final int START_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final List<String> playersName) {
        this.players = Players.from(playersName);
        this.dealer = new Dealer(Deck.create());
    }

    public boolean isNotDealerBlackjack() {
        return dealer.isNotBlackjack();
    }

    public StartCardsDTO start() {
        dealer.addCard(START_CARD_COUNT);

        players.divideCard(dealer.drawCards(players.count(), START_CARD_COUNT));

        return getStartCards();
    }

    private StartCardsDTO getStartCards() {
        final Map<ParticipantName, Hands> playersCard = players.getPlayersHands();

        final Hands dealerHands = dealer.getOpenedHands();
        playersCard.put(dealer.getName(), dealerHands);

        return StartCardsDTO.from(playersCard);
    }

    public List<String> getPlayersName() {
        return players.getNames().stream()
                .map(ParticipantName::getName)
                .toList();
    }

    public boolean isPlayerAliveByName(final String name) {
        return players.isNotDead(name);
    }

    public void addCardToPlayers(final String name) {
        players.addCardTo(name, dealer.drawCard());
    }

    public int giveDealerMoreCard() {
        int count = 0;

        while (dealer.needMoreCard()) {
            dealer.addCard();
            count++;
        }

        return count;
    }

    public List<CardDTO> getCardsOf(final String name) {
        return players.getHandsOf(name).getCards().stream()
                .map(CardDTO::from)
                .toList();
    }

    public FinalHandsScoreDTO getFinalResults() {
        final Map<ParticipantName, Hands> participantsHands = players.getPlayersHands();
        final Map<ParticipantName, Score> participantsScores = players.getPlayersScore();

        final Hands dealerHands = dealer.getHands();
        final Score dealerScore = dealer.calculateScore();

        participantsHands.put(dealer.getName(), dealerHands);
        participantsScores.put(dealer.getName(), dealerScore);

        return FinalHandsScoreDTO.of(participantsHands, participantsScores);
    }

    public WinningResultDTO getWinningResults() {
        final WinningResult winningResult = WinningResult.of(players, dealer.calculateScore());
        final Map<ParticipantName, WinStatus> playerWinningResults = winningResult.getParticipantsWinStatus();
        final Map<WinStatus, Long> dealerWinningResult = winningResult.summarizeDealerWinningResult();

        return WinningResultDTO.of(playerWinningResults, dealerWinningResult);
    }
}
