package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.NoSuchElementException;

public class BlackJackGame {

    private final Participants participants;
    private final Deck deck;

    public BlackJackGame(final Participants participants, final Deck deck, final int drawCount) {
        this.participants = participants;
        this.deck = deck;
        initGame(drawCount);
    }

    private void initGame(final int drawCount) {
        deck.shuffle();
        participants.drawCard(deck, drawCount);
    }

    public void drawOrNot(final boolean wantMoreDraw, final Participant participant) {
        if (!wantMoreDraw) {
            participant.changeDrawable();
            return;
        }
        participant.drawCard(deck.draw());
    }

    public Player findDrawablePlayer() {
        return players().stream()
                .filter(Player::isDrawable)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("카드를 받을 수 있는 플레이어가 존재하지 않습니다."));
    }

    public boolean existDrawablePlayer() {
        return players().stream()
                .anyMatch(Player::isDrawable);
    }

    public boolean isDealerDrawable() {
        return dealer().isDrawable();
    }

    public Participants participants() {
        return participants;
    }

    public Dealer dealer() {
        return participants.getDealer();
    }

    public List<Player> players() {
        return participants.getPlayers();
    }
}
