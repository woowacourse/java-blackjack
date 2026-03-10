package domain;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Rank;
import constant.Suit;
import dto.ParticipantDto;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Nested
    class GetPlayerTest {

        @Nested
        class Success {

            @Test
            void 이름에_해당하는_플레이어를_반환해야_한다() {

                // given
                Participants participants = createParticipants();
                Player expected = participants.players().getPlayer("jacob");

                // when
                Player actual = participants.getPlayer("jacob");

                // then
                assertThat(actual).isSameAs(expected);
            }

            @Test
            void 해당_이름의_플레이어가_없으면_null을_반환해야_한다() {

                // given
                Participants participants = createParticipants();

                // when
                Player actual = participants.getPlayer("brown");

                // then
                assertThat(actual).isNull();
            }
        }
    }

    @Nested
    class GenerateInitialDealerDtoTest {

        @Nested
        class Success {

            @Test
            void 딜러의_첫_카드만_포함한_Dto를_반환해야_한다() {

                // given
                Dealer dealer = new Dealer();
                Card firstCard = new Card(Rank.TEN, Suit.HEART);
                Card secondCard = new Card(Rank.ACE, Suit.SPADE);
                dealer.addCard(firstCard);
                dealer.addCard(secondCard);
                Participants participants = new Participants(dealer, new Players(List.of("jacob", "seoye")));

                // when
                ParticipantDto actual = participants.generateInitialDealerDto();

                // then
                assertThat(actual.name()).isEqualTo("딜러");
                assertThat(actual.hand()).containsExactly(firstCard.getName());
                assertThat(actual.score()).isEqualTo(21);
            }
        }
    }

    @Nested
    class GeneratePlayersDtoTest {

        @Nested
        class Success {

            @Test
            void 모든_플레이어의_이름과_손패와_점수를_담은_Dto_목록을_반환해야_한다() {

                // given
                Dealer dealer = new Dealer();
                dealer.addCard(new Card(Rank.NINE, Suit.HEART));
                dealer.addCard(new Card(Rank.SEVEN, Suit.SPADE));

                Players players = new Players(List.of("jacob", "seoye"));
                Player jacob = players.getPlayer("jacob");
                jacob.addCard(new Card(Rank.TEN, Suit.CLOVER));
                jacob.addCard(new Card(Rank.EIGHT, Suit.DIAMOND));

                Player seoye = players.getPlayer("seoye");
                seoye.addCard(new Card(Rank.TEN, Suit.HEART));
                seoye.addCard(new Card(Rank.SEVEN, Suit.CLOVER));

                Participants participants = new Participants(dealer, players);

                // when
                List<ParticipantDto> actual = participants.generatePlayersDto();

                // then
                assertThat(actual).hasSize(2);
                assertThat(actual.get(0).name()).isEqualTo("jacob");
                assertThat(actual.get(0).hand()).containsExactly("10클로버", "8다이아몬드");
                assertThat(actual.get(0).score()).isEqualTo(18);

                assertThat(actual.get(1).name()).isEqualTo("seoye");
                assertThat(actual.get(1).hand()).containsExactly("10하트", "7클로버");
                assertThat(actual.get(1).score()).isEqualTo(17);
            }
        }
    }

    @Nested
    class GetDealerTest {

        @Nested
        class Success {

            @Test
            void 현재_딜러_객체를_반환해야_한다() {

                // given
                Dealer dealer = new Dealer();
                Participants participants = new Participants(dealer, new Players(List.of("jacob", "seoye")));

                // when
                Dealer actual = participants.getDealer();

                // then
                assertThat(actual).isSameAs(dealer);
            }
        }
    }

    @Nested
    class GenerateDealerDtoTest {

        @Nested
        class Success {

            @Test
            void 딜러의_전체_손패와_점수를_담은_Dto를_반환해야_한다() {

                // given
                Dealer dealer = new Dealer();
                dealer.addCard(new Card(Rank.TEN, Suit.HEART));
                dealer.addCard(new Card(Rank.ACE, Suit.SPADE));
                Participants participants = new Participants(dealer, new Players(List.of("jacob", "seoye")));

                // when
                ParticipantDto actual = participants.generateDealerDto();

                // then
                assertThat(actual.name()).isEqualTo("딜러");
                assertThat(actual.hand()).containsExactly("10하트", "A스페이드");
                assertThat(actual.score()).isEqualTo(21);
            }
        }
    }

    private Participants createParticipants() {
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("jacob", "seoye"));
        return new Participants(dealer, players);
    }
}
