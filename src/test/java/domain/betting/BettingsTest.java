package domain.betting;

import static util.TestUtil.createBetting;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BettingsTest {

    //    public Bettings() {
//        this(List.of());
//    }
//
//    public Bettings(List<Betting> bettings) {
//        this.bettings = List.copyOf(bettings);
//    }
//
//    public Bettings addBetting(Betting betting) {
//        List<Betting> bettings = new ArrayList<>(this.bettings);
//        bettings.add(betting);
//        return new Bettings(bettings);
//    }
    @Test
    void 매개변수_없는_생성자로_생성이_가능하다() {
        // given
        Bettings bettings = new Bettings();

        // when, then
        Assertions.assertNotNull(bettings);
    }

    @Test
    void 베팅_리스트로_생성이_가능하다() {
        // given
        Bettings bettings = new Bettings(List.of(createBetting("봉구스", 10000), createBetting("시오", 20000)));

        // when, then
        Assertions.assertEquals(2, bettings.bettings().size());
    }

    @Test
    void 베팅을_추가할_수_있다() {
        // given
        Bettings bettings = new Bettings();

        // when
        Bettings newBettings = bettings.addBetting(createBetting("봉구스", 10000));

        // then
        Assertions.assertEquals(1, newBettings.bettings().size());
    }
}