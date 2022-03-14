package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final CardDeck CARD_DECK = CardDeckGenerator.createCardDeckByCardNumber();

    private final Players players;
    private final Dealer dealer;

    private BlackjackGame(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static BlackjackGame create(final List<String> playerNames) {
        return new BlackjackGame(setUpPlayers(playerNames), setUpDealer());
    }

    private static Players setUpPlayers(final List<String> playerNames) {
        return new Players(playerNames.stream()
                .map(name -> new Player(name, drawInitialCards()))
                .collect(Collectors.toUnmodifiableList()));
    }

    private static Dealer setUpDealer() {
        return new Dealer(drawInitialCards());
    }

    private static Cards drawInitialCards() {
        return CARD_DECK.drawInitialCards();
    }

    public void drawCardToParticipant(final Participant participant) {
        participant.receiveCard(drawCard());
    }

    private Card drawCard() {
        return CARD_DECK.drawCard();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
