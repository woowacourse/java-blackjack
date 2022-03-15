package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BlackjackBoard {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    private BlackjackBoard(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackBoard createGame(final List<String> playerNames) {
        Objects.requireNonNull(playerNames, "blackjackgame은 null이 들어올 수 없습니다.");

        final CardDeck cardDeck = CardDeck.createNewCardDek();
        final Players players = Players.createByPlayerNames(playerNames, cardDeck);
        final Dealer dealer = Dealer.createNewDealer(Cards.createByCardDeck(cardDeck));
        return new BlackjackBoard(cardDeck, dealer, players);
    }

    public boolean isPlayersTurnEnd() {
        return players.isAllTurnEnd();
    }

    public Player takeCurrentPlayerTurn(final HitCommand command) {
        if (command.isNo()) {
            final Player currentPlayer = players.currentTurnPlayer();
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

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public String getCurrentTurnPlayerName() {
        return players.getCurrentParticipantName();
    }

    public List<Participant> getAllParticipants() {
        final List<Participant> results = new ArrayList<>();
        results.add(dealer);
        results.addAll(players.getPlayers());
        return results;
    }

    public Map<String, GameOutcome> calculateAllResults() {
        return players.fight(dealer);
    }
}
