import domain.game.ResultInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ResultInfoTest {

    @Test
    void 승무패블랙잭승에_따라_알맞게_enum을_반환한다() {
        String win="승";
        String defeat="패";
        String draw="무";
        String bjwin="블랙잭승";

        assertThat(win).isEqualTo(ResultInfo.from(win).getInfo());
        assertThat(defeat).isEqualTo(ResultInfo.from(defeat).getInfo());
        assertThat(draw).isEqualTo(ResultInfo.from(draw).getInfo());
        assertThat(bjwin).isEqualTo(ResultInfo.from(bjwin).getInfo());
    }
}
