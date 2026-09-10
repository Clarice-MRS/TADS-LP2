package br.edu.ifsp.biblioteca;

import br.edu.ifsp.biblioteca.cli.CatalogoRunner;
import br.edu.ifsp.biblioteca.domain.Livro;

public class BibliotecaApplication {

    public static void main(String[] args) {
        CatalogoRunner appExecutor = new CatalogoRunner();
        appExecutor.run();
    }
}
