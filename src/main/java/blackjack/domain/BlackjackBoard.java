package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.dto.OutComeResult;
import blackjack.dto.ParticipantCards;
import blackjack.dto.ParticipantScoreResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlackjackBoard {

    private final CardDeck cardDeck;
    private final Participant dealer;
    private final Participants players;

    private BlackjackBoard(final CardDeck cardDeck, final Participant dealer, final Participants players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackBoard createGame(final List<String> playerNames) {
        Objects.requireNonNull(playerNames, "blackjackgame은 null이 들어올 수 없습니다.");

        final CardDeck cardDeck = CardDeck.createNewCardDek();
        final Participants players = Participants.createByPlayerNames(playerNames, cardDeck);
        final Participant dealer = Dealer.createNewDealer(Cards.createByCardDeck(cardDeck));
        return new BlackjackBoard(cardDeck, dealer, players);
    }

    public boolean isPlayersTurnEnd() {
        return players.isAllTurnEnd();
    }

    public ParticipantCards takeCurrentPlayerTurn(final HitCommand command) {
        if (command.isNo()) {
            final ParticipantCards currentPlayer = players.getCurrentParticipantCards();
            players.turnToNextParticipant();
            return currentPlayer;
        }
        return players.hitCurrentParticipant(cardDeck.provideCard());
    }

    public boolean isDealerTurnEnd() {
        return !dealer.canHit();
    }

    public void hitDealer() {
        dealer.hit(cardDeck.provideCard());
    }

    public ParticipantCards getDealerFirstCard() {
        return ParticipantCards.toParticipantFirstCards(dealer);
    }

    public List<ParticipantCards> getPlayersFirstCards() {
        return players.getFirstCards();
    }

    public String getCurrentTurnPlayerName() {
        return players.getCurrentParticipantName();
    }

    public List<ParticipantScoreResult> allPlayerScoreResults() {
        final List<ParticipantScoreResult> results = new ArrayList<>();
        results.add(ParticipantScoreResult.from(dealer));
        results.addAll(players.getParticipantScoreResults());
        return results;
    }

    public OutComeResult calculateAllResults() {
        return players.outcomeResult(dealer);
    }
}
