package es2.pagamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es2.pagamentos.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
