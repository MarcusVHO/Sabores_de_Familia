package br.com.saboresdefamilia.shared.view;
import java.util.ArrayList;
import java.util.Scanner;

public class BaseView {
    private final Scanner scanner;

    public BaseView () {
        scanner = new Scanner(System.in);
    }

    public String readString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public Integer readInt(String message) {
        System.out.println(message);
        String number = scanner.nextLine();
        if (number.isBlank()) {
            return null;
        }

        return Integer.parseInt(number);
    }

    public void listObjects(ArrayList<?> objects, String title) {
        System.out.println("======================== " + title + " ========================");
        if (!objects.isEmpty()) {
            for (Object object : objects) {
                System.out.println(objects.indexOf(object) + " - " + object);
            }
        }
        System.out.println("==================================================" + "=".repeat(title.length()));
    }

}
