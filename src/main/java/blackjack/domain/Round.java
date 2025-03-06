package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Card;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Player;
import java.util.List;
import java.util.Map;

public class Round {
    private final CardDeck cardDeck;
    private final List<Player> players;
    private final Dealer dealer;

    public Round(final CardDeck cardDeck, final List<Name> playerNames) {
        this.cardDeck = cardDeck;
        this.players = registerPlayers(playerNames);
        this.dealer = new Dealer();
    }

    private List<Player> registerPlayers(final List<Name> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    public void distributeCards(final Name playerName, final int cardCount) {
        Player foundPlayer = findPlayer(playerName);
        for (int i = 0; i < cardCount; i++) {
            Card card = cardDeck.getCard();
            foundPlayer.addCard(card);
        }
    }

    public int getScoreByPlayer(Name name) {
        return findPlayer(name).calculateScore();
    }

    public int getScoreByDealer() {
        return dealer.calculateScore();
    }

    private Player findPlayer(Name name) {
        return players.stream()
                .filter(player -> player.isNameEquals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어 입니다:" + name));
    }

    public void distributeInitialCards() {
        dealer.addCard(cardDeck.getCard());
        dealer.addCard(cardDeck.getCard());
        for (Player player : players) {
            player.addCard(cardDeck.getCard());
            player.addCard(cardDeck.getCard());
        }
    }

    public WinningDiscriminator getWinningDiscriminator() {
        int dealerScore = dealer.calculateScore();
        Map<Name, Integer> playerScores = players.stream()
                .collect(toMap(Player::getName, Player::calculateScore));
        return new WinningDiscriminator(dealerScore, playerScores);
    }

    public List<Card> getCardsByPlayer(Name name) {
        return findPlayer(name).getCards();
    }

    public List<Card> getCardsByDealer() {
        return dealer.getCards();
    }

    public List<Card> getInitialCardsByDealer() {
        return dealer.getInitialCards();
    }

    public void addDealerCard() {
        dealer.addCard(cardDeck.getCard());
    }

    public boolean dealerMustDraw() {
        return dealer.mustDraw();
    }

    public boolean isPlayerBusted(Name name) {
        return findPlayer(name).isBust();
    }
}
