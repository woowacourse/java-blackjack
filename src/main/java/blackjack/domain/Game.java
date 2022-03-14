package blackjack.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.DrawCount;
import blackjack.domain.participant.Player;

public class Game {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final List<Player> players;

    public Game(CardDeck cardDeck, List<String> playerNames) {
        this.cardDeck = cardDeck;
        this.dealer = new Dealer();
        this.players = List.copyOf(playerNames).stream()
            .map(Player::new)
            .collect(Collectors.toUnmodifiableList());
        init();
    }

    private void init() {
        dealer.init(cardDeck);
        players.forEach(player -> player.init(cardDeck));
    }

    public Card dealerFirstCard() {
        return dealer.openCard();
    }

    public Optional<Player> findHitPlayer() {
        return players.stream()
            .filter(Player::isHit)
            .findFirst();
    }

    public void drawPlayerCard(Player player, PlayStatus playStatus) {
        if (playStatus == PlayStatus.HIT) {
            player.hit(cardDeck.drawCard());
            return;
        }
        player.stay();
    }

    public DrawCount drawDealerCards() {
        return dealer.drawCards(cardDeck);
    }

    public int getDealerScore() {
        return dealer.getScore();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}