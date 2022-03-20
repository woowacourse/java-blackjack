package blackjack.domain.machine;

import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Guest;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final Cards cards;
    private final Players blackjackPlayers;

    public BlackjackGame(List<String> playerNames) {
        this.cards = new Cards(new CardShuffleMachine());
        this.blackjackPlayers = new Players(playerNames);

    }

    public void initGame() {
        blackjackPlayers.startWithTwoCards(cards);
    }

    public boolean hasNextGuest() {
        return blackjackPlayers.hasNextGuest();
    }

    public void turnGuest() {
        blackjackPlayers.turnNextGuest();
    }

    public GameResponse addCardToPlayer() {
        Player player = blackjackPlayers.getTurnPlayer();
        player.addCard(cards.assignCard());
        return new GameResponse(player.getName(), player.getDeck());
    }

    public boolean checkOverLimit() {
        Player player = blackjackPlayers.getTurnPlayer();
        return player.isOverLimit(Guest.LIMIT_POINT);
    }

    public boolean canGetMoreCardToDealer() {
        Player dealer = blackjackPlayers.getDealer();
        return !dealer.isOverLimit(Dealer.LIMIT_POINT);
    }

    public void addCardToDealer() {
        Player dealer = blackjackPlayers.getDealer();
        dealer.addCard(cards.assignCard());
    }

    public MatchResults calculateResult(Map<Player, Double> bettingBox) {
        Player dealer = blackjackPlayers.getDealer();
        return scoreResults(bettingBox, dealer);
    }

    private MatchResults scoreResults(Map<Player, Double> bettingBox, Player dealer) {
        MatchResults matchResults = new MatchResults(dealer);
        for (Player guest : bettingBox.keySet()) {
            Double money = bettingBox.get(guest);
            MatchJudge result = MatchJudge.judgeMatch(guest.getScore(), dealer.getScore());
            matchResults.addResult(guest, dealer, result.calculateProfit(money));
        }
        return matchResults;
    }

    public List<String> getPlayerNames() {
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
