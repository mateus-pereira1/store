package com.baozi.store.service;

import com.baozi.store.exception.RecursoNaoEncontradoException;
import com.baozi.store.model.Produto;
import com.baozi.store.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criar(Produto produto) {
        produto.setId(null);
        return repository.save(produto);
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: id " + id));
    }

    public Produto atualizar(Long id, Produto dados) {
        Produto existente = buscarPorId(id);
        existente.setNome(dados.getNome());
        existente.setPreco(dados.getPreco());
        existente.setEstoque(dados.getEstoque());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Produto existente = buscarPorId(id);
        repository.delete(existente);
    }
}