package blackjack.service;

import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Players;
import blackjack.domain.result.WinStatus;
import blackjack.domain.result.WinningResult;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.PlayerCardsDto;
import blackjack.dto.StartCardsDto;
import blackjack.dto.WinningResultDto;
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

    public StartCardsDto start() {
        dealer.addCard(START_CARD_COUNT);

        players.divideCard(dealer.drawCards(players.count(), START_CARD_COUNT));

        return getStartCards();
    }

    private StartCardsDto getStartCards() {
        final Map<ParticipantName, Hands> playersCard = players.getPlayersHands();
        final Hands dealerHands = dealer.getOpenedHands();

        return StartCardsDto.of(playersCard, dealerHands);
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

    public PlayerCardsDto getCardsOf(final String name) {
        final Hands hands = players.getHandsOf(name);
        return PlayerCardsDto.of(name, hands);
    }

    public FinalHandsScoreDto getFinalHandsScore() {
        final Map<ParticipantName, Hands> participantsHands = players.getPlayersHands();
        final Hands dealerHands = dealer.getHands();

        return FinalHandsScoreDto.of(participantsHands, dealerHands);
    }

    public WinningResultDto getWinningResults() {
        final WinningResult winningResult = WinningResult.of(players, dealer.calculateScore());
        final Map<ParticipantName, WinStatus> playerWinningResults = winningResult.getParticipantsWinStatus();
        final Map<WinStatus, Long> dealerWinningResult = winningResult.summarizeDealerWinningResult();

        return WinningResultDto.of(playerWinningResults, dealerWinningResult);
    }
}
