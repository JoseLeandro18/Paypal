package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Enter the contract data: ");
        System.out.print("Number: ");
        Integer number = sc.nextInt();
        System.out.print("Date (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(),fmt);
        System.out.print("Contract Value: ");
        Double contractValue = sc.nextDouble();

        Contract obj = new Contract(number, date, contractValue);

        System.out.print("Enter the installments numbers: ");
        Integer everyMonth = sc.nextInt();

        ContractService service = new ContractService(new PaypalService());
        service.processContract(obj, everyMonth);

        System.out.println("Installments: ");
        for (Installment installment : obj.getInstallments()) {
            System.out.println(installment);
        }

        sc.close();
    }
}
