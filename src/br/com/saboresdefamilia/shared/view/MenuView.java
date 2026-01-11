package br.com.saboresdefamilia.shared.view;

import br.com.saboresdefamilia.shared.util.ConsoleUtils;

import java.util.Scanner;

public abstract class MenuView {
    private boolean executando = true;
    protected abstract void showTitle();
    protected abstract void showOptions();
    protected abstract void router(int option);

    protected int readOption() {
        System.out.println("Digite a opção desejada:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void showMenu() {
        while (executando) {
            ConsoleUtils.clear();
            showTitle();
            showOptions();
            int option = readOption();
            router(option);
        }
    };

    public void exit() {
        executando = false;
    }
}
