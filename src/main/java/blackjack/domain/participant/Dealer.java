package blackjack.domain.participant;

import static blackjack.domain.participant.Participant.INIT_CARD_COUNT;
import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;
import java.util.stream.IntStream;

public final class Dealer {

    private static final String NAME = "딜러";
    public static final int CAN_DRAW_SCORE = 16;

    private final Deck deck;
    private final Participant participant;

    public Dealer() {
        this.deck = new Deck();
        this.participant = new Participant(NAME);
    }

    public void settingCards(Players players) {
        settingPlayersCards(players);
        settingSelfCards();
    }

    private void settingPlayersCards(Players players) {
        List<Card> initCards = IntStream.range(0, players.size() * INIT_CARD_COUNT)
                .mapToObj(x -> deck.drawCard())
                .collect(toList());

        players.receiveSettingCards(initCards);
    }

    private void settingSelfCards() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            receiveCard(drawCard());
        }
    }

    public void receiveCard(final Card card) {
        participant.receiveCard(card);
    }

    public int totalScore() {
        return participant.calculateTotalScore();
    }

    public Card getOnlyOneCard() {
        return participant.getCards().get(0);
    }

    public void giveCard(Player player) {
        player.receiveCard(deck.drawCard());
    }

    public Card drawCard() {
        return deck.drawCard();
    }

    public boolean canDraw() {
        return participant.calculateTotalScore() <= CAN_DRAW_SCORE;
    }

    public List<Card> getCards() {
        return participant.getCards();
    }

    public String getName() {
        return NAME;
    }
}
