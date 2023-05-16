package br.ufc.es2.pagamentos.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufc.es2.pagamentos.dto.PagamentoDto;
import br.ufc.es2.pagamentos.http.PedidoClient;
import br.ufc.es2.pagamentos.model.Pagamento;
import br.ufc.es2.pagamentos.model.Pagamento.Status;
import br.ufc.es2.pagamentos.repository.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    private ModelMapper modelMapper = new ModelMapper();   

    public Page<PagamentoDto> listar(Pageable pageable) {
        return repository
            .findAll(pageable)
            .map(pagamento -> modelMapper.map(pagamento, PagamentoDto.class));
    }

    public PagamentoDto buscar(Long id) {
        Pagamento pagamento = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto criarPagamento(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto atualizarPagamento(Long id, PagamentoDto dto) {
        buscar(id);
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void excluirPagamento(Long id) {
        buscar(id);
        repository.deleteById(id);
    }
}
