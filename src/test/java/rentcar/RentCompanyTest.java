package rentcar;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RentCompanyTest {
    private static final String NEWLINE = System.getProperty("line.separator");
    private RentCompany company;


//    @Test
//    public void report() throws Exception {
//        RentCompany company = RentCompany.create(); // factory method를 사용해 생성
//        company.addCar(new Sonata(150));
//        company.addCar(new K5(260));
//        company.addCar(new Sonata(120));
//        company.addCar(new Avante(300));
//        company.addCar(new K5(390));
//
//        String report = company.generateReport();
//        assertThat(report).isEqualTo(
//                "Sonata : 15리터" + NEWLINE +
//                        "K5 : 20리터" + NEWLINE +
//                        "Sonata : 12리터" + NEWLINE +
//                        "Avante : 20리터" + NEWLINE +
//                        "K5 : 30리터" + NEWLINE
//        );
//    }
}