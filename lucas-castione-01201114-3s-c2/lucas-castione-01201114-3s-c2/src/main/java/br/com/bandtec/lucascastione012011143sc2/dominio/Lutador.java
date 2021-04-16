package br.com.bandtec.lucascastione012011143sc2.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Lutador {

    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 12)
    @NotBlank
    private String nome;

    @PositiveOrZero
    private Double forcaGolpe;

    private Double vida;
    private Integer concentracoesRealizadas;
    private Boolean vivo;

    //getters
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getForcaGolpe() {
        return forcaGolpe;
    }

    public Double getVida() {
        return vida;
    }

    public Integer getConcentracoesRealizadas() {
        return concentracoesRealizadas;
    }

    public Boolean getVivo() {
        return vivo;
    }

    //setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setForcaGolpe(Double forcaGolpe) {
        this.forcaGolpe = forcaGolpe;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }

    public void setConcentracoesRealizadas(Integer concentracoesRealizadas) {
        this.concentracoesRealizadas = concentracoesRealizadas;
    }

    public void setVivo(Boolean vivo) {
        this.vivo = vivo;
    }
}
