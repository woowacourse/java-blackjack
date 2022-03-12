package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.dto.OutComeResult;
import blackjack.dto.PlayerCards;
import blackjack.dto.PlayerScoreResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlackJackBoard {

    private final CardDeck cardDeck;
    private final Participant dealer;
    private final Participants players;

    private BlackJackBoard(final CardDeck cardDeck, final Participant dealer, final Participants players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackJackBoard createGame(final List<String> playerNames) {
        Objects.requireNonNull(playerNames, "blackjackgame은 null이 들어올 수 없습니다.");
        final CardDeck cardDeck = CardDeck.createNewCardDek();
        final Participants participants = Participants.createByPlayerNames(playerNames, cardDeck);
        final Participant dealer = Dealer.createNewDealer(cardDeck.provideFirstHitCards());
        return new BlackJackBoard(cardDeck, dealer, participants);
    }

    public boolean isPlayersTurnEnd() {
        return players.isAllTurnEnd();
    }

    public PlayerCards hitCurrentPlayer(final HitCommand command) {
        if (command.isNo()) {
            final PlayerCards currentPlayer = players.getCurrentTurnPlayerCards();
            players.turnToNextPlayer();
            return currentPlayer;
        }
        return players.hitCurrentPlayer(cardDeck.provideCard());
    }

    public boolean isDealerTurnEnd() {
        return !dealer.canHit();
    }

    public void hitDealer() {
        dealer.hit(cardDeck.provideCard());
    }

    public PlayerCards getDealerFirstCard() {
        return PlayerCards.toPlayerFirstCards(dealer);
    }

    public List<PlayerCards> getPlayersFirstCards() {
        return players.getPlayerFirstCards();
    }

    public String getCurrentTurnPlayerName() {
        return players.getCurrentTurnPlayerName();
    }

    public List<PlayerScoreResult> allPlayerScoreResults() {
        final List<PlayerScoreResult> results = new ArrayList<>();
        results.add(PlayerScoreResult.from(dealer));
        results.addAll(players.getPlayerScoreResults());
        return results;
    }

    public OutComeResult calculateAllResults() {
        return players.outcomeResult(dealer);
    }
}
