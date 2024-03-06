package domain;

import java.util.List;
import view.InputView;

public class Dealer {

    private static final int INIT_CARD_NUMBER = 2; //TODO 이름 생각해보기

    private final Players players;
    private final CardDeck cardDeck;
    private final Hands hands;

    public Dealer(final Players players, final CardDeck cardDeck) {
        this.players = players;
        this.cardDeck = cardDeck;
        this.hands = Hands.createEmptyPacket();
    }

    public void startDeal() {
        for (int i = 0; i < INIT_CARD_NUMBER; i++) {
            players.forEach(player -> player.add(cardDeck.pop()));
            hands.add(cardDeck.pop());
        }
    }

    public List<String> getCards() {
        return hands.getCards()
                .stream()
                .map(Card::toString)
                .toList();
    }

    public void play(final InputView inputView) {
        List<Player> names = players.getNames();
        for (Player player : names) {
            hitOrStay(inputView.readAnswer(player.getName()), player);
        }
    }

    public void hitOrStay(final String value, final Player player) {
        Answer answer = Answer.from(value);
        if (Answer.HIT == answer) {
            player.add(cardDeck.pop());
        }
    }
}
