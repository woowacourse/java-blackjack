package domain;

import domain.card.Deck;
import domain.card.ShuffledDeck;
import domain.user.AllWinningAmountDto;
import domain.user.Dealer;
import domain.user.Participants;
import domain.user.Player;
import java.util.List;
import java.util.Map;

public final class BlackjackGame {
    private static final List<Integer> AVAILABLE_DECK_COUNT = List.of(1, 2, 4, 6, 8);

    private final Participants participants;
    private final Deck deck;

    public BlackjackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackjackGame of(Map<String, Integer> playerBettingAmountTable, int deckCount) {
        validateDeckCount(deckCount);
        return new BlackjackGame(Participants.from(playerBettingAmountTable), ShuffledDeck.createByCount(deckCount));
    }

    private static void validateDeckCount(int deckCount) {
        if (AVAILABLE_DECK_COUNT.contains(deckCount)) {
            return;
        }
        throw new IllegalArgumentException(String.format(
                "%s개의 덱만 사용 가능합니다.", AVAILABLE_DECK_COUNT.toString()));
    }

    public void initializeGame() {
        participants.drawInitialCardsEachParticipant(deck);
    }

    public boolean hitOrStayByDealer() {
        return this.participants.hitOrStayByDealer(this.deck);
    }

    public AllWinningAmountDto calculateAllWinningAmounts() {
        return this.participants.calculateWinningAmountOfAllPlayers();
    }

    public void hitBy(Player player) {
        player.addCard(this.deck.draw());
    }

    public Dealer getDealer() {
        return this.participants.getDealer();
    }

    public List<Player> getPlayers() {
        return this.participants.getPlayers();
    }
}
