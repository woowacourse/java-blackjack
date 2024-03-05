package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PacketTest {

    @Test
    @DisplayName("카드를 가지고 있는 객체를 생성한다.")
    void createPacket() {
        Assertions.assertThatCode(Packet::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 추가한다.")
    void addCard() {
        //given
        final Packet packet = new Packet();

        //when
        packet.add(new Card(CardNumber.EIGHT, CardShape.CLOVER));

        //then
        Assertions.assertThat(packet.size()).isEqualTo(1);
    }
}
