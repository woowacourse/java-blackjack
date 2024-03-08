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
import blackjack.dto.FinalResultDTO;
import blackjack.dto.StartCardsDTO;
import blackjack.dto.WinningResultDTO;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final List<String> playersName) {
        this.players = Players.from(playersName);
        this.dealer = new Dealer(Deck.create());
    }

    public StartCardsDTO start() {
        dealer.shuffleDeck();
        dealer.addStartCard();

        final int playersCardCount = players.count() * 2;
        players.divideCard(dealer.drawCards(playersCardCount));

        return getStartCards();
    }

    private StartCardsDTO getStartCards() {
        final Map<ParticipantName, Hands> playersCard = players.getPlayerHands();

        final Hands dealerHands = dealer.getOpenedHands();
        playersCard.put(dealer.getName(), dealerHands);

        return StartCardsDTO.of(playersCard);
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

    public FinalResultDTO getFinalResults() {
        final Map<ParticipantName, Hands> participantsHands = players.getPlayerHands();
        final Map<ParticipantName, Score> participantsScores = players.getPlayerScores();

        final Hands dealerHands = dealer.getHands();
        final Score dealerScore = dealer.calculate();

        participantsHands.put(dealer.getName(), dealerHands);
        participantsScores.put(dealer.getName(), dealerScore);

        return FinalResultDTO.of(participantsHands, participantsScores);
    }

    public WinningResultDTO getWinningResults() {
        final WinningResult winningResult = WinningResult.of(players, dealer.calculate());
        final Map<ParticipantName, WinStatus> playerWinningResults = winningResult.getParticipantsWinStatus();
        final Map<WinStatus, Long> dealerWinningResult = winningResult.summarizeDealerWinningResult();

        return WinningResultDTO.of(playerWinningResults, dealerWinningResult);
    }

    public boolean isPlayerAliveByName(final String name) {
        return players.isNotDead(name);
    }

    public boolean isNotDealerBlackjack() {
        return dealer.isNotBlackjack();
    }

    public List<CardDTO> getCardsOf(final String name) {
        return players.getCardsOf(name).getCards().stream()
                .map(CardDTO::from)
                .toList();
    }

    public List<String> getPlayersName() {
        return players.getNames().stream()
                .map(ParticipantName::getName)
                .toList();
    }
}
