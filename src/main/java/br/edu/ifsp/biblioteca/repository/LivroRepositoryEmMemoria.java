package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;

import java.util.*;

public class LivroRepositoryEmMemoria  implements ILivroRepository {

    private final Map<Long, Livro> livros = new HashMap<Long, Livro>();
    private Long sequenciaId = 0L;

    @Override
    public Livro salvar(Livro livro) {

        if(livro.getId() == null) {
            this.sequenciaId++; //= this.sequenciaId + 1;
            livro.setId(this.sequenciaId);
        }

        this.livros.put(livro.getId(), livro);



        return livro;
    }

    @Override
    public List<Livro> listartodos() {

//        List<Livro> colecaolivros = new ArrayList<>(this.livros.values());    OUTRO METODO MAIS FACIL
//        return colecaolivros;

        List<Livro> todososlivros = new ArrayList();

        for (Livro livro : this.livros.values()) {
            todososlivros.add(livro);
        }
        return todososlivros;
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {

        return Optional.ofNullable(this.livros.get(id));

//        Livro l = this.livros.get(id);
//
//        if (l == null) {
//            return Optional.empty();
//        }
//
//        return Optional.of(l);

    }

    @Override
    public Optional<Livro> buscarPorIsbn(String isbn) {

        List<Livro> colecaolivros = new ArrayList<>(this.livros.values());

        for (Livro item : colecaolivros) {

            if (item.getIsbn().equals(isbn)) { //equals compara o isbn do item com o isbn que foi passado no parametro
                return Optional.of(item);      //retorna o item que foi encontrado
            }
        }
        return Optional.empty();
    }

//    for (int i = 0; i < colecaolivros.size(); i++) {
//
//        Livro item = colecaolivros.get(i);
//
 //          if(item.getIsbn().equals(isbn)) {
//            return Optional.of(item);
//            }
//        }
//        return Optional.empty();
//    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {

        List<Livro> colecaolivros = new ArrayList<>(this.livros.values());

        List<Livro> resultadosencontrados = new ArrayList<>(); //os exemplares que serão encontrados serão adicionados nessa lista que está vazia.

        for (int i = 0; i < colecaolivros.size() ; i++){

            Livro exemplar = colecaolivros.get(i);

            if(exemplar.getTitulo().contains(titulo)) {        // o contains é o responsável por retornar todos os exemplares que contém o titulo no nome,
                resultadosencontrados.add(exemplar);           // e não somente os que são EXATAMENTE iguais ao titulo (equalsignorecase)
            }                                                  // equalsignorecase ignora as maiúsculas e minúsculas
        }
        return resultadosencontrados;                          //retorna todos os livros (exemplares) que foram adicionados na lista
    }
}

// for each
//        for (Livro exemplar : colecaolivros) {
//
 //            if (exemplar.getTitulo().contains(titulo)) {
//                resultadosencontrados.add(exemplar);
//            }
//        }
//        return resultadosencontrados;
//    }
//}
