import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class AgeAndSexHandler {
    public record Person(String surname, String name, String patronymic, LocalDate birthDate, Sex sex){
        private Person(String surname, String name, String patronymic, LocalDate birthDate) {
            this(surname, name, patronymic, birthDate, calcSexByPatronymic(patronymic));
        }
        public static Sex calcSexByPatronymic(String patronymic){
            return (patronymic.contains("вич")?(Sex.MALE):(Sex.FEMALE));
        }
        protected enum Sex{
            MALE("М"), FEMALE("Ж");
            private final String alias;
            Sex(String alias) {
                this.alias = alias;
            }
            public String toString(){
                return alias;
            }
        }
        private int calcAge(){
            return (int) ChronoUnit.YEARS.between(this.birthDate, LocalDate.now());
        }
        public static String calcAgeSuffix(int age){
            if(age>=10 && age<=20){
                return "лет";
            }
            return switch (age%10){
                case 1 -> "год";
                case 2, 3, 4 -> "года";
                default -> "лет";
            };
        }
    }
    public static String getAgeAndSexData(Person person){
        int age = person.calcAge();
        return String.format("%s %c.%c. %s %d %s",
                person.surname, person.name().charAt(0), person.patronymic().charAt(0),
                person.sex, age, Person.calcAgeSuffix(age));
    }
    protected static void handle(Scanner in, PrintWriter out){
        Person person = new Person(in.next(), in.next(), in.next(),
                LocalDate.parse(in.next(), DateTimeFormatter.ofPattern("dd.MM.uuuu"))
        );
        out.println(getAgeAndSexData(person));
    }
    public static void main(String[] args) {
        AgeAndSexHandler.handle(new Scanner(System.in), new PrintWriter(System.out, true));
    }
}