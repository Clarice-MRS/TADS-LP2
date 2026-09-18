package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class LivroRepositoryJdbc implements ILivroRepository {

    private final DataSource dataSource;

    public LivroRepositoryJdbc(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Livro salvar(Livro livro) {

        try(Connection conexaoDb = this.dataSource.getConnection())
        {

            conexaoDb.setAutoCommit(false);

            if(livro.getId() == null){ //precisa cadastrar
                this.inserirLivro(conexaoDb, livro);
            } else { //precisa atualizar
                this.atualizarLivro(conexaoDb, livro);
            }

            this.salvarAutores(conexaoDb, livro);
            this.salvarExemplares(conexaoDb, livro);

        } catch (SQLException erro) {

        }

        return livro;
    }

    private void inserirLivro(Connection coenxao, Livro livro) {

        String sql ="INSERT INTO livro(isbn, titulo, ano publicacao) VALUES (?,?, ?)";

    }

    private void atualizarLivro(Connection coenxao, Livro livro) {

    }

    private void salvarAutores(Connection coenxao, Livro livro) {

    }

    private void salvarExemplares(Connection coenxao, Livro livro) {

    }

    @Override
    public List<Livro> listarTodos() {
        return List.of();
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Livro> buscarPorIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {
        return List.of();
    }
}
