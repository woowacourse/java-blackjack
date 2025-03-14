package blackjack.game;

import blackjack.card.CardDeck;
import blackjack.card.CardHand;
import blackjack.game.betting.Betting;
import blackjack.game.betting.BetAmount;
import blackjack.user.Dealer;
import blackjack.user.Participants;
import blackjack.user.Player;
import blackjack.user.PlayerName;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private static final int INITIAL_DISTRIBUTE_CARD_NUMBER = 2;
    private static final int EXTRA_DISTRIBUTE_CARD_NUMBER = 1;

    private final CardDeck cardDeck;
    private final Betting betting;
    private final Participants participants;

    private BlackjackGame(final CardDeck cardDeck, Betting betting, final Participants participants) {
        this.cardDeck = cardDeck;
        this.betting = betting;
        this.participants = participants;
    }

    public static BlackjackGame createWithEmptyBet(final CardDeck cardDeck,
        final List<PlayerName> names) {
        Betting betting = Betting.createWithEmptyTable();

        Dealer dealer = new Dealer(new CardHand());
        List<Player> players = names.stream()
            .map(name -> new Player(name, new CardHand()))
            .toList();

        return new BlackjackGame(cardDeck, betting, new Participants(dealer, players));
    }

    public static BlackjackGame createWithActiveBet(final CardDeck cardDeck,
        final Map<PlayerName, BetAmount> playerAmounts) {
        Betting betting = new Betting(playerAmounts);

        Dealer dealer = new Dealer(new CardHand());
        List<Player> players = playerAmounts.keySet().stream()
            .map(name -> new Player(name, new CardHand()))
            .toList();

        return new BlackjackGame(cardDeck, betting, new Participants(dealer, players));
    }

    public void initCardsToParticipants() {
        Dealer dealer = participants.getDealer();
        dealer.addCards(cardDeck, INITIAL_DISTRIBUTE_CARD_NUMBER);

        for (Player player : participants.getPlayers()) {
            player.addCards(cardDeck, INITIAL_DISTRIBUTE_CARD_NUMBER);
        }
    }

    public void addExtraCardToDealer(final Dealer dealer) {
        dealer.addCards(cardDeck, EXTRA_DISTRIBUTE_CARD_NUMBER);
    }

    public void addExtraCardToPlayer(final Player player) {
        player.addCards(cardDeck, EXTRA_DISTRIBUTE_CARD_NUMBER);
    }

    public Map<PlayerName, BetAmount> calculateProfitForPlayers() {
        betting.calculateProfitForPlayer(participants.getDealer(), participants.getPlayers());
        return betting.getBettingTable();
    }

    public Participants getParticipants() {
        return participants;
    }

    public List<PlayerName> getPlayerNames() {
        return participants.getPlayerNames();
    }
}
