package com.baozi.store.service;

import com.baozi.store.exception.RecursoNaoEncontradoException;
import com.baozi.store.model.Cliente;
import com.baozi.store.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente criar(Cliente cliente) {
        cliente.setId(null); // garante que é um registro novo
        return repository.save(cliente);
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: id " + id));
    }

    public Cliente atualizar(Long id, Cliente dados) {
        Cliente existente = buscarPorId(id);
        existente.setNome(dados.getNome());
        existente.setClienteDesde(dados.getClienteDesde());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Cliente existente = buscarPorId(id);
        repository.delete(existente);
    }
}