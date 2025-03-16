package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.user.Player;
import blackjack.domain.value.BettingAmount;
import blackjack.domain.value.Nickname;
import java.util.List;

public class PlayerFixture {

    public static final BettingAmount DEFAULT_BETTING_AMOUNT = new BettingAmount(1000);

    public static Player create(String nickname, List<Card> cards) {
        Player player = new Player(new Nickname(nickname), DEFAULT_BETTING_AMOUNT);
        player.addInitialCards(cards);
        return player;
    }

    public static Player createLessThanBlackjack(String nickname) {
        Player player = new Player(new Nickname(nickname), DEFAULT_BETTING_AMOUNT);
        List<Card> bustCard = CardFixture.makeLessThanBlackjack();
        player.addInitialCards(bustCard);
        return player;
    }

    public static Player createBlackJackWithInitialHand(String nickname) {
        Player player = new Player(new Nickname(nickname), DEFAULT_BETTING_AMOUNT);
        List<Card> bustCard = CardFixture.makeBlackjackByTwoCard();
        player.addInitialCards(bustCard);
        return player;
    }

    public static Player createBlackJackWithFinalHand(String nickname) {
        Player player = new Player(new Nickname(nickname), DEFAULT_BETTING_AMOUNT);
        List<Card> bustCard = CardFixture.makeBlackjackOverTwoCard();
        player.addInitialCards(bustCard);
        return player;
    }

    public static Player createBust(String nickname) {
        Player player = new Player(new Nickname(nickname), DEFAULT_BETTING_AMOUNT);
        List<Card> bustCard = CardFixture.makeBust();
        player.addInitialCards(bustCard);
        return player;
    }

}
