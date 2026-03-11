package team.blackjack.domain;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.domain.rule.DefaultBlackjackRule;

public class BlackjackGame {
    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
        this.deck = new Deck();
    }

    public void drawInitialCards() {
        this.players.initPlayerHands(deck);

        this.dealer.hit(dealer.draw(deck));
        this.dealer.hit(dealer.draw(deck));
    }

    public Map<String, Result> calculatePlayersResultMap() {
        return players.getPlayerList().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> DefaultBlackjackRule.judgeResult(player.getScore(), dealer.getScore()),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    public boolean shouldPlayerHit(String name) {
        return this.players.getPlayerByName(name).isBust();
    }

    public void hitPlayer(String name) {
        this.players.getPlayerByName(name)
                .hit(deck.draw());
    }

    public boolean shouldDealerHit() {
        final int score = this.dealer.getScore();
        return DefaultBlackjackRule.isDealerMustDraw(score);
    }

    public void hitDealer() {
        this.dealer.hit(deck.draw());
    }

    public int getDealerScore() {
        return this.dealer.getScore();
    }

    public Map<String, Integer> getAllPlayerScore() {
        return this.players.getPlayerScoresByPlayer();
    }

    public List<Card> getDealerCards() {
        return this.dealer.getHand().getCards();
    }

    public List<Card> getPlayerCardsByName(String name) {
        return this.players.getPlayerByName(name).getCardInAllHand();
    }

    public Map<String, List<Card>> getAllPlayerCards() {
        return this.players.getCardsByPlayer();
    }

    public List<String> getAllPlayerNames() {
        return this.players.getPlayerNames();
    }
}
