package com.baozi.store.service;

import com.baozi.store.exception.RecursoNaoEncontradoException;
import com.baozi.store.exception.RegraNegocioException;
import com.baozi.store.model.Pedido;
import com.baozi.store.model.Produto;
import com.baozi.store.repository.ClienteRepository;
import com.baozi.store.repository.PedidoRepository;
import com.baozi.store.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public Pedido criar(Pedido pedido) {
        pedido.setId(null);
        validarReferencias(pedido);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado: id " + id));
    }

    public Pedido atualizar(Long id, Pedido dados) {
        Pedido existente = buscarPorId(id);
        validarReferencias(dados);
        existente.setClienteId(dados.getClienteId());
        existente.setProdutoId(dados.getProdutoId());
        existente.setQuantidade(dados.getQuantidade());
        return pedidoRepository.save(existente);
    }

    public void excluir(Long id) {
        Pedido existente = buscarPorId(id);
        pedidoRepository.delete(existente);
    }

    // O pedido guarda só os IDs, então conferimos aqui se cliente e produto existem.
    private void validarReferencias(Pedido pedido) {
        if (!clienteRepository.existsById(pedido.getClienteId())) {
            throw new RegraNegocioException("Cliente não encontrado: id " + pedido.getClienteId());
        }
        Produto produto = produtoRepository.findById(pedido.getProdutoId())
                .orElseThrow(() -> new RegraNegocioException("Produto não encontrado: id " + pedido.getProdutoId()));
        if (!Boolean.TRUE.equals(produto.getEstoque())) {
            throw new RegraNegocioException("Produto sem estoque: " + produto.getNome());
        }
    }
}