package blackjack.service;

import blackjack.domain.Participants;
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

public class BlackjackGame {
    private final Deck deck;
    private final Participants participants;

    public BlackjackGame(final List<String> playersName, final Deck deck) {
        this.participants = new Participants(Players.from(playersName), new Dealer());
        this.deck = deck;
    }

    public void divideCard() {
        deck.shuffle();
        participants.addStartCards(deck);
    }

    // TODO: 네이밍 이해가 애매한데...
    public List<ParticipantCardsDto> getStartCards() {
        return participants.getStartCards();
    }

    public boolean addCardToPlayers(final String name) {
        // TODO: 여기서 pick? 아니면 안에서?
        return participants.addCardToPlayers(name, deck);
    }

    public int giveDealerMoreCards() {
        return participants.giveDealerMoreCards(deck);
    }

    public Map<ParticipantName, Hands> getHandResult() {
        return participants.getHandResult();
    }

    public Map<ParticipantName, Score> getScoreResult() {
        return participants.getScoreResult();
    }

    public WinningResult getWinningResult() {
        return participants.getWinningResult();
    }

    public boolean isNotDealerBlackjack() {
        return participants.isDealerNotBlackjack();

    }

    public List<CardDto> getCardsOf(final String name) {
        return participants.getCardsOf(name);
    }

    public List<String> getPlayersNames() {
        return participants.getPlayersNames();
    }
}
