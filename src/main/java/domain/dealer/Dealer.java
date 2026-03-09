package domain.dealer;

import domain.card.Card;
import domain.card.CardBundle;
import domain.card.CardDeck;
import domain.player.Player;
import domain.player.PlayerName;

import java.util.List;
import java.util.stream.Stream;

public class Dealer {

    private static final String CANNOT_HAND_OUT_CARDS_ERROR = "딜러가 카드를 나눠줄 수 없습니다.";
    private static final String DEFAULT_DEALER_NAME = "딜러"; // NOTE Player와 추상화를 위해서는 수정 필요
    private static final int ADDITIONAL_DRAW_CONDITION = 16;
    private static final int INITIAL_DEAL_COUNT = 2;
    private static final int HIT_COUNT = 1;

    private final PlayerName name;
    private final CardDeck cardDeck;
    private CardBundle cardBundle;

    private Dealer(PlayerName name, CardDeck cardDeck) {
        this.name = name;
        this.cardBundle = CardBundle.empty();
        this.cardDeck = cardDeck;
    }

    public static Dealer from(PlayerName name, CardDeck cardDeck) {
        return new Dealer(name, cardDeck);
    }

    public static Dealer from(CardDeck cardDeck) {
        return new Dealer(PlayerName.from(DEFAULT_DEALER_NAME), cardDeck);
    }

    public CardBundle dealCardToPlayer(Player player) {
        return handOutCardToPlayer(player, INITIAL_DEAL_COUNT);
    }

    public CardBundle hitCardToPlayer(Player player) {
        return handOutCardToPlayer(player, HIT_COUNT);
    }

    private CardBundle handOutCardToPlayer(Player player, int tryCount) {
        CardBundle cardBundle = handOutCard(tryCount);
        return player.addCardBundle(cardBundle);
    }

    public CardBundle handOutCard(int tryCount) {
        try {
            List<Card> cardList = Stream.generate(this::drawCard)
                    .limit(tryCount)
                    .toList();
            return CardBundle.from(cardList);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(CANNOT_HAND_OUT_CARDS_ERROR);
        }
    }

    public boolean hitIfRequired() {
        if (canHit()) {
            drawMySelf(HIT_COUNT);
            return true;
        }
        return false;
    }

    private boolean canHit() {
        return cardBundle.getBasicScore() <= ADDITIONAL_DRAW_CONDITION;
    }

    public CardBundle dealMyself() {
        cardBundle = drawMySelf(INITIAL_DEAL_COUNT);
        return cardBundle;
    }

    private CardBundle drawMySelf(int tryCount) {
        return cardBundle.add(handOutCard(tryCount));
    }

    public Card drawCard() {
        return cardDeck.giveCard();
    }

    public int getResultScore() {
        return cardBundle.getResultScore();
    }

    public String toDisplayMyName() {
        return name.name();
    }

    public List<String> disPlayMyCardBundle() {
        return cardBundle.toDisplay();
    }

    public boolean isBusted() {
        return cardBundle.isBusted();
    }

}
