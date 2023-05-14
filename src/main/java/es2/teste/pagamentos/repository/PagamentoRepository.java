package es2.teste.pagamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es2.teste.pagamentos.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
