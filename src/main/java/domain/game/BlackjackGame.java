package domain.game;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.PlayerNames;
import domain.playingcard.Deck;
import domain.playingcard.PlayingCards;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Collections.unmodifiableList;

public class BlackjackGame {
    private static final int DEALER_INDEX = 0;
    private static final List<Player> DECLINE_DRAWING_PLAYERS = new ArrayList<>();

    private final List<Participant> participants;
    private final Deck deck;

    private BlackjackGame(final List<Participant> participants, final Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackjackGame init(final PlayerNames playerNames) {
        Deck deck = Deck.init(PlayingCards.getValue());
        List<Participant> participants = new ArrayList<>();
        participants.add(Dealer.init(deck));
        IntStream.range(0, playerNames.values().size())
                .forEach(index -> participants.add(Player.of(playerNames.values().get(index), deck)));

        return new BlackjackGame(unmodifiableList(participants), deck);
    }

    public boolean hasDrawablePlayer() {
        return getPlayers().stream()
                .anyMatch(player ->
                        player.isDrawable() && !DECLINE_DRAWING_PLAYERS.contains(player));
    }

    public Optional<Player> findDrawablePlayer() {
        return getPlayers().stream()
                .filter(player ->
                        player.isDrawable() && !DECLINE_DRAWING_PLAYERS.contains(player))
                .findAny();
    }

    public boolean drawPlayerIfAccept(final Player player, final boolean isAccept) {
        if (isAccept) {
            player.draw(deck);
        }

        if (!isAccept) {
            DECLINE_DRAWING_PLAYERS.add(player);
        }

        return isAccept;
    }

    public boolean hasDrawableDealer() {
        return participants.get(DEALER_INDEX)
                .isDrawable();
    }

    public void playDealer() {
        Dealer dealer = getDealer();
        while (dealer.isDrawable()) {
            dealer.draw(deck);
        }
    }

    public Dealer getDealer() {
        return (Dealer) participants.get(DEALER_INDEX);
    }

    public List<Player> getPlayers() {
        return IntStream.range(DEALER_INDEX + 1, participants.size())
                .mapToObj(index -> (Player) participants.get(index))
                .toList();
    }
}
