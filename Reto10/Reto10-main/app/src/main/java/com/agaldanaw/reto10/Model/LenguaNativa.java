package com.jojimenezt.reto10.Model;

public class LenguaNativa {
    public String nombre_de_lengua;
    public String descripci_n_de_lengua;
    public String departamento;
    public String familia_ling_stica;
    public int n_mero_de_habitantes;
    public int n_mero_de_hablantes;
    public String vitalidad;

    public LenguaNativa(String nombre_de_lengua,
            String descripci_n_de_lengua,
            String departamento,
            String familia_ling_stica,
            int n_mero_de_habitantes,
            int n_mero_de_hablantes, String vitalidad)
    {
        this.nombre_de_lengua = nombre_de_lengua;
        this.departamento = departamento;
        this.descripci_n_de_lengua = descripci_n_de_lengua;
        this.familia_ling_stica = familia_ling_stica;
        this.n_mero_de_habitantes = n_mero_de_habitantes;
        this.n_mero_de_hablantes = n_mero_de_hablantes;
        this.vitalidad = vitalidad;
    }

}
