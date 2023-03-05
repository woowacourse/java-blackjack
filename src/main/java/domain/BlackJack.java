package domain;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;

import java.util.List;
import java.util.stream.Collectors;

public final class BlackJack {

    public static final int INITIAL_DRAW_CARD_COUNT = 2;

    private final Users users;
    private final Dealer dealer;
    private final Deck deck;

    private BlackJack(List<Name> userNames, Deck deck) {
        final List<Player> playerList = userNames.stream()
                .map(Player::of)
                .collect(Collectors.toList());
        this.users = new Users(playerList);
        this.dealer = new Dealer();
        this.deck = deck;
        initGame();
    }

    public static BlackJack getInstance(List<Name> userNames, Deck deck) {
        return new BlackJack(userNames, deck);
    }

    private void initGame() {
        users.giveEachUser(deck, INITIAL_DRAW_CARD_COUNT);
        dealer.give(deck, INITIAL_DRAW_CARD_COUNT);
    }

    public void drawCard(Player player) {
        users.findUserAndGive(player, deck.drawCard());
    }


    public List<Card> getCardsFrom(Player player) {
        return users.getCardsOf(player);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public int finalizeDealer() {
        int additionalCardCount = 0;
        while (dealer.needCard()) {
            dealer.draw(deck.drawCard());
            additionalCardCount += 1;
        }
        return additionalCardCount;
    }

    public List<Player> getUsersStatus() {
        return users.getUsers();
    }

    public Dealer getDealerStatus() {
        if (dealer.needCard()) {
            throw new IllegalStateException("딜러가 아직 카드의 결론이 나지 않았습니다.");
        }
        return dealer;
    }

    public GameResult getGameResult() {
        return new GameResult(dealer, users);
    }

    public Users getUsers() {
        return users;
    }

    public List<Player> getUserList() {
        return users.getUsers();
    }

    public boolean isBusted(final Player player) {
        return users.isBusted(player);
    }
}
