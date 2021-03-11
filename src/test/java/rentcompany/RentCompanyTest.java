package rentcompany;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RentCompanyTest {
    private static final String NEWLINE = System.getProperty("line.separator");

    @Test
    public void report() throws Exception {
        RentCompany company = RentCompany.create();
        company.addCar(new Sonata(150));
        company.addCar(new K5(260));
        company.addCar(new Sonata(120));
        company.addCar(new Avante(300));
        company.addCar(new K5(390));

        String report = company.generateReport();
        assertThat(report).isEqualTo(
                "Sonata : 15.0리터" + NEWLINE +
                        "K5 : 20.0리터" + NEWLINE +
                        "Sonata : 12.0리터" + NEWLINE +
                        "Avante : 20.0리터" + NEWLINE +
                        "K5 : 30.0리터" + NEWLINE
        );
    }

    @Test
    public void report1() throws Exception {
        RentCompany company = RentCompany.create();
        company.addCar(new Sonata(15));
        company.addCar(new K5(26));
        company.addCar(new Sonata(12));
        company.addCar(new Avante(30));
        company.addCar(new K5(39));

        String report = company.generateReport();
        assertThat(report).isEqualTo(
                "Sonata : 1.5리터" + NEWLINE +
                        "K5 : 2.0리터" + NEWLINE +
                        "Sonata : 1.2리터" + NEWLINE +
                        "Avante : 2.0리터" + NEWLINE +
                        "K5 : 3.0리터" + NEWLINE
        );
    }
}

