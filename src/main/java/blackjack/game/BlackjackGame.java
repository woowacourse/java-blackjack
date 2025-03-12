package blackjack.game;

import blackjack.card.CardDeck;
import blackjack.user.Dealer;
import blackjack.user.Participant;
import blackjack.user.Participants;
import blackjack.user.Player;
import blackjack.user.PlayerName;
import java.util.HashMap;
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

    public static BlackjackGame createByPlayerNames(final CardDeck cardDeck, final List<PlayerName> names) {
        Dealer dealer = new Dealer();
        List<Player> players = names.stream()
            .map(Player::new)
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

    public Map<Player, GameResult> calculateStatisticsForPlayer() {
        Map<Player, GameResult> playerResult = new HashMap<>();
        Dealer dealer = participants.getDealer();

        for (Player player : participants.getPlayers()) {
            GameResult gameResult = dealer.judgePlayerResult(player);
            playerResult.put(player, gameResult);
        }
        return playerResult;
    }

    public Map<GameResult, Integer> calculateStatisticsForDealer() {
        Map<GameResult, Integer> result = new HashMap<>();
        Dealer dealer = participants.getDealer();

        for (Player player : participants.getPlayers()) {
            GameResult playerResult = dealer.judgePlayerResult(player);
            GameResult dealerResult = playerResult.changeStatusOpposite();
            result.put(dealerResult, result.getOrDefault(dealerResult, 0) + 1);
        }
        return result;
    }

    public Participants getParticipants() {
        return participants;
    }
}
