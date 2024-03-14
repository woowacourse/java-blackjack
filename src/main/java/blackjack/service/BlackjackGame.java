package blackjack.service;

import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Players;
import blackjack.domain.result.Score;
import blackjack.domain.result.WinningResult;
import blackjack.dto.CardDto;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final List<String> playersName) {
        this.players = Players.from(playersName);
        this.dealer = new Dealer(Deck.create());
        divideCard();
    }

    private void divideCard() {
        dealer.shuffleDeck();
        dealer.addStartCard();

        final int playersCardCount = players.count() * 2;
        players.divideCard(dealer.drawCards(playersCardCount));
    }

    public List<ParticipantCardsDto> readyCards() {
        final Map<ParticipantName, Hands> playersCard = players.getPlayerHands();

        List<ParticipantCardsDto> participantCardsDtos = playersCard.entrySet().stream()
                .map(entry -> ParticipantCardsDto.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        participantCardsDtos.add(ParticipantCardsDto.of(dealer.getName(), dealer.getOpenedCards()));
        return participantCardsDtos;
    }

    public boolean addCardToPlayers(final String name) {
        if (!isPlayerAliveByName(name)) {
            return false;
        }

        players.addCardTo(name, dealer.drawCard());

        return true;
    }

    public int giveDealerMoreCards() {
        int count = 0;

        while (dealer.needMoreCard()) {
            dealer.addCard();
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
    public List<ParticipantScoreDto> getFinalResults() {
        final Map<ParticipantName, Hands> participantsHands = players.getPlayerHands();
        final Map<ParticipantName, Score> participantsScores = players.getPlayerScores();

        final Hands dealerHands = dealer.getHands();
        final Score dealerScore = dealer.calculate();

        participantsHands.put(dealer.getName(), dealerHands);
        participantsScores.put(dealer.getName(), dealerScore);

        return participantsHands.entrySet().stream()
                .map(entry -> ParticipantScoreDto.of(entry.getKey(), entry.getValue(), participantsScores.get(entry.getKey())))
                .toList();
    }
    public WinningResult getWinningResult() {
        return WinningResult.of(players, dealer.calculate());
    }

    public boolean isPlayerAliveByName(final String name) {
        return players.isNotDead(name);
    }

    public boolean isNotDealerBlackjack() {
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
