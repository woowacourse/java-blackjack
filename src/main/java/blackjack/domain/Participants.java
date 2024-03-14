package blackjack.domain;

import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Players;
import blackjack.domain.result.Score;
import blackjack.domain.result.WinningResult;
import blackjack.dto.CardDto;
import blackjack.dto.ParticipantCardsDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {
    private final Players players;
    private final Dealer dealer;

    public Participants(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void addStartCards(final Deck deck) {
        final int playersCardCount = players.count() * 2;
        players.divideCard(deck.pick(playersCardCount));

        dealer.addCard(deck.pick(2));
    }

    public List<ParticipantCardsDto> getStartCards() {
        final Map<ParticipantName, Hands> playersCard = players.getPlayerHands();

        List<ParticipantCardsDto> participantCardsDtos = playersCard.entrySet().stream()
                .map(entry -> ParticipantCardsDto.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        participantCardsDtos.add(ParticipantCardsDto.of(dealer.getName(), dealer.getOpenedCards()));
        return participantCardsDtos;
    }

    public boolean addCardToPlayers(final String name, final Deck deck) {
        if (!isPlayerAliveByName(name)) {
            return false;
        }

        players.addCardTo(name, deck.pick());

        return true;
    }

    private boolean isPlayerAliveByName(final String name) {
        return players.isNotDead(name);
    }

    public int giveDealerMoreCards(final Deck deck) {
        int count = 0;

        while (dealer.needMoreCard()) {
            dealer.addCard(deck.pick());
            count++;
        }

        return count;
    }

    public Map<ParticipantName, Hands> getHandResult() {
        final Map<ParticipantName, Hands> participantsHands = players.getPlayerHands();
        final Hands dealerHands = dealer.getHands();

        participantsHands.put(dealer.getName(), dealerHands);

        return participantsHands;
    }

    public Map<ParticipantName, Score> getScoreResult() {
        final Map<ParticipantName, Score> participantsScores = players.getPlayerScores();
        final Score dealerScore = dealer.calculate();

        participantsScores.put(dealer.getName(), dealerScore);

        return participantsScores;
    }

    public WinningResult getWinningResult() {
//        return WinningResult.of(players, dealer.calculate());
        return WinningResult.of(players, dealer);
    }

    public boolean isDealerNotBlackjack() {
        return dealer.isNotBlackjack();
    }

    public List<CardDto> getCardsOf(final String name) {
        return players.getCardsOf(name).getCards().stream()
                .map(CardDto::from)
                .toList();
    }

    public List<String> getPlayersNames() {
        return players.getNames().stream()
                .map(ParticipantName::getName)
                .toList();
    }
}
