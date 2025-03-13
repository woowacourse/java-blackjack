package blackjack.game;

import blackjack.card.CardDeck;
import blackjack.card.CardHand;
import blackjack.user.BettingPlayer;
import blackjack.user.Dealer;
import blackjack.user.Participants;
import blackjack.user.Player;
import blackjack.user.PlayerName;
import blackjack.user.Wallet;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private static final int INITIAL_DISTRIBUTE_CARD_NUMBER = 2;
    private static final int EXTRA_DISTRIBUTE_CARD_NUMBER = 1;

    private final CardDeck cardDeck;
    private final Participants participants;

    private BlackjackGame(final CardDeck cardDeck, final Participants participants) {
        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public static BlackjackGame createByPlayerNames(final CardDeck cardDeck,
        final List<PlayerName> names) {
        Dealer dealer = new Dealer(new CardHand());
        List<Player> players = names.stream()
            .map(name -> new Player(name, new CardHand()))
            .toList();

        return new BlackjackGame(cardDeck, new Participants(dealer, players));
    }

    public static BlackjackGame createByBettingPlayers(final CardDeck cardDeck,
        final Map<PlayerName, Wallet> playerWallet) {
        Dealer dealer = new Dealer(new CardHand());
        List<Player> players = playerWallet.entrySet().stream()
            .map(entry -> (Player) new BettingPlayer(entry.getKey(), new CardHand(),
                entry.getValue()))
            .toList();

        return new BlackjackGame(cardDeck, new Participants(dealer, players));
    }

    public void initCardsToDealer() {
        Dealer dealer = participants.getDealer();
        dealer.addCards(cardDeck, INITIAL_DISTRIBUTE_CARD_NUMBER);
    }

    public void initCardsToPlayer() {
        for (Player player : participants.getPlayers()) {
            player.addCards(cardDeck, INITIAL_DISTRIBUTE_CARD_NUMBER);
        }
    }

    public void addExtraCardToDealer() {
        participants.getDealer().addCards(cardDeck, EXTRA_DISTRIBUTE_CARD_NUMBER);
    }

    public void addExtraCardToPlayer(final Player player) {
        player.addCards(cardDeck, EXTRA_DISTRIBUTE_CARD_NUMBER);
    }

    public int calculateProfitForPlayer() {
        Dealer dealer = participants.getDealer();

        return participants.getPlayers().stream()
            .filter(player -> player instanceof BettingPlayer)
            .map(player -> (BettingPlayer) player)
            .mapToInt(player -> player.updateWalletByGameResult(dealer.judgePlayerResult(player)))
            .sum();
    }

    public Participants getParticipants() {
        return participants;
    }
}
