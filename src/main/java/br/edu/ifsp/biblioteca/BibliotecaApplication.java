package br.edu.ifsp.biblioteca;

import br.edu.ifsp.biblioteca.domain.Livro;

public class BibliotecaApplication {

    public static void main(String[] args) {

        Livro livro1 = new Livro(
            1L,
            "9791160073904",
            "Dias Perfeitos",
            2014
        );

        Livro livro2 = new Livro(
            2L,
            "9781632061218",
            "Fim",
            2013
        );

        Livro livro3 = new Livro(
                3L,
                "9788535919752",
                "As Esganadas",
                2011
        );

        System.out.println(livro1);
        System.out.println(livro2);
        System.out.println(livro3);
    }
}
