import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgeAndSexHandlerTest {
    @Test
    void simpleTest(){
        String inData = "Молчанов Максим Дмитриевич 25.04.2003";
        StringWriter outData = new StringWriter();
        AgeAndSexHandler.handle(new Scanner(inData), new PrintWriter(outData));
        assertEquals(outData.toString(), "Молчанов М.Д. М 19 лет\n");
    }

    @Test
    void zeroAgeTest(){
        String inData = "Волокова Ирина Валерьевна 02.04.2023";
        StringWriter outData = new StringWriter();
        AgeAndSexHandler.handle(new Scanner(inData), new PrintWriter(outData));
        assertEquals(outData.toString(), "Волокова И.В. Ж 0 лет\n");
    }

    @Test
    void godaAndLetTest(){
        Map<Integer, String> yearsTests = new HashMap<>(
                Map.of(
                        1, "год",
                        2, "года",
                        5, "лет",
                        21, "год",
                        22, "года",
                        25, "лет"
                )
        );
        yearsTests.keySet()
                .forEach(age -> Assertions.assertEquals(yearsTests.get(age),
                        AgeAndSexHandler.Person.calcAgeSuffix(age),
                        "Comparison with age = "+age));
    }
}