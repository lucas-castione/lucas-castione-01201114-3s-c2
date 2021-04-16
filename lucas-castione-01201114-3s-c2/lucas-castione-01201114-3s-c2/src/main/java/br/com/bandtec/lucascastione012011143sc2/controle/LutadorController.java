package br.com.bandtec.lucascastione012011143sc2.controle;

import br.com.bandtec.lucascastione012011143sc2.dominio.Briga;
import br.com.bandtec.lucascastione012011143sc2.dominio.Lutador;
import br.com.bandtec.lucascastione012011143sc2.repositorio.LutadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepository repository;

    @PostMapping
    public ResponseEntity postLutador(@RequestBody @Valid Lutador lutador ){

        lutador.setVivo(true);
        lutador.setVida(100.0);
        lutador.setConcentracoesRealizadas(0);

        repository.save(lutador);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getLutadores(){
        if (repository.findByLutadoresOrdenados().isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok().body(repository.findByLutadoresOrdenados());
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getContagemVivos(){

        return ResponseEntity.ok().body(repository.countByVivo(true));
    }

    @PostMapping("/{id}/concentrar")
    public ResponseEntity postConcentrar(@PathVariable Integer id){

        if(repository.existsById(id)){
            Lutador lutador = repository.getOne(id);
            if(lutador.getConcentracoesRealizadas() < 3){
                lutador.setVida(lutador.getVida() * 1.15);
                lutador.setConcentracoesRealizadas(lutador.getConcentracoesRealizadas()+1);
                repository.save(lutador);
                return ResponseEntity.ok().build();

            }else{
                return ResponseEntity.status(400).body("Lutador já se concentrou 3 vezes!");
            }
        }else{
            return ResponseEntity.status(404).body(id);
        }

    }



    @GetMapping("/mortos")
    public ResponseEntity getMortos(){
        return ResponseEntity.ok().body(repository.findByVivo(false));
    }

    @PostMapping("/lutador/golpe")
    public ResponseEntity postGolpe(@RequestBody Briga b){
        if(b.getIdLutadorApanha() == 0 || b.getIdLutadorBate() == 0){
            return ResponseEntity.status(400).build();
        }
        if(repository.existsById(b.getIdLutadorApanha()) && repository.existsById(b.getIdLutadorBate())){
            Lutador lutadorBate = repository.getOne(b.getIdLutadorBate());
            Lutador lutadorApanha = repository.getOne(b.getIdLutadorApanha());

            if(!lutadorApanha.getVivo() || !lutadorBate.getVivo()){
                return ResponseEntity.status(400).body("Ambos os lutadores devem estar vivos!");
            }

            lutadorApanha.setVida(lutadorApanha.getVida() - lutadorBate.getForcaGolpe());
            if(lutadorApanha.getVida() <= 0 ){
                lutadorApanha.setVivo(false);
                lutadorApanha.setVida(0.0);

            }
            repository.save(lutadorApanha);
            List<Lutador> lutadores = new ArrayList<>();
            lutadores.add(lutadorBate);
            lutadores.add(lutadorApanha);
            return ResponseEntity.status(201).body(lutadores);
        }
        return ResponseEntity.status(404).build();
    }



}
