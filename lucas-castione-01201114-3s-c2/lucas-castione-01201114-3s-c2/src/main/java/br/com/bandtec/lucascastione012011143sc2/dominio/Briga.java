package br.com.bandtec.lucascastione012011143sc2.dominio;

public class Briga {
    private Integer idLutadorBate;
    private Integer idLutadorApanha;

    public Briga(Integer idLutadorBate, Integer idLutadorApanha) {
        this.idLutadorBate = idLutadorBate;
        this.idLutadorApanha = idLutadorApanha;
    }

    public Integer getIdLutadorBate() {
        return idLutadorBate;
    }

    public void setIdLutadorBate(Integer idLutadorBate) {
        this.idLutadorBate = idLutadorBate;
    }

    public Integer getIdLutadorApanha() {
        return idLutadorApanha;
    }

    public void setIdLutadorApanha(Integer idLutadorApanha) {
        this.idLutadorApanha = idLutadorApanha;
    }
}
