package blackjack.domain.machine;

import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final Deck deck;
    private final Players blackjackPlayers;

    public BlackjackGame(List<String> playerNames) {
        this.deck = new Deck(new CardShuffleMachine());
        this.blackjackPlayers = new Players(playerNames);

    }

    public void initGame() {
        blackjackPlayers.startWithTwoCards(deck);
    }

    public boolean hasNextGuest() {
        return blackjackPlayers.hasNextGuest();
    }

    public void turnGuest() {
        blackjackPlayers.turnNextGuest();
    }

    public GameResponse addCardToPlayer() {
        Player player = blackjackPlayers.getTurnPlayer();
        player.addCard(deck.assignCard());
        return new GameResponse(player.getName(), player.getDeck());
    }

    public boolean checkOverLimit() {
        Player player = blackjackPlayers.getTurnPlayer();
        return player.canGetMoreCard();
    }

    public boolean canGetMoreCardToDealer() {
        Player dealer = blackjackPlayers.getDealer();
        return dealer.canGetMoreCard();
    }

    public void addCardToDealer() {
        Player dealer = blackjackPlayers.getDealer();
        dealer.addCard(deck.assignCard());
    }

    public MatchResults calculateResult(Map<Name, Double> bettingBox) {
        MatchResults matchResults = new MatchResults(blackjackPlayers);
        Player dealer = blackjackPlayers.getDealer();
        return yieldMatchResults(bettingBox, matchResults, dealer);
    }

    private MatchResults yieldMatchResults(Map<Name, Double> bettingBox, MatchResults matchResults, Player dealer) {
        for (Player guest : blackjackPlayers.getGuests()) {
            Double money = bettingBox.get(guest.getName());
            MatchJudge result = MatchJudge.judgeMatch(guest.getScore(), dealer.getScore());
            matchResults.addResult(guest, dealer, result.calculateProfit(money));
        }
        return matchResults;
    }

    public List<Name> getPlayerNames() {
        return blackjackPlayers.getNames();
    }

    public List<Player> getPlayers() {
        return blackjackPlayers.getPlayers();
    }

    public Player getTurnPlayer() {
        return blackjackPlayers.getTurnPlayer();
    }

    public List<Player> getGuests() {
        return blackjackPlayers.getGuests();
    }
}
