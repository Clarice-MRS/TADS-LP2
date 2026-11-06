package br.edu.ifsp.biblioteca.cli;

import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.domain.Usuario;
import br.edu.ifsp.biblioteca.exception.RegraDeNegocioException;
import br.edu.ifsp.biblioteca.service.LivroService;
import br.edu.ifsp.biblioteca.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogoRunner implements CommandLineRunner {

    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public CatalogoRunner(
            LivroService livroService,
            UsuarioService usuarioService
    ){
            this.livroService = livroService;
            this.usuarioService =  usuarioService;
    }

    public void run(String... args) {
        System.out.println();
        System.out.println("=== Biblioteca IFSP - v0 (tudo em memória) ===");

        Livro diasPerfeitos = new Livro(
                "9791160073904",
                "Dias Perfeitos",
                2014
        );

        this.livroService.cadastrar(diasPerfeitos);
        this.livroService.adicionarAutor(diasPerfeitos.getId(), "Raphael Montes");
        this.livroService.adicionarExemplar(diasPerfeitos.getId(), "DP-001");
        this.livroService.adicionarExemplar(diasPerfeitos.getId(), "DP-002");

        Livro fim = new Livro(
                "97911600739042",
                "Fim",
                2013
        );

        this.livroService.cadastrar(fim);
        this.livroService.adicionarAutor(fim.getId(), "Fernanda Torres");
        this.livroService.adicionarExemplar(fim.getId(), "F-001");

        this.usuarioService.cadastrar(new Usuario("Lady Gaga", "ladygaga@gmail.com"));
        this.usuarioService.cadastrar(new Usuario("Pitty", "pitty@gmail.com"));

        System.out.println();
        System.out.println("-- Catálogo --");

        for(Livro livro : this.livroService.listarTodos()) {
            System.out.println(" " + livro);
        }

        System.out.println();
        System.out.println("-- Usuários --");

        for(Usuario usuario : this.usuarioService.listarTodos()) {
            System.out.println(" " + usuario);
        }

        System.out.println();
        System.out.println("-- Buscar por título contendo 'Fim'-- ");

        List<Livro> encontrados = this.livroService.buscarPorTitulo("Fim");

        for(Livro livro : encontrados) {
            System.out.println(" " + livro);
        }

        System.out.println();
        System.out.println("-- Testando cadastrar o mesmo isbn de novo --");

        try {

            this.livroService.cadastrar(
                    new Livro(
                            "97911600739042",
                            "fim",
                            2013
                    )
            );

        } catch (RegraDeNegocioException erro) {
            System.out.println(" Regra de Negócio impediu: " + erro.getMessage());
        }
    }
}