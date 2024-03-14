package domain.participant;

import domain.playingcard.Deck;
import domain.playingcard.PlayingCard;
import domain.playingcard.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;

import static domain.playingcard.PlayingCardShape.DIAMOND;
import static domain.playingcard.PlayingCardValue.EIGHT;
import static domain.playingcard.PlayingCardValue.TEN;
import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @DisplayName("딜러 인스턴스를 생성한다.")
    @Test
    void createDealerTest() {
        // When
        Dealer dealer = Dealer.init(Deck.init(PlayingCards.getValue()));

        // Then
        assertThat(dealer).isNotNull();
    }

    @DisplayName("딜러가 카드를 뽑을 수 있는 상태이면 true를 반환한다.")
    @Test
    void isDrawableTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, EIGHT));
        Hand hand = new Hand(playingCards);
        Dealer dealer = new Dealer(hand);

        // When
        boolean isDrawable = dealer.isDrawable();

        // Then
        assertThat(isDrawable).isTrue();
    }

    @DisplayName("딜러는 덱으로부터 카드를 한 장 뽑는다.")
    @Test
    void drawTest() {
        // Given
        PlayingCard playingCard = new PlayingCard(DIAMOND, TEN);
        Deck deck = new Deck(new ArrayDeque<>(List.of(playingCard)));
        Hand initHand = Hand.init();
        List<PlayingCard> playingCards = initHand.getPlayingCards();
        Dealer dealer = new Dealer(initHand);

        // When
        dealer.draw(deck);

        // Then
        assertThat(playingCards).contains(playingCard);
    }
}
