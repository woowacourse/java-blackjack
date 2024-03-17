package blackjack.domain.participant;

import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import blackjack.domain.result.Score;
import blackjack.domain.result.WinningResult;
import blackjack.dto.ParticipantCardsDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Participants {
    public static final int INT_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;

    public Participants(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void addStartCards(final Deck deck) {
        final int playersCardCount = players.count() * INT_CARD_COUNT;
        players.divideCard(deck.pick(playersCardCount));

        dealer.addCard(deck.pick(INT_CARD_COUNT));
    }

    public List<ParticipantCardsDto> getStartCards() {
        List<ParticipantCardsDto> participantCardsDtos = players.getStartCards();
        participantCardsDtos.add(ParticipantCardsDto.of(dealer.getName(), dealer.getOpenedCards()));

        return participantCardsDtos;
    }

    public void addCardToPlayer(final Player rawPlayer, final Deck deck) {
        final Player player = players.findPlayer(rawPlayer);
        players.addCardTo(player, deck.pick());
    }

    public int giveDealerMoreCards(final Deck deck) {
        int count = 0;

        while (dealer.needMoreCard()) {
            dealer.addCard(deck.pick());
            count++;
        }

        return count;
    }

    public Map<ParticipantName, Hands> getPlayersHandResult() {
        return players.getPlayerHands();
    }

    public Map<ParticipantName, Score> getPlayersScoreResult() {
        return players.getPlayerScores();
    }

    public Entry<ParticipantName, Hands> getDealerHandResult() {
        return Map.entry(dealer.getName(), dealer.getHands());
    }

    public Score getDealerScore() {
        return dealer.calculate();
    }

    public WinningResult getWinningResult() {
        return WinningResult.of(players, dealer);
    }

    public boolean isPlayerAlive(final Player rawPlayer) {
        final Player player = players.findPlayer(rawPlayer);
        return player.isAlive();
    }

    public boolean isDealerNotBlackjack() {
        return dealer.isNotBlackjack();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
