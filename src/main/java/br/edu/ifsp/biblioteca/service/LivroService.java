package br.edu.ifsp.biblioteca.service;

import br.edu.ifsp.biblioteca.domain.Autor;
import br.edu.ifsp.biblioteca.domain.Exemplar;
import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.exception.RegraDeNegocioException;
import br.edu.ifsp.biblioteca.repository.ILivroRepository;

import java.util.List;
import java.util.Optional;

public class LivroService {

    private final ILivroRepository livroRepository;

    public LivroService(ILivroRepository repository){
        this.livroRepository = repository;
    }

    public Livro cadastrar(Livro livro){
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty()){
            throw new RegraDeNegocioException("O titulo é obrigatorio!");
        }

        if (livro.getIsbn() == null || livro.getIsbn().isEmpty()){
            throw new RegraDeNegocioException("O ISBN é obrigatorio!");
        }

        if (livro.getAnoPublicacao() <= 0){
            throw new RegraDeNegocioException("O ano de publicação é obrigatorio");
        }

        Optional<Livro>  livroJaCadastradoOptional = this.livroRepository.buscarPorIsbn(livro.getIsbn());

        if (livroJaCadastradoOptional.isPresent()){
            throw new RegraDeNegocioException("Já existe um Livro cadastrado com po ISBN: " + livro.getIsbn());
        }

        Livro livroCadastrado = this.livroRepository.salvar(livro);

        return livroCadastrado;
    }

    public Livro adicionarExemplar(Long livroId, String codigoDoExemplar){
        Livro livro = this.buscaPorId(livroId);
        livro.adicionarExemplar(new Exemplar(codigoDoExemplar, livro));

        return this.livroRepository.salvar(livro);
    }

    public Livro adicionarAutor(Long livroId, String nomeAutor){
        Livro livroEncontrado = this.buscaPorId(livroId);
        livroEncontrado.adicionarAutor(new Autor(nomeAutor));

        return this.livroRepository.salvar(livroEncontrado);
    }

    public Livro buscaPorId(Long id){
        Optional<Livro> livroOptional = this.livroRepository.buscarPorId(id);

        if (livroOptional.isPresent()){
            return livroOptional.get();
        }

        throw new RegraDeNegocioException("Livro não encontrado: " + id);
    }

    public List<Livro> buscarPorTitulo(String trechoDoTitulo){
        return this.livroRepository.buscarPorTitulo(trechoDoTitulo);
    }

    public List<Livro> listarTodos(){
        return this.livroRepository.listarTodos();
    }
}
