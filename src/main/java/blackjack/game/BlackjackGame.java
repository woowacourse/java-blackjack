package blackjack.game;

import blackjack.card.CardDeck;
import blackjack.user.Dealer;
import blackjack.user.Participant;
import blackjack.user.Participants;
import blackjack.user.Player;
import blackjack.user.PlayerName;
import blackjack.user.Wallet;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private static final int INITIAL_CARD_NUMBER = 2;
    private static final int EXTRA_CARD_NUMBER = 1;

    private final CardDeck cardDeck;
    private final Participants participants;

    private BlackjackGame(final CardDeck cardDeck, final Participants participants) {
        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public static BlackjackGame createByPlayerNames(final CardDeck cardDeck,
        final Map<PlayerName, Wallet> playerWallet) {
        Dealer dealer = new Dealer();
        List<Player> players = playerWallet.entrySet().stream()
            .map(entry -> new Player(entry.getKey(), entry.getValue()))
            .toList();

        return new BlackjackGame(cardDeck, new Participants(dealer, players));
    }

    public void initCardsToDealer() {
        Dealer dealer = participants.getDealer();
        dealer.addCards(cardDeck, INITIAL_CARD_NUMBER);
    }

    public void initCardsToPlayer() {
        for (Participant participant : participants.getPlayers()) {
            participant.addCards(cardDeck, INITIAL_CARD_NUMBER);
        }
    }

    public void addExtraCard(final Participant participant) {
        participant.addCards(cardDeck, EXTRA_CARD_NUMBER);
    }

    public int calculateProfitForPlayer() {
        Dealer dealer = participants.getDealer();
        int totalProfit = 0;

        for (Player player : participants.getPlayers()) {
            GameResult gameResult = dealer.judgePlayerResult(player);
            int profit = player.updateWalletByGameResult(gameResult);
            totalProfit += profit;
        }
        return totalProfit;
    }

    public Participants getParticipants() {
        return participants;
    }
}
