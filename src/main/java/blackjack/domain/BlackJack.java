package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.dto.FinalStatusDto;
import blackjack.domain.dto.GameResultDto;
import blackjack.domain.dto.InitialStatusDto;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {

    private final Users users;
    private final Dealer dealer;

    public BlackJack(List<Name> usersNames, Deck deck) {
        this.users = new Users(usersNames, deck);
        this.dealer = new Dealer(getInitialCards(deck));
    }

    private List<Card> getInitialCards(final Deck randomDeck) {
        List<Card> cards = new ArrayList<>();
        cards.add(randomDeck.drawCard());
        cards.add(randomDeck.drawCard());
        return cards;
    }

    public void giveCard(Name user, Deck deck) {
        users.giveCardByName(user, deck.drawCard());
    }

    public int giveCardToDealerUntilDontNeed(Deck deck) {
        int additionalCardCount = 0;
        while (dealer.needCardToGetResult()) {
            dealer.draw(deck.drawCard());
            additionalCardCount += 1;
        }
        return additionalCardCount;
    }

    public boolean isBust(final Name name) {
        return users.checkBustBy(name);
    }

    public List<Card> getUserCard(Name user) {
        return users.getCardsOf(user);
    }

    public Card getDealerFirstCard() {
        return dealer.getFirstCard();
    }

    public Dealer getDealer() {
        if (dealer.needCardToGetResult()) {
            throw new IllegalStateException("딜러가 아직 카드의 결론이 나지 않았습니다.");
        }
        return dealer;
    }

    public InitialStatusDto getInitialStatus() {
        return new InitialStatusDto(dealer, users);
    }

    public FinalStatusDto getFinalStatus() {
        return new FinalStatusDto(dealer, users);
    }

    public GameResultDto getGameResult() {
        return new GameResultDto(dealer, users);
    }

    public Users getUsers() {
        return users;
    }
}
