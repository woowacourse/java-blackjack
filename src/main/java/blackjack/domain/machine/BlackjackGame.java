package blackjack.domain.machine;

import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Cards;
import blackjack.domain.machine.result.JudgeFactory;
import blackjack.domain.machine.result.MatchCalculator;
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
        List<Player> guests = blackjackPlayers.getGuests();
        return scoreResults(bettingBox, dealer, guests);
    }

    private MatchResults scoreResults(Map<Player, Double> bettingBox, Player dealer, List<Player> guests) {
        MatchResults matchResults = new MatchResults(dealer);
        for (Player guest : guests) {
            MatchCalculator result = JudgeFactory.matchResult(guest.getScore(), dealer.getScore());
            Double money = bettingBox.get(guest);
            matchResults.addResult(guest, dealer, money, result);
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
