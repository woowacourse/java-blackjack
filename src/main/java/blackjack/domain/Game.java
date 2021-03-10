package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Information;
import blackjack.domain.player.Participants;

public class Game {

    private final Dealer dealer;
    private final Participants participants;
    private final Cards cards;

    private Game(Dealer dealer, Participants participants, Cards cards) {
        this.dealer = dealer;
        this.participants = participants;
        this.cards = cards;
    }

    public static Game of(Information... information) {
        Cards cards = Cards.createNormalCards();
        Dealer dealer = Dealer.of(cards.next(), cards.next());
        Participants participants = Participants.of(cards, information);
        return new Game(dealer, participants, cards);
    }
}
