package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Information;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private final Dealer dealer;
    private final Participants participants;
    private final Cards cards;

    private Game(Dealer dealer, Participants participants, Cards cards) {
        this.dealer = dealer;
        this.participants = participants;
        this.cards = cards;
    }

    public static Game of(List<Information> information) {
        Cards cards = Cards.createNormalCards();
        cards.shuffle();
        Dealer dealer = Dealer.of(cards.next(), cards.next());
        Participants participants = Participants.of(cards, information);
        return new Game(dealer, participants, cards);
    }

    public static Game of(Information... information) {
        return of(Arrays.asList(information));
    }

    public static Game of(Dealer dealer, Participants participants, Cards cards) {
        return new Game(dealer, participants, cards);
    }

    public void drawCardTo(Player player) {
        player.drawCard(cards.next());
    }

    public List<Participant> participantsAsList() {
        return participants.participants();
    }

    public boolean isAnyParticipantBlackjack() {
        return participants.anyoneBlackjack();
    }

    public Result result() {
        return new Result(dealer, participants);
    }

    public boolean drawableDealer() {
        return dealer.drawable();
    }

    public List<Player> allPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(participantsAsList());
        return players;
    }

    public Dealer dealer() {
        return dealer;
    }
}
