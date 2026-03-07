package blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MatchResultTest {

    @Test
    void 플레이어가_버스트라면_진다(){
        Player player = Player.of(Name.of("pobi"));
        player.receiveCard(TrumpCard.of(Suit.SPADE, Rank.TEN));
        player.receiveCard(TrumpCard.of(Suit.HEART, Rank.TEN));
        player.receiveCard(TrumpCard.of(Suit.DIAMOND, Rank.FIVE));

        Dealer dealer = Dealer.of();
        dealer.receive(TrumpCard.of(Suit.CLOVER, Rank.TEN));
        dealer.receive(TrumpCard.of(Suit.SPADE, Rank.SEVEN));

        assertThat(MatchResult.playerResult(player, dealer)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    void 플레이어가_버스트가_아니고_딜러가_버스트면_이긴다(){
        Player player = Player.of(Name.of("pobi"));
        player.receiveCard(TrumpCard.of(Suit.SPADE, Rank.TEN));
        player.receiveCard(TrumpCard.of(Suit.HEART, Rank.SEVEN));

        Dealer dealer = Dealer.of();
        dealer.receive(TrumpCard.of(Suit.CLOVER, Rank.TEN));
        dealer.receive(TrumpCard.of(Suit.DIAMOND, Rank.TEN));
        dealer.receive(TrumpCard.of(Suit.SPADE, Rank.FIVE));

        assertThat(MatchResult.playerResult(player, dealer)).isEqualTo(MatchResult.WIN);
    }

    @Test
    void 둘_다_버스트가_아니면_더_큰_쪽이_이긴다(){
        Player player = Player.of(Name.of("pobi"));
        player.receiveCard(TrumpCard.of(Suit.SPADE, Rank.TEN));
        player.receiveCard(TrumpCard.of(Suit.HEART, Rank.TEN));

        Dealer dealer = Dealer.of();
        dealer.receive(TrumpCard.of(Suit.CLOVER, Rank.TEN));
        dealer.receive(TrumpCard.of(Suit.SPADE, Rank.SEVEN));

        assertThat(MatchResult.playerResult(player, dealer)).isEqualTo(MatchResult.WIN);
    }

    @Test
    void 점수가_같으면_무승부() {
        Player player = Player.of(Name.of("pobi"));
        player.receiveCard(TrumpCard.of(Suit.SPADE, Rank.TEN));
        player.receiveCard(TrumpCard.of(Suit.HEART, Rank.SEVEN));

        Dealer dealer = Dealer.of();
        dealer.receive(TrumpCard.of(Suit.CLOVER, Rank.TEN));
        dealer.receive(TrumpCard.of(Suit.DIAMOND, Rank.SEVEN));

        assertThat(MatchResult.playerResult(player, dealer)).isEqualTo(MatchResult.DRAW);
    }
}