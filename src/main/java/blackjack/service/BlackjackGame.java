package blackjack.service;

import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.Score;
import blackjack.domain.result.WinningResult;
import blackjack.dto.ParticipantCardsDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackGame {
    private Deck deck;
    private final Participants participants;

    public BlackjackGame(final Players players) {
        this.participants = new Participants(players, new Dealer());
        this.deck = Deck.empty();
    }

    BlackjackGame(final List<String> playersName, final Deck deck) {
        this.participants = new Participants(Players.from(playersName), new Dealer());
        this.deck = deck;
    }

    public void divideCard() {
        deck = deck.generate();
        deck.shuffle();
        participants.addStartCards(deck);
    }

    public List<ParticipantCardsDto> getStartCards() {
        return participants.getStartCards();
    }

    public void addCardToPlayer(final Player player) {
        participants.addCardToPlayer(player, deck);
    }

    public int giveDealerMoreCards() {
        return participants.giveDealerMoreCards(deck);
    }

    public Map<ParticipantName, Hands> getPlayersHandResult() {
        return participants.getPlayersHandResult();
    }

    public Map<ParticipantName, Score> getPlayersScoreResult() {
        return participants.getPlayersScoreResult();
    }

    public Entry<ParticipantName, Hands> getDealerHandResult() {
        return participants.getDealerHandResult();
    }

    public Score getDealerScore() {
        return participants.getDealerScore();
    }

    public WinningResult getWinningResult() {
        return participants.getWinningResult();
    }

    public boolean isPlayerAlive(final Player player) {
        return participants.isPlayerAlive(player);
    }

    public boolean isNotDealerBlackjack() {
        return participants.isDealerNotBlackjack();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
