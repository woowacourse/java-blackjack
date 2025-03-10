package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import java.util.ArrayList;
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

    public static BlackjackGame createByPlayerNames(final CardDeck cardDeck, final List<String> names) {
        Dealer dealer = new Dealer();

        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }

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
            GameResult gameResult = GameResult.playerResultFrom(dealer, player);
            playerResult.put(player, gameResult);
        }
        return playerResult;
    }

    public Map<GameResult, Integer> calculateStatisticsForDealer() {
        Map<GameResult, Integer> result = new HashMap<>();
        Dealer dealer = participants.getDealer();

        for (Player player : participants.getPlayers()) {
            GameResult playerResult = GameResult.playerResultFrom(dealer, player);
            GameResult dealerResult = playerResult.changeStatusOpposite();
            result.put(dealerResult, result.getOrDefault(dealerResult, 0) + 1);
        }
        return result;
    }

    public Participants getParticipants() {
        return participants;
    }
}
