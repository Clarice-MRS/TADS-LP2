package br.edu.ifsp.biblioteca.service;

import br.edu.ifsp.biblioteca.domain.Usuario;
import br.edu.ifsp.biblioteca.exception.RegraDeNegocioException;
import br.edu.ifsp.biblioteca.repository.IUsuarioRepository;

import java.util.Optional;

public class UsuarioService {
    private final IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository repository){
        this.usuarioRepository = repository;
    }

    public Usuario cadastrar(Usuario usuario){
        if (usuario.getNome() == null || usuario.getNome().isEmpty()){
            throw new RegraDeNegocioException("Nome é obrigatorio");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()){
            throw new RegraDeNegocioException("E-mail é obrigatorio");
        }

        this.validarEmail(usuario.getEmail());

        return this.usuarioRepository.salvar(usuario);
    }

    public Usuario buscarPorId(Long id){
        Optional<Usuario> usuarioOptional = this.usuarioRepository.buscarPorId(id);

        if (usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }

        throw new RegraDeNegocioException("Usuario não encontrado: " + id);
    }

    public void validarEmail(String email){
        Optional<Usuario> usuarioOptional = this.usuarioRepository.buscarPorEmail(email);

        if (usuarioOptional.isPresent()){
            throw new RegraDeNegocioException("Já existe um usuario cadastrado com o email: " + email);
        }
    }
}
