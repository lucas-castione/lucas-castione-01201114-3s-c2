package br.com.bandtec.lucascastione012011143sc2.repositorio;

import br.com.bandtec.lucascastione012011143sc2.dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {
    @Query("select l from Lutador l order by forca_golpe desc")
    List<Lutador> findByLutadoresOrdenados();

    Integer countByVivo(Boolean vivo);

    List<Lutador> findByVivo(Boolean vivo);


}
