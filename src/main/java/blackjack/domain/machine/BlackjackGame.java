package blackjack.domain.machine;

import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Cards;
import blackjack.domain.machine.result.JudgeFactory;
import blackjack.domain.machine.result.MatchResults;
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

    public Results calculateResult(Map<Player, Double> bettingBox) {
        Player dealer = blackjackPlayers.getDealer();
        List<Player> guests = blackjackPlayers.getGuests();
        return scoreResults(bettingBox, dealer, guests);
    }

    private Results scoreResults(Map<Player, Double> bettingBox, Player dealer, List<Player> guests) {
        Results results = new Results(dealer);
        for (Player guest : guests) {
            MatchResults result = JudgeFactory.matchResult(guest.getScore(), dealer.getScore());
            Double money = bettingBox.get(guest);
            results.addResult(guest, dealer, money, result);
        }
        return results;
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
